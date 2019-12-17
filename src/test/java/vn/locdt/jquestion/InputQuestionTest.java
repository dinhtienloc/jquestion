package vn.locdt.jquestion;

import org.junit.Test;
import vn.locdt.jquestion.constant.VirtualKey;

import java.io.IOException;

import static junit.framework.TestCase.assertEquals;

public class InputQuestionTest extends JQuestionTest {

    @Test
    public void test() throws IOException {
        processInput(VirtualKey.ENTER);
        assertEquals("", jQuestion.input("Test input question").prompt());

    }
}
