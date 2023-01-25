package ee.taltech.iti0202.university;

import ee.taltech.iti0202.university.course.Course;
import ee.taltech.iti0202.university.course.CourseType;
import ee.taltech.iti0202.university.course.TypeOfPassing;
import ee.taltech.iti0202.university.exeptions.Course.CourseAlreadyExistException;
import ee.taltech.iti0202.university.exeptions.Programme.InvalidProgrammException;
import ee.taltech.iti0202.university.exeptions.Student.StudenAlreadyStudingUniversityException;
import ee.taltech.iti0202.university.exeptions.Student.StudentAlreadyInThisCourseException;
import ee.taltech.iti0202.university.exeptions.Student.StudentToYoungOrOldException;
import ee.taltech.iti0202.university.exeptions.Teacher.InvalidCourseException;
import ee.taltech.iti0202.university.exeptions.Teacher.TeacherAlreadyManageThisCourseException;
import ee.taltech.iti0202.university.exeptions.grade.InvalidAssessmentTypeException;
import ee.taltech.iti0202.university.exeptions.university.InvalidStudentOrCourseException;
import ee.taltech.iti0202.university.people.Student;
import ee.taltech.iti0202.university.people.Teacher;
import ee.taltech.iti0202.university.strategy.DifModuleStartegy;
import ee.taltech.iti0202.university.strategy.EasyStrategy;
import ee.taltech.iti0202.university.strategy.PopularStrategy;
import ee.taltech.iti0202.university.strategy.Strategy;
import ee.taltech.iti0202.university.studyprogramm.Module;
import ee.taltech.iti0202.university.studyprogramm.StudyProgramme;

import javax.management.InvalidAttributeValueException;

public class Main {

    public static void main(String[] args) throws StudentToYoungOrOldException, TeacherAlreadyManageThisCourseException, StudenAlreadyStudingUniversityException, CourseAlreadyExistException, StudentAlreadyInThisCourseException, InvalidStudentOrCourseException, InvalidAssessmentTypeException, InvalidCourseException, InvalidProgrammException, InvalidAttributeValueException {
        University u1 = new University("TTU");
        University u2 = new University("EKA");

        Student mari = new Student("Mari", 19);
        Student kati = new Student("Kati", 29);
        Student jaan = new Student("Jaan", 23);

        Teacher ago = new Teacher("Ago Luberg");
        Teacher piret = new Teacher("Piret Puusepp");

        Course java = new Course("JAVA", ago, 6, TypeOfPassing.EXAM, CourseType.GENERAL);
        Course math = new Course("MATH", piret, 6, TypeOfPassing.EXAM, CourseType.GENERAL);
        Course python = new Course("PYTHON", ago, 6, TypeOfPassing.EXAM, CourseType.INTERNSHIP);
        Course robots = new Course("ROBOTS", ago, 6, TypeOfPassing.PASS_FALL_ASSESSMENT, CourseType.DIPLOMA);
        Course kunstHistory = new Course("KUNST HISTORY", ago, 9, TypeOfPassing.PASS_FALL_ASSESSMENT, CourseType.SEMINAR);
        Course huh = new Course("huh", ago, 9, TypeOfPassing.PASS_FALL_ASSESSMENT, CourseType.GENERAL);
        Course psih = new Course("huh", ago, 9, TypeOfPassing.PASS_FALL_ASSESSMENT, CourseType.GENERAL);

        StudyProgramme informaatika = new StudyProgramme("Informaatika", 18);//for test such small num
        StudyProgramme devOps = new StudyProgramme("Devops", 12);
        StudyProgramme design = new StudyProgramme("Devops design", 175);

        Strategy easy = new EasyStrategy();
        Strategy mod = new DifModuleStartegy();
        Strategy pop = new PopularStrategy();

        informaatika.addCourse(java, Module.PRAKTIKALINE_INF);
        informaatika.addCourse(python, Module.INFORMAATIIKA_ALUSED);
        informaatika.addCourse(robots, Module.ULDOPE);
        informaatika.addCourse(math, Module.ULDOPE);
        informaatika.addCourse(huh, Module.VABAOPE);

        devOps.addCourse(robots, Module.ULDOPE);
        devOps.addCourse(java, Module.PRAKTIKALINE_INF);
        devOps.addCourse(python, Module.VABAOPE);
        devOps.addCourse(math, Module.MATEMAATIKA_JA_LOODUSTEADUSED);
        devOps.addCourse(huh, Module.VABAOPE);
        devOps.addCourse(psih, Module.MATEMAATIKA_JA_LOODUSTEADUSED);

        devOps.addCourse(kunstHistory, Module.VABAOPE);


        u1.addStudyProgramme(informaatika);
        u1.addStudyProgramme(devOps);
        u2.addStudyProgramme(design);

        u1.getStudyProgrammes().forEach(System.out::println);

        u1.registerStudent(mari);
        try {
            u2.registerStudent(mari);
        } catch (Exception e) {
            System.out.println(e);//Student already studying at any university
        }
        try {
            u1.registerStudent(mari);
        } catch (Exception e) {
            System.out.println(e);//Student already studying at any university
        }

        u1.registerStudent(kati);

        u1.registerStudent(jaan);

        System.out.println(u1.getAllStudents().size());//2
        System.out.println(u2.getAllStudents().size());//1


        mari.setCurrentProgram(informaatika);
        kati.setCurrentProgram(devOps);
        jaan.setCurrentProgram(devOps);

        System.out.println(u1.getAllCourses().size());//4
        System.out.println(u2.getAllCourses().size());//1

        System.out.println(java.getPassingType());//exam
        System.out.println(robots.getPassingType());//not exam

        u2.removeCourse(python);

        System.out.println(u2.getAllCourses().size());//1

        try {
            u2.addCourse(python);
        } catch (Exception e) {
            System.out.println(e);
        }

        System.out.println(u2.getAllCourses().size());//2

        jaan.createDeclaration(mod);
        jaan.submittDeclaretion();

        mari.createDeclaration(mod);
        mari.submittDeclaretion();

        kati.createDeclaration(pop);
        kati.submittDeclaretion();

        kati.specificCourseApplication(robots);
        kati.specificCourseApplication(python);


        System.out.println("Declaretion completed");

        u1.getAllStudents().forEach(System.out::println);
        u2.getAllStudents().forEach(System.out::println);
        System.out.println(" ");
        System.out.println(u1.getAllActiveStudents().size());
        System.out.println(" ");

        System.out.println(kati.getStudyProgrammeProgress());

        ago.evaluateStudent(kati, java, '4');
        piret.evaluateStudent(kati, math, '4');
        piret.evaluateStudent(mari, math, '4');
        ago.evaluateStudent(kati, robots, 'a');

        u1.getAllStudents().forEach(System.out::println);
        u2.getAllStudents().forEach(System.out::println);
        System.out.println(" ");
        System.out.println(u1.getAllActiveStudents().size());

        System.out.println(mari.getStudyProgrammeProgress());
        System.out.println(kati.getStudyProgrammeProgress());


    }
}
