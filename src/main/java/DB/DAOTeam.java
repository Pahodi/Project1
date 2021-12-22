package DB;

import Models.Team;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DAOTeam implements TeamDao {
    private DAOTeam() {
    }

    private static class SingletonHelper {
        private static final DAOTeam INSTANCE = new DAOTeam();
    }

    public static DAOTeam getInstance() {
        return SingletonHelper.INSTANCE;
    }

    @Override
    public Optional<Team> find(String id) throws SQLException {

        String sql = "SELECT id_Team,id_User,score,minTime FROM Team WHERE id_Team = ?";
        int id_Team = 0, id_User = 0, score = 0;
        String minTime = "";
        Connection conn = DAO.getConnection();

        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, id);
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            id_Team = resultSet.getInt("id_Team");
            id_User = resultSet.getInt("id_User");
            score = resultSet.getInt("score");
            minTime = resultSet.getString("minTime");
        }
        return Optional.of(new Team(id_Team, id_User, score, minTime));
    }

    @Override
    public List<Team> findAll() throws SQLException {
        List<Team> listTeam = new ArrayList<>();
        String sql = "SELECT id_Team,id_User,score,minTime FROM Team";

        Connection conn = DAO.getConnection();
        Statement statement = conn.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            int id_Team = resultSet.getInt("id_Team");
            int id_User = resultSet.getInt("id_User");
            int score = resultSet.getInt("score");
            String minTime = resultSet.getString("minTime");

            Team team = new Team(id_Team, id_User, score, minTime);
            listTeam.add(team);
        }

        return listTeam;
    }

    @Override
    public boolean save(Team team) throws SQLException {
        String sql = "INSERT into Team (id_Team, id_User, score, minTime) VALUES (?, ?, ?, ?)";
        boolean rowInserted = false;
        Connection conn = DAO.getConnection();
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1,team.getIdTeam());
        statement.setInt(2, team.getIdUser());
        statement.setInt(3, team.getScore());
        statement.setString(4, team.getMinTimeTeam());
        rowInserted = statement.executeUpdate() > 0;
        return rowInserted;
    }

    @Override
    public boolean update(Team team) throws SQLException {
        String sql = "UPDATE Team SET id_Team = ?, id_User = ?, score = ?, minTime = ?";
        sql += " WHERE id_Team = ?";
        boolean rowUpdateted = false;
        Connection conn = DAO.getConnection();
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1, team.getIdTeam());
        statement.setInt(2, team.getIdUser());
        statement.setInt(3, team.getScore());
        statement.setString(4, team.getMinTimeTeam());
        rowUpdateted = statement.executeUpdate() > 0;

        return rowUpdateted;
    }

    @Override
    public boolean delete(Team team) throws SQLException {

        String sql = "DELETE FROM Team where id_Team = ?";
        boolean rowDeleted = false;

        Connection conn = DAO.getConnection();
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1, team.getIdTeam());
        rowDeleted = statement.executeUpdate() > 0;

        return rowDeleted;
    }
}
