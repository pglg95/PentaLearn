package pl.pollub.cs.pentalearn.domain;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Size;

/**
 * Created by pglg on 25-04-2016.
 */
public class QuestionCreateForm {

    @NotEmpty
    @Size(max = 64)
    private String questionText;

    private Long questionCategoryId;

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
