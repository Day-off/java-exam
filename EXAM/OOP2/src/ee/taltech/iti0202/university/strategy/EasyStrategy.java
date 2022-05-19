package ee.taltech.iti0202.university.strategy;

import ee.taltech.iti0202.university.course.Course;
import ee.taltech.iti0202.university.course.CourseType;
import ee.taltech.iti0202.university.declaration.Declaration;
import ee.taltech.iti0202.university.people.Student;
import ee.taltech.iti0202.university.studyprogramm.StudyProgramme;

import java.util.Comparator;
import java.util.List;

public class EasyStrategy extends Strategy {

    public EasyStrategy() {
    }

    /***
     * sort courses by eap amount
     * count min requarid eap from needed programme
     * if student didn't declare this course yet add this course to declaration
     * @param programme
     * @param student
     */
    @Override
    public void createCourseForDec(StudyProgramme programme, Student student) {
        List<Course> sortCourse = programme.getProgrammeCourses();
        sortCourse.sort(Comparator.comparing(Course::getEap));
        int countMinEap = 0;
        for (Course course : sortCourse) {
            if (!student.getPassedCourses().contains(course)
                    && countMinEap < Declaration.getMinEap()
                    && course.getType() != CourseType.DIPLOMA
                    && course.getType() != CourseType.INTERNSHIP) {
                if (!student.getNotPassedCourses().contains(course)) {
                    countMinEap += course.getEap();
                    coursesDec.add(course);
                }
            } else if (countMinEap >= Declaration.getMinEap()) {
                break;
            }
        }
        super.createCourseForDec(programme, student);
    }
}
