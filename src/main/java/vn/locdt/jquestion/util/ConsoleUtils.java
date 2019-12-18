package vn.locdt.jquestion.util;

import org.fusesource.jansi.Ansi;
import org.fusesource.jansi.Ansi.Color;
import org.fusesource.jansi.AnsiConsole;
import vn.locdt.jquestion.element.item.Item;
import vn.locdt.jquestion.element.item.choice.Choice;
import vn.locdt.jquestion.element.item.choice.Selector;
import vn.locdt.jquestion.element.question.PasswordQuestion;
import vn.locdt.jquestion.element.question.Question;
import vn.locdt.jquestion.element.question.SingleChoiceQuestion;

import static org.fusesource.jansi.Ansi.ansi;


public class ConsoleUtils {

	public static String createTitle(String title) {
		return ansi().fg(Ansi.Color.DEFAULT).bold().a(title).boldOff().fg(Color.WHITE).toString();
	}

	public static void renderChoiceQuestion(SingleChoiceQuestion singleChoiceQuestion) {
		AnsiConsole.out.print(ansi().cursorUp(singleChoiceQuestion.getRenderHeight()).a("\r").eraseScreen(Ansi.Erase.FORWARD).fg(Ansi.Color.DEFAULT).a(singleChoiceQuestion.toString()).eraseScreen(Ansi.Erase.FORWARD).reset());
	}

	public static void renderQuestion(Question q) {
		AnsiConsole.out.print(ansi().fg(Ansi.Color.DEFAULT).bold().a(q).boldOff());
	}

	public static String printSelector(Selector selector) {
		Ansi.Color color = selector.getPrefix().equals(Choice.activedPrefix) ? Ansi.Color.CYAN : Ansi.Color.DEFAULT;
		return selector.getPrefix() +
				ansi().eraseScreen(Ansi.Erase.FORWARD).fg(color).a(selector.getValue()).reset().toString();
	}

	public static void printResult(Question inputQuestion) {
		String answer = inputQuestion.getValue().toString();

		if (inputQuestion instanceof PasswordQuestion)
			answer = answer.replaceAll(".", "*");

		Item item = inputQuestion.getItem();
		AnsiConsole.out.println(ansi().cursorUp(item.getRenderHeight()).a("\r").eraseScreen(Ansi.Erase.FORWARD)
				.a(createTitle(item.getTitle()))
				.fg(Ansi.Color.GREEN).bold().a(" " + answer).boldOff()
				.eraseScreen(Ansi.Erase.FORWARD).reset());
	}
}
