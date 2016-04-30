package pl.pollub.cs.pentalearn.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;


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
    @Size(max = 64)
    private String questionText;

    @NotNull
    @OneToMany(mappedBy = "question",cascade = CascadeType.ALL, fetch=FetchType.EAGER)
    private List<Answer> answers=new ArrayList<>();

    private Question(){}

    public Question(String questionText,QuestionCategory questionCategory) {
        this.questionText = questionText;
        this.questionCategory=questionCategory;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
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

    public List<Answer> getAnswers() {
        return answers;
    }
}
