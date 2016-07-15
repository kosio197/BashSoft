package bg.softuni.io;

import java.io.IOException;

import bg.softuni.contract.AsynchDownloader;
import bg.softuni.contract.ContentComparer;
import bg.softuni.contract.Database;
import bg.softuni.contract.DirectoryManager;
import bg.softuni.contract.Executable;
import bg.softuni.contract.Interpreter;
import bg.softuni.exceptions.InvalidCommandException;
import bg.softuni.io.commands.ChangeAbsolutePathCommand;
import bg.softuni.io.commands.ChangeRelativePathCommand;
import bg.softuni.io.commands.CompareFilesCommand;
import bg.softuni.io.commands.DownloadAsynchCommand;
import bg.softuni.io.commands.DownloadFileCommand;
import bg.softuni.io.commands.DropDatabaseCommand;
import bg.softuni.io.commands.GetHelpCommand;
import bg.softuni.io.commands.MakeDirectoryCommand;
import bg.softuni.io.commands.OpenFileCommand;
import bg.softuni.io.commands.PrintFilteredStudentsCommand;
import bg.softuni.io.commands.PrintOrderedStudentsCommand;
import bg.softuni.io.commands.ReadDatabaseCommand;
import bg.softuni.io.commands.ShowCourseCommand;
import bg.softuni.io.commands.TraverseFoldersCommand;

public class CommandInterpreter implements Interpreter {
    private ContentComparer tester;
    private Database repository;
    private AsynchDownloader downloadManager;
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
        switch (command) {
        case "open": return new OpenFileCommand(input, data, repository, tester, ioManager, downloadManager);
        case "mkdir": return new MakeDirectoryCommand(input, data, repository, tester, ioManager, downloadManager);
        case "ls": return new TraverseFoldersCommand(input, data, repository, tester, ioManager, downloadManager);
        case "cmp": return new CompareFilesCommand(input, data, repository, tester, ioManager, downloadManager);
        case "cdrel": return new ChangeRelativePathCommand(input, data, repository, tester, ioManager, downloadManager);
        case "cdabs": return new ChangeAbsolutePathCommand(input, data, repository, tester, ioManager, downloadManager);
        case "readdb": return new ReadDatabaseCommand(input, data, repository, tester, ioManager, downloadManager);
        case "help": return new GetHelpCommand(input, data, repository, tester, ioManager, downloadManager);
        case "show": return new ShowCourseCommand(input, data, repository, tester, ioManager, downloadManager);
        case "filter": return new PrintFilteredStudentsCommand(input, data, repository, tester, ioManager, downloadManager);
        case "order": return new PrintOrderedStudentsCommand(input, data, repository, tester, ioManager, downloadManager);
        case "download": return new DownloadFileCommand(input, data, repository, tester, ioManager, downloadManager);
        case "downloadasynch": return new DownloadAsynchCommand(input, data, repository, tester, ioManager, downloadManager);
        case "dropdb": return new DropDatabaseCommand(input, data, repository, tester, ioManager, downloadManager);

        default: throw new InvalidCommandException(input);
        }
    }
}
