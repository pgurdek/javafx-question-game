package pl.model;

import pl.db.Question;

import java.util.ArrayList;
import java.util.List;

public class QuestionSummary {

    Question question;
    String userAnswer;

    public QuestionSummary(final Question question, final String userAnswer) {
        this.question = question;
        this.userAnswer = userAnswer;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(final Question question) {
        this.question = question;
    }

    public String getUserAnswer() {
        return userAnswer;
    }

    public void setUserAnswer(final String userAnswer) {
        this.userAnswer = userAnswer;
    }

    public Boolean isUserAnswerCorrect() {
        if (question.getCorrectAnswer().equals(userAnswer)) {
            return true;
        }
        return false;
    }

    public List<String> getAllAnswersContent() {
        final List<String> questionsList = new ArrayList<>(4);
        questionsList.add(question.getAnswer1());
        questionsList.add(question.getAnswer2());
        questionsList.add(question.getAnswer3());
        questionsList.add(question.getAnswer4());
        return questionsList;
    }
}
