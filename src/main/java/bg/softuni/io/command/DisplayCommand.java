package bg.softuni.io.command;

import java.util.Comparator;

import bg.softuni.annotation.Alias;
import bg.softuni.annotation.Inject;
import bg.softuni.contract.Course;
import bg.softuni.contract.Database;
import bg.softuni.contract.SimpleOrderedBag;
import bg.softuni.contract.Student;
import bg.softuni.exception.InvalidCommandException;
import bg.softuni.io.OutputWriter;

@Alias(value = "display")
public class DisplayCommand extends Command {

    @Inject
    private Database repository;

    public DisplayCommand(String input, String[] data) {
        super(input, data);
    }

    @Override
    protected boolean validate() {
        return getData().length == 3;
    }

    @Override
    protected void doExecute() throws Exception {
        String [] data = getData();

        String entiryToDisplay = data[1];
        String sortType = data[2];

        if (entiryToDisplay.equalsIgnoreCase("students")) {
            Comparator<Student> studentComparator = createComparator(sortType);
            SimpleOrderedBag<Student> list = this.repository.getAllStudentsSorted(studentComparator);
            OutputWriter.writeMessageOnNewLine(list.joinWith(System.lineSeparator()));
        } else if (entiryToDisplay.equalsIgnoreCase("courses")) {
            Comparator<Course> courseComparator = createComparator(sortType);
            SimpleOrderedBag<Course> list = this.repository.getAllCoursesSorted(courseComparator);
            OutputWriter.writeMessageOnNewLine(list.joinWith(System.lineSeparator()));
        } else {
            throw new InvalidCommandException(getInput());
        }
    }

    private <T extends Comparable<T>> Comparator<T> createComparator (String sortType) {
        if (sortType.equalsIgnoreCase("ascending")) {
            return (o1, o2) -> o1.compareTo(o2);
        } else if (sortType.equalsIgnoreCase("descending")) {
            return (o1, o2) -> o2.compareTo(o1);
        } else {
            throw new InvalidCommandException(getInput());
        }
    }
}
