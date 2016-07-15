package bg.softuni.io.commands;

import bg.softuni.contract.AsynchDownloader;
import bg.softuni.contract.ContentComparer;
import bg.softuni.contract.Database;
import bg.softuni.contract.DirectoryManager;

public class ReadDatabaseCommand extends Command {

    public ReadDatabaseCommand(String input, String[] data, Database repository,
            ContentComparer tester, DirectoryManager ioManager, AsynchDownloader downloadManager) {
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
