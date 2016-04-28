package pl.pollub.cs.pentalearn.domain;

import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by pglg on 24-04-2016.
 */
@Entity
public class QuestionCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Size(max = 64)
    private String name;

    @NotNull
    @OneToMany(mappedBy = "questionCategory",cascade = CascadeType.ALL)
    private List<Question> questions=new ArrayList<>();

    public QuestionCategory(String name) {
        this.name = name;
    }

    private QuestionCategory() {
    }

    public void addQuestion(Question question){
        question.setQuestionCategory(this);
        questions.add(question);
    }
    public List<Question> getQuestion() {
        return questions;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "QuestionCategory{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
