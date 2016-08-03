package bg.softuni.io.command;

import bg.softuni.annotation.Alias;
import bg.softuni.annotation.Inject;
import bg.softuni.contract.Database;

@Alias(value = "readdb")
public class ReadDatabaseCommand extends Command {

    @Inject
    private Database repository;

    public ReadDatabaseCommand(String input, String[] data) {
        super(input, data);
    }

    @Override
    protected void doExecute() throws Exception {
        String fileName = getData()[1];
        this.repository.loadData(fileName);
    }

    @Override
    protected boolean validate() {
        return getData().length == 2;
    }
}
