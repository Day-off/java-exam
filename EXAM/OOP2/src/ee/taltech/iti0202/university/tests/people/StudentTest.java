package ee.taltech.iti0202.university.tests.people;

import ee.taltech.iti0202.university.University;
import ee.taltech.iti0202.university.course.Course;
import ee.taltech.iti0202.university.course.CourseType;
import ee.taltech.iti0202.university.course.TypeOfPassing;
import ee.taltech.iti0202.university.exeptions.Course.CourseAlreadyExistException;
import ee.taltech.iti0202.university.exeptions.Programme.InvalidProgrammException;
import ee.taltech.iti0202.university.exeptions.Student.StudentToYoungOrOldException;
import ee.taltech.iti0202.university.exeptions.Teacher.InvalidCourseException;
import ee.taltech.iti0202.university.exeptions.Teacher.TeacherAlreadyManageThisCourseException;
import ee.taltech.iti0202.university.exeptions.grade.InvalidAssessmentTypeException;
import ee.taltech.iti0202.university.grade.Grade;
import ee.taltech.iti0202.university.people.Student;
import ee.taltech.iti0202.university.people.Teacher;
import ee.taltech.iti0202.university.studyprogramm.StudyProgramme;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class StudentTest {

    private final Teacher ago = new Teacher("Ago");
    private final StudyProgramme inform = new StudyProgramme("Inform", 10);
    private final University uni = new University("TTU");
    private final Student kati = new Student("Kati Liis", 23);
    private final Course java = new Course("JAVA", ago, 6, TypeOfPassing.EXAM, CourseType.GENERAL);
    private final Course python = new Course("PYTHON", ago, 3, TypeOfPassing.PASS_FALL_ASSESSMENT, CourseType.GENERAL);


    public StudentTest() throws StudentToYoungOrOldException, TeacherAlreadyManageThisCourseException {
    }

    @Test
    public void createInvalidStudent() {
        StudentToYoungOrOldException exception1 = assertThrows(StudentToYoungOrOldException.class, () -> {
                    new Student("Mark", 109);
                }
        );
        assertEquals(StudentToYoungOrOldException.class, exception1.getClass());

        StudentToYoungOrOldException exception12 = assertThrows(StudentToYoungOrOldException.class, () -> {
                    new Student("Paul", 10);
                }
        );
        assertEquals(StudentToYoungOrOldException.class, exception12.getClass());

    }

    @Test
    public void changeAgeYounger() {
        IllegalArgumentException exception12 = assertThrows(IllegalArgumentException.class, () -> {
                    kati.setAge(5);
                }
        );
        assertEquals(IllegalArgumentException.class, exception12.getClass());
    }

    @Test
    public void invalidProgramme() {
        InvalidProgrammException exception12 = assertThrows(InvalidProgrammException.class,
                () -> kati.setCurrentProgram(inform)
        );
        assertEquals(InvalidProgrammException.class, exception12.getClass());

    }

    @Test
    public void getPassedAndUnPassedCourses() throws InvalidAssessmentTypeException, InvalidCourseException {
        kati.addCourse(java, new Grade(kati, java));
        kati.addCourse(python, new Grade(kati, python));
        ago.evaluateStudent(kati, java, '4');
        assertEquals(java, kati.getPassedCourses().get(0));
        assertEquals(1, kati.getPassedCourses().size());

        assertEquals(0, kati.getNotPassedCourses().size());
    }

    @Test
    public void kkh() throws InvalidProgrammException, CourseAlreadyExistException {
        kati.addCourse(java, new Grade(kati, java));
        kati.addCourse(python, new Grade(kati, python));
        kati.setCurrentUniversity(uni);
        uni.addStudyProgramme(inform);
        kati.setCurrentProgram(inform);
        kati.getAllCourses().get(java).setGrade('5');
        kati.getAllCourses().get(java).setPassed(true);
        kati.getAllCourses().get(python).setGrade('4');
        kati.getAllCourses().get(python).setPassed(true);
        assertEquals(4.667, Double.valueOf(kati.getKkh()));
    }

}
