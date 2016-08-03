package bg.softuni.io.command;

import bg.softuni.annotation.Alias;
import bg.softuni.annotation.Inject;
import bg.softuni.contract.DirectoryManager;

@Alias(value = "ls")
public class TraverseFoldersCommand extends Command {

    @Inject
    private DirectoryManager ioManager;

    public TraverseFoldersCommand(String input, String[] data) {
        super(input, data);
    }

    @Override
    protected void doExecute() throws Exception {
        if (getData().length == 1) {
            this.ioManager.traverseDirectory(0);
        }

        if (getData().length == 2) {
            this.ioManager.traverseDirectory(Integer.valueOf(getData()[1]));
        }
    }

    @Override
    protected boolean validate() {
        return getData().length == 1 || getData().length == 2;
    }
}
