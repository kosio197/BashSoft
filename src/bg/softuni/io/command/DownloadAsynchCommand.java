package bg.softuni.io.command;

import bg.softuni.contract.AsynchDownloader;
import bg.softuni.contract.ContentComparer;
import bg.softuni.contract.Database;
import bg.softuni.contract.DirectoryManager;

public class DownloadAsynchCommand extends Command {

    public DownloadAsynchCommand(String input, String[] data, Database repository,
            ContentComparer tester, DirectoryManager ioManager, AsynchDownloader downloadManager) {
        super(input, data, repository, tester, ioManager, downloadManager);
    }

    @Override
    protected void doExecute() throws Exception {
        String fileUrl = getData()[1];
        getDownloadManager().downloadOnNewThread(fileUrl);
    }

    @Override
    protected boolean validate() {
        return getData().length == 2;
    }
}
