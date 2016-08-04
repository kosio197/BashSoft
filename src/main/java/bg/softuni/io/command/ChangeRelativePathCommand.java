package bg.softuni.io.command;

import bg.softuni.annotation.Alias;
import bg.softuni.annotation.Inject;
import bg.softuni.contract.DirectoryManager;

@Alias(value = "cdrel")
public class ChangeRelativePathCommand extends Command {

    @Inject
    private DirectoryManager ioManager;

    public ChangeRelativePathCommand(String input, String[] data) {
        super(input, data);
    }

    @Override
    protected void doExecute() throws Exception {
        String relativePath = super.getData()[1];
        this.ioManager.changeCurrentDirRelativePath(relativePath);
    }

    @Override
    protected boolean validate() {
        return super.getData().length == 2;
    }
}
