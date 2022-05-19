package ee.taltech.iti0202.university.strategy;

import ee.taltech.iti0202.university.course.Course;
import ee.taltech.iti0202.university.course.CourseType;
import ee.taltech.iti0202.university.declaration.Declaration;
import ee.taltech.iti0202.university.people.Student;
import ee.taltech.iti0202.university.studyprogramm.Module;
import ee.taltech.iti0202.university.studyprogramm.StudyProgramme;

import java.util.List;

public class DifModuleStartegy extends Strategy {

    /***
     * control if student have any course in required module
     * if not add any course from module else nothing
     * @param programme
     * @param student
     */
    @Override
    public void createCourseForDec(StudyProgramme programme, Student student) {
        List<Module> programmeModulsControl = new java.util.ArrayList<>(programme.getCoursesSortedByModule().keySet());
        int countMaxEap = 0;
        for (Module module : programme.getCoursesSortedByModule().keySet()) {
            for (Course course : student.getAllCourses().keySet()) {
                if (programme.getCoursesSortedByModule().get(module).contains(course)) {
                    programmeModulsControl.remove(module);
                }
            }
            if (programmeModulsControl.contains(module) && countMaxEap <= Declaration.getMaxEap()) {
                for (Course course : programme.getCoursesSortedByModule().get(module)) {
                    if (course.getType() != CourseType.DIPLOMA
                            && course.getType() != CourseType.INTERNSHIP) {
                        coursesDec.add(course);
                        countMaxEap += course.getEap();
                        break;
                    }
                }
                programmeModulsControl.remove(module);
            }

        }
        super.createCourseForDec(programme, student);
    }
}
