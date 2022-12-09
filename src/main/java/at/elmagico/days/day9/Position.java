package at.elmagico.days.day9;

import lombok.Data;
import lombok.NonNull;

@Data
public class Position {
    @NonNull
    private Integer x;
    @NonNull
    private Integer y;
}
