package pl.pollub.cs.pentalearn.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pl.pollub.cs.pentalearn.domain.Question;
import pl.pollub.cs.pentalearn.domain.QuestionCategory;
import pl.pollub.cs.pentalearn.domain.QuestionCreateForm;
import pl.pollub.cs.pentalearn.service.QuestionCategoryService;
import pl.pollub.cs.pentalearn.service.QuestionService;
import pl.pollub.cs.pentalearn.service.exception.NoSuchQuestionCategory;

import javax.inject.Inject;
import javax.jws.WebParam;
import javax.validation.Valid;

/**
 * Created by pglg on 25-04-2016.
 */
@Controller
public class QuestionCreateController {


    private final QuestionService questionService;
    private final QuestionCategoryService questionCategoryService;
    private static final Logger LOGGER = LoggerFactory.getLogger(UserCreateController.class);


    @Inject
    public QuestionCreateController(QuestionService questionService, QuestionCategoryService questionCategoryService) {
        this.questionService = questionService;
        this.questionCategoryService = questionCategoryService;
    }

    @InitBinder("form")
    public void initBinder(WebDataBinder binder) {
       // binder.addValidators(questionCreateFormQuestionCategoryIdValidator);
    }

    @RequestMapping(value = "/question_create.html", method = RequestMethod.GET)
    public ModelAndView getQuestionCategoryCreateView() {
        ModelMap model = new ModelMap();
        model.addAttribute("questionCategories", questionCategoryService.getList());
        model.addAttribute("form",new QuestionCreateForm());

        return new ModelAndView("question_create", model);
    }

    @RequestMapping(value = "/question_create.html", method = RequestMethod.POST)
    public ModelAndView createUser(@ModelAttribute("form") @Valid QuestionCreateForm form, BindingResult result) {
        if (result.hasErrors()) {

            LOGGER.info(form.getQuestionText()+" "+form.getQuestionCategoryId());
            ModelMap model = new ModelMap();
            model.addAttribute("questionCategories", questionCategoryService.getList());
            model.addAttribute("form");

            return new ModelAndView("question_create", model);
           // return "question_create";

        }
        try {
            QuestionCategory category=questionCategoryService.getById(form.getQuestionCategoryId());
            questionService.save(new Question(form.getQuestionText(),category));

        } catch (NoSuchQuestionCategory e) {
            result.reject("question.error");

            ModelMap model = new ModelMap();
            model.addAttribute("questionCategories", questionCategoryService.getList());
            model.addAttribute("form");
            return new ModelAndView("question_create", model);
            //return "question_create";
        }

        return new ModelAndView("redirect:/question_category_list.html");
        //return "redirect:/question_category_list.html";

    }

}
