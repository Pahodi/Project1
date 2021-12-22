package DB;

import Models.Keyword;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DAOKeyword implements KeywordDao {
    private DAOKeyword() {
    }

    private static class SingletonHelper {
        private static final DAOKeyword INSTANCE = new DAOKeyword();
    }

    public static DAOKeyword getInstance() {
        return SingletonHelper.INSTANCE;
    }

    @Override
    public Optional<Keyword> find(String id) throws SQLException {

        String sql = "SELECT id_Word,queue,keyword,id_Score FROM Keyword WHERE id_Word = ?";
        int id_Word = 0, queue = 0, id_Score = 0;
        String keyword = "";
        Connection conn = DAO.getConnection();

        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, id);
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            id_Word = resultSet.getInt("id_Word");
            queue = resultSet.getInt("queue");
            keyword = resultSet.getString("keyword");
            id_Score = resultSet.getInt("id_Score");
        }
        return Optional.of(new Keyword(id_Word, queue, keyword, id_Score));
    }

    @Override
    public List<Keyword> findAll() throws SQLException {
        List<Keyword> listKeyword = new ArrayList<>();
        String sql = "SELECT id_Word,queue,keyword,id_Score FROM Keyword";

        Connection conn = DAO.getConnection();
        Statement statement = conn.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            int id_Word = resultSet.getInt("id_Word");
            int queue = resultSet.getInt("queue");
            String keyword = resultSet.getString("keyword");
            int id_Score = resultSet.getInt("id_Score");

            Keyword keyWord = new Keyword(id_Word, queue, keyword, id_Score);
            listKeyword.add(keyWord);
        }

        return listKeyword;
    }

    @Override
    public boolean save(Keyword keyWord) throws SQLException {
        String sql = "INSERT into Keyword (id_Word, queue, keyword, id_Score) VALUES (?, ?, ?, ?)";
        boolean rowInserted = false;
        Connection conn = DAO.getConnection();
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1,keyWord.getIdWord());
        statement.setInt(2, keyWord.getQueueKeyword());
        statement.setString(3, keyWord.getKeyword());
        statement.setInt(4, keyWord.getIdScore());
        rowInserted = statement.executeUpdate() > 0;
        return rowInserted;
    }

    @Override
    public boolean update(Keyword keyWord) throws SQLException {
        String sql = "UPDATE Keyword SET id_Word = ?, queue = ?, keyword = ?, id_Score = ?";
        sql += " WHERE id_Word = ?";
        boolean rowUpdateted = false;
        Connection conn = DAO.getConnection();
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1,keyWord.getIdWord());
        statement.setInt(2, keyWord.getQueueKeyword());
        statement.setString(3, keyWord.getKeyword());
        statement.setInt(4, keyWord.getIdScore());
        rowUpdateted = statement.executeUpdate() > 0;

        return rowUpdateted;
    }

    @Override
    public boolean delete(Keyword keyWord) throws SQLException {

        String sql = "DELETE FROM Keyword where id_Word = ?";
        boolean rowDeleted = false;

        Connection conn = DAO.getConnection();
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1, keyWord.getIdWord());
        rowDeleted = statement.executeUpdate() > 0;

        return rowDeleted;
    }
}
