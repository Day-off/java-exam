package ee.taltech.iti0202.university.studyprogramm;

import ee.taltech.iti0202.university.course.Course;
import ee.taltech.iti0202.university.exeptions.Course.CourseAlreadyExistException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class StudyProgramme {

    private final String name;
    private final List<Course> programmeCourses = new ArrayList<>();
    private final HashMap<Module, List<Course>> coursesSortedByModule = new HashMap<>();
    private final int requiredEapAmount;

    public StudyProgramme(String name, int eap) {
        this.name = name;
        if (eap < 170) {
            throw new IllegalArgumentException("Eap amount too small");
        }
        requiredEapAmount = eap;
    }

    /*
    MAIN METHODS
     */

    public void addCourse(Course cour, Module module) throws CourseAlreadyExistException {
        if (!programmeCourses.contains(cour)) {
            programmeCourses.add(cour);
            if (!coursesSortedByModule.containsKey(module)) {
                coursesSortedByModule.put(module, List.of(cour));
            } else {
                List<Course> courses = new ArrayList<>(coursesSortedByModule.get(module));
                courses.add(cour);
                coursesSortedByModule.put(module, courses);
            }
        } else {
            throw new CourseAlreadyExistException();
        }
    }

    /*
    GETTERS
     */

    public String getName() {
        return name;
    }

    public HashMap<Module, List<Course>> getCoursesSortedByModule() {
        return coursesSortedByModule;
    }

    public List<Course> getProgrammeCourses() {
        return programmeCourses;
    }

    public int getRequiredEapAmount() {
        return requiredEapAmount;
    }

    @Override
    public String toString() {
        return name + "\n PallCourses: " + programmeCourses.size()
                + ", coursesSortedByModule:" + coursesSortedByModule;
    }
}
