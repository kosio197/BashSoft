package bg.softuni.io.commands;

import java.awt.Desktop;
import java.io.File;

import bg.softuni.io.IOManager;
import bg.softuni.judge.Tester;
import bg.softuni.network.DownloadManager;
import bg.softuni.repository.StudentsRepository;
import bg.softuni.static_data.SessionData;

public class OpenFileCommand extends Command {

    public OpenFileCommand(String input, String[] data,
            StudentsRepository repository, Tester tester, IOManager ioManager,
            DownloadManager downloadManager) {
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
