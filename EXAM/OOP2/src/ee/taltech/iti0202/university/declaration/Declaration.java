package ee.taltech.iti0202.university.declaration;

import ee.taltech.iti0202.university.course.Course;
import ee.taltech.iti0202.university.people.Student;

import java.util.List;

public class Declaration {

    private final Student student;
    private final List<Course> coursesForDeclaration;
    private static final int MIN_EAP = 26;
    private static final int MAX_EAP = 45;
    private int minCreditPoints;
    private int maxCreditPoints;
    private boolean isSubmitted = false;
//    private boolean isCompleted = false;


    public Declaration(Student student, List<Course> courses) {
        this.student = student;
        this.coursesForDeclaration = courses;
    }

    /*
    GETTERS
     */

    public Student getStudent() {
        return student;
    }

    public List<Course> getCoursesForDeclaration() {
        return coursesForDeclaration;
    }

    public boolean getIsSubmitted() {
        return isSubmitted;
    }

    /*
    MAIN METHODS
     */
    public int getSumEap() {
        return coursesForDeclaration.stream().mapToInt(Course::getEAP).sum();
    }

    public void setSumit(boolean res) {
        isSubmitted = res;
    }
}
