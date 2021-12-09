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
public class Score {
    protected int idScore;
    protected int noError;
    protected int oneError;
    protected int twoError;
    protected int threeError;
    protected int threeAndMoreError;
}
