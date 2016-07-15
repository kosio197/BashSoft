package bg.softuni.io.commands;

import bg.softuni.io.IOManager;
import bg.softuni.judge.Tester;
import bg.softuni.network.DownloadManager;
import bg.softuni.repository.StudentsRepository;

public class CompareFilesCommand extends Command {

    public CompareFilesCommand(String input, String[] data,
            StudentsRepository repository, Tester tester, IOManager ioManager,
            DownloadManager downloadManager) {
        super(input, data, repository, tester, ioManager, downloadManager);
    }

    @Override
    protected void doExecute() throws Exception {
        String firstPath = getData()[1];
        String secondPath = getData()[2];

        getTester().compareContent(firstPath, secondPath);
    }

    @Override
    protected boolean validate() {
        return getData().length == 3;
    }
}
