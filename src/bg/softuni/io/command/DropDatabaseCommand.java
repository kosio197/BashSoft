package bg.softuni.io.command;

import bg.softuni.annotation.Alias;
import bg.softuni.annotation.Inject;
import bg.softuni.contract.Database;
import bg.softuni.io.OutputWriter;

@Alias(value = "dropdb")
public class DropDatabaseCommand extends Command {

    @Inject
    private Database repository;

    public DropDatabaseCommand(String input, String[] data) {
        super(input, data);
    }

    @Override
    protected void doExecute() throws Exception {
        this.repository.unloadData();
        OutputWriter.writeMessageOnNewLine("Database dropped!");
    }

    @Override
    public boolean validate() {
        return getData().length == 1;
    }
}
