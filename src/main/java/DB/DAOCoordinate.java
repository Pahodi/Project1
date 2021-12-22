package DB;

import Models.Coordinate;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DAOCoordinate implements CoordinateDao {
    private DAOCoordinate() {
    }

    private static class SingletonHelper {
        private static final DAOCoordinate INSTANCE = new DAOCoordinate();
    }

    public static DAOCoordinate getInstance() {
        return SingletonHelper.INSTANCE;
    }

    @Override
    public Optional<Coordinate> find(String id) throws SQLException {

        String sql = "SELECT id_Coordinate,id_Quest,queue,coordinate,id_Word FROM Coordinate WHERE id_Coordinate = ?";
        int id_Coordinate = 0, id_Quest = 0, queue = 0, id_Word = 0;
        String coordinate = "";
        Connection conn = DAO.getConnection();

        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, id);
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            id_Coordinate = resultSet.getInt("id_Coordinate");
            id_Quest = resultSet.getInt("id_Quest");
            queue = resultSet.getInt("queue");
            coordinate = resultSet.getString("coordinate");
            id_Word = resultSet.getInt("id_Word");
        }
        return Optional.of(new Coordinate(id_Coordinate, id_Quest, queue, coordinate, id_Word));
    }

    @Override
    public List<Coordinate> findAll() throws SQLException {
        List<Coordinate> listCoordinate = new ArrayList<>();
        String sql = "SELECT id_Coordinate,id_Quest,queue,coordinate,id_Word FROM Coordinate";

        Connection conn = DAO.getConnection();
        Statement statement = conn.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            int id_Coordinate = resultSet.getInt("id_Coordinate");
            int id_Quest = resultSet.getInt("id_Quest");
            int queue = resultSet.getInt("queue");
            String coordinate = resultSet.getString("coordinate");
            int id_Word = resultSet.getInt("id_Word");

            Coordinate coorDinate = new Coordinate(id_Coordinate, id_Quest, queue, coordinate, id_Word);
            listCoordinate.add(coorDinate);
        }

        return listCoordinate;
    }

    @Override
    public boolean save(Coordinate coorDinate) throws SQLException {
        String sql = "INSERT into Coordinate (id_Coordinate, id_Quest, queue, coordinate, id_Word) VALUES (?, ?, ?, ?, ?)";
        boolean rowInserted = false;
        Connection conn = DAO.getConnection();
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1,coorDinate.getIdCoordinate());
        statement.setInt(2, coorDinate.getIdQuest());
        statement.setInt(3, coorDinate.getQueueCoordinate());
        statement.setString(4, coorDinate.getCoordinate());
        statement.setInt(5, coorDinate.getIdWord());
        rowInserted = statement.executeUpdate() > 0;
        return rowInserted;
    }

    @Override
    public boolean update(Coordinate coorDinate) throws SQLException {
        String sql = "UPDATE Coordinate SET id_Coordinate = ?, id_Quest = ?, queue = ?, coordinate = ?, id_Word = ?";
        sql += " WHERE id_Coordinate = ?";
        boolean rowUpdateted = false;
        Connection conn = DAO.getConnection();
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1,coorDinate.getIdCoordinate());
        statement.setInt(2, coorDinate.getIdQuest());
        statement.setInt(3, coorDinate.getQueueCoordinate());
        statement.setString(4, coorDinate.getCoordinate());
        statement.setInt(5, coorDinate.getIdWord());
        rowUpdateted = statement.executeUpdate() > 0;

        return rowUpdateted;
    }

    @Override
    public boolean delete(Coordinate coorDinate) throws SQLException {

        String sql = "DELETE FROM Coordinate where id_Coordinate = ?";
        boolean rowDeleted = false;

        Connection conn = DAO.getConnection();
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1, coorDinate.getIdCoordinate());
        rowDeleted = statement.executeUpdate() > 0;

        return rowDeleted;
    }
}
