package pl.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import pl.db.Questions;
import pl.mappers.XMLFileMapper;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ResourceBundle;

public class QuestionControllerMain implements Initializable {

    private Stage stage;
    private File file;
    private Questions questions;

    @FXML
    public Button browseFile;
    @FXML
    public Button saveFile;
    @FXML
    public Button startGame;
    @FXML
    public Label wk;

    public void searchFile() {

        System.out.printf("Searching for file ");
        setFile();
        if (file != null) {
            questions = XMLFileMapper.getQuestionsFromFile(file);
            System.out.println(questions.getQuestion().get(0).getAnswer4());
        }
    }

    public void startGame(ActionEvent event) throws IOException {
//        if(questions == null){
//            new AlertBox().display("Error", "Please upload questions before starting game");
//        }else {
//        questions = XMLFileMapper.getQuestionsFromFile(new File("C:\\Users\\Mytka\\IdeaProjects\\aids\\db\\src\\test\\resources\\data.xml"));

        questions = XMLFileMapper.getQuestionsFromFile(file);
        final FXMLLoader secondPageLoader = new FXMLLoader(getClass().getResource("/question-game.fxml"));
        final Parent questionControllerGameParent = secondPageLoader.load();
        final Scene secondScene = new Scene(questionControllerGameParent);
        final QuestionControllerGame controller = secondPageLoader.getController();
        controller.setStage(stage);
        controller.setQuestions(questions);
        controller.startGame();
        final Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(secondScene);
        window.show();
        //            questionControllerGame.set

//        }
    }


    private void setFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        File file = fileChooser.showOpenDialog(stage);
        this.file = file;
    }


    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        final Path path = Paths.get(System.getProperty("user.dir"), "data.xml");
        file = path.toFile();
        wk.setText(path.toAbsolutePath().toString() + " is exists " + file.exists());
    }
//

//    public void saveFile() throws IOException {
//        if (this.esprForm == null) {
//            new AlertBox().display("Error", "No ESPR file uploaded, use Browse file first");
//        } else {
//            System.out.println("ok");
//            try {
//                EsprToXssf.save(esprForm, file);
//            } catch (IllegalAccessException e) {
//                e.printStackTrace();
//
//            }
//        }
//    }
}
