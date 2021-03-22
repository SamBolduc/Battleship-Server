package ca.school.battleship.packet;

import ca.school.battleship.packet.types.JoinPacket;
import com.google.common.collect.Sets;

import java.util.Set;

public class PacketHandler {

    private Set<GenericPacket> packets = Sets.newHashSet();

    public PacketHandler() {
        this.registerPacket(1, JoinPacket.class);
    }

    public Class<? extends GenericPacket> getClass(int packetId) {
        GenericPacket packet = this.packets.stream().filter(el -> el.getId() == packetId).findFirst().orElse(null);

        return packet == null ? null : packet.getClass();
    }

    private void registerPacket(int id, Class<? extends GenericPacket> clazz) {
        if (this.packets.stream().anyMatch(el -> el.getId() == id))
            throw new IllegalStateException("Duplicate packet id.");

        try {
            GenericPacket packet = clazz.getDeclaredConstructor(Integer.class).newInstance(id);
            this.packets.add(packet);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
