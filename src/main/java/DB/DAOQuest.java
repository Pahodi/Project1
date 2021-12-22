package DB;

import Models.Quest;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DAOQuest implements QuestDao {
    private DAOQuest() {
    }

    private static class SingletonHelper {
        private static final DAOQuest INSTANCE = new DAOQuest();
    }

    public static DAOQuest getInstance() {
        return SingletonHelper.INSTANCE;
    }

    @Override
    public Optional<Quest> find(String id) throws SQLException {

        String sql = "SELECT id_Quest,nameQuest,descriptionQuest,id_Team,id_SingleUser FROM Quest WHERE id_Quest = ?";
        int id_Quest = 0, id_Team = 0, id_SingleUser = 0;
        String nameQuest = "", descriptionQuest = "";
        Connection conn = DAO.getConnection();

        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, id);
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            id_Quest = resultSet.getInt("id_Quest");
            nameQuest = resultSet.getString("nameQuest");
            descriptionQuest = resultSet.getString("descriptionQuest");
            id_Team = resultSet.getInt("id_Team");
            id_SingleUser = resultSet.getInt("id_SingleUser");
        }
        return Optional.of(new Quest(id_Quest, nameQuest, descriptionQuest, id_Team, id_SingleUser));
    }

    @Override
    public List<Quest> findAll() throws SQLException {
        List<Quest> listQuest = new ArrayList<>();
        String sql = "SELECT id_Quest,nameQuest,descriptionQuest,id_Team,id_SingleUser FROM Quest";

        Connection conn = DAO.getConnection();
        Statement statement = conn.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            int id_Quest = resultSet.getInt("id_Quest");
            String nameQuest = resultSet.getString("nameQuest");
            String descriptionQuest = resultSet.getString("descriptionQuest");
            int id_Team = resultSet.getInt("id_Team");
            int id_SingleUser = resultSet.getInt("id_SingleUser");

            Quest quest = new Quest(id_Quest, nameQuest, descriptionQuest, id_Team, id_SingleUser);
            listQuest.add(quest);
        }

        return listQuest;
    }

    @Override
    public boolean save(Quest quest) throws SQLException {
        String sql = "INSERT into Quest (id_Quest, nameQuest, descriptionQuest, id_Team, id_SingleUser) VALUES (?, ?, ?, ?, ?)";
        boolean rowInserted = false;
        Connection conn = DAO.getConnection();
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1,quest.getIdQuest());
        statement.setString(2, quest.getNameQuest());
        statement.setString(3, quest.getDescriptionQuest());
        statement.setInt(4,quest.getIdTeam());
        statement.setInt(5, quest.getIdSingleUser());
        rowInserted = statement.executeUpdate() > 0;
        return rowInserted;
    }

    @Override
    public boolean update(Quest quest) throws SQLException {
        String sql = "UPDATE Quest SET id_Quest = ?, nameQuest = ?, descriptionQuest = ?, id_Team = ?, id_SingleUser = ?";
        sql += " WHERE id_Quest = ?";
        boolean rowUpdateted = false;
        Connection conn = DAO.getConnection();
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1,quest.getIdQuest());
        statement.setString(2, quest.getNameQuest());
        statement.setString(3, quest.getDescriptionQuest());
        statement.setInt(4,quest.getIdTeam());
        statement.setInt(5, quest.getIdSingleUser());
        rowUpdateted = statement.executeUpdate() > 0;

        return rowUpdateted;
    }

    @Override
    public boolean delete(Quest quest) throws SQLException {

        String sql = "DELETE FROM Quest where id_Quest = ?";
        boolean rowDeleted = false;

        Connection conn = DAO.getConnection();
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1, quest.getIdQuest());
        rowDeleted = statement.executeUpdate() > 0;

        return rowDeleted;
    }
}
