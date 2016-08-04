package bg.softuni.repository;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import bg.softuni.contract.DataSorter;
import bg.softuni.io.OutputWriter;
import bg.softuni.static_data.ExceptionMessages;

public class RepositorySorter implements DataSorter {
    @Override
    public void printSortedStudents(Map<String, Double> studentsWithMarks, String comparisonType, int numberOfStudents) {
        comparisonType = comparisonType.toLowerCase();

        if (!comparisonType.equals("ascending") && !comparisonType.equals("descending")) {
            throw new RuntimeException(ExceptionMessages.INVALID_COMPARISON_QUERY);
        }

        Comparator<Map.Entry<String, Double>> comparator = (x, y) -> {
            double value1 = x.getValue();
            double value2 = y.getValue();

            return Double.compare(value1, value2);
        };

        List<String> sortedStudents = studentsWithMarks.entrySet()
                .stream()
                .sorted(comparator)
                .limit(numberOfStudents)
                .map(x -> x.getKey())
                .collect(Collectors.toList());

        if (comparisonType.equals("descending")) {
            Collections.reverse(sortedStudents);
        }


        printStudents(studentsWithMarks, sortedStudents);
    }

    private void printStudents(Map<String, Double> courseData, List<String> sortedStudents) {
        for (String student : sortedStudents) {
            OutputWriter.printStudent(student, courseData.get(student));
        }
    }
}

