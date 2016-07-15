package bg.softuni.io.command;

import java.awt.Desktop;
import java.io.File;

import bg.softuni.contract.AsynchDownloader;
import bg.softuni.contract.ContentComparer;
import bg.softuni.contract.Database;
import bg.softuni.contract.DirectoryManager;
import bg.softuni.static_data.SessionData;

public class OpenFileCommand extends Command {

    public OpenFileCommand(String input, String[] data, Database repository,
            ContentComparer tester, DirectoryManager ioManager, AsynchDownloader downloadManager) {
        super(input, data, repository, tester, ioManager, downloadManager);
    }

    @Override
    protected void doExecute() throws Exception {
        String [] data = getData();

        String fileName = data[1];
        String filePath = SessionData.currentPath + File.separator + fileName;
        File file = new File(filePath);

        Desktop.getDesktop().open(file);
    }

    @Override
    protected boolean validate() {
        return getData().length == 2;
    }
}
