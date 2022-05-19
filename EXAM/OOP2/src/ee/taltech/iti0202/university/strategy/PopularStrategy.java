package ee.taltech.iti0202.university.strategy;

import ee.taltech.iti0202.university.course.Course;
import ee.taltech.iti0202.university.course.CourseType;
import ee.taltech.iti0202.university.declaration.Declaration;
import ee.taltech.iti0202.university.people.Student;
import ee.taltech.iti0202.university.studyprogramm.StudyProgramme;

import java.util.Comparator;
import java.util.List;

public class PopularStrategy extends Strategy {

    @Override
    public void createCourseForDec(StudyProgramme programme, Student student) {
        List<Course> sortCourse = programme.getProgrammeCourses();
        sortCourse.sort(Comparator.comparing(Course::getDeclaretionAmount).reversed());
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
            super.createCourseForDec(programme, student);
        }
    }
}
