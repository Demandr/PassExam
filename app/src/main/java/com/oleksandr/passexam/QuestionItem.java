package com.oleksandr.passexam;

public class QuestionItem {
    private String mQuestion;
    private String mAnswer1;
    private String mAnswer2;
    private String mAnswer3;
    private String mAnswer4;

    private String mChoiceAnswer;
    private String mTrueAnswer;

    private int mChoiceAnswerId = -1;

    @Override
    public String toString() {
        return "Question:  " + mQuestion + "\nAnswer: " + mAnswer1+ "\nAnswer: " + mAnswer2
                + "\nAnswer: " + mAnswer3 + "\nAnswer: " + mAnswer4
                + "\n\nTrue answer: " + mTrueAnswer + "\nChose answer: " + mChoiceAnswer + "\n\n";
    }

    public String getQuestion() {
        return mQuestion;
    }

    public void setQuestion(String question) {
        mQuestion = question;
    }

    public String getAnswer1() {
        return mAnswer1;
    }

    public void setAnswer1(String answer1) {
        mAnswer1 = answer1;
    }

    public String getAnswer2() {
        return mAnswer2;
    }

    public void setAnswer2(String answer2) {
        mAnswer2 = answer2;
    }

    public String getAnswer3() {
        return mAnswer3;
    }

    public void setAnswer3(String answer3) {
        mAnswer3 = answer3;
    }

    public String getAnswer4() {
        return mAnswer4;
    }

    public void setAnswer4(String answer4) {
        mAnswer4 = answer4;
    }

    public String getChoiceAnswer() {
        return mChoiceAnswer;
    }

    public void setChoiceAnswer(String choiceAnswer) {
        mChoiceAnswer = choiceAnswer;
    }

    public int getChoiceAnswerId() {
        return mChoiceAnswerId;
    }

    public void setChoiceAnswerId(int choiceAnswerId) {
        mChoiceAnswerId = choiceAnswerId;
    }

    public String getTrueAnswer() {
        return mTrueAnswer;
    }

    public void setTrueAnswer(String trueAnswer) {
        mTrueAnswer = trueAnswer;
    }
}
