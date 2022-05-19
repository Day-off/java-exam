package ee.taltech.iti0202.university.tests.people;

import ee.taltech.iti0202.university.University;
import ee.taltech.iti0202.university.course.Course;
import ee.taltech.iti0202.university.course.CourseType;
import ee.taltech.iti0202.university.course.TypeOfPassing;
import ee.taltech.iti0202.university.exeptions.Student.StudentToYoungOrOldException;
import ee.taltech.iti0202.university.exeptions.Teacher.InvalidCourseException;
import ee.taltech.iti0202.university.exeptions.Teacher.TeacherAlreadyManageThisCourseException;
import ee.taltech.iti0202.university.exeptions.grade.InvalidAssessmentTypeException;
import ee.taltech.iti0202.university.grade.Grade;
import ee.taltech.iti0202.university.people.Student;
import ee.taltech.iti0202.university.people.Teacher;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.testng.Assert.assertEquals;

public class TeacherTest {
    private Teacher ago = new Teacher("Ago");
    private University uni = new University("TTU");
    private Student student = new Student("Kati Liis", 23);
    private Course course = new Course("JAVA", ago, 6, TypeOfPassing.EXAM, CourseType.GENERAL);
    private Course course2 = new Course("JAVA2", ago, 6,
            TypeOfPassing.PASS_FALL_ASSESSMENT, CourseType.GENERAL);

    public TeacherTest() throws TeacherAlreadyManageThisCourseException, StudentToYoungOrOldException {
    }

    @Test
    public void correctAssessment() throws
            InvalidAssessmentTypeException, InvalidCourseException {
        student.addCourse(course, new Grade(student, course));
        ago.evaluateStudent(student, course, '2');
        Character grade = '2';
        assertEquals(student.getAllCourses().get(course).getGrade(), grade);
        assertEquals(student.getAllCourses().get(course).getTeacher(), ago);

        student.addCourse(course2, new Grade(student, course2));
        ago.evaluateStudent(student, course2, 'a');
        assertEquals(student.getAllCourses().get(course).getGrade(), grade);
    }

    /**
     * Assessment partly located in student class(but test is here)
     */
    @Test
    public void InvalidAssessment() {
        student.addCourse(course, new Grade(student, course));
        student.addCourse(course2, new Grade(student, course2));
        Character grade = '9';
        InvalidAssessmentTypeException exception1 = assertThrows(InvalidAssessmentTypeException.class,
                () -> ago.evaluateStudent(student, course, grade)
        );

        assertEquals(InvalidAssessmentTypeException.class, exception1.getClass());
        Character grade2 = 'c'; //lubatut ainult a - arvestatud m - mittearvestatud
        InvalidAssessmentTypeException exception2 = assertThrows(InvalidAssessmentTypeException.class,
                () -> ago.evaluateStudent(student, course2, grade2)
        );
        assertEquals(InvalidAssessmentTypeException.class, exception2.getClass());

    }
}
