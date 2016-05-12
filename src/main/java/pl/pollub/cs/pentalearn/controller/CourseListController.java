package pl.pollub.cs.pentalearn.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import pl.pollub.cs.pentalearn.domain.Course;
import pl.pollub.cs.pentalearn.service.ChapterService;
import pl.pollub.cs.pentalearn.service.CourseService;
import pl.pollub.cs.pentalearn.service.exception.NoSuchCourse;

import javax.inject.Inject;

/**
 *
 * Created by Wojciech on 2016-04-30.
 */
@Controller
public class CourseListController {

    private final CourseService courseService;
    private final ChapterService chapterService;

    @Inject
    public CourseListController(CourseService courseService, ChapterService chapterService) {
        this.courseService = courseService;
        this.chapterService = chapterService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView showAllCourses(){
        return new ModelAndView("Course/course", "courses", courseService.getList());
    }

    @RequestMapping(value = "/showChapters", method = RequestMethod.POST)
    public ModelAndView moveToCourse(@RequestParam("courseNumber") Long courseNumber){
        ModelAndView mav = new ModelAndView("Course/showChapters");

        try {
           Course course = courseService.getById(courseNumber);
            //add chapters to view
           // mav.addObject("chapters", CourseDB.getChaptersByCourseId(courseNumber));
            //add name and description of course
            mav.addObject("courseName", course.getName());
            mav.addObject("courseDescription", course.getDescription());
        } catch (NoSuchCourse noSuchCourse) {
            mav.addObject("courseName", "Blad");
            mav.addObject("courseDescription", "Blad");
        }
        finally {
            return mav;
        }

    }

}
