package educationalSystem.model.tools;

import lombok.Getter;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

    public class ConnectionProvider {
        // Singleton
        @Getter
        private final static ConnectionProvider provider = new ConnectionProvider();
        private final static BasicDataSource basicDataSource = new BasicDataSource();

        private ConnectionProvider() {
        }

        public Connection getOracleConnection() throws SQLException {
            basicDataSource.setDriverClassName("oracle.jdbc.OracleDriver");
            basicDataSource.setUrl("jdbc:oracle:thin:@localhost:1521:XE");
            basicDataSource.setUsername("javase");
            basicDataSource.setPassword("java123");
            basicDataSource.setMinIdle(4);
            basicDataSource.setMaxTotal(16);

            return basicDataSource.getConnection();
        }

        public int getNextId(String sequenceName) throws Exception{
            ResultSet resultSet = getOracleConnection().prepareStatement(String.format("select %s.nextval as NEXT_ID from dual", sequenceName)).executeQuery();
            resultSet.next();
            return resultSet.getInt("NEXT_ID");
        }

        public Connection getH2Connection() throws SQLException {
//        Class.forName("org.h2.Driver");
            return DriverManager.getConnection(
                    "jdbc:h2:file:./db/mft_class;AUTO_SERVER=TRUE;MODE=Oracle",
                    "sa",
                    ""
            );
        }

        public  Connection getPostgresConnection() throws SQLException {
            return DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/mft_class",
                    "postgres",
                    "138067sh"
            );
        }
    }

