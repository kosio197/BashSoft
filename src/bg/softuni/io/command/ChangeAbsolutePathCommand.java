package bg.softuni.io.command;

import bg.softuni.contract.AsynchDownloader;
import bg.softuni.contract.ContentComparer;
import bg.softuni.contract.Database;
import bg.softuni.contract.DirectoryManager;

public class ChangeAbsolutePathCommand extends Command {

    public ChangeAbsolutePathCommand(String input, String[] data, Database repository,
            ContentComparer tester, DirectoryManager ioManager, AsynchDownloader downloadManager) {
        super(input, data, repository, tester, ioManager, downloadManager);
    }

    @Override
    public boolean validate() {
        return getData().length == 2;
    }

    @Override
    protected void doExecute() throws Exception {
        String absolutePath = getData()[1];
        getIoManager().changeCurrentDirAbsolutePath(absolutePath);
    }
}
