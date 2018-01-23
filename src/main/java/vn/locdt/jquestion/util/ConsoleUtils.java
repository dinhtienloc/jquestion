package vn.locdt.jquestion.util;

import org.fusesource.jansi.Ansi;
import org.fusesource.jansi.AnsiConsole;
import vn.locdt.jquestion.element.item.Choice;
import vn.locdt.jquestion.element.item.Item;
import vn.locdt.jquestion.element.item.Selector;
import vn.locdt.jquestion.element.question.SingleChoiceQuestion;
import vn.locdt.jquestion.element.question.Question;

import static org.fusesource.jansi.Ansi.ansi;

public class ConsoleUtils {
    private final static String OS_NAME = System.getProperty("os.name");

    public static void renderChoiceQuestion(SingleChoiceQuestion singleChoiceQuestion) {
        AnsiConsole.out.print(ansi().cursorUp(singleChoiceQuestion.getRenderHeight()).a("\r").eraseScreen(Ansi.Erase.FORWARD).fg(Ansi.Color.DEFAULT).a(singleChoiceQuestion.toString()).eraseScreen(Ansi.Erase.FORWARD).reset());
    }

    public static void renderQuestion(Question q) {
        AnsiConsole.out.print(ansi().fg(Ansi.Color.DEFAULT).a(q));
    }

    public static String printSelector(Selector selector) {
        Ansi.Color color = selector.getPrefix().equals(Choice.activedPrefix) ? Ansi.Color.CYAN : Ansi.Color.DEFAULT;
        return selector.getPrefix() +
                ansi().eraseScreen(Ansi.Erase.FORWARD).fg(color).a(selector.getValue()).reset().toString();
    }

    public static void printResult(Question inputQuestion) {
        Item item = inputQuestion.getItem();
        AnsiConsole.out.println(ansi().cursorUp(item.getRenderHeight()).a("\r").eraseScreen(Ansi.Erase.FORWARD)
                                .fg(Ansi.Color.DEFAULT).a(item.getTitle())
                                .fg(Ansi.Color.GREEN).a(" ("+inputQuestion.getAnswerValue()+")")
                                .eraseScreen(Ansi.Erase.FORWARD).reset());
    }

    public static boolean isWindowOS() {
        return OS_NAME.startsWith("Windows");
    }
}
