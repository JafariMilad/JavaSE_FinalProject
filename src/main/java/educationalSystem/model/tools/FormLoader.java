package educationalSystem.model.tools;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lombok.Getter;

import java.io.IOException;

    public class FormLoader {
        @Getter
        private final static FormLoader  formLoader = new FormLoader();

        private FormLoader() {
        }

        public void showStage(Stage stage, String fxmlPath, String title) throws IOException {
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource(fxmlPath)));

            stage.setScene(scene);
            stage.setTitle(title);
            stage.show();
        }
    }
