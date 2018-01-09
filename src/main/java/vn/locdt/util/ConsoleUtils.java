package vn.locdt.util;

import org.fusesource.jansi.Ansi;
import org.fusesource.jansi.AnsiConsole;
import vn.locdt.JQuestion;
import vn.locdt.item.Choice;
import vn.locdt.item.Item;
import vn.locdt.item.Selector;
import vn.locdt.question.SingleChoiceQuestion;
import vn.locdt.question.Question;

import static org.fusesource.jansi.Ansi.ansi;

public class ConsoleUtils {
    private final static String OS_NAME = System.getProperty("os.name");

    public static void renderChoiceQuestion(SingleChoiceQuestion singleChoiceQuestion) {
        AnsiConsole.out.print(singleChoiceQuestion.getRenderHeight());
        AnsiConsole.out.print(ansi().cursorUp(singleChoiceQuestion.getRenderHeight()).a("\r").eraseScreen(Ansi.Erase.FORWARD).fg(Ansi.Color.DEFAULT).a(singleChoiceQuestion.toString()).eraseScreen(Ansi.Erase.FORWARD).reset());
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
