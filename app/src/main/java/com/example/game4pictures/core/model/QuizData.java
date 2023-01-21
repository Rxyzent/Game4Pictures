package com.example.game4pictures.core.model;

import java.util.ArrayList;

public class QuizData {
    private String answer;
    private ArrayList<String> variants;
    private ArrayList<Integer> images;

    public QuizData(String answer) {
        this.answer = answer;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public ArrayList<String> getVariants() {
        return variants;
    }

    public void setVariants(ArrayList<String> variants) {
        this.variants = variants;
    }

    public ArrayList<Integer> getImages() {
        return images;
    }

    public void setImages(ArrayList<Integer> images) {
        this.images = images;
    }

    @Override
    public String toString() {
        return "QuizData{" +
                "answer='" + answer + '\'' +
                ", variants=" + variants +
                ", images=" + images +
                '}';
    }
}


