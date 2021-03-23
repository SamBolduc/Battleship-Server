package ca.school.battleship.packet;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class JsonMessage {
    private int packetId;
    private String content;
}
