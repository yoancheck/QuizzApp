package com.example.project_java_natan.Model;

import java.util.List;

public class Question {
    public String question;
    public List<String> response;
    public int correctAnswer;

    public Question() {
    }

    public Question(String question, List<String> response, int correctAnswer) {
        this.question = question;
        this.response = response;
        this.correctAnswer = correctAnswer;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<String> getResponse() {
        return response;
    }

    public void setResponse(List<String>  response) {
        this.response = response;
    }

    public int getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(int correctAnswer) {
        this.correctAnswer = correctAnswer;
    }
}
