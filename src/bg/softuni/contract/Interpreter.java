package bg.softuni.contract;

import java.io.IOException;

public interface Interpreter {
    void interpretCommand(String input) throws IOException;
}
