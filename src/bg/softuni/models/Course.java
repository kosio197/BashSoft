package bg.softuni.models;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

import bg.softuni.exceptions.DuplicateEntryInStructureException;
import bg.softuni.static_data.ExceptionMessages;

public class Course {
    public static final int NUMBER_OF_TASKS_ON_EXAM = 5;
    public static final int MAX_SCORE_ON_EXAM_TASK = 100;

    private String name;
    private Map<String, Student> studentsByName;

    public Course(String name) {
        setName(name);
        studentsByName = new LinkedHashMap<String, Student>();
    }

    public void setName(String name) {
        if (name == null || name.equals("")) {
            throw new IllegalArgumentException(ExceptionMessages.NULL_OR_EMPTY_VALUE);
        }

        this.name = name;
    }


    public String getName() {
        return name;
    }

    public void enrollStudent(Student student) {
        if(studentsByName.containsKey(student.getUsername())) {
            throw new DuplicateEntryInStructureException(student.getUsername(), name);
        }

        studentsByName.put(student.getUsername(), student);
    }

    public Map<String, Student> getStudentsByName() {
        return Collections.unmodifiableMap(studentsByName);
    }
}
