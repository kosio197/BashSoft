package bg.softuni.io;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import bg.softuni.contract.Interpreter;
import bg.softuni.contract.Reader;
import bg.softuni.static_data.SessionData;

public class InputReader implements Reader {

    private static final String END_COMMAND = "quit";

    private Interpreter interpreter;

    public InputReader(Interpreter interpreter) {
        this.interpreter = interpreter;
    }

    @Override
    public void readCommands() throws Exception {
        OutputWriter.writeMessage(String.format("%s > ", SessionData.currentPath));

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String input = reader.readLine().trim();

        while (!input.equals(END_COMMAND)) {
            interpreter.interpretCommand(input);
            OutputWriter.writeMessage(String.format("%s > ", SessionData.currentPath));

            input = reader.readLine().trim();
        }

        for (Thread thread : SessionData.threadPool) {
            thread.join();
        }
    }
}
