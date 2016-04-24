package pl.pollub.cs.pentalearn.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


/**
 * Created by pglg on 24-04-2016.
 */
@Entity
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @ManyToOne
    private QuestionCategory questionCategory;

    @NotNull
    private String questionText;

  //  @NotNull
   // private AnswerScheme answerScheme;


    public Question(String questionText) {
        this.questionText = questionText;
    }

    public void setQuestionCategory(QuestionCategory questionCategory) {
        this.questionCategory = questionCategory;
    }

    public Long getId() {
        return id;
    }

    public QuestionCategory getQuestionCategory() {
        return questionCategory;
    }

    public String getQuestionText() {
        return questionText;
    }
}
