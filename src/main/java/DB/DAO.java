package DB;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DAO {
    private final DataSource daso;
    private static final Logger LOGGER = Logger.getLogger(DAO.class.getName());

    DAO()
    {
        MysqlDataSource daso = new MysqlDataSource();
        String rootPath =
                Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource("database.properties")).getPath();
        InputStream input = null;

        try {
            input = new FileInputStream(rootPath);
            Properties prop = new Properties();
            prop.load(input);
            daso.setDatabaseName(prop.getProperty("database"));
            daso.setServerName(prop.getProperty("serverName"));
            daso.setPort(Integer.parseInt(prop.getProperty("port")));
            daso.setUser(prop.getProperty("user"));
            daso.setPassword(prop.getProperty("password"));
        }

        catch (FileNotFoundException e) {
            LOGGER.log(Level.SEVERE, "File database.properties Not Found",e);
        }

        catch(IOException e) {
            LOGGER.log(Level.SEVERE, "IO Error", e);
        } finally {
            if (input !=null) {
                try {
                    input.close();
                }

                catch (IOException e) {
                    LOGGER.log(Level.SEVERE, "Failed to close streams",e);
                }
            }
        }
        this.daso = daso;
    }

    public static Connection getConnection() throws SQLException
    {
        return SingletonHelper.INSTANCE.daso.getConnection();
    }

    private static class SingletonHelper
    {
        private static final DAO INSTANCE = new DAO();
    }
}
