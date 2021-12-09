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
public class SingleUser {
    protected int idSingleUser;
    protected int idUser;
    protected int score;
    protected String minTimeSingleUser;
}
