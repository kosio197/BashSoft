package bg.softuni.io.commands;

import bg.softuni.contract.AsynchDownloader;
import bg.softuni.contract.ContentComparer;
import bg.softuni.contract.Database;
import bg.softuni.contract.DirectoryManager;

public class MakeDirectoryCommand extends Command {

    public MakeDirectoryCommand(String input, String[] data, Database repository,
            ContentComparer tester, DirectoryManager ioManager, AsynchDownloader downloadManager) {
        super(input, data, repository, tester, ioManager, downloadManager);
    }

    @Override
    protected void doExecute() throws Exception {
        String folderName = getData()[1];
        getIoManager().createDirectoryInCurrentFolder(folderName);
    }

    @Override
    protected boolean validate() {
        return getData().length == 2;
    }
}
