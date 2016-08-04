package bg.softuni.contract;

import java.util.Comparator;

public interface Requester {
    void getStudentMarksInCourse(String courseName, String studentName);

    void getStudentsByCourse(String courseName);

    SimpleOrderedBag<Course> getAllCoursesSorted(Comparator<Course> cmp);

    SimpleOrderedBag<Student> getAllStudentsSorted(Comparator<Student> cmp);
}
