package pl.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import pl.db.Question;
import pl.model.GameSummary;
import pl.model.QuestionSummary;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class QuestionControllerSummary implements Initializable {

    @FXML
    ScrollPane scrollPane;
    @FXML
    Label summaryLabel;
    @FXML
    VBox mainVBoxContainer;

    @Override
    public void initialize(final URL location, final ResourceBundle resources) {

    }

    public void showSummary(final GameSummary gameSummary) throws IOException {
        //Set Main Summary score
        scrollPane.setPrefHeight(600);
        scrollPane.setHmax(600);
        summaryLabel.setText("Wynik: " + gameSummary.getTotalCorrectAnsweredAnswers() + " / " + gameSummary.getTotalAvailablePoints());

        VBox.setMargin(mainVBoxContainer, new Insets(0, 0, 45, 0));

        for (final QuestionSummary questionSummary : gameSummary.getQuestionSummaryList()) {
            final Question question = questionSummary.getQuestion();
            final VBox questionWrapper = new VBox();

            questionWrapper.minHeight(Region.USE_PREF_SIZE);
            questionWrapper.setPrefHeight(200);
            questionWrapper.setPrefWidth(100);
            questionWrapper.setFillWidth(true);
            VBox.setMargin(questionWrapper, new Insets(0, 0, 30, 0));
            VBox.setVgrow(questionWrapper, Priority.ALWAYS);

            final Label questionContent = new Label(question.getContent());
            questionContent.setPadding(new Insets(15));
            questionContent.setAlignment(Pos.TOP_LEFT);
            questionContent.setMinHeight(Region.USE_PREF_SIZE);
            questionContent.setWrapText(true);
            VBox.setMargin(questionContent, new Insets(0, 0, 30, 0));
            VBox.setVgrow(questionContent, Priority.ALWAYS);

            final GridPane answersContainer = new GridPane();
            answersContainer.setAlignment(Pos.TOP_LEFT);
            VBox.setVgrow(answersContainer, Priority.ALWAYS);
            questionWrapper.getChildren().add(questionContent);
//            answersContainer.setVgap(5);
            addAnswers(answersContainer, questionSummary);
            questionWrapper.getChildren().add(answersContainer);
            GridPane.setFillWidth(answersContainer, true);
//            mainBox.getChildren().add(questionWrapper);

            mainVBoxContainer.getChildren().add(questionWrapper);
        }

    }

    private void addAnswers(final GridPane answersContainer, final QuestionSummary questionSummary) {
        final Question question = questionSummary.getQuestion();
        for (int i = 0; i < 4; i++) {
            final ColumnConstraints columnConstraints = new ColumnConstraints();
            columnConstraints.setHalignment(HPos.LEFT);
            columnConstraints.setHgrow(Priority.ALWAYS);
            columnConstraints.setPrefWidth(200);
            columnConstraints.setMinWidth(10);
            columnConstraints.setMaxWidth(200);
            answersContainer.getColumnConstraints().add(columnConstraints);
//            break;
        }
        final RowConstraints rowConstraint = new RowConstraints();
        rowConstraint.setValignment(VPos.TOP);
        rowConstraint.setVgrow(Priority.ALWAYS);
        rowConstraint.setPrefHeight(100);
        rowConstraint.setMinHeight(10);
        answersContainer.getRowConstraints().add(rowConstraint);
        int count = 0;
        for (final String availableAnswer : questionSummary.getAllAnswersContent()) {
            final Label answer = new Label(availableAnswer);

            count++;
            answer.setPadding(new Insets(15.0));
            answer.setMinHeight(Region.USE_PREF_SIZE);
            answer.setWrapText(true);
            GridPane.setValignment(answer, VPos.TOP);
            answer.setAlignment(Pos.TOP_LEFT);
            VBox.setVgrow(answer, Priority.ALWAYS);
            answersContainer.add(answer,count,0);

            if (availableAnswer.equals(question.getCorrectAnswer()) && availableAnswer.equals(questionSummary.getUserAnswer())) {
                answer.getStyleClass().add("correct-answer");
            } else if (availableAnswer.equals(questionSummary.getUserAnswer())) {
                answer.getStyleClass().add("bad-answer");
            }
//            break;

        }
    }

}
