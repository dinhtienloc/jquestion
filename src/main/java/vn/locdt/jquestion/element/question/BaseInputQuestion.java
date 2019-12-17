package vn.locdt.jquestion.element.question;

import org.jline.reader.LineReader;
import org.jline.reader.impl.LineReaderImpl;
import vn.locdt.jquestion.element.item.Input;
import vn.locdt.jquestion.event.InputEvent;
import vn.locdt.jquestion.listener.InputListener;
import vn.locdt.jquestion.util.ConsoleUtils;

public abstract class BaseInputQuestion<V> extends Question<Input, V> implements InputListener<V> {

	BaseInputQuestion(LineReader reader, String title) {
		super(reader, new Input(title, null), true);
	}

	@Override
	public V prompt() {
		String title = ConsoleUtils.createTitle(this.item.getTitle());
		String result = this.lineReader.readLine(title + " ");
		return this.onInput(new InputEvent(result));
	}

	protected abstract V convertInput(String inputValue);

	@Override
	public V onInput(InputEvent e) {
		V value = this.convertInput(e.getInputValue());
		if (value != null) {
			this.setValue(value);
			if (this.isPrintedResult) ConsoleUtils.printResult(this);
			return value;
		} else {
			// the answer has a problem (wrong, null,...), rerender the question
			return this.prompt();
		}
	}
}
