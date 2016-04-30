package pl.pollub.cs.pentalearn.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import pl.pollub.cs.pentalearn.service.QuestionCategoryService;

import javax.inject.Inject;

/**
 * Created by pglg on 25-04-2016.
 */
@Controller
public class QuestionCategoryListController {
    private final QuestionCategoryService questionCategoryService;

    @Inject
    public QuestionCategoryListController(final QuestionCategoryService questionCategoryService) {
        this.questionCategoryService=questionCategoryService;
    }

    @RequestMapping("/question_category_list.html")
    public ModelAndView getListQuestionCategoriesView() {
        ModelMap model = new ModelMap();
        model.addAttribute("qcategories", questionCategoryService.getList());
        return new ModelAndView("question_category_list", model);
    }
}
