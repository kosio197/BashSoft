package bg.softuni.contract;

public interface AsynchDownloader extends Downloader {
    void downloadOnNewThread(String fileUrl);
}
