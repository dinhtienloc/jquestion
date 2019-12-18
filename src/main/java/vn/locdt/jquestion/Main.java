package vn.locdt.jquestion;

import org.fusesource.jansi.AnsiConsole;
import org.jline.reader.LineReader;
import org.jline.reader.LineReaderBuilder;
import org.jline.reader.impl.LineReaderImpl;
import org.jline.terminal.Size;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;
import org.jline.terminal.impl.AbstractWindowsTerminal;
import vn.locdt.jquestion.constant.VirtualKey;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import static org.jline.terminal.impl.AbstractWindowsTerminal.TYPE_WINDOWS_256_COLOR;


public class Main {
    public static void main(String[] args) throws IOException {
//        AnsiConsole.systemInstall();
//        Terminal terminal = TerminalBuilder.builder()
//                .system(true)
//                .encoding(StandardCharsets.UTF_8)
//                .jansi(true)
//                .build();
//        LineReader lr = LineReaderBuilder.builder().terminal(terminal).build();
//        JQuestion jQuestion = JQuestion.initialize(lr);
//        jQuestion.select("test", new String[]{"a", "b", "c"}).prompt();
//        EofPipedInputStream in = new EofPipedInputStream();
//        ByteArrayOutputStream out = new ByteArrayOutputStream();
//
//        Terminal terminal = TerminalBuilder.builder()
//                .system(false)
//                .streams(in, out)
//                .build();
//
//        in.setIn(new ByteArrayInputStream("\uD834\uDD21abc".getBytes(StandardCharsets.UTF_8)));
//        LineReaderImpl lineReader = new LineReaderImpl(terminal);
//        JQuestion jQuestion = JQuestion.initialize(lineReader);
////        jQuestion.select("test", new String[]{"a", "b", "c"}).prompt();
//        jQuestion.input("test").prompt();

    }

    public static class EofPipedInputStream extends InputStream {

        private InputStream in;

        public InputStream getIn() {
            return in;
        }

        public void setIn(InputStream in) {
            this.in = in;
        }

        @Override
        public int read() throws IOException {
            return in != null ? in.read() : -1;
        }

        @Override
        public int available() throws IOException {
            return in != null ? in.available() : 0;
        }
    }
}

