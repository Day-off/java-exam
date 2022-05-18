package ee.taltech.iti0202.university.people;

import ee.taltech.iti0202.university.course.Course;
import ee.taltech.iti0202.university.course.TypeOfPassing;
import ee.taltech.iti0202.university.exeptions.Teacher.InvalidCourseException;
import ee.taltech.iti0202.university.exeptions.grade.InvalidAssessmentTypeException;

import java.util.ArrayList;
import java.util.List;

public class Teacher {
    private String name;
    private final List<Course> myCourses = new ArrayList<>();

    public Teacher(String name) {
        this.name = name;
    }

    public void addCourse(Course course) {
        myCourses.add(course);
    }

    public void removeCourse(Course course) {
        myCourses.remove(course);
    }

    public List<Course> getMyCourses() {
        return myCourses;
    }

    public void evaluateStudent(Student student, Course course, Character grade) throws InvalidCourseException, InvalidAssessmentTypeException {
        if (!myCourses.contains(course)) {
            throw new InvalidCourseException();
        }
        if (Character.isDigit(grade)) {
            if (Integer.parseInt(String.valueOf(grade)) < 0 || 5 < Integer.parseInt(String.valueOf(grade))) {
                throw new InvalidAssessmentTypeException();
            }
        }
        if (course.getPassingType() == TypeOfPassing.EXAM) {
            student.changeGradeExam(course, grade);
        } else if (course.getPassingType() == TypeOfPassing.PASS_FALL_ASSESSMENT) {
            student.changeGradePassFail(course, grade);
        } else {
            throw new InvalidAssessmentTypeException();
        }

    }

}
