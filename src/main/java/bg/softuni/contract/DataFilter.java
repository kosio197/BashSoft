package bg.softuni.contract;

import java.util.Map;

public interface DataFilter {
    void printFilteredStudents(Map<String, Double> studentsWithMarks, String filterType, Integer numberOfStudents);
}
