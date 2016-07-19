package bg.softuni.io.command;

import java.util.Comparator;

import bg.softuni.contract.AsynchDownloader;
import bg.softuni.contract.ContentComparer;
import bg.softuni.contract.Course;
import bg.softuni.contract.Database;
import bg.softuni.contract.DirectoryManager;
import bg.softuni.contract.SimpleOrderedBag;
import bg.softuni.contract.Student;
import bg.softuni.exception.InvalidCommandException;
import bg.softuni.io.OutputWriter;

public class DisplayCommand extends Command {

    public DisplayCommand(String input, String[] data, Database repository,
            ContentComparer tester, DirectoryManager ioManager,
            AsynchDownloader downloadManager) {
        super(input, data, repository, tester, ioManager, downloadManager);
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
            SimpleOrderedBag<Student> list = getRepository().getAllStudentsSorted(studentComparator);
            OutputWriter.writeMessageOnNewLine(list.joinWith(System.lineSeparator()));
        } else if (entiryToDisplay.equalsIgnoreCase("courses")) {
            Comparator<Course> courseComparator = createComparator(sortType);
            SimpleOrderedBag<Course> list = getRepository().getAllCoursesSorted(courseComparator);
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
