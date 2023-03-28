import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    static Stage stage;

    @Override
    public void start(Stage stage) throws Exception {
        Main.stage = stage;

        Parent root = null;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/weatherView.fxml"));
        root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {
        launch(args);


    }
}