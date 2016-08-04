package bg.softuni.io.command;

import bg.softuni.contract.Executable;
import bg.softuni.exception.InvalidCommandException;

public abstract class Command implements Executable {
    private String input;
    private String [] data;


    protected Command(String input, String[] data) {
        setInput(input);
        setData(data);

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
}
