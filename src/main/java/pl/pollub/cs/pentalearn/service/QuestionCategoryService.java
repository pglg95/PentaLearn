package pl.pollub.cs.pentalearn.service;

import pl.pollub.cs.pentalearn.domain.QuestionCategory;
import pl.pollub.cs.pentalearn.service.exception.NoSuchQuestionCategory;
import pl.pollub.cs.pentalearn.service.exception.QuestionCategoryAlreadyExistException;

import java.util.List;

/**
 * Created by pglg on 24-04-2016.
 */
public interface QuestionCategoryService {

    QuestionCategory save(QuestionCategory questionCategory) throws QuestionCategoryAlreadyExistException;
    List<QuestionCategory> getList();
    QuestionCategory getById(Long id) throws NoSuchQuestionCategory;
}

