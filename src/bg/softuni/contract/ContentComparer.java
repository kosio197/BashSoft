package bg.softuni.contract;

import java.io.IOException;

public interface ContentComparer {
    void compareContent(String actualOutput, String expectedOutput) throws IOException;
}
