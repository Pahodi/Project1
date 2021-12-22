package DB;

import Models.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DAOUser implements UserDao {
    private DAOUser() {
    }

    private static class SingletonHelper {
        private static final DAOUser INSTANCE = new DAOUser();
    }

    public static DAOUser getInstance() {
        return SingletonHelper.INSTANCE;
    }

    @Override
    public Optional<User> find(String id) throws SQLException {

        String sql = "SELECT id_User,number,password FROM User WHERE id_User = ?";
        int id_User = 0, number = 0;
        String password = "";
        Connection conn = DAO.getConnection();

        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, id);
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            id_User = resultSet.getInt("id_User");
            number = resultSet.getInt("number");
            password = resultSet.getString("password");
        }
        return Optional.of(new User(id_User, number, password));
    }

    @Override
    public List<User> findAll() throws SQLException {
        List<User> listUser = new ArrayList<>();
        String sql = "SELECT id_User,number,password FROM User";

        Connection conn = DAO.getConnection();
        Statement statement = conn.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            int id_User = resultSet.getInt("id_User");
            int number = resultSet.getInt("number");
            String password = resultSet.getString("password");

            User user = new User(id_User, number, password);
            listUser.add(user);
        }

        return listUser;
    }

    @Override
    public boolean save(User user) throws SQLException {
        String sql = "INSERT into User (id_User, number, password) VALUES (?, ?, ?)";
        boolean rowInserted = false;
        Connection conn = DAO.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, user.getIdUser());
            statement.setInt(2, user.getNumber());
            statement.setString(3, user.getPassword());
            rowInserted = statement.executeUpdate() > 0;
        return rowInserted;
    }

    @Override
    public boolean update(User user) throws SQLException {
        String sql = "UPDATE User SET id_User = ?, number = ?, password = ?";
        sql += " WHERE id_User = ?";
        boolean rowUpdateted = false;
        Connection conn = DAO.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, user.getIdUser());
            statement.setInt(2, user.getNumber());
            statement.setString(3, user.getPassword());
            rowUpdateted = statement.executeUpdate() > 0;

        return rowUpdateted;
    }

    @Override
    public boolean delete(User user) throws SQLException {

        String sql = "DELETE FROM User where id_User = ?";
        boolean rowDeleted = false;

        Connection conn = DAO.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, user.getIdUser());
            rowDeleted = statement.executeUpdate() > 0;

        return rowDeleted;
    }
}