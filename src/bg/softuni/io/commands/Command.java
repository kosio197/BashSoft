package bg.softuni.io.commands;

import bg.softuni.exceptions.InvalidCommandException;
import bg.softuni.io.IOManager;
import bg.softuni.judge.Tester;
import bg.softuni.network.DownloadManager;
import bg.softuni.repository.StudentsRepository;

public abstract class Command {
    private String input;
    private String [] data;

    private StudentsRepository repository;
    private Tester tester;
    private IOManager ioManager;
    private DownloadManager downloadManager;

    protected Command(String input, String[] data, StudentsRepository repository,
            Tester tester, IOManager ioManager, DownloadManager downloadManager) {
        setInput(input);
        setData(data);

        this.repository = repository;
        this.tester = tester;
        this.ioManager = ioManager;
        this.downloadManager = downloadManager;
    }

    protected abstract boolean validate();
    protected abstract void doExecute() throws Exception;

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

    protected StudentsRepository getRepository() {
        return repository;
    }

    protected Tester getTester() {
        return tester;
    }

    protected IOManager getIoManager() {
        return ioManager;
    }

    protected DownloadManager getDownloadManager() {
        return downloadManager;
    }
}
