package bg.softuni.io.commands;

import bg.softuni.io.IOManager;
import bg.softuni.io.OutputWriter;
import bg.softuni.judge.Tester;
import bg.softuni.network.DownloadManager;
import bg.softuni.repository.StudentsRepository;

public class DropDatabaseCommand extends Command {

    public DropDatabaseCommand(String input, String[] data,
            StudentsRepository repository, Tester tester, IOManager ioManager,
            DownloadManager downloadManager) {
        super(input, data, repository, tester, ioManager, downloadManager);
    }

    @Override
    protected void doExecute() throws Exception {
        getRepository().unloadData();
        OutputWriter.writeMessageOnNewLine("Database dropped!");
    }

    @Override
    public boolean validate() {
        return getData().length == 1;
    }
}
