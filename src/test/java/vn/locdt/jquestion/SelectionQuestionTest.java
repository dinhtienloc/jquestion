package vn.locdt.jquestion;

import org.junit.Ignore;
import org.junit.Test;
import vn.locdt.jquestion.constant.VirtualKey;
import vn.locdt.jquestion.constant.VirtualKey.ArrowKey;
import vn.locdt.jquestion.constant.VirtualKey.WindowOS;
import vn.locdt.jquestion.element.question.SingleChoiceQuestion;

import java.io.IOException;

import static junit.framework.TestCase.assertEquals;

public class SelectionQuestionTest extends JQuestionTest {
    @Test
    public void test() throws IOException {
        JQuestion jQuestion = JQuestion.instance();
        SingleChoiceQuestion q = jQuestion.select("Test selection input", new String[]{"a", "b", "c"});

        processInput(VirtualKey.ENTER);
        assertEquals("a", q.prompt());

        processInput(ArrowKey.DOWN, ArrowKey.DOWN);
        processInput(VirtualKey.ENTER);
        assertEquals("c", q.prompt());


    }
}
