package vn.locdt.jquestion;

import org.jline.reader.LineReader;
import org.jline.reader.impl.LineReaderImpl;
import org.jline.terminal.Size;
import org.jline.terminal.Terminal;
import org.jline.terminal.impl.AbstractWindowsTerminal;
import org.junit.Before;
import vn.locdt.jquestion.constant.VirtualKey;
import vn.locdt.jquestion.constant.VirtualKey.ArrowKey;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.charset.Charset;

import static org.jline.terminal.impl.AbstractWindowsTerminal.TYPE_WINDOWS_256_COLOR;

class JQuestionTest {
    static AbstractWindowsTerminal  terminal;
    static JQuestion jQuestion;

    static {
        try {
            terminal = new AbstractWindowsTerminal(new BufferedWriter(new StringWriter()), "name", TYPE_WINDOWS_256_COLOR, Charset.defaultCharset(), 0,
                    false, Terminal.SignalHandler.SIG_DFL) {
                @Override
                protected int getConsoleOutputCP() {
                    return 0;
                }

                @Override
                protected int getConsoleMode() {
                    return 0;
                }

                @Override
                protected void setConsoleMode(int mode) {
                }

                @Override
                protected boolean processConsoleInput() {
                    return false;
                }

                @Override
                public Size getSize() {
                    return new Size(80, 25);
                }
            };

            LineReader lineReader = new LineReaderImpl(terminal);
            jQuestion = JQuestion.initialize(lineReader);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    static void processInput(char... inputs) throws IOException {
        for (char input : inputs) {
            terminal.processInputChar(input);
        }
    }

    static void processInput(ArrowKey... arrowKeys) throws IOException {
        for (ArrowKey arrowKey : arrowKeys) {
            char[] chars = VirtualKey.getArrowCharCode(arrowKey);
            if (chars != null) {
                processInput(chars);
            }
        }
    }
}
