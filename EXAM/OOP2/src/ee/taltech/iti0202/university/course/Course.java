package ee.taltech.iti0202.university.course;

import ee.taltech.iti0202.university.University;
import ee.taltech.iti0202.university.exeptions.Student.StudentAlreadyInThisCourseException;
import ee.taltech.iti0202.university.exeptions.Teacher.TeacherAlreadyManageThisCourseException;
import ee.taltech.iti0202.university.people.Student;
import ee.taltech.iti0202.university.people.Teacher;

import java.util.ArrayList;
import java.util.List;

public class Course {

    private final String name;
    private final List<Teacher> teachers = new ArrayList<>();
    private int eap;
    private final List<Student> students = new ArrayList<>();
    private final TypeOfPassing passingType;
    private CourseType type;
    private University university;
    private int declaretionAmount;

    public Course(String name, Teacher teacher, int eap, TypeOfPassing passingType,
                  CourseType type) throws TeacherAlreadyManageThisCourseException {
        if (name.isEmpty() || eap < 1 || type == null) {
            throw new IllegalArgumentException();
        } else {
            this.name = name;
            this.eap = eap;
            this.passingType = passingType;
            addTeacher(teacher);
            setCourseType(type);
        }
    }

    /*
    SETTERS
     */

    public void setDeclaretionAmount() {
        this.declaretionAmount += 1;
    }

    public void setCourseType(CourseType type) {
        if (type == CourseType.SEMINAR) {
            eap = 1;
        }
        this.type = type;
    }

    public void addTeacher(Teacher teacher) throws TeacherAlreadyManageThisCourseException {
        if (!teachers.contains(teacher)) {
            teachers.add(teacher);
            teacher.addCourse(this);
        } else {
            throw new TeacherAlreadyManageThisCourseException();
        }
    }

    public void addStudent(Student student) throws StudentAlreadyInThisCourseException {
        if (!students.contains(student)) {
            students.add(student);
        } else {
            throw new StudentAlreadyInThisCourseException();
        }
    }

    public void setUniversity(University university) {
        this.university = university;
    }

    /*
    REMOVE
     */

    public void removeTeacher(Teacher teacher) {
        teachers.remove(teacher);
        teacher.removeCourse(this);
    }

    public void removeStudent(Student student) {
        students.remove(student);
    }

    public void removeUniversity() {
        university = null;
    }

    /*
    GETTERS
     */

    public int getDeclaretionAmount() {
        return declaretionAmount;
    }

    public CourseType getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public int getEap() {
        return eap;
    }

    public List<Student> getStudents() {
        return students;
    }

    public List<Teacher> getTeachers() {
        return teachers;
    }

    public University getUniversity() {
        return university;
    }

    public TypeOfPassing getPassingType() {
        return passingType;
    }

    @Override
    public String toString() {
        return name + ", " + eap + " eap " + type + ", DecTim:" + declaretionAmount;
    }
}
