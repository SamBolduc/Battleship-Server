package ca.school.battleship;

import ca.school.battleship.handler.ServerHandler;
import ca.school.battleship.packet.PacketHandler;
import ca.school.battleship.packet.decoder.PacketDecoder;
import ca.school.battleship.packet.encoder.PacketEncoder;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.Getter;

import java.lang.reflect.Modifier;

public class Server {
    private static Server instance;

    public static Server get() {return instance;}

    private int port;

    @Getter
    private Gson gson;

    @Getter
    private PacketHandler packetHandler;

    public Server(int port) {
        this.port = port;
        this.gson = new GsonBuilder().disableHtmlEscaping().setPrettyPrinting().excludeFieldsWithModifiers(Modifier.TRANSIENT, Modifier.VOLATILE, Modifier.STATIC).enableComplexMapKeySerialization().create();
        this.packetHandler = new PacketHandler();
    }

    public static void main(String[] args) throws Exception {
        int port = args.length > 0 ? Integer.parseInt(args[0]) : 8989;

        instance = new Server(port);
        instance.run();
    }

    public void run() throws Exception {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class).childHandler(new ChannelInitializer<SocketChannel>() {
                @Override
                public void initChannel(SocketChannel ch) {
                    ch.pipeline().addLast(new PacketDecoder(), new PacketEncoder(), new ServerHandler());
                }
            }).option(ChannelOption.SO_BACKLOG, 128).childOption(ChannelOption.TCP_NODELAY, true).childOption(ChannelOption.SO_KEEPALIVE, true);

            ChannelFuture f = b.bind(port).sync();
            f.channel().closeFuture().sync();
        } finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }
}

