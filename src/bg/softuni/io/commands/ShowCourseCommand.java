package bg.softuni.io.commands;

import bg.softuni.io.IOManager;
import bg.softuni.judge.Tester;
import bg.softuni.network.DownloadManager;
import bg.softuni.repository.StudentsRepository;

public class ShowCourseCommand extends Command {

    public ShowCourseCommand(String input, String[] data,
            StudentsRepository repository, Tester tester, IOManager ioManager,
            DownloadManager downloadManager) {
        super(input, data, repository, tester, ioManager, downloadManager);
    }

    @Override
    protected void doExecute() throws Exception {
        String [] data = getData();

        if (data.length == 2) {
            String courseName = data[1];
            getRepository().getStudentsByCourse(courseName);
        }

        if (data.length == 3) {
            String courseName = data[1];
            String userName = data[2];
            getRepository().getStudentMarksInCourse(courseName, userName);
        }
    }

    @Override
    protected boolean validate() {
        return getData().length == 2 || getData().length == 3;
    }
}
