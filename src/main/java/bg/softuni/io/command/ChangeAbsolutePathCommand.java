package bg.softuni.io.command;

import bg.softuni.annotation.Alias;
import bg.softuni.annotation.Inject;
import bg.softuni.io.IOManager;

@Alias(value = "cdabs")
public class ChangeAbsolutePathCommand extends Command {

    @Inject
    private IOManager IoManager;

    public ChangeAbsolutePathCommand(String input, String[] data) {
        super(input, data);
    }

    @Override
    public boolean validate() {
        return getData().length == 2;
    }

    @Override
    protected void doExecute() throws Exception {
        String absolutePath = getData()[1];
        this.IoManager.changeCurrentDirAbsolutePath(absolutePath);
    }
}
