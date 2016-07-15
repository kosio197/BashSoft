package bg.softuni.models;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

import bg.softuni.exceptions.DuplicateEntryInStructureException;
import bg.softuni.exceptions.KeyNotFoundException;
import bg.softuni.static_data.ExceptionMessages;

public class Student {
    private String username;
    private Map<String, Course> enrolledCources;
    private Map<String, Double> marksByCourseName;

    public Student(String username) {
        setUsername(username);
        enrolledCources = new LinkedHashMap<String, Course>();
        marksByCourseName = new LinkedHashMap<String, Double>();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        if (username == null || username.equals("")) {
            throw new IllegalArgumentException(ExceptionMessages.NULL_OR_EMPTY_VALUE);
        }

        this.username = username;
    }

    public void enrollInCourse(Course course) {
        if (enrolledCources.containsKey(course.getName())) {
            throw new DuplicateEntryInStructureException(username, course.getName());
        }

        enrolledCources.put(course.getName(), course);
    }

    public void setMarksInCourse(String courseName, int... scores) {
        if (!enrolledCources.containsKey(courseName)) {
            throw new KeyNotFoundException(ExceptionMessages.NOT_ENROLLED_IN_COURSE);
        }

        if (scores.length > Course.NUMBER_OF_TASKS_ON_EXAM) {
            throw new IllegalArgumentException(ExceptionMessages.INVALID_NUMBER_OF_SCORES);
        }

        marksByCourseName.put(courseName, calculateMark(scores));
    }

    public Map<String, Double> getMarksByCourseName() {
        return Collections.unmodifiableMap(marksByCourseName);
    }

    public Map<String, Course> getEnrolledCources() {
        return Collections.unmodifiableMap(enrolledCources);
    }

    private double calculateMark(int[] scores) {
        double percentageOfSolvedExam = Arrays.stream(scores).sum()/
                (double) (Course.NUMBER_OF_TASKS_ON_EXAM * Course.MAX_SCORE_ON_EXAM_TASK);

        return percentageOfSolvedExam * 4 + 2;
    }
}
