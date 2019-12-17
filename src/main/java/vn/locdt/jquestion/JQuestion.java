package vn.locdt.jquestion;

import org.jline.reader.LineReader;
import org.jline.terminal.Attributes;
import org.jline.terminal.Terminal;
import org.jline.utils.NonBlockingReader;
import vn.locdt.jquestion.element.question.ConfirmQuestion;
import vn.locdt.jquestion.element.question.InputQuestion;
import vn.locdt.jquestion.element.question.Question;
import vn.locdt.jquestion.element.question.SingleChoiceQuestion;

import java.io.IOException;
import java.util.List;

public class JQuestion {
    private NonBlockingReader nonBlockingReader;
    private LineReader lineReader;

    private static JQuestion instance;

    public static JQuestion initialize(LineReader lineReader) {
        if (JQuestion.instance != null) {
            throw new ExceptionInInitializerError("Can not call create method twice");
        }
        JQuestion.instance = new JQuestion(lineReader);
        return JQuestion.instance;
    }

    public static JQuestion instance() {
        return JQuestion.instance;
    }

    private JQuestion(LineReader lineReader) {
        this.lineReader = lineReader;
    }

    public InputQuestion input(String title) {
        return new InputQuestion(this.lineReader, title);
    }

    public SingleChoiceQuestion select(String title, String[] selection) {
        return new SingleChoiceQuestion(this.lineReader, title, null, selection);
    }

    public SingleChoiceQuestion select(String title, List<String> selection) {
        return new SingleChoiceQuestion(this.lineReader, title, null, selection);
    }

    public ConfirmQuestion confirm(String title) {
        return new ConfirmQuestion(this.lineReader, title);

    }

    public NonBlockingReader startCharacterReader() {
        if (this.nonBlockingReader == null) {
            Terminal terminal = this.lineReader.getTerminal();
            terminal.enterRawMode();
            this.nonBlockingReader = terminal.reader();
        }
        return this.nonBlockingReader;
    }

    public void stopCharacterReader() {
        if (this.nonBlockingReader != null) {
            // remove raw mode attributes
            this.lineReader.getTerminal().setAttributes(new Attributes());
            this.nonBlockingReader = null;
        }
    }

    public LineReader getLineReader() {
        return this.lineReader;
    }
}
