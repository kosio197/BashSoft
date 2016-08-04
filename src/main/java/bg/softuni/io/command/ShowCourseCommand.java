package bg.softuni.io.command;

import bg.softuni.annotation.Alias;
import bg.softuni.annotation.Inject;
import bg.softuni.contract.Database;

@Alias(value = "show")
public class ShowCourseCommand extends Command {

    @Inject
    private Database repository;

    public ShowCourseCommand(String input, String[] data) {
        super(input, data);
    }

    @Override
    protected void doExecute() throws Exception {
        String [] data = getData();

        if (data.length == 2) {
            String courseName = data[1];
            this.repository.getStudentsByCourse(courseName);
        }

        if (data.length == 3) {
            String courseName = data[1];
            String userName = data[2];
            this.repository.getStudentMarksInCourse(courseName, userName);
        }
    }

    @Override
    protected boolean validate() {
        return getData().length == 2 || getData().length == 3;
    }
}
