package com.MultipleTableFetch.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.nio.file.Path;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UploadQuiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long quizId;
    private String quizName;
    private String quizFile1;
}