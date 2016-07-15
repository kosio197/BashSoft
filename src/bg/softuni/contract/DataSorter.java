package bg.softuni.contract;

import java.util.Map;

public interface DataSorter {
    void printSortedStudents(Map<String, Double> studentsWithMarks, String comparisonType, int numberOfStudents);
}
