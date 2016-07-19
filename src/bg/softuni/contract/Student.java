package bg.softuni.contract;

import java.util.Map;

public interface Student extends Comparable<Student> {

    String getUsername();
    void enrollInCourse(Course course);
    void setUsername(String username);
    void setMarksInCourse(String courseName, int... scores);
    Map<String, Double> getMarksByCourseName();
    Map<String, Course> getEnrolledCources();
}
