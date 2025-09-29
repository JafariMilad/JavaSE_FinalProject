package educationalSystem.model.repository;

import lombok.extern.log4j.Log4j2;
import educationalSystem.model.tools.ConnectionProvider;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.Scanner;

@Log4j2
public class DatabaseManager {
    public static void createDatabase() throws FileNotFoundException, SQLException {

        Scanner scanner = new Scanner(new File("src/main/resources/database_creator.sql"));

        String sqlCommand = "";
        while (scanner.hasNextLine()) {
            sqlCommand += scanner.nextLine();
        }
        String[] sqlCommands = sqlCommand.split(";");

        for (String command : sqlCommands) {
            log.info(command);
            try {
                ConnectionProvider.getProvider().getOracleConnection().prepareStatement(command).execute();
                log.info("Created");
            }catch (SQLException e){
                log.error(e.getMessage());
            }

            System.out.println("-------------------------------------------------------");
        }
    }
}
