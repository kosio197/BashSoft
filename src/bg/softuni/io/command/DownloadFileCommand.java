package bg.softuni.io.command;

import bg.softuni.annotation.Alias;
import bg.softuni.annotation.Inject;
import bg.softuni.contract.AsynchDownloader;

@Alias(value = "download URL")
public class DownloadFileCommand extends Command {

    @Inject
    private AsynchDownloader downloadManager;

    public DownloadFileCommand(String input, String[] data) {
        super(input, data);
    }

    @Override
    protected void doExecute() throws Exception {
        String fileUrl = getData()[1];
        this.downloadManager.download(fileUrl);
    }

    @Override
    protected boolean validate() {
        return getData().length == 2;
    }
}
