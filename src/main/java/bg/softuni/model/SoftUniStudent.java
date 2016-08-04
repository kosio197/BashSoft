package bg.softuni.model;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

import bg.softuni.contract.Course;
import bg.softuni.contract.Student;
import bg.softuni.exception.DuplicateEntryInStructureException;
import bg.softuni.exception.KeyNotFoundException;
import bg.softuni.static_data.ExceptionMessages;

public class SoftUniStudent implements Student {
    private String username;
    private Map<String, Course> enrolledCources;
    private Map<String, Double> marksByCourseName;

    public SoftUniStudent(String username) {
        setUsername(username);
        enrolledCources = new LinkedHashMap<String, Course>();
        marksByCourseName = new LinkedHashMap<String, Double>();
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public void setUsername(String username) {
        if (username == null || username.equals("")) {
            throw new IllegalArgumentException(ExceptionMessages.NULL_OR_EMPTY_VALUE);
        }

        this.username = username;
    }

    @Override
    public void enrollInCourse(Course course) {
        if (enrolledCources.containsKey(course.getName())) {
            throw new DuplicateEntryInStructureException(username, course.getName());
        }

        enrolledCources.put(course.getName(), course);
    }

    @Override
    public void setMarksInCourse(String courseName, int... scores) {
        if (!enrolledCources.containsKey(courseName)) {
            throw new KeyNotFoundException(ExceptionMessages.NOT_ENROLLED_IN_COURSE);
        }

        if (scores.length > SoftUniCourse.NUMBER_OF_TASKS_ON_EXAM) {
            throw new IllegalArgumentException(ExceptionMessages.INVALID_NUMBER_OF_SCORES);
        }

        marksByCourseName.put(courseName, calculateMark(scores));
    }

    @Override
    public Map<String, Double> getMarksByCourseName() {
        return Collections.unmodifiableMap(marksByCourseName);
    }

    @Override
    public Map<String, Course> getEnrolledCources() {
        return Collections.unmodifiableMap(enrolledCources);
    }

    private double calculateMark(int[] scores) {
        double percentageOfSolvedExam = Arrays.stream(scores).sum()/
                (double) (SoftUniCourse.NUMBER_OF_TASKS_ON_EXAM * SoftUniCourse.MAX_SCORE_ON_EXAM_TASK);

        return percentageOfSolvedExam * 4 + 2;
    }

    @Override
    public int compareTo(Student o) {
        return getUsername().compareTo(o.getUsername());
    }

    @Override
    public String toString() {
        return getUsername();
    }
}
