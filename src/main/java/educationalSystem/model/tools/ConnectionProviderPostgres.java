package educationalSystem.model.tools;

import lombok.Getter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionProviderPostgres {

    @Getter

    private static ConnectionProviderPostgres provider = new ConnectionProviderPostgres();
    private ConnectionProviderPostgres() {}

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/marriage_db",
                "postgres",
                "138067sh"
        );
    }
}
