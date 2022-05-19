package ee.taltech.iti0202.university.tests;

import ee.taltech.iti0202.university.University;
import ee.taltech.iti0202.university.course.Course;
import ee.taltech.iti0202.university.course.CourseType;
import ee.taltech.iti0202.university.course.TypeOfPassing;
import ee.taltech.iti0202.university.exeptions.Course.CourseAlreadyExistException;
import ee.taltech.iti0202.university.exeptions.Programme.InvalidProgrammException;
import ee.taltech.iti0202.university.exeptions.Student.StudenAlreadyStudingUniversityException;
import ee.taltech.iti0202.university.exeptions.Student.StudentToYoungOrOldException;
import ee.taltech.iti0202.university.exeptions.Teacher.InvalidCourseException;
import ee.taltech.iti0202.university.exeptions.Teacher.TeacherAlreadyManageThisCourseException;
import ee.taltech.iti0202.university.exeptions.grade.InvalidAssessmentTypeException;
import ee.taltech.iti0202.university.people.Student;
import ee.taltech.iti0202.university.people.Teacher;
import ee.taltech.iti0202.university.strategy.DifModuleStartegy;
import ee.taltech.iti0202.university.strategy.EasyStrategy;
import ee.taltech.iti0202.university.strategy.PopularStrategy;
import ee.taltech.iti0202.university.strategy.Strategy;
import ee.taltech.iti0202.university.studyprogramm.Module;
import ee.taltech.iti0202.university.studyprogramm.StudyProgramme;
import org.junit.Before;
import org.junit.Test;

