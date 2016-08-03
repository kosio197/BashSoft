package bg.softuni.io.command;

import bg.softuni.annotation.Alias;
import bg.softuni.io.OutputWriter;

@Alias(value = "help")
public class GetHelpCommand extends Command {

    public GetHelpCommand(String input, String[] data) {
        super(input, data);
    }

    @Override
    public void doExecute() throws Exception {
        StringBuilder helpBuilder = new StringBuilder();

        helpBuilder.append("make directory - mkdir nameOfFolder").append(System.lineSeparator());
        helpBuilder.append("traverse directory - ls depth").append(System.lineSeparator());
        helpBuilder.append("comparing files - cmp absolutePath1 absolutePath2").append(System.lineSeparator());
        helpBuilder.append("change directory - cdrel relativePath or \"..\" for level up").append(System.lineSeparator());
        helpBuilder.append("change directory - cdabs absolutePath").append(System.lineSeparator());
        helpBuilder.append("read students data base - readdb fileName").append(System.lineSeparator());
        helpBuilder.append("drop students data base - dropdb").append(System.lineSeparator());
        helpBuilder.append("filter students - filter {courseName} excellent/average/poor take 2/5/all").append(System.lineSeparator());
        helpBuilder.append("order students - order {courseName} ascending/descending take 20/10/all").append(System.lineSeparator());
        helpBuilder.append("show course – show {courseName} {studentName}").append(System.lineSeparator());
        helpBuilder.append("download file - download URL (saved in current directory)").append(System.lineSeparator());
        helpBuilder.append("download file on new thread - downloadAsynch URL (saved in the current directory)").append(System.lineSeparator());
        helpBuilder.append("open file – open {fileName}").append(System.lineSeparator());
        helpBuilder.append("display data entities - display students/courses ascending/descending");
        helpBuilder.append("get help – help").append(System.lineSeparator());

        OutputWriter.writeMessage(helpBuilder.toString());
        OutputWriter.writeEmptyLine();    }

    @Override
    public boolean validate() {
        return getData().length == 1;
    }
}
