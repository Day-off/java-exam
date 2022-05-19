package ee.taltech.iti0202.university.grade;

import ee.taltech.iti0202.university.course.Course;
import ee.taltech.iti0202.university.course.TypeOfPassing;
import ee.taltech.iti0202.university.people.Student;
import ee.taltech.iti0202.university.people.Teacher;

public class Grade {

    private final Student student;
    private final Course course;
    private Teacher teacher;
    private Character grade;
    private boolean isPassed = false;
    private final TypeOfPassing passing;
    private boolean courseIsVaba = false;

    public Grade(Student student, Course course) {
        this.student = student;
        this.course = course;
        passing = course.getPassingType();
    }

    /*
    GETTERS
     */

    public Course getCourse() {
        return course;
    }

    public Character getGrade() {
        return grade;
    }

    public Student getStudent() {
        return student;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public boolean getIsPassed() {
        return isPassed;
    }

    public TypeOfPassing getPassing() {
        return passing;
    }

    public boolean isCourseIsVaba() {
        return courseIsVaba;
    }

    /*
    SETTERS
     */

    public void setCourseIsVaba() {
        courseIsVaba = true;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public void setGrade(Character grade) {
        this.grade = grade;
    }

    public void setPassed(boolean passed) {
        isPassed = passed;
    }

    @Override
    public String toString() {
        return " " + grade + ", isPassed: " + isPassed;
    }
}
