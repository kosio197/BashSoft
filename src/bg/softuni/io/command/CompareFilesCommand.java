package bg.softuni.io.command;

import bg.softuni.contract.AsynchDownloader;
import bg.softuni.contract.ContentComparer;
import bg.softuni.contract.Database;
import bg.softuni.contract.DirectoryManager;

public class CompareFilesCommand extends Command {

    public CompareFilesCommand(String input, String[] data, Database repository,
            ContentComparer tester, DirectoryManager ioManager, AsynchDownloader downloadManager) {
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
