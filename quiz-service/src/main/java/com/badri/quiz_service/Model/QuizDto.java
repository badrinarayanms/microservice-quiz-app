package com.badri.quiz_service.Model;

public class QuizDto {
    private String category;
    private Integer numQ;
    private String title;

    public QuizDto(String category, Integer numQ, String title) {
        this.category = category;
        this.numQ = numQ;
        this.title = title;
    }

    public QuizDto() {
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getNumQ() {
        return numQ;
    }

    public void setNumQ(Integer numQ) {
        this.numQ = numQ;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
