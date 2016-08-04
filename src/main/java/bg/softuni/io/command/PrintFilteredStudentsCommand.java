package bg.softuni.io.command;

import bg.softuni.annotation.Alias;
import bg.softuni.annotation.Inject;
import bg.softuni.contract.Database;
import bg.softuni.io.OutputWriter;
import bg.softuni.static_data.ExceptionMessages;

@Alias(value = "filter")
public class PrintFilteredStudentsCommand extends Command {

    @Inject
    private Database repository;

    public PrintFilteredStudentsCommand(String input, String[] data) {
        super(input, data);
    }

    @Override
    protected void doExecute() throws Exception {
        String[] data = super.getData();

        String course = data[1];
        String filter = data[2].toLowerCase();
        String takeCommand = data[3].toLowerCase();
        String takeQuantity = data[4].toLowerCase();

        tryParseParametersForFilter(takeCommand, takeQuantity, course, filter);
    }

    private void tryParseParametersForFilter(String takeCommand, String takeQuantity, String courseName, String filter) {
        if (!takeCommand.equals("take")) {
            OutputWriter.displayException(ExceptionMessages.INVALID_TAKE_COMMAND);
            return;
        }

        if (takeQuantity.equals("all")) {
            this.repository.filterAndTake(courseName, filter);
            return;
        }

        try {
            int studentsToTake = Integer.parseInt(takeQuantity);
            this.repository.filterAndTake(courseName, filter, studentsToTake);
        } catch (NumberFormatException nfe) {
            OutputWriter.displayException(ExceptionMessages.IVALID_TAKE_QUANTITY_PARAMETER);
        }
    }

    @Override
    protected boolean validate() {
        return getData().length == 5;
    }
}
