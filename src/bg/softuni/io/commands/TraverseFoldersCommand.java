package bg.softuni.io.commands;

import bg.softuni.io.IOManager;
import bg.softuni.judge.Tester;
import bg.softuni.network.DownloadManager;
import bg.softuni.repository.StudentsRepository;

public class TraverseFoldersCommand extends Command {

    public TraverseFoldersCommand(String input, String[] data,
            StudentsRepository repository, Tester tester, IOManager ioManager,
            DownloadManager downloadManager) {
        super(input, data, repository, tester, ioManager, downloadManager);
    }

    @Override
    protected void doExecute() throws Exception {
        if (getData().length == 1) {
            getIoManager().traverseDirectory(0);
        }

        if (getData().length == 2) {
            getIoManager().traverseDirectory(Integer.valueOf(getData()[1]));
        }
    }

    @Override
    protected boolean validate() {
        return getData().length == 1 || getData().length == 2;
    }
}
