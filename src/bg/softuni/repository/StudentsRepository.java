package bg.softuni.repository;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import bg.softuni.contract.DataFilter;
import bg.softuni.contract.DataSorter;
import bg.softuni.contract.Database;
import bg.softuni.contract.Student;
import bg.softuni.io.OutputWriter;
import bg.softuni.model.SoftUniCourse;
import bg.softuni.model.SoftUniStudent;
import bg.softuni.static_data.ExceptionMessages;
import bg.softuni.static_data.SessionData;

public class StudentsRepository implements Database {
    private boolean isDataInitialized = false;
    private DataFilter filter;
    private DataSorter sorter;
    private Map<String, SoftUniCourse> courses;
    private Map<String, SoftUniStudent> students;

    public StudentsRepository(DataFilter filter, DataSorter sorter) {
        this.filter = filter;
        this.sorter = sorter;
    }

    @Override
    public void loadData(String fileName) throws IOException {
        if (isDataInitialized) {
            throw new RuntimeException(ExceptionMessages.DATA_ALREADY_INITIALIZED);
        }

        courses = new LinkedHashMap<String, SoftUniCourse>();
        students = new HashMap<String, SoftUniStudent>();

        readData(fileName);
    }

    @Override
    public void unloadData() {
        if (!isDataInitialized) {
            throw new RuntimeException(ExceptionMessages.DATA_NOT_INITIALIZED);
        }

        courses = null;
        students = null;
        isDataInitialized = false;
    }

    private void readData(String fileName) throws IOException {
        String regex = "([A-Z][a-zA-Z#\\+]*_[A-Z][a-z]{2}_\\d{4})\\s+([A-Za-z]+\\d{2}_\\d{2,4})\\s([\\s0-9]+)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher;

        String path = SessionData.currentPath + (File.separator.equals("/") ? "/" : "\\") + fileName;
        List<String> lines = Files.readAllLines(Paths.get(path));
        int lineIndex = 0;

        for (String line : lines) {
            lineIndex++;
            matcher = pattern.matcher(line);

            if (!line.isEmpty() && matcher.find()) {
                String courseName = matcher.group(1);
                String studentName = matcher.group(2);
                String scoreStr = matcher.group(3);

                try {
                    String[] splitScores = scoreStr.split("\\s+");
                    int [] scores = new int[scoreStr.length()];

                    for (int i = 0; i < splitScores.length; i++) {
                        scores[i] = Integer.parseInt(splitScores[i]);
                    }

                    if (Arrays.stream(scores).anyMatch(score -> score > 100 || score < 0)) {
                        OutputWriter.displayException(ExceptionMessages.INVALID_SCORE);
                        continue;
                    }

                    if (scores.length > SoftUniCourse.NUMBER_OF_TASKS_ON_EXAM) {
                        OutputWriter.displayException(ExceptionMessages.INVALID_NUMBER_OF_SCORES);
                        continue;
                    }

                    if (!students.containsKey(studentName)) {
                        students.put(studentName, new SoftUniStudent(studentName));
                    }

                    if (!courses.containsKey(courseName)) {
                        courses.put(courseName, new SoftUniCourse(courseName));
                    }

                    SoftUniCourse course = courses.get(courseName);
                    SoftUniStudent student = students.get(studentName);
                    student.enrollInCourse(course);
                    student.setMarksInCourse(courseName, scores);
                    course.enrollStudent(student);
                } catch (NumberFormatException nfe) {
                    OutputWriter.displayException(nfe.getMessage() + " at line: " + lineIndex);
                }
            }
        }

        isDataInitialized = true;
        OutputWriter.writeMessageOnNewLine("Data read.");
    }

    @Override
    public void getStudentMarksInCourse(String courseName, String studentName) {
        if (!isQueryForStudentPossible(courseName, studentName)) {
            return;
        }

        double mark = courses.get(courseName).getStudentsByName().get(studentName).getMarksByCourseName().get(courseName);
        OutputWriter.printStudent(studentName, mark);
    }

    @Override
    public void getStudentsByCourse(String courseName) {
        if (!isQueryForCoursePossible(courseName)) {
            return;
        }

        OutputWriter.writeMessageOnNewLine(courseName + ":");

        for (Map.Entry<String, Student> student : courses.get(courseName).getStudentsByName().entrySet()) {
            getStudentMarksInCourse(courseName, student.getKey());
        }
    }

    private boolean isQueryForCoursePossible(String courseName) {
        if (!isDataInitialized) {
            OutputWriter.displayException(ExceptionMessages.DATA_NOT_INITIALIZED);
            return false;
        }

        if (!courses.containsKey(courseName)) {
            OutputWriter.displayException(ExceptionMessages.NON_EXISTING_COURSE);
            return false;
        }

        return true;
    }

    private boolean isQueryForStudentPossible(String courseName, String studentName) {
        if (!isQueryForCoursePossible(courseName)) {
            return false;
        }

        if (!courses.get(courseName).getStudentsByName().containsKey(studentName)) {
            OutputWriter.displayException(ExceptionMessages.NON_EXISTING_STUDENT);
            return false;
        }

        return true;
    }

    @Override
    public void filterAndTake(String courseName, String filter) {
        int studentsToTake = courses.get(courseName).getStudentsByName().size();
        filterAndTake(courseName, filter, studentsToTake);
    }

    @Override
    public void filterAndTake(String courseName, String filter, int studentsToTake) {
        if (!isQueryForCoursePossible(courseName)) {
            return;
        }

        Map<String, Double> marks = new LinkedHashMap<String, Double>();

        for (Map.Entry<String, Student> entry : courses.get(courseName).getStudentsByName().entrySet()) {
            marks.put(entry.getKey(), entry.getValue().getMarksByCourseName().get(courseName));
        }

        this.filter.printFilteredStudents(marks, filter, studentsToTake);
    }

    @Override
    public void orderAndTake(String courseName, String orderType, int studentsToTake) {
        if (!isQueryForCoursePossible(courseName)) {
            return;
        }

        Map<String, Double> marks = new LinkedHashMap<String, Double>();

        for (Map.Entry<String, Student> entry : courses.get(courseName).getStudentsByName().entrySet()) {
            marks.put(entry.getKey(), entry.getValue().getMarksByCourseName().get(courseName));
        }

        sorter.printSortedStudents(marks, orderType, studentsToTake);
    }

    @Override
    public void orderAndTake(String courseName, String orderType) {
        if (!courses.containsKey(courseName)) {
            OutputWriter.displayException(ExceptionMessages.NON_EXISTING_COURSE);
            return;
        }

        int studentsToTake = courses.get(courseName).getStudentsByName().size();
        orderAndTake(courseName, orderType, studentsToTake);
    }
}
