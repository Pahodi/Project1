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
public class Quest {
    protected int idQuest;
    protected String nameQuest;
    protected String descriptionQuest;
    protected int idTeam;
    protected int idSingleUser;
}
