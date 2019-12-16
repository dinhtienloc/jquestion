package vn.locdt.jquestion;

import org.fusesource.jansi.AnsiConsole;
import org.jline.reader.LineReader;
import org.jline.reader.LineReaderBuilder;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;

import java.io.IOException;
import java.nio.charset.Charset;


public class Main {
    public static void main(String[] args) throws IOException {
        AnsiConsole.systemInstall();
        Terminal terminal = TerminalBuilder.builder()
                .system(true)
                .dumb(false)
                .encoding(Charset.forName("UTF-8"))
                .jansi(true)
                .build();
        LineReader lr = LineReaderBuilder.builder().terminal(terminal).build();
        JQuestion.select(lr, "test", new String[]{"a", "b", "c"}).prompt();
    }

}

