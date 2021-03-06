package bg.softuni.io;

import java.io.File;
import java.util.LinkedList;
import java.util.Queue;

import bg.softuni.contract.DirectoryManager;
import bg.softuni.exception.InvalidFileNameException;
import bg.softuni.exception.InvalidPathException;
import bg.softuni.static_data.SessionData;

public class IOManager implements DirectoryManager {
    @Override
    public void traverseDirectory(int depth) {
        Queue<File> subFolders = new LinkedList<>();

        String path = SessionData.currentPath;
        int initialIndentation = path.split(File.separator).length;

        File root = new File(path);

        subFolders.add(root);

        while (subFolders.size() != 0) {
            File currentFolder = subFolders.poll();
            int currentIndentation = currentFolder.toString().split(File.separator).length - initialIndentation;

            if (depth - currentIndentation < 0) {
                break;
            }

            OutputWriter.writeMessageOnNewLine(currentFolder.toString());

            if (currentFolder.listFiles() != null) {
                for (File file : currentFolder.listFiles()) {
                    if (file.isDirectory()) {
                        subFolders.add(file);
                    } else {
                        int indexOfLastSlash = file.toString().lastIndexOf(File.separator);
                        for (int i = 0; i < indexOfLastSlash; i++) {
                            OutputWriter.writeMessage("-");
                        }

                        OutputWriter.writeMessageOnNewLine(file.getName());
                    }
                }
            }
        }
    }

    @Override
    public void createDirectoryInCurrentFolder(String name) {
        String path = SessionData.currentPath + File.separator + name;
        File file = new File(path);

        boolean wasDirMade = file.mkdir();

        if (!wasDirMade) {
            throw new InvalidFileNameException();
        }
    }

    @Override
    public void changeCurrentDirRelativePath(String relativePath) {
        if (relativePath.equals("..")) {
            // go one directory up
            try {
                String currentPath = SessionData.currentPath;
                int indexOfLastSlash = currentPath.lastIndexOf(File.separator);
                String newPath = currentPath.substring(0, indexOfLastSlash);
                SessionData.currentPath = newPath;
            } catch (StringIndexOutOfBoundsException sioobe) {
                throw new InvalidPathException();
            }
        } else {
            // go to a given directory
            String currentPath = SessionData.currentPath;
            currentPath += File.separator + relativePath;
            changeCurrentDirAbsolutePath(currentPath);
        }
    }

    @Override
    public void changeCurrentDirAbsolutePath(String absolutePath) {
        File file = new File(absolutePath);
        if (!file.exists()) {
            throw new InvalidPathException();
        }

        SessionData.currentPath = absolutePath;
    }
}

