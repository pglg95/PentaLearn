package pl.pollub.cs.pentalearn.domain;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * Created by pglg on 25-04-2016.
 */
public class QuestionCreateForm {

    @NotEmpty
    @Size(max = 64)
    private String questionText;

    private Long questionCategoryId;

    private List<Boolean> corrects;

    private List<String> answerTexts;


    public List<Boolean> getCorrects() {
        return corrects;
    }

    public void setCorrects(List<Boolean> correct) {
        this.corrects = correct;
    }

    public List<String> getAnswerTexts() {
        return answerTexts;
    }

    public void setAnswerTexts(List<String> answerText) {
        this.answerTexts = answerText;
    }

    public String getQuestionText() {
        return questionText;
    }

    public Long getQuestionCategoryId() {
        return questionCategoryId;
    }

    public void setQuestionCategoryId(Long questionCategoryId) {
        this.questionCategoryId = questionCategoryId;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    @Override
    public String toString() {
        return "QuestionCreateForm{" +
                "text='" + questionText + '\'' +
                '}';
    }
}
