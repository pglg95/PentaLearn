package pl.pollub.cs.pentalearn.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * Chapter class, contains one lecture and one exercise
 * Created by Wojciech on 2016-05-02.
 */

@Entity
public class Chapter {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @ManyToOne
    private Course course;

    @NotNull
    @OneToMany(mappedBy = "chapter",cascade = CascadeType.ALL,fetch=FetchType.EAGER)
    private List<Lecture> lectures=new ArrayList<>();

    @NotNull
    @OneToMany(mappedBy = "chapter",cascade = CascadeType.ALL,fetch=FetchType.EAGER)
    private List<Exercise> exercises=new ArrayList<>();

    private Chapter(){}

    public Chapter(Course course) {
        this.course = course;
    }

    public void addLecture(Lecture lecture){
        this.lectures.add(lecture);
    }

    public void addExercise(Exercise exercise){
        this.exercises.add(exercise);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public List<Lecture> getLectures() {
        return lectures;
    }

    public void setLectures(List<Lecture> lectures) {
        this.lectures = lectures;
    }

    public List<Exercise> getExercises() {
        return exercises;
    }

    public void setExercises(List<Exercise> exercises) {
        this.exercises = exercises;
    }
}
