package ee.taltech.iti0202.university;

import ee.taltech.iti0202.university.course.Course;
import ee.taltech.iti0202.university.exeptions.Course.CourseAlreadyExistException;
import ee.taltech.iti0202.university.exeptions.Programme.InvalidProgrammException;
import ee.taltech.iti0202.university.exeptions.Student.StudenAlreadyStudingUniversityException;
import ee.taltech.iti0202.university.people.Student;
import ee.taltech.iti0202.university.people.Teacher;
import ee.taltech.iti0202.university.studyprogramm.StudyProgramme;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class University {

    private final String name;
    private final List<Course> allCourses = new ArrayList<>();
    private final List<Teacher> allTeachers = new ArrayList<>();
    private final List<Student> allStudents = new ArrayList<>();
    private final List<Student> allActiveStudents = new ArrayList<>();
    private final List<StudyProgramme> studyProgrammes = new ArrayList<>();
    private List<Student> rankingStudentList = new ArrayList<>();

    private int[] scholarship;


    public University(String name) {
        this.name = name;
    }

    /*
    ADD OBJECTS TO UNI
     */

    public void addActiveStudent(Student student) {
        allActiveStudents.add(student);
    }

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

    public void addStudyProgramme(StudyProgramme programme) throws CourseAlreadyExistException {
        if (!studyProgrammes.contains(programme)) {
            studyProgrammes.add(programme);
        }
        for (Course c : programme.getProgrammeCourses()) {
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

    public void setScholarship(int amount, int money) {
        this.scholarship = new int[amount];
        Arrays.fill(scholarship, money);
    }

    public void giveScholar() {
        for (int i = 0; i + 1 < scholarship.length; i++) {
            allActiveStudents.get(i).setScholar(Array.getInt(scholarship, i));
            scholarship[i] = 0;
            allActiveStudents.get(i).isScholarship(true);
        }
    }

    public void removeCourse(Course course) {
        if (allCourses.contains(course)) {
            allCourses.remove(course);
            course.removeUniversity();
        }
    }

    public void removeTeacher(Teacher teacher) {
        allTeachers.remove(teacher);
    }

    public void removeStudent(Student student) {
        for (Course course : student.getAllCourses().keySet()) {
            course.removeStudent(student);
        }
        student.getAllCourses().clear();
        student.getNotPassedCourses().clear();
        student.removeCurrentProgram();
        allStudents.remove(student);
        student.removeCurrentUniversity();
        allCourses.forEach(course -> course.removeStudent(student));
    }

    public void removeStudyProgramm(StudyProgramme programme) {
        studyProgrammes.remove(programme);
    }

    /*
    GETTERS
     */

    public List<Student> getRankingStudentList() {
        return rankingStudentList;
    }

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
            if (student.getNotPassedCourses().size()
                    + student.getPassedCourses().size() != student.getAllCourses().keySet().size()) {
                if (!allActiveStudents.contains(student)) {
                    allActiveStudents.add(student);
                }
            } else {
                allActiveStudents.remove(student);
            }
        }
    }

    public void setStudentToProgramme(Student student, StudyProgramme studyProgramme) throws InvalidProgrammException {
        if (!studyProgrammes.contains(studyProgramme)) {
            throw new IllegalArgumentException("Not such programme");
        } else {
            student.setCurrentProgram(studyProgramme);
        }
    }

    public void setRankingStudentList() {
        rankingStudentList = new ArrayList<>(allStudents);
        rankingStudentList.sort(Comparator.comparing(Student::getKkhDob)
                .thenComparing(Student::getStudyProgrammeProgress)
                .thenComparing(Student::getCurrentEapAmount)
                .thenComparing(Student::getName).reversed());
    }

    @Override
    public String toString() {
        return name;
    }
}
