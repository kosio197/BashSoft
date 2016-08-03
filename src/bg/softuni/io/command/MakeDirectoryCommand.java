package bg.softuni.io.command;

import bg.softuni.annotation.Alias;
import bg.softuni.annotation.Inject;
import bg.softuni.contract.DirectoryManager;

@Alias(value = "mkdir")
public class MakeDirectoryCommand extends Command {

    @Inject
    private DirectoryManager ioManager;

    public MakeDirectoryCommand(String input, String[] data) {
        super(input, data);
    }

    @Override
    protected void doExecute() throws Exception {
        String folderName = super.getData()[1];
        this.ioManager.createDirectoryInCurrentFolder(folderName);
    }

    @Override
    protected boolean validate() {
        return super.getData().length == 2;
    }
}
