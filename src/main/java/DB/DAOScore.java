package DB;

import Models.Score;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DAOScore implements ScoreDao {
    private DAOScore() {
    }

    private static class SingletonHelper {
        private static final DAOScore INSTANCE = new DAOScore();
    }

    public static DAOScore getInstance() {
        return SingletonHelper.INSTANCE;
    }

    @Override
    public Optional<Score> find(String id) throws SQLException {

        String sql = "SELECT id_Score,noError,oneError,twoError,threeError,threeAndMoreError FROM Score WHERE id_Score = ?";
        int id_Score = 0, noError = 0, oneError = 0, twoError = 0, threeError = 0, threeAndMoreError = 0;
        Connection conn = DAO.getConnection();

        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, id);
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            id_Score = resultSet.getInt("id_Score");
            noError = resultSet.getInt("noError");
            oneError = resultSet.getInt("oneError");
            twoError = resultSet.getInt("twoError");
            threeError = resultSet.getInt("threeError");
            threeAndMoreError = resultSet.getInt("threeAndMoreError");
        }
        return Optional.of(new Score(id_Score, noError, oneError, twoError, threeError, threeAndMoreError));
    }

    @Override
    public List<Score> findAll() throws SQLException {
        List<Score> listScore = new ArrayList<>();
        String sql = "SELECT id_Score,noError,oneError,twoError,threeError,threeAndMoreError FROM Score";

        Connection conn = DAO.getConnection();
        Statement statement = conn.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            int id_Score = resultSet.getInt("id_Score");
            int noError = resultSet.getInt("noError");
            int oneError = resultSet.getInt("oneError");
            int twoError = resultSet.getInt("twoError");
            int threeError = resultSet.getInt("threeError");
            int threeAndMoreError = resultSet.getInt("threeAndMoreError");

            Score score = new Score(id_Score, noError, oneError, twoError, threeError, threeAndMoreError);
            listScore.add(score);
        }

        return listScore;
    }

    @Override
    public boolean save(Score score) throws SQLException {
        String sql = "INSERT into Score (id_Score, noError, oneError, twoError, threeError, threeAndMoreError) VALUES (?, ?, ?, ?, ?, ?)";
        boolean rowInserted = false;
        Connection conn = DAO.getConnection();
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1,score.getIdScore());
        statement.setInt(2, score.getNoError());
        statement.setInt(3, score.getOneError());
        statement.setInt(4,score.getTwoError());
        statement.setInt(5, score.getThreeError());
        statement.setInt(6, score.getThreeAndMoreError());
        rowInserted = statement.executeUpdate() > 0;
        return rowInserted;
    }

    @Override
    public boolean update(Score score) throws SQLException {
        String sql = "UPDATE Score SET id_Score = ?, noError = ?, oneError = ?, twoError = ?, threeError = ?, threeAndMoreError = ?";
        sql += " WHERE id_Score = ?";
        boolean rowUpdateted = false;
        Connection conn = DAO.getConnection();
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1,score.getIdScore());
        statement.setInt(2, score.getNoError());
        statement.setInt(3, score.getOneError());
        statement.setInt(4,score.getTwoError());
        statement.setInt(5, score.getThreeError());
        statement.setInt(6, score.getThreeAndMoreError());
        rowUpdateted = statement.executeUpdate() > 0;

        return rowUpdateted;
    }

    @Override
    public boolean delete(Score score) throws SQLException {

        String sql = "DELETE FROM Score where id_Score = ?";
        boolean rowDeleted = false;

        Connection conn = DAO.getConnection();
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1, score.getIdScore());
        rowDeleted = statement.executeUpdate() > 0;

        return rowDeleted;
    }
}
