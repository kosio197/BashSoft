package bg.softuni.io.commands;

import bg.softuni.contract.AsynchDownloader;
import bg.softuni.contract.ContentComparer;
import bg.softuni.contract.Database;
import bg.softuni.contract.DirectoryManager;

public class ChangeRelativePathCommand extends Command {

    public ChangeRelativePathCommand(String input, String[] data, Database repository,
            ContentComparer tester, DirectoryManager ioManager, AsynchDownloader downloadManager) {
        super(input, data, repository, tester, ioManager, downloadManager);
    }

    @Override
    protected void doExecute() throws Exception {
        String relativePath = getData()[1];
        getIoManager().changeCurrentDirRelativePath(relativePath);
    }

    @Override
    protected boolean validate() {
        return getData().length == 2;
    }
}
