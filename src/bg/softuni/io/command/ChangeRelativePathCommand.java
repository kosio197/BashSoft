package bg.softuni.io.command;

import bg.softuni.annotation.Alias;
import bg.softuni.annotation.Inject;
import bg.softuni.io.IOManager;

@Alias(value = "cdrel")
public class ChangeRelativePathCommand extends Command {

    @Inject
    private IOManager IoManager;

    public ChangeRelativePathCommand(String input, String[] data) {
        super(input, data);
    }

    @Override
    protected void doExecute() throws Exception {
        String relativePath = super.getData()[1];
        this.IoManager.changeCurrentDirRelativePath(relativePath);
    }

    @Override
    protected boolean validate() {
        return super.getData().length == 2;
    }
}
