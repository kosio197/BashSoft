package bg.softuni.io.commands;

import bg.softuni.io.IOManager;
import bg.softuni.judge.Tester;
import bg.softuni.network.DownloadManager;
import bg.softuni.repository.StudentsRepository;

public class ChangeAbsolutePathCommand extends Command {

    public ChangeAbsolutePathCommand(String input, String[] data,
            StudentsRepository repository, Tester tester, IOManager ioManager,
            DownloadManager downloadManager) {
        super(input, data, repository, tester, ioManager, downloadManager);
    }

    @Override
    public boolean validate() {
        return getData().length == 2;
    }

    @Override
    protected void doExecute() throws Exception {
        String absolutePath = getData()[1];
        getIoManager().changeCurrentDirAbsolute(absolutePath);
    }
}
