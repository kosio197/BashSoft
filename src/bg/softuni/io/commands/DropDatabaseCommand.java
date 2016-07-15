package bg.softuni.io.commands;

import bg.softuni.contract.AsynchDownloader;
import bg.softuni.contract.ContentComparer;
import bg.softuni.contract.Database;
import bg.softuni.contract.DirectoryManager;
import bg.softuni.io.OutputWriter;

public class DropDatabaseCommand extends Command {

    public DropDatabaseCommand(String input, String[] data, Database repository,
            ContentComparer tester, DirectoryManager ioManager, AsynchDownloader downloadManager) {
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
