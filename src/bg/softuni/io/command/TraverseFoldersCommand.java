package bg.softuni.io.command;

import bg.softuni.contract.AsynchDownloader;
import bg.softuni.contract.ContentComparer;
import bg.softuni.contract.Database;
import bg.softuni.contract.DirectoryManager;

public class TraverseFoldersCommand extends Command {

    public TraverseFoldersCommand(String input, String[] data, Database repository,
            ContentComparer tester, DirectoryManager ioManager, AsynchDownloader downloadManager) {
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
