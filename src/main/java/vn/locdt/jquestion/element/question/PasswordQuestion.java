package vn.locdt.jquestion.element.question;

import org.jline.reader.LineReader;
import vn.locdt.jquestion.event.InputEvent;
import vn.locdt.jquestion.util.ConsoleUtils;

public class PasswordQuestion extends InputQuestion {
	private Character mask;

	public PasswordQuestion(LineReader reader, String title, Character mask) {
		super(reader, title);
		this.mask = mask;
	}

	@Override
	public String prompt() {
		String title = ConsoleUtils.createTitle(this.item.getTitle());
		String result = this.lineReader.readLine(title + " ", this.mask);
		return this.onInput(new InputEvent(result));
	}

	public Character getMask() {
		return this.mask;
	}

	public void setMask(Character mask) {
		this.mask = mask;
	}
}
