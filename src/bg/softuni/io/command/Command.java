package bg.softuni.io.command;

import bg.softuni.contract.AsynchDownloader;
import bg.softuni.contract.ContentComparer;
import bg.softuni.contract.Database;
import bg.softuni.contract.DirectoryManager;
import bg.softuni.contract.Executable;
import bg.softuni.exception.InvalidCommandException;

public abstract class Command implements Executable {
    private String input;
    private String [] data;

    private Database repository;
    private ContentComparer tester;
    private DirectoryManager ioManager;
    private AsynchDownloader downloadManager;

    protected Command(String input, String[] data, Database repository,
            ContentComparer tester, DirectoryManager ioManager, AsynchDownloader downloadManager) {
        setInput(input);
        setData(data);

        this.repository = repository;
        this.tester = tester;
        this.ioManager = ioManager;
        this.downloadManager = downloadManager;
    }

    protected abstract boolean validate();
    protected abstract void doExecute() throws Exception;

    @Override
    public void execute() throws Exception {
        if(!validate()) {
            throw new InvalidCommandException(input);
        }

        doExecute();
    };

    protected String getInput() {
        return input;
    }

    private void setInput(String input) {
        if(input == null || input.equals("")) {
            throw new InvalidCommandException(input);
        }

        this.input = input;
    }

    protected String[] getData() {
        return data;
    }

    private void setData(String[] data) {
        if(data == null || data.length < 1) {
            throw new InvalidCommandException(input);
        }

        this.data = data;
    }

    protected Database getRepository() {
        return repository;
    }

    protected ContentComparer getTester() {
        return tester;
    }

    protected DirectoryManager getIoManager() {
        return ioManager;
    }

    protected AsynchDownloader getDownloadManager() {
        return downloadManager;
    }
}
