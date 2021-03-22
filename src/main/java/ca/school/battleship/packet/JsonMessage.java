package ca.school.battleship.packet;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@Data
@ToString
public class JsonMessage {
    private int packetId;
    private String content;
}
