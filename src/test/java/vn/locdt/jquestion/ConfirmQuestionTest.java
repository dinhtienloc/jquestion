package vn.locdt.jquestion;

import junit.framework.TestCase;
import org.jline.reader.EndOfFileException;
import org.junit.Test;
import vn.locdt.jquestion.constant.VirtualKey;

import java.io.IOException;
import java.util.Arrays;

public class ConfirmQuestionTest extends JQuestionTest {

    @Test(expected = EndOfFileException.class)
    public void loopForYOrNAnswer() throws IOException {
        char[] enterChars = new char[10];
        Arrays.fill(enterChars, VirtualKey.ENTER);
        processInput(enterChars);
        processInput(VirtualKey.EOF);
        jQuestion.confirm("Test confirm question").prompt();
        TestCase.fail("Should have thrown an EndOfFileException");
    }

    @Test
    public void testYesAnswer() throws IOException {
        processInput('y', VirtualKey.ENTER);
        TestCase.assertEquals(Boolean.TRUE, jQuestion.confirm("Test confirm question").prompt());
    }

    @Test
    public void testNoAnswer() throws IOException {
        processInput('n', VirtualKey.ENTER);
        TestCase.assertEquals(Boolean.FALSE, jQuestion.confirm("Test confirm question").prompt());
    }
}
