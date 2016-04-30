package pl.pollub.cs.pentalearn.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import pl.pollub.cs.pentalearn.domain.QuestionCategory;
import pl.pollub.cs.pentalearn.domain.QuestionCategoryCreateForm;
import pl.pollub.cs.pentalearn.service.QuestionCategoryService;
import pl.pollub.cs.pentalearn.service.exception.QuestionCategoryAlreadyExistException;

import javax.inject.Inject;
import javax.validation.Valid;

/**
 * Created by pglg on 24-04-2016.
 */
@Controller
public class QuestionCategoryCreateController {

    private final QuestionCategoryService questionCategoryService;

    @Inject
    public QuestionCategoryCreateController(QuestionCategoryService questionCategoryService) {
        this.questionCategoryService = questionCategoryService;
    }

    @InitBinder("form")
    public void initBinder(WebDataBinder binder) {
    }

    @RequestMapping(value = "/question_category_create.html", method = RequestMethod.GET)
    public ModelAndView getQuestionCategoryCreateView() {
        return new ModelAndView("question_category_create", "form", new QuestionCategoryCreateForm());
    }

    @RequestMapping(value = "/question_category_create.html", method = RequestMethod.POST)
    public String createQuestionCategory(@Valid @ModelAttribute("form") QuestionCategoryCreateForm form, BindingResult result) {
        if (result.hasErrors()) {
            return "question_category_create";
        }
        try {
            questionCategoryService.save(new QuestionCategory(form.getName()));
        } catch (QuestionCategoryAlreadyExistException e) {
            result.reject("question.category.error.exist");
            return "question_category_create";
        }
        return "redirect:/question_category_list.html";
    }

}
