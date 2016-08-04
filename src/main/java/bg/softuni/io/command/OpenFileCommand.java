package bg.softuni.io.command;

import java.awt.Desktop;
import java.io.File;

import bg.softuni.annotation.Alias;
import bg.softuni.static_data.SessionData;

@Alias(value = "open")
public class OpenFileCommand extends Command {

    public OpenFileCommand(String input, String[] data) {
        super(input, data);
    }

    @Override
    protected void doExecute() throws Exception {
        String[] data = super.getData();

        String fileName = data[1];
        String filePath = SessionData.currentPath + File.separator + fileName;
        File file = new File(filePath);

        Desktop.getDesktop().open(file);
    }

    @Override
    protected boolean validate() {
        return getData().length == 2;
    }
}
