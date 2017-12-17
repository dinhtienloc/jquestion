package vn.locdt.util;

import org.fusesource.jansi.Ansi;
import org.fusesource.jansi.AnsiConsole;
import vn.locdt.eception.EmptySelectorListException;
import vn.locdt.item.Choice;
import vn.locdt.item.Input;
import vn.locdt.item.Item;
import vn.locdt.item.Selector;
import vn.locdt.question.ChoiceQuestion;
import vn.locdt.question.InputQuestion;
import vn.locdt.question.Question;

import java.util.List;

import static org.fusesource.jansi.Ansi.ansi;

public class ConsoleUtils {
    public static void rerenderChoiceQuestion(ChoiceQuestion choiceQuestion) {
        AnsiConsole.out.println(ansi().cursorUp(choiceQuestion.getItem().getChoiceList().size()+2).fg(Ansi.Color.DEFAULT).a(choiceQuestion.toString()).eraseScreen(Ansi.Erase.FORWARD).reset());
    }

    public static String printSelector(Selector selector) {
        Ansi.Color color = selector.getPrefix().equals(Choice.activedPrefix) ? Ansi.Color.CYAN : Ansi.Color.DEFAULT;
        return selector.getPrefix() +
                ansi().fg(color).a(selector.getValue()).reset().toString();
    }

    public static void printResult(Question inputQuestion) {
        Item item = inputQuestion.getItem();
        AnsiConsole.out.println(ansi().cursorUp(item.getRenderHeight()).fg(Ansi.Color.DEFAULT).a(item.getTitle())
                                .fg(Ansi.Color.GREEN).a("("+item.getResultHandler().getValue()+")")
                                .eraseScreen(Ansi.Erase.FORWARD).reset());
    }
}
