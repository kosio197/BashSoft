package bg.softuni.io.commands;

import bg.softuni.io.IOManager;
import bg.softuni.judge.Tester;
import bg.softuni.network.DownloadManager;
import bg.softuni.repository.StudentsRepository;

public class ReadDatabaseCommand extends Command {

    public ReadDatabaseCommand(String input, String[] data,
            StudentsRepository repository, Tester tester, IOManager ioManager,
            DownloadManager downloadManager) {
        super(input, data, repository, tester, ioManager, downloadManager);
    }

    @Override
    protected void doExecute() throws Exception {
        String fileName = getData()[1];
        getRepository().loadData(fileName);
    }

    @Override
    protected boolean validate() {
        return getData().length == 2;
    }
}
