package ee.taltech.iti0202.university.strategy;

import ee.taltech.iti0202.university.course.Course;
import ee.taltech.iti0202.university.people.Student;
import ee.taltech.iti0202.university.studyprogramm.StudyProgramme;

import java.util.ArrayList;
import java.util.List;

public abstract class Strategy {

    protected List<Course> coursesDec = new ArrayList<>();

    public void createCourseForDec(StudyProgramme programme, Student student){

    }

    public List<Course> getCoursesDec() {
        return coursesDec;
    }

    public void clearCoursesDec(){
        coursesDec.clear();
    }
}
