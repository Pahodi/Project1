package Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.Objects;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
public class Coordinate {
    protected int idCoordinate;
    protected int idQuest;
    protected int priorityCoordinate;
    protected int coordinate;
    protected int idWord;
}
