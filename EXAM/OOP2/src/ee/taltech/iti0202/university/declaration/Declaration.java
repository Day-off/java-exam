package ee.taltech.iti0202.university.declaration;

import ee.taltech.iti0202.university.course.Course;
import ee.taltech.iti0202.university.people.Student;

import java.util.List;

public class Declaration {

    private final Student student;
    private final List<Course> coursesForDeclaration;
    private static int minEap = 16;
    private static int maxEap = 45;
    private boolean isSubmitted = false;
    private boolean isCompleted = false;


    public Declaration(Student student, List<Course> courses) {
        this.student = student;
        this.coursesForDeclaration = courses;
    }

    /*
    GETTERS
     */

    public boolean getIsCompleted() {
        return isCompleted;
    }

    public Student getStudent() {
        return student;
    }

    public List<Course> getCoursesForDeclaration() {
        return coursesForDeclaration;
    }

    public boolean getIsSubmitted() {
        return isSubmitted;
    }

    public static int getMinEap() {
        return minEap;
    }

    public static int getMaxEap() {
        return maxEap;
    }

    /*
            MAIN METHODS
             */
    public int getSumEap() {
        return coursesForDeclaration.stream().mapToInt(Course::getEap).sum();
    }

    public void setSumit(boolean res) {
        isSubmitted = res;
    }

    public void setCompleted(boolean res) {
        isCompleted = res;
    }

    public void setMinEap(int eap) {
        minEap = eap;
    }

    public void setMaxEap(int eap) {
        maxEap = eap;
    }

}
