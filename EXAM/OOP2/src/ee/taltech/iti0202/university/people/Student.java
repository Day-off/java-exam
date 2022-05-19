package ee.taltech.iti0202.university.people;

import ee.taltech.iti0202.university.University;
import ee.taltech.iti0202.university.course.Course;
import ee.taltech.iti0202.university.course.CourseType;
import ee.taltech.iti0202.university.declaration.Declaration;
import ee.taltech.iti0202.university.exeptions.Programme.InvalidProgrammException;
import ee.taltech.iti0202.university.exeptions.Student.StudentToYoungOrOldException;
import ee.taltech.iti0202.university.exeptions.grade.InvalidAssessmentTypeException;
import ee.taltech.iti0202.university.grade.Grade;
import ee.taltech.iti0202.university.strategy.Strategy;
import ee.taltech.iti0202.university.studyprogramm.StudyProgramme;

import javax.management.InvalidAttributeValueException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class Student {

    private final String name;
    private int age;
    private University currentUniversity;
    private StudyProgramme currentProgram;
    private float currentEapAmount;
    private String kkh;
    private DecimalFormat dfSharp = new DecimalFormat("#.##");

    private Declaration declaration;

    private final HashMap<Course, Grade> allCourses = new HashMap<>();
//    private final HashMap<Module, List<Course>> sortedByModuleCourses = new HashMap<>();

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

    public void setAge(int age) {
        if (this.age >= age) {
            throw new IllegalArgumentException("Invalid age.");
        } else {
            this.age = age;
        }

    }

    public void setCurrentProgram(StudyProgramme currentProgram) throws InvalidProgrammException {
        if (currentUniversity == null || !currentUniversity.getStudyProgrammes().contains(currentProgram)
                || getCurrentProgram() == currentProgram) {
            throw new InvalidProgrammException();
        } else {
            this.currentProgram = currentProgram;
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
    }

    /*
    GETTERS
     */

    public String getKkh() {
        setKkh();
        return kkh;
    }

    public double getKkhDob() {
        return Double.parseDouble(dfSharp.format(Double.parseDouble(getKkh())));
    }

    public Declaration getDeclaration() {
        return declaration;
    }

    public float getCurrentEapAmount() {
        getStudyProgrammeProgress();
        return currentEapAmount;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public HashMap<Course, Grade> getAllCourses() {
        return allCourses;
    }

    public University getCurrentUniversity() {
        return currentUniversity;
    }

    public StudyProgramme getCurrentProgram() {
        return currentProgram;
    }


    /**
     * filter passed courses
     *
     * @return
     */
    public List<Course> getPassedCourses() {
        return new ArrayList<Grade>(allCourses.values())
                .stream().filter(Grade::getIsPassed)
                .map(Grade::getCourse).collect(Collectors.toList());
    }

    /**
     * filter courses didn't pass
     *
     * @return
     */
    public List<Course> getNotPassedCourses() {
        return new ArrayList<Grade>(allCourses.values())
                .stream().filter(c -> !c.getIsPassed() && c.getIsAssest())
                .map(Grade::getCourse).collect(Collectors.toList());
    }

    /*
    MAIN METHODS
     */

    /**
     * change grade for subjects exam system
     *
     * @param course
     * @param grade
     * @throws InvalidAssessmentTypeException
     */
    public void changeGradeExam(Course course, Character grade) {
        Grade courseGrade = allCourses.get(course);
        courseGrade.setGrade(grade);
        if (Character.isDigit(grade)) {
            courseGrade.setPassed(Integer.parseInt(String.valueOf(grade)) >= 1);
            courseGrade.isAssest(true);
        }
    }

    /**
     * change grade for subjects pass fail system
     *
     * @param course
     * @param grade
     * @throws InvalidAssessmentTypeException
     */
    public void changeGradePassFail(Course course, Character grade) throws InvalidAssessmentTypeException {
        Grade courseGrade = allCourses.get(course);
        courseGrade.setGrade(grade);
        if (grade.equals('a')) {
            courseGrade.setPassed(true);
            courseGrade.setGrade(grade);
            courseGrade.isAssest(true);
        } else if (grade.equals('m')) {
            courseGrade.setPassed(false);
            courseGrade.setGrade(grade);
            courseGrade.isAssest(true);
        } else {
            throw new InvalidAssessmentTypeException();
        }
    }

    /**
     * Count programme progress by eap
     *
     * @return
     */
    public float getStudyProgrammeProgress() {
        int programmeEapAmount = getCurrentProgram().getRequiredEapAmount();
        float studentPassedEapAmount = 0;
        for (Course c : getPassedCourses()) {
            if (getCurrentProgram().getProgrammeCourses().contains(c)) studentPassedEapAmount += c.getEap();
            if (studentPassedEapAmount > programmeEapAmount) {
                allCourses.get(c).setCourseIsVaba();
            }
        }
        currentEapAmount = studentPassedEapAmount;
        return studentPassedEapAmount * 100 / programmeEapAmount;
    }

    /***
     * create declaretion by strategy
     * @param strategy
     */
    public void createDeclaration(Strategy strategy) {
        if (this.getNotPassedCourses().size()
                + this.getPassedCourses().size() != this.getAllCourses().keySet().size()) {
            throw new IllegalArgumentException("You not finished your courses yet");
        }
        strategy.createCourseForDec(currentProgram, this);
        List<Course> finalCourses = List.copyOf(strategy.getCoursesDec());
        this.declaration = new Declaration(this, finalCourses);
        strategy.clearCoursesDec();
    }


    public void compleateDeclaretion() {
        if (declaration.getSumEap() <= Declaration.getMaxEap()
                && declaration.getSumEap() >= Declaration.getMinEap()) {
            declaration.setCompleted(true);
        } else {
            throw new IllegalArgumentException("Invalid eap amount");
        }
    }

    public void submittDeclaretion() {
        compleateDeclaretion();
        if (declaration.getIsCompleted()) {
            declaration.getCoursesForDeclaration()
                    .forEach(course -> allCourses.put(course, new Grade(this, course)));
            currentUniversity.addActiveStudent(this);
            declaration.setSumit(true);
            declaration.getCoursesForDeclaration().forEach(Course::setDeclaretionAmount);
        } else {
            throw new IllegalArgumentException("Invalid eap amount");
        }

    }


    public void specificCourseApplication(Course course) throws InvalidAttributeValueException {
        if (course.getUniversity() != currentUniversity) {
            throw new InvalidAttributeValueException("Course doesn't exist");
        }
        if (course.getType() != CourseType.DIPLOMA && course.getType() != CourseType.INTERNSHIP) {
            throw new InvalidAttributeValueException("It's not specific course you need declare it!");
        } else if (CourseType.DIPLOMA == course.getType()) {
            addCourse(course, new Grade(this, course));
        } else if (CourseType.INTERNSHIP == course.getType()
                && getCurrentEapAmount() >= getCurrentProgram().getRequiredEapAmount()) {
            addCourse(course, new Grade(this, course));
        } else {
            throw new InvalidAttributeValueException("Not enough progress!");
        }
    }

    public void setKkh() {
        double kkh = 0;
//        List<Grade> allGrades = new ArrayList<>(allCourses.values()).stream().filter(Grade::getIsPassed).toList();
        double eapAmount = getPassedCourses().stream().mapToDouble(Course::getEap).sum()
                + getNotPassedCourses().stream().mapToDouble(Course::getEap).sum();
        List<Course> graded = new ArrayList<>(getPassedCourses());
        graded.addAll(getNotPassedCourses());
        for (Course course : graded) {
            int courseEap = 0;
            int grade;
            if (Character.isDigit(allCourses.get(course).getGrade())) {
                grade = Integer.parseInt(String.valueOf(allCourses.get(course).getGrade()));
            } else {
                if (allCourses.get(course).getGrade().equals('a')) {
                    grade = 5;
                } else {
                    grade = 0;
                }
            }
            if (grade != 0) {
                courseEap = course.getEap();
            } else {
                courseEap = course.getEap() / 2;
            }
            double coursePre = (courseEap * 100) / eapAmount;
            double res = (coursePre * grade) / 100;
            kkh += res;
        }
        DecimalFormat df = new DecimalFormat("#.###");
        this.kkh = df.format(kkh);
    }

    @Override
    public String toString() {
        return "name: '" + name + '\''
                + ", age: " + age
                + ", currentUniversity: "
                + currentUniversity
                + ", currentProgram: " + currentProgram
                + "\n allCourses: " + allCourses
                + "\n passedCourses: " + getPassedCourses()
                + "\n notPassedCourses:" + getNotPassedCourses() + "\n";
    }
}
