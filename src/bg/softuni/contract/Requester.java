package bg.softuni.contract;

public interface Requester {
    void getStudentMarksInCourse(String courseName, String studentName);
    void getStudentsByCourse(String courseName);
}
