package bg.softuni.io.command;

import bg.softuni.annotation.Alias;
import bg.softuni.annotation.Inject;
import bg.softuni.contract.ContentComparer;

@Alias(value = "cmp")
public class CompareFilesCommand extends Command {

    @Inject
    private ContentComparer tester;

    public CompareFilesCommand(String input, String[] data) {
        super(input, data);
    }

    @Override
    protected void doExecute() throws Exception {
        String firstPath = getData()[1];
        String secondPath = getData()[2];

        this.tester.compareContent(firstPath, secondPath);
    }

    @Override
    protected boolean validate() {
        return getData().length == 3;
    }
}