import javax.management.InvalidAttributeValueException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class MainFunctionTest {

    private final University u1 = new University("TTU");
    private final University u2 = new University("EKA");

    private final Student mari = new Student("Mari", 19);
    private final Student kati = new Student("Kati", 29);
    private final Student lili = new Student("Lili", 39);
    private final Student jaan = new Student("Jaan", 23);
    private final Student marko = new Student("Marko", 56);

    private final Teacher ago = new Teacher("Ago Luberg");
    private final Teacher piret = new Teacher("Piret Puusepp");
    private final Teacher gert = new Teacher("Gert Kanter");

    private final Course java = new Course("JAVA", ago, 6, TypeOfPassing.EXAM, CourseType.GENERAL);
    private final Course math = new Course("MATH", piret, 6, TypeOfPassing.EXAM, CourseType.GENERAL);
    private final Course python = new Course("PYTHON", ago, 6, TypeOfPassing.EXAM, CourseType.INTERNSHIP);
    private final Course robots = new Course("ROBOTS", gert, 6,
            TypeOfPassing.PASS_FALL_ASSESSMENT, CourseType.DIPLOMA);
    private final Course kunstHistory = new Course("KUNST HISTORY", ago, 9,
            TypeOfPassing.PASS_FALL_ASSESSMENT, CourseType.SEMINAR);
    private final Course machineLearning = new Course("MACHINE LEARNING", gert, 12,
            TypeOfPassing.PASS_FALL_ASSESSMENT, CourseType.GENERAL);
    private final Course oilPainting = new Course("OIL PAINTING", ago, 9,
            TypeOfPassing.PASS_FALL_ASSESSMENT, CourseType.GENERAL);
    private final Course justPainting = new Course("JUST PAINTING", ago, 9,
            TypeOfPassing.PASS_FALL_ASSESSMENT, CourseType.GENERAL);

    //for test such small num
    private final StudyProgramme informaatika = new StudyProgramme("Informaatika", 18);
    private final StudyProgramme devOps = new StudyProgramme("Devops", 20);
    private final StudyProgramme design = new StudyProgramme("Devops design", 10);

    private final Strategy easy = new EasyStrategy();
    private final Strategy mod = new DifModuleStartegy();
    private final Strategy pop = new PopularStrategy();

    public MainFunctionTest() throws StudentToYoungOrOldException, TeacherAlreadyManageThisCourseException {
    }

    @Before
    public void setup() throws CourseAlreadyExistException,
            StudenAlreadyStudingUniversityException, InvalidProgrammException {
        //add course to programme
        informaatika.addCourse(java, Module.PRAKTIKALINE_INF);
        informaatika.addCourse(python, Module.INFORMAATIIKA_ALUSED);
        informaatika.addCourse(robots, Module.ULDOPE);
        informaatika.addCourse(math, Module.VABAOPE);
        informaatika.addCourse(machineLearning, Module.BAKALAUREUSETOO);

        //programmes can share courses
        devOps.addCourse(robots, Module.ULDOPE);
        devOps.addCourse(java, Module.PRAKTIKALINE_INF);
        devOps.addCourse(python, Module.VABAOPE);
        devOps.addCourse(math, Module.MATEMAATIKA_JA_LOODUSTEADUSED);
        devOps.addCourse(machineLearning, Module.VABAOPE);

        design.addCourse(kunstHistory, Module.ULDOPE);
        design.addCourse(oilPainting, Module.VABAOPE);
        design.addCourse(justPainting, Module.VABAOPE);

        //correct adding programmes
        u1.addStudyProgramme(informaatika);
        u1.addStudyProgramme(devOps);
        u2.addStudyProgramme(design);

        //correct register students
        u1.registerStudent(mari);
        u1.registerStudent(lili);
        u1.registerStudent(kati);
        u2.registerStudent(jaan);
        u2.registerStudent(marko);

        //correct add student to programme
        u1.setStudentToProgramme(mari, informaatika);
        u1.setStudentToProgramme(kati, devOps);
        u1.setStudentToProgramme(lili, devOps);
        u2.setStudentToProgramme(jaan, design);
    }

    /*
    UNIVERSITY METHODS
     */
    @Test
    public void activeStudents() {
        mari.createDeclaration(mod);
        mari.submittDeclaretion();
        lili.createDeclaration(easy);
        lili.submittDeclaretion();

        u1.sortStudents();

        assertEquals(2, u1.getAllActiveStudents().size());
        assertEquals(3, u1.getAllStudents().size());


    }

    /*
    EVALUATE STUDENTS
     */
    @Test
    public void extraCoursesIsVabaAndProgrammeProgress() throws InvalidAssessmentTypeException,
            InvalidCourseException, InvalidAttributeValueException {
        mari.createDeclaration(mod);
        mari.submittDeclaretion();
        lili.createDeclaration(easy);
        lili.submittDeclaretion();
        kati.createDeclaration(pop);
        kati.submittDeclaretion();

        //courses go to Vaba if the requirement for passing the EAP is exceeded
        piret.evaluateStudent(kati, math, '5');
        ago.evaluateStudent(kati, java, '3');
        gert.evaluateStudent(kati, machineLearning, 'a');

        float progress = kati.getStudyProgrammeProgress();
        assertTrue(progress > 100);
        assertTrue(kati.getAllCourses().get(math).isCourseIsVaba());
        assertEquals(3, kati.getPassedCourses().size());

        //after completing the program by 100%, you can take an internship
        kati.specificCourseApplication(python);
        assertTrue(kati.getAllCourses().containsKey(python));

        //not 100 programme
        InvalidAttributeValueException exception = assertThrows(InvalidAttributeValueException.class,
                () -> mari.specificCourseApplication(python)
        );
        assertEquals(InvalidAttributeValueException.class, exception.getClass());
    }


    /*
    DECLARATION AND STRATEGY TEST
     */
    @Test
    public void easyStrategyTest() {
        jaan.createDeclaration(easy);
        assertNotNull(jaan.getDeclaration());
        jaan.submittDeclaretion();
        assertTrue(jaan.getDeclaration().getIsSubmitted());
        assertEquals(3, jaan.getAllCourses().size());
    }

    /**
     * Bc robot and python you can't declare,
     * form 5 programme modules we have 2 modules
     * where we can't declare any course,
     * so we have only free courses from available 3 modules.
     */
    @Test
    public void modStrategyTest() {
        mari.createDeclaration(mod);
        assertNotNull(mari.getDeclaration());
        mari.submittDeclaretion();
        assertTrue(mari.getDeclaration().getIsSubmitted());
        assertEquals(3, mari.getAllCourses().size());

        //enable make new declaration while student have active course
        IllegalArgumentException exception1 = assertThrows(IllegalArgumentException.class, () -> {
                    mari.createDeclaration(easy); //already there
                }
        );
        assertEquals(IllegalArgumentException.class, exception1.getClass());


    }

    @Test
    public void popStrategyTest() {
        mari.createDeclaration(mod);
        mari.submittDeclaretion();
        lili.createDeclaration(easy);
        lili.submittDeclaretion();

        kati.createDeclaration(pop);
        assertNotNull(kati.getDeclaration());
        kati.submittDeclaretion();
        assertTrue(kati.getDeclaration().getIsSubmitted());
        // mari+kati+lili = 3 most declare time
        assertEquals(3, kati.getNotPassedCourses().get(0).getDeclaretionAmount());
    }


    @Test
    public void invalidStrategy() {
        jaan.createDeclaration(mod);
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                jaan::submittDeclaretion //there is not enough sap as we take courses only
                // by modules and there are few of them
        );
        assertEquals(IllegalArgumentException.class, exception.getClass());
    }


    /*
    Invalid Inputs Tests
     */
    @Test
    public void invalidSettingToProgramme() {
        InvalidProgrammException exception = assertThrows(InvalidProgrammException.class, () -> {
                    u1.setStudentToProgramme(mari, informaatika); //already there
                }
        );
        assertEquals(InvalidProgrammException.class, exception.getClass());

        IllegalArgumentException exception1 = assertThrows(IllegalArgumentException.class, () -> {
                    u2.setStudentToProgramme(marko, informaatika); //already there
                }
        );
        assertEquals(IllegalArgumentException.class, exception1.getClass());
    }

    /**
     * University can't share one course
     */
    @Test
    public void addInvalidCourse() {
        CourseAlreadyExistException exception1 = assertThrows(CourseAlreadyExistException.class, () -> {
                    u1.addCourse(kunstHistory); //course from EKA
                }
        );
        assertEquals(CourseAlreadyExistException.class, exception1.getClass());
        CourseAlreadyExistException exception2 = assertThrows(CourseAlreadyExistException.class, () -> {
                    u1.addCourse(python); //course already in TTU
                }
        );
        assertEquals(CourseAlreadyExistException.class, exception2.getClass());
    }

    @Test
    public void addInvalidStudent() {
        StudenAlreadyStudingUniversityException exception = assertThrows(StudenAlreadyStudingUniversityException.class,
                () -> {
                    u1.registerStudent(kati); //student already in TTU
                }
        );
        assertEquals(StudenAlreadyStudingUniversityException.class, exception.getClass());

        StudenAlreadyStudingUniversityException exception1 = assertThrows(StudenAlreadyStudingUniversityException.class,
                () -> {
                    u1.registerStudent(jaan); //student from EKA
                }
        );
        assertEquals(StudenAlreadyStudingUniversityException.class, exception1.getClass());
    }


}
