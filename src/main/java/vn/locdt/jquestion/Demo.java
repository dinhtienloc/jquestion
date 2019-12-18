package vn.locdt.jquestion;

import org.fusesource.jansi.AnsiConsole;
import org.jline.reader.LineReader;
import org.jline.reader.LineReaderBuilder;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;

import java.io.IOException;
import java.nio.charset.StandardCharsets;


public class Demo {
    public static void main(String[] args) throws IOException {
        AnsiConsole.systemInstall();
        Terminal terminal = TerminalBuilder.builder()
                .system(true)
                .encoding(StandardCharsets.UTF_8)
                .jansi(true)
                .build();
        LineReader lr = LineReaderBuilder.builder().terminal(terminal).build();
        JQuestion jQuestion = JQuestion.initialize(lr);

        String value = jQuestion.input("What's your name?").prompt();
        System.out.println("System out: " + value);

        value = jQuestion.select("What's your gender?", new String[]{"Male", "Female"}).prompt();
        System.out.println("System out: " + value);

        boolean bool = jQuestion.confirm("Do you like JQuestion?").prompt();
        System.out.println("System out: " + bool);

    }
}

