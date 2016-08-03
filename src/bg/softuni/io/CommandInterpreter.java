package bg.softuni.io;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

import bg.softuni.annotation.Alias;
import bg.softuni.annotation.Inject;
import bg.softuni.contract.AsynchDownloader;
import bg.softuni.contract.ContentComparer;
import bg.softuni.contract.Database;
import bg.softuni.contract.DirectoryManager;
import bg.softuni.contract.Executable;
import bg.softuni.contract.Interpreter;

public class CommandInterpreter implements Interpreter {

    private static final String COMMAND_LOCATION = "src/bg/softuni/io/command";
    private static final String COMMAND_PAKAGE = "bg.softuni.io.command.";

    @SuppressWarnings("unused")
    private ContentComparer tester;
    @SuppressWarnings("unused")
    private Database repository;
    @SuppressWarnings("unused")
    private AsynchDownloader downloadManager;
    @SuppressWarnings("unused")
    private DirectoryManager ioManager;

    public CommandInterpreter(ContentComparer tester, Database repository, AsynchDownloader downloadManager,
            DirectoryManager ioManager) {
        this.tester = tester;
        this.repository = repository;
        this.downloadManager = downloadManager;
        this.ioManager = ioManager;
    }

    @Override
    public void interpretCommand(String input) {
        String[] data = input.split("\\s+");
        String commandName = data[0].toLowerCase();

        try {
            Executable command = parseCommand(input, data, commandName);
            command.execute();
        } catch (Throwable e) {
            OutputWriter.displayException(e.getMessage());
        }
    }

    private Executable parseCommand(String input, String [] data, String command) throws IOException {
        File commandsFolder = new File(COMMAND_LOCATION);
        Executable executable = null;

        for (File file : commandsFolder.listFiles()) {
            if (!file.isFile() || !file.getName().endsWith(".java")) {
                continue;
            }

            try {
                String className = file.getName().substring(0, file.getName().lastIndexOf('.'));
                @SuppressWarnings("unchecked")
                Class<Executable> exeClass = (Class<Executable>) Class.forName(COMMAND_PAKAGE + className);

                if (!exeClass.isAnnotationPresent(Alias.class)) {
                    continue;
                }

                Alias alias = exeClass.getAnnotation(Alias.class);
                String value = alias.value();

                if (!value.equalsIgnoreCase(command)) {
                    continue;
                }

                Constructor<Executable> exeCtor = exeClass.getConstructor(String.class, String[].class);
                executable = exeCtor.newInstance(input, data);

                this.injectDependancies(executable, exeClass);

            } catch (ReflectiveOperationException roe) {
                roe.printStackTrace();
            }
        }
        return executable;
    }

    private void injectDependancies(Executable executable, Class<Executable> exeClass)
            throws ReflectiveOperationException {

        Field exeClassField[] = exeClass.getDeclaredFields();
        Field thisField[] = CommandInterpreter.class.getDeclaredFields();

        for (Field field : exeClassField) {
            if (!field.isAnnotationPresent(Inject.class)) {
                continue;
            }
            field.setAccessible(true);

            for (Field currentfield : thisField) {
                if (!field.getType().equals(currentfield.getType())) {
                    continue;
                }

                currentfield.setAccessible(true);
                field.set(executable, currentfield.get(this));
            }
        }
    }

}
