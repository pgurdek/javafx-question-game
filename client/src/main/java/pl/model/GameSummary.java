package pl.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GameSummary {

    private List<QuestionSummary> questionSummaryList = new ArrayList<>();
    private Integer totalAvailablePoints = 0;
    private Integer totalCorrectAnsweredAnswers = 0;

    public GameSummary() {

    }

    public void addQuestionSummary(final QuestionSummary questionSummary) {
        questionSummaryList.add(questionSummary);
        validateQuestionSummary(questionSummary);
    }

    private void validateQuestionSummary(final QuestionSummary questionSummary) {
        if (questionSummary.isUserAnswerCorrect()) {
            totalCorrectAnsweredAnswers += 1;
        }
        totalAvailablePoints += 1;
    }


    public Integer getTotalAvailablePoints() {
        return totalAvailablePoints;
    }

    public Integer getTotalCorrectAnsweredAnswers() {
        return totalCorrectAnsweredAnswers;
    }

    public List<QuestionSummary> getQuestionSummaryList() {
        return Collections.unmodifiableList(questionSummaryList);
    }
}
