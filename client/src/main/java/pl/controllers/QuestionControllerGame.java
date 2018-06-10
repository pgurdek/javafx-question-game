package pl.controllers;

import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DialogPane;
import javafx.stage.Stage;
import pl.UtilsTime.Tasks;
import pl.db.Question;
import pl.db.Questions;
import pl.model.GameSummary;
import pl.model.QuestionSummary;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class QuestionControllerGame implements Initializable, EventHandler<ActionEvent> {
    public static final String CORRECT_ANSWER_CLASS = "correct-answer";
    public static final String BAD_ANSWER_CLASS = "bad-answer";
    public static final int QUESTION_SLEEP_TIME = 1000;
    private Stage stage;
    private Questions questions;
    private Map<Button, String> buttonStringMap = new HashMap<>(4);
    private Integer questionCount = 0;
    private Integer questionsTotalCount = 0;
    private GameSummary gameSummary = new GameSummary();
    private Boolean isClicked = false;
    private String correctAnswer;

    @FXML
    private DialogPane questionDialogPane;
    @FXML
    private Button A;
    @FXML
    private Button B;
    @FXML
    private Button C;
    @FXML
    private Button D;


    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @Override
    public void initialize(final URL location, final ResourceBundle resources) {

    }

    public void startGame() {
        final Question firstQuestion = questions.getQuestion().get(0);
        questionDialogPane.setContentText(firstQuestion.getContent());
        questionDialogPane.setHeaderText("Question number " + (questionCount + 1) + "/" + questionsTotalCount);
        setButtons(firstQuestion);
        questionCount += 1;

    }

    private void setGameSummaryDetails(final Question question, final String userAnswer) {
        gameSummary.addQuestionSummary(new QuestionSummary(question, userAnswer));
    }

    @FXML
    public void continueGame(final ActionEvent actionEvent) throws InterruptedException {
        if (!isClicked) {
            isClicked = true;
            final Button button = (Button) actionEvent.getSource();
            final String userAnswer = button.getText();

            if (userAnswer.equals(correctAnswer)) {
                button.getStyleClass().add(CORRECT_ANSWER_CLASS);
            } else {
                button.getStyleClass().add(BAD_ANSWER_CLASS);
                for (final Map.Entry<Button, String> pair : buttonStringMap.entrySet()
                ) {
                    if (pair.getValue().equals(correctAnswer)) {
                        pair.getKey().getStyleClass().add(CORRECT_ANSWER_CLASS);
                    }
                }
            }

            final Task<Void> sleep = Tasks.sleep(QUESTION_SLEEP_TIME);
            sleep.setOnSucceeded(e -> {
                try {
                    setDataForNextQuestion(userAnswer,actionEvent);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                isClicked = false;
                    }
            );
        }


    }

    private void setDataForNextQuestion(final String userAnswer, final ActionEvent actionEvent) throws IOException {
        if (questionCount < questions.getQuestion().size()) {
            setGameSummaryDetails(questions.getQuestion().get(questionCount - 1), userAnswer);
            final Question nextQuestion = questions.getQuestion().get(questionCount);
            questionDialogPane.setHeaderText("Question number " + (questionCount + 1) + "/" + questionsTotalCount);
            questionDialogPane.setContentText(nextQuestion.getContent());
            setButtons(nextQuestion);
            questionCount += 1;
        } else {
            setGameSummaryDetails(questions.getQuestion().get(questionCount - 1), userAnswer);
//            new InfoBox().display("Game Over", "You have scored" + gameSummary.getTotalCorrectAnsweredAnswers() + " points on Total:"+ gameSummary.getTotalAvailablePoints());
            final FXMLLoader secondPageLoader = new FXMLLoader(getClass().getResource("/summary.fxml"));
            final Parent summaryController = secondPageLoader.load();
            final Scene summaryStage = new Scene(summaryController);
            final QuestionControllerSummary controller = secondPageLoader.getController();
            controller.showSummary(gameSummary);
            final Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            window.setScene(summaryStage);
            window.show();
        }
    }

    private void setButtons(Question question) {
        final List<String> questionAnswers = Arrays.asList(question.getAnswer1(), question.getAnswer2(),
                question.getAnswer3(), question.getAnswer4());
        Collections.shuffle(questionAnswers);
        correctAnswer = question.getCorrectAnswer();
        buttonStringMap.put(A, questionAnswers.get(0));
        buttonStringMap.put(B, questionAnswers.get(1));
        buttonStringMap.put(C, questionAnswers.get(2));
        buttonStringMap.put(D, questionAnswers.get(3));

        for (final Map.Entry<Button, String> pair :
                buttonStringMap.entrySet()) {
            pair.getKey().setText(pair.getValue());
            pair.getKey().getStyleClass().removeAll(CORRECT_ANSWER_CLASS, BAD_ANSWER_CLASS);
        }
    }


    public void setQuestions(final Questions questions) {
        Collections.shuffle(questions.getQuestion());
        this.questions = questions;
        this.questionsTotalCount = questions.getQuestion().size();
    }

    @Override
    public void handle(final ActionEvent event) {

    }
}
