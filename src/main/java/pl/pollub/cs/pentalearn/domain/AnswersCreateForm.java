package pl.pollub.cs.pentalearn.domain;

import com.sun.org.apache.xpath.internal.operations.Bool;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * Created by pglg on 29-04-2016.
 */
public class AnswersCreateForm {

    @NotNull
    private List<Boolean> corrects;

    @NotNull
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

}
