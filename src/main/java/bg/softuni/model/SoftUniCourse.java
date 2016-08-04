package bg.softuni.model;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

import bg.softuni.contract.Course;
import bg.softuni.contract.Student;
import bg.softuni.exception.DuplicateEntryInStructureException;
import bg.softuni.static_data.ExceptionMessages;

public class SoftUniCourse implements Course {
    public static final int NUMBER_OF_TASKS_ON_EXAM = 5;
    public static final int MAX_SCORE_ON_EXAM_TASK = 100;

    private String name;
    private Map<String, Student> studentsByName;

    public SoftUniCourse(String name) {
        setName(name);
        studentsByName = new LinkedHashMap<String, Student>();
    }

    public void setName(String name) {
        if (name == null || name.equals("")) {
            throw new IllegalArgumentException(ExceptionMessages.NULL_OR_EMPTY_VALUE);
        }

        this.name = name;
    }


    @Override
    public String getName() {
        return name;
    }

    @Override
    public void enrollStudent(Student student) {
        if(studentsByName.containsKey(student.getUsername())) {
            throw new DuplicateEntryInStructureException(student.getUsername(), name);
        }

        studentsByName.put(student.getUsername(), student);
    }

    @Override
    public Map<String, Student> getStudentsByName() {
        return Collections.unmodifiableMap(studentsByName);
    }

    @Override
    public int compareTo(Course o) {
        return getName().compareTo(o.getName());
    }

    @Override
    public String toString() {
        return getName();
    }
}
