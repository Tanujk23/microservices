package com.tanuj.question_service.model;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionWrapper {
    private Integer Id;
    private String questionTitle;
    private String option1;
    private String option2;
    private String option3;
}
