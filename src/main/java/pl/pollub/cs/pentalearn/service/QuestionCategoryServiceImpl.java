package pl.pollub.cs.pentalearn.service;

import org.hibernate.validator.constraints.NotEmpty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import pl.pollub.cs.pentalearn.domain.QuestionCategory;
import pl.pollub.cs.pentalearn.domain.User;
import pl.pollub.cs.pentalearn.repository.QuestionCategoryRepository;
import pl.pollub.cs.pentalearn.service.exception.NoSuchQuestionCategory;
import pl.pollub.cs.pentalearn.service.exception.QuestionCategoryAlreadyExistException;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by pglg on 24-04-2016.
 */
@Service
@Validated
public class QuestionCategoryServiceImpl implements QuestionCategoryService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
    private final QuestionCategoryRepository questionCategoryRepository;

    @Inject
    public QuestionCategoryServiceImpl(final QuestionCategoryRepository questionCategoryRepository) {
        this.questionCategoryRepository = questionCategoryRepository;
    }

    @Override
    @Transactional
    public QuestionCategory save(@NotNull @Valid final QuestionCategory questionCategory) throws QuestionCategoryAlreadyExistException {
        QuestionCategory existing = questionCategoryRepository.findByName(questionCategory.getName());
        if (existing != null) {
            throw new QuestionCategoryAlreadyExistException(
                    String.format("There already exists a user with id=%s", questionCategory.getId()));
        }
        return questionCategoryRepository.save(questionCategory);
    }

    @Override
    @Transactional(readOnly =true)
    public List<QuestionCategory> getList() {
        return (List<QuestionCategory>) questionCategoryRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public QuestionCategory getById(@NotNull @Valid final Long id) throws NoSuchQuestionCategory {
        QuestionCategory existing=questionCategoryRepository.findById(id);
        if(existing!=null){
            return existing;
        }
        else{
            throw new NoSuchQuestionCategory("There isn't such category of question");
        }
    }
}
