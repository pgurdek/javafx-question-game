package pl;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pl.controllers.QuestionControllerMain;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/question-main.fxml"));
            Parent root = fxmlLoader.load();
            ((QuestionControllerMain) fxmlLoader.getController()).setStage(primaryStage);
            primaryStage.setTitle("Question Game");
            primaryStage.setScene(new Scene(root, 800, 600));
            primaryStage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
