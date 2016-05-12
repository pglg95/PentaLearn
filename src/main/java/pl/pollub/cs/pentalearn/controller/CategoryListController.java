package pl.pollub.cs.pentalearn.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import pl.pollub.cs.pentalearn.service.CategoryService;

import javax.inject.Inject;

/**
 * Created by pglg on 25-04-2016.
 */
@Controller
public class CategoryListController {
    private final CategoryService categoryService;

    @Inject
    public CategoryListController(final CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @RequestMapping("/question_category_list.html")
    public ModelAndView getListQuestionCategoriesView() {
        ModelMap model = new ModelMap();
        model.addAttribute("qcategories", categoryService.getList());
        return new ModelAndView("question_category_list", model);
    }
}
