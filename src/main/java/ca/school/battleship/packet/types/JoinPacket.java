package ca.school.battleship.packet.types;

import ca.school.battleship.packet.GenericPacket;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@NoArgsConstructor
public class JoinPacket extends GenericPacket<JoinPacket> {

    @Setter
    private int test;

    @Override
    public void read(JoinPacket packet) {

    }
}
