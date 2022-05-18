package ee.taltech.iti0202.university.people;

import ee.taltech.iti0202.university.University;
import ee.taltech.iti0202.university.course.Course;
import ee.taltech.iti0202.university.exeptions.Programme.InvalidProgrammException;
import ee.taltech.iti0202.university.exeptions.Student.StudentToYoungOrOldException;
import ee.taltech.iti0202.university.exeptions.grade.InvalidAssessmentTypeException;
import ee.taltech.iti0202.university.grade.Grade;
import ee.taltech.iti0202.university.studyprogramm.StudyProgramme;

import java.util.*;
import java.util.stream.Collectors;

public class Student {

    private String name;
    private int age;
    private University currentUniversity;
    private StudyProgramme currentProgram;

    private final HashMap<Course, Grade> allCourses = new HashMap<>();

    public Student(String name, int age) throws StudentToYoungOrOldException {
        this.name = name;
        if (age <= 17 || age > 100) {
            throw new StudentToYoungOrOldException();
        }
        this.age = age;
    }

    /*
    SETTERS
     */

    public void setCurrentProgram(StudyProgramme currentProgram) throws InvalidProgrammException {
        if (currentUniversity != null && currentUniversity.getStudyProgrammes().contains(currentProgram)) {
            this.currentProgram = currentProgram;
        } else {
            throw new InvalidProgrammException();
        }
    }

    public void setCurrentUniversity(University currentUniversity) {
        this.currentUniversity = currentUniversity;
    }

    public void removeCurrentUniversity() {
        currentProgram = null;
    }

    public void removeCurrentProgram() {
        currentUniversity = null;
    }

    /*
    ADD
     */
    public void addCourse(Course course, Grade grade) {
        allCourses.put(course, grade);
//        sortCourses();
    }

    /*
    GETTERS
     */

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public Set<Course> getAllCourses() {
        return allCourses.keySet();
    }

    public University getCurrentUniversity() {
        return currentUniversity;
    }

    public StudyProgramme getCurrentProgram() {
        return currentProgram;
    }

    public List<Course> getPassedCourses() {
        return new ArrayList<Grade>(allCourses.values())
                .stream().filter(Grade::getIsPassed)
                .map(Grade::getCourse).collect(Collectors.toList());
    }

    public List<Course> getNotPassedCourses() {
        return new ArrayList<Grade>(allCourses.values())
                .stream().filter(g -> !g.getIsPassed())
                .map(Grade::getCourse).collect(Collectors.toList());
    }

    /*
    MAIN METHODS
     */

    public void changeGradeExam(Course course, Character grade) {
        Grade courseGrade = allCourses.get(course);
        courseGrade.setGrade(grade);
        if (Character.isDigit(grade)) {
            courseGrade.setPassed(Integer.parseInt(String.valueOf(grade)) >= 1);
        }
    }

    public void changeGradePassFail(Course course, Character grade) throws InvalidAssessmentTypeException {
        Grade courseGrade = allCourses.get(course);
        courseGrade.setGrade(grade);
        if (grade.equals('a')) {
            courseGrade.setPassed(true);
        } else if (grade.equals('m')) {
            courseGrade.setPassed(false);
        } else {
            throw new InvalidAssessmentTypeException();
        }
    }

    public float getStudyProgrammeProgress() {
        int programmeAmount = getCurrentProgram().getRequiredEapAmount();
        float studentPassedAmount = 0;
        for (Course c : getPassedCourses()) {
            if (getCurrentProgram().getAllCourses().contains(c)) studentPassedAmount += 1;
        }
        return studentPassedAmount * 100 / programmeAmount;
    }

    @Override
    public String toString() {
        return "name: '" + name + '\'' +
                ", age: " + age +
                ", currentUniversity: " + currentUniversity +
                ", currentProgram: " + currentProgram +
                "\n allCourses: " + allCourses +
                "\n passedCourses: " + getPassedCourses() +
                "\n notPassedCourses:" + getNotPassedCourses() + "\n";
    }
}
