package ca.school.battleship.packet.handler;

import ca.school.battleship.game.packet.*;
import ca.school.battleship.packet.GenericPacket;
import com.google.common.collect.Lists;

import java.util.List;

public class PacketHandler {

    private List<GenericPacket> packets = Lists.newArrayList();

    public PacketHandler() {
        this.registerPacket(1, PlayPacket.class);
        this.registerPacket(2, OpponentFoundPacket.class);
        this.registerPacket(3, BoatPositionPacket.class);
        this.registerPacket(4, GameStartPacket.class);
        this.registerPacket(5, PlayerWinPacket.class);
        this.registerPacket(6, PlayerLeftPacket.class);
        this.registerPacket(7, AttackPacket.class);
    }

    public GenericPacket getPacket(Class<? extends GenericPacket> clazz) {
        return this.packets.stream().filter(el -> el.getClass() == clazz).findFirst().orElse(null);
    }

    public Class<? extends GenericPacket> getClass(int packetId) {
        GenericPacket packet = this.packets.stream().filter(el -> el.getId() == packetId).findFirst().orElse(null);

        return packet == null ? null : packet.getClass();
    }

    private void registerPacket(int id, Class<? extends GenericPacket> clazz) {
        if (this.packets.stream().anyMatch(el -> el.getId() == id))
            throw new IllegalStateException("Duplicate packet id.");

        try {
            GenericPacket packet = clazz.newInstance();
            packet.setId(id);
            this.packets.add(packet);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
