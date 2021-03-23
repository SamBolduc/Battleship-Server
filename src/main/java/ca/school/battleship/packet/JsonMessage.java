package ca.school.battleship.packet;

import ca.school.battleship.Server;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class JsonMessage {
    private int packetId;
    private String content;

    public String toJson() {
        return Server.get().getGson().toJson(this);
    }
}
