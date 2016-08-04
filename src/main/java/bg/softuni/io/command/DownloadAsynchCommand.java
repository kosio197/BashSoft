package bg.softuni.io.command;

import bg.softuni.annotation.Alias;
import bg.softuni.annotation.Inject;
import bg.softuni.contract.AsynchDownloader;

@Alias(value = "downloadAsynch URL")
public class DownloadAsynchCommand extends Command {

    @Inject
    private AsynchDownloader downloadManager;

    public DownloadAsynchCommand(String input, String[] data) {
        super(input, data);
    }

    @Override
    protected void doExecute() throws Exception {
        String fileUrl = getData()[1];
        this.downloadManager.downloadOnNewThread(fileUrl);
    }

    @Override
    protected boolean validate() {
        return getData().length == 2;
    }
}
