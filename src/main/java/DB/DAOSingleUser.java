package DB;

import Models.SingleUser;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DAOSingleUser implements SingleUserDao {
    private DAOSingleUser() {
    }

    private static class SingletonHelper {
        private static final DAOSingleUser INSTANCE = new DAOSingleUser();
    }

    public static DAOSingleUser getInstance() {
        return SingletonHelper.INSTANCE;
    }

    @Override
    public Optional<SingleUser> find(String id) throws SQLException {

        String sql = "SELECT id_SingleUser,id_User,score,minTime FROM SingleUser WHERE id_SingleUser = ?";
        int id_SingleUser = 0, id_User = 0, score = 0;
        String minTime = "";
        Connection conn = DAO.getConnection();

        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, id);
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            id_SingleUser = resultSet.getInt("id_SingleUser");
            id_User = resultSet.getInt("id_User");
            score = resultSet.getInt("score");
            minTime = resultSet.getString("minTime");
        }
        return Optional.of(new SingleUser(id_SingleUser, id_User, score, minTime));
    }

    @Override
    public List<SingleUser> findAll() throws SQLException {
        List<SingleUser> listSingleUser = new ArrayList<>();
        String sql = "SELECT id_SingleUser,id_User,score,minTime FROM SingleUser";

        Connection conn = DAO.getConnection();
        Statement statement = conn.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            int id_SingleUser = resultSet.getInt("id_SingleUser");
            int id_User = resultSet.getInt("id_User");
            int score = resultSet.getInt("score");
            String minTime = resultSet.getString("minTime");

            SingleUser singleUser = new SingleUser(id_SingleUser, id_User, score, minTime);
            listSingleUser.add(singleUser);
        }

        return listSingleUser;
    }

    @Override
    public boolean save(SingleUser singleUser) throws SQLException {
        String sql = "INSERT into SingleUser (id_SingleUser, id_User, score, minTime) VALUES (?, ?, ?, ?)";
        boolean rowInserted = false;
        Connection conn = DAO.getConnection();
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1, singleUser.getIdSingleUser());
        statement.setInt(2, singleUser.getIdUser());
        statement.setInt(3, singleUser.getScore());
        statement.setString(4, singleUser.getMinTimeSingleUser());
        rowInserted = statement.executeUpdate() > 0;
        return rowInserted;
    }

    @Override
    public boolean update(SingleUser singleUser) throws SQLException {
        String sql = "UPDATE SingleUser SET id_SingleUser = ?, id_User = ?, score = ?, minTime = ?";
        sql += " WHERE id_SingleUser = ?";
        boolean rowUpdateted = false;
        Connection conn = DAO.getConnection();
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1, singleUser.getIdSingleUser());
        statement.setInt(2, singleUser.getIdUser());
        statement.setInt(3, singleUser.getScore());
        statement.setString(4, singleUser.getMinTimeSingleUser());
        rowUpdateted = statement.executeUpdate() > 0;

        return rowUpdateted;
    }

    @Override
    public boolean delete(SingleUser singleUser) throws SQLException {

        String sql = "DELETE FROM SingleUser where id_SingleUser = ?";
        boolean rowDeleted = false;

        Connection conn = DAO.getConnection();
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1, singleUser.getIdSingleUser());
        rowDeleted = statement.executeUpdate() > 0;

        return rowDeleted;
    }
}
