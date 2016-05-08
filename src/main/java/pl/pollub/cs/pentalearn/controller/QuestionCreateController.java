package pl.pollub.cs.pentalearn.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pl.pollub.cs.pentalearn.domain.*;
import pl.pollub.cs.pentalearn.service.AnswerService;
import pl.pollub.cs.pentalearn.service.QuestionCategoryService;
import pl.pollub.cs.pentalearn.service.QuestionService;
import pl.pollub.cs.pentalearn.service.exception.NoSuchQuestionCategory;

import javax.inject.Inject;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by pglg on 25-04-2016.
 */
@Controller
public class QuestionCreateController {


    private final QuestionService questionService;
    private final QuestionCategoryService questionCategoryService;
    private final AnswerService answerService;
    private static final Logger LOGGER = LoggerFactory.getLogger(UserCreateController.class);


    @Inject
    public QuestionCreateController(QuestionService questionService, QuestionCategoryService questionCategoryService, AnswerService answerService) {
        this.questionService = questionService;
        this.questionCategoryService = questionCategoryService;
        this.answerService = answerService;
    }

    @InitBinder("form")
    public void initBinder(WebDataBinder binder) {
       // binder.addValidators(questionCreateFormQuestionCategoryIdValidator);
    }


    public ModelAndView getQuestionCreateView(QuestionCreateForm questionCreateForm) {
        ModelMap model = new ModelMap();
        model.addAttribute("questionCategories", questionCategoryService.getList());
        model.addAttribute("form",questionCreateForm);
        return new ModelAndView("question_create", model);
    }

    @RequestMapping(value = "/question_create.html", method = RequestMethod.GET)
    public ModelAndView getQuestionCreateView() {
        QuestionCreateForm questionCreateForm=new QuestionCreateForm();
        questionCreateForm.setAnswersNumber(3);
        return getQuestionCreateView(questionCreateForm);
    }


   /* @RequestMapping(value="/question_create.html",method = RequestMethod.POST)
    public ModelAndView setAnswersNumber(@ModelAttribute("form") @Valid QuestionCreateForm form,
                                         @ModelAttribute("answers") @Valid AnswersCreateForm answersCreateForm,
                                         BindingResult result){
        if (result.hasErrors()) {
            ModelMap model = new ModelMap();
            model.addAttribute("questionCategories", questionCategoryService.getList());
            model.addAttribute("form");
            model.addAttribute("answers",new AnswersCreateForm());
            return new ModelAndView("question_create", model);

        }
        else{
            ModelMap model = new ModelMap();
            model.addAttribute("questionCategories", questionCategoryService.getList());
            model.addAttribute("form");
            model.addAttribute("answers");
            return new ModelAndView("question_create", model);
        }


    }*/

    @RequestMapping(value = "/question_create.html", method = RequestMethod.POST)
    public ModelAndView createQuestion(@ModelAttribute("form") @Valid QuestionCreateForm form,
                                      /* @ModelAttribute("answers") @Valid AnswersCreateForm answersCreateForm,*/
                                       BindingResult result) {
        if(form.getAnswersNumberChanged()){
            return getQuestionCreateView(form);
        }
        if (result.hasErrors()) {

            ModelMap model = new ModelMap();
            model.addAttribute("questionCategories", questionCategoryService.getList());
            model.addAttribute("form");

            return new ModelAndView("question_create", model);

        }
        else {
            try {
                QuestionCategory category = questionCategoryService.getById(form.getQuestionCategoryId());
                Question question = new Question(form.getQuestionText(), category);
                questionService.save(question);
                category.addQuestion(question);

                List<Answer> answers = new ArrayList<>();
                for (int i = 0; i < form.getAnswerTexts().size(); i++) {
                    Answer answer = new Answer(question, form.getAnswerTexts().get(i),
                            form.getCorrects().get(i));
                    answers.add(answer);
                    answerService.save(answer);
                }
                question.setAnswers(answers);


            } catch (NoSuchQuestionCategory e) {
                result.reject("question.error");

                ModelMap model = new ModelMap();
                model.addAttribute("questionCategories", questionCategoryService.getList());
                model.addAttribute("form");
                return new ModelAndView("question_create", model);
            }

            return new ModelAndView("redirect:/question_category_list.html");
        }

    }

}
