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
public class Keyword {
    protected int idWord;
    protected int queueKeyword;
    protected String Keyword;
    protected int idScore;
}
