package vn.locdt.jquestion;

import org.fusesource.jansi.AnsiConsole;
import org.jline.reader.LineReader;
import org.jline.reader.LineReaderBuilder;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;


public class Main {
    public static void main(String[] args) throws IOException {
        AnsiConsole.systemInstall();
        Terminal terminal = TerminalBuilder.builder()
                .system(true)
                .encoding(StandardCharsets.UTF_8)
                .jansi(true)
                .build();
        LineReader lr = LineReaderBuilder.builder().terminal(terminal).build();
        JQuestion jQuestion = JQuestion.initialize(lr);
//        JQuestion.select(lr, "test", new String[]{"a", "b", "c"}).prompt();
//        Boolean value = jQuestion.confirm("test").prompt();
        System.out.println("Inputed: " + jQuestion.confirm("test").prompt());
    }

}

