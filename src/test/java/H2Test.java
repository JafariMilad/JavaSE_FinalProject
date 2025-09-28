import educationalSystem.model.tools.ConnectionProvider;

import java.sql.Connection;
import java.sql.SQLException;

public class H2Test {
    public static void main(String[] args) throws SQLException {
        Connection connection = ConnectionProvider.getProvider().getH2Connection();

        connection.close();
    }
}
