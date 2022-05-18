package ee.taltech.iti0202.university;

import ee.taltech.iti0202.university.course.Course;
import ee.taltech.iti0202.university.exeptions.Course.CourseAlreadyExistException;
import ee.taltech.iti0202.university.exeptions.Programme.InvalidProgrammException;
import ee.taltech.iti0202.university.exeptions.Student.StudenAlreadyStudingUniversityException;
import ee.taltech.iti0202.university.exeptions.Student.StudentAlreadyInThisCourseException;
import ee.taltech.iti0202.university.exeptions.university.InvalidStudentOrCourseException;
import ee.taltech.iti0202.university.grade.Grade;
import ee.taltech.iti0202.university.people.Student;
import ee.taltech.iti0202.university.people.Teacher;
import ee.taltech.iti0202.university.studyprogramm.StudyProgramme;

import java.util.ArrayList;
import java.util.List;

public class University {

    private final String name;
    private final List<Course> allCourses = new ArrayList<>();
    private final List<Teacher> allTeachers = new ArrayList<>();
    private final List<Student> allStudents = new ArrayList<>();
    private final List<Student> allActiveStudents = new ArrayList<>();
    private final List<StudyProgramme> studyProgrammes = new ArrayList<>();
//    private HashMap<StudyProgramme, List<Student>> studentsByProgrammes = new HashMap<>();


    public University(String name) {
        this.name = name;
    }

    /*
    ADD OBJECTS TO UNI
     */

    public void addCourse(Course course) throws CourseAlreadyExistException {
        if (!allCourses.contains(course) && course.getUniversity() == null) {
            allCourses.add(course);
            course.setUniversity(this);
        } else {
            throw new CourseAlreadyExistException();
        }
    }

    public void addTeacher(Teacher teacher) {
        if (!allTeachers.contains(teacher)) {
            allTeachers.add(teacher);
        }
    }

    public void registerStudent(Student student) throws StudenAlreadyStudingUniversityException {
        if (!allStudents.contains(student) && student.getCurrentUniversity() == null) {
            allStudents.add(student);
            student.setCurrentUniversity(this);
        } else {
            throw new StudenAlreadyStudingUniversityException();
        }
        sortStudents();
    }

    public void addStudyProgramm(StudyProgramme programme) throws CourseAlreadyExistException {
        if (!studyProgrammes.contains(programme)) {
            studyProgrammes.add(programme);
        }
        for (Course c : programme.getAllCourses()) {
            if (!allCourses.contains(c)) {
                try {
                    addCourse(c);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }


    /*
    REMOVE OBJECTS FROM UNI
     */

    public void removeCourse(Course course) {
        allCourses.remove(course);
        course.removeUniversity();
    }

    public void removeTeacher(Teacher teacher) {
        allTeachers.remove(teacher);
    }

    public void removeStudent(Student student) {
        for (Course course : student.getAllCourses()) {
            course.removeStudent(student);
        }
        student.getAllCourses().clear();
        student.getNotPassedCourses().clear();
        student.removeCurrentProgram();
        allStudents.remove(student);
        student.removeCurrentUniversity();
    }

    public void removeStudyProgramm(StudyProgramme programme) {
        studyProgrammes.remove(programme);
    }

    /*
    GETTERS
     */

    public String getName() {
        return name;
    }

    public List<Course> getAllCourses() {
        return allCourses;
    }

    public List<Student> getAllStudents() {
        return allStudents;
    }

    public List<StudyProgramme> getStudyProgrammes() {
        return studyProgrammes;
    }

    public List<Teacher> getAllTeachers() {
        return allTeachers;
    }

    public List<Student> getAllActiveStudents() {
        sortStudents();
        return allActiveStudents;
    }

    /*
    MAIN METHODS
     */

    public void sortStudents() {
        for (Student student : allStudents) {
            if (student.getNotPassedCourses().size() != 0) {
                if (!allActiveStudents.contains(student)) {
                    allActiveStudents.add(student);
                }
            } else {
                allActiveStudents.remove(student);
            }
        }
    }

    public void registerOnCourse(Student student, Course course) throws InvalidStudentOrCourseException, StudentAlreadyInThisCourseException, StudentAlreadyInThisCourseException {
        if (!allStudents.contains(student) || !allCourses.contains(course)) {
            throw new InvalidStudentOrCourseException();
        } else {
            course.addStudent(student);
            student.addCourse(course, new Grade(student, course));
        }
    }

    public void setStudentToProgramme(Student student, StudyProgramme studyProgramme) throws InvalidProgrammException {
        if (!studyProgrammes.contains(studyProgramme)) {
            throw new IllegalArgumentException("Not such programme");
        } else {
            student.setCurrentProgram(studyProgramme);
        }
    }

    @Override
    public String toString() {
        return name;
    }
}
