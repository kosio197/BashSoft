package bg.softuni.io.command;

import bg.softuni.annotation.Alias;
import bg.softuni.annotation.Inject;
import bg.softuni.contract.Database;
import bg.softuni.io.OutputWriter;
import bg.softuni.static_data.ExceptionMessages;

@Alias(value = "order")
public class PrintOrderedStudentsCommand extends Command {

    @Inject
    private Database repository;

    public PrintOrderedStudentsCommand(String input, String[] data) {
        super(input, data);
    }

    @Override
    protected void doExecute() throws Exception {
        String [] data = getData();

        String courseName = data[1];
        String orderType = data[2].toLowerCase();
        String takeCommand = data[3].toLowerCase();
        String takeQuantity = data[4].toLowerCase();

        tryParseParametersForOrder(takeCommand, takeQuantity, courseName, orderType);

    }

    private void tryParseParametersForOrder(String takeCommand, String takeQuantity, String courseName, String orderType) {
        if (!takeCommand.equals("take")) {
            OutputWriter.displayException(ExceptionMessages.INVALID_TAKE_COMMAND);
            return;
        }

        if (takeQuantity.equals("all")) {
            this.repository.orderAndTake(courseName, orderType);
            return;
        }

        try {
            int studentsToTake = Integer.parseInt(takeQuantity);
            this.repository.orderAndTake(courseName, orderType, studentsToTake);
        } catch (NumberFormatException nfe) {
            OutputWriter.displayException(ExceptionMessages.IVALID_TAKE_QUANTITY_PARAMETER);
        }
    }

    @Override
    protected boolean validate() {
        return getData().length == 5;
    }
}
