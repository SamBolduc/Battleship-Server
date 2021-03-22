package ca.school.battleship.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
public class RequestData {
    private int intValue;
    private String stringValue;
}
