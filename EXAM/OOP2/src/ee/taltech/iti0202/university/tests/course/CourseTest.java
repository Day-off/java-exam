package ee.taltech.iti0202.university.tests.course;

import ee.taltech.iti0202.university.course.Course;
import ee.taltech.iti0202.university.course.CourseType;
import ee.taltech.iti0202.university.course.TypeOfPassing;
import ee.taltech.iti0202.university.exeptions.Student.StudentAlreadyInThisCourseException;
import ee.taltech.iti0202.university.exeptions.Student.StudentToYoungOrOldException;
import ee.taltech.iti0202.university.exeptions.Teacher.TeacherAlreadyManageThisCourseException;
import ee.taltech.iti0202.university.people.Student;
import ee.taltech.iti0202.university.people.Teacher;
import org.junit.Before;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CourseTest {

    private Teacher teacher;
    private Student student;
    private static final String TEACHER_NAME = "Mari Pirn";
    private static final String STUDENT_NAME = "Kati";
    private Course correctCourse;

    @Before
    public void setup() throws TeacherAlreadyManageThisCourseException, StudentToYoungOrOldException {
        teacher = new Teacher(TEACHER_NAME);
        student = new Student(STUDENT_NAME, 19);
        final String ACTUAL_COURSE_NAME = "iti0202";
        final int ACTUAL_CREDIT_POINTS = 5;
        final TypeOfPassing ACTUAL_PASS_TYPE = TypeOfPassing.EXAM;
        final CourseType ACTUAL_TYPE = CourseType.GENERAL;
        correctCourse = new Course(ACTUAL_COURSE_NAME, teacher,
                ACTUAL_CREDIT_POINTS, ACTUAL_PASS_TYPE, ACTUAL_TYPE);
    }

    @Test
    public void createNormalCourse() {
        final String EXPECTED_NAME = "iti0202";
        assertEquals(EXPECTED_NAME, correctCourse.getName());
        assertEquals(TypeOfPassing.EXAM, correctCourse.getPassingType());
        assertEquals(CourseType.GENERAL, correctCourse.getType());
    }

    @Test
    public void createInvalidCourse() {
        int ACTUAL_CREDIT_POINTS = 5;
        final TypeOfPassing ACTUAL_PASS_TYPE = TypeOfPassing.EXAM;
        final CourseType ACTUAL_TYPE = CourseType.GENERAL;

        //Illegal name
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> new Course("", teacher,
                        ACTUAL_CREDIT_POINTS, ACTUAL_PASS_TYPE, ACTUAL_TYPE)
        );

        assertEquals("Invalid info.", exception.getMessage());

        //Illegal eap
        int ACTUAL_CREDIT_POINTS_1 = 0;
        IllegalArgumentException exception1 = assertThrows(IllegalArgumentException.class,
                () -> new Course("", teacher,
                        ACTUAL_CREDIT_POINTS_1, ACTUAL_PASS_TYPE, ACTUAL_TYPE)
        );

        assertEquals("Invalid info.", exception1.getMessage());
        //Illegal name
        IllegalArgumentException exception2 = assertThrows(IllegalArgumentException.class,
                () -> new Course("", teacher,
                        ACTUAL_CREDIT_POINTS, null, null)
        );

        assertEquals("Invalid info.", exception2.getMessage());

    }

    @Test
    public void setDecAmount() {
        correctCourse.setDeclaretionAmount();
        correctCourse.setDeclaretionAmount();
        correctCourse.setDeclaretionAmount();
        assertEquals(3, correctCourse.getDeclaretionAmount());
    }

    @Test
    public void addExistTeacher() {
        TeacherAlreadyManageThisCourseException excep = assertThrows(TeacherAlreadyManageThisCourseException.class,
                () -> correctCourse.addTeacher(teacher)
        );
        assertEquals("Teacher already manage this course", excep.getMessage());

    }

    @Test
    public void addExistStudent() throws StudentAlreadyInThisCourseException {
        correctCourse.addStudent(student);
        StudentAlreadyInThisCourseException excep = assertThrows(StudentAlreadyInThisCourseException.class, () -> {
                    correctCourse.addStudent(student); //the same student
                }
        );
        assertEquals(StudentAlreadyInThisCourseException.class, excep.getClass());

    }

    @Test
    public void removeStudent() throws StudentAlreadyInThisCourseException {
        correctCourse.addStudent(student);
        assertEquals(1, correctCourse.getStudents().size());

        correctCourse.removeStudent(student);
        assertEquals(0, correctCourse.getStudents().size());
        assertEquals(0, student.getAllCourses().size());
    }

}