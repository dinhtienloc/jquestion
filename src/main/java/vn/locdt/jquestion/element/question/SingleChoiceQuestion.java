package vn.locdt.jquestion.element.question;

import org.jline.reader.LineReader;
import org.jline.utils.NonBlockingReader;
import vn.locdt.jquestion.JQuestion;
import vn.locdt.jquestion.constant.VirtualKey;
import vn.locdt.jquestion.element.item.choice.Selector;
import vn.locdt.jquestion.element.item.choice.SingleChoice;
import vn.locdt.jquestion.event.ChangeSelectorEvent;
import vn.locdt.jquestion.event.ChooseSelectorEvent;
import vn.locdt.jquestion.event.NonBlockInputEvent;
import vn.locdt.jquestion.listener.ChoiceListener;
import vn.locdt.jquestion.listener.NonBlockInputListener;
import vn.locdt.jquestion.util.ConsoleUtils;
import vn.locdt.jquestion.util.DetectArrowKey;

import java.io.IOException;
import java.util.List;

public class SingleChoiceQuestion extends Question<SingleChoice, String> implements NonBlockInputListener, ChoiceListener {

	private SingleChoiceQuestion(LineReader reader, String title, String name) {
		super(reader, new SingleChoice(title, name));
	}

	SingleChoiceQuestion(LineReader reader, String title, String name, boolean isPrintedResult) {
		this(reader, title, name);
		this.isPrintedResult = isPrintedResult;
		this.updateRenderHeight();
	}

	public SingleChoiceQuestion(LineReader reader, String title, String name, List<Selector> selectors, boolean isPrintedResult) throws IOException {
		this(reader, title, name, isPrintedResult);
		this.item.setSelectors(selectors);
		this.updateRenderHeight();
	}

	public SingleChoiceQuestion(LineReader reader, String title, String name, String[] selections) {
		this(reader, title, name);
		for (Object select : selections) {
			this.item.addSelector(new Selector(select.toString()));
		}
		this.updateRenderHeight();
	}

	public SingleChoiceQuestion(LineReader reader, String title, String name, List<String> selections) {
		this(reader, title, name);
		for (Object select : selections) {
			this.item.addSelector(new Selector(select.toString()));
		}
		this.updateRenderHeight();
	}

	public SingleChoiceQuestion addSelector(String value) {
		this.item.addSelector(new Selector(value));
		this.updateRenderHeight();
		return this;
	}

	public SingleChoiceQuestion addSelector(String value, boolean isActive) {
		Selector selector = new Selector(value);
		this.item.addSelector(selector);
		if (isActive)
			this.item.setActivedSelector(selector);

		this.updateRenderHeight();
		return this;
	}

	public SingleChoiceQuestion addSelectors(List<Selector> selectors) {
		this.item.addSelectors(selectors);
		this.updateRenderHeight();
		return this;
	}

	public SingleChoiceQuestion addSelectors(String... values) {
		this.item.addSelectors(values);
		this.updateRenderHeight();
		return this;
	}

	@Override
	public String prompt() {
		ConsoleUtils.renderQuestion(this);

		if (this.item.getSelectors().size() == 0) {
			this.setValue("");
		} else {

			// read input
			int input;
			boolean finished;
			JQuestion jQuestion = JQuestion.instance();
			NonBlockingReader nonBlockingReader = jQuestion.startCharacterReader();

			try {
				do {
					input = nonBlockingReader.read();
					finished = this.onInput(new NonBlockInputEvent(input));
				} while (!finished);
			} catch (IOException e) {
				e.printStackTrace();
			}

			jQuestion.stopCharacterReader();
		}

		return this.getValue();
	}

	private void changeActiveSelector(VirtualKey.ArrowKey arrowKey) {
		int cursor = this.item.indexOfActivedSelector();
		Selector lastSelector = this.item.getActivedSelector();
		Selector nextSelector;

		List<Selector> selectors = this.item.getSelectors();
		switch (arrowKey) {
			case UP:
				if (cursor > 0) cursor--;
				break;
			case DOWN:
				if (cursor < selectors.size() - 1) cursor++;
				break;
		}

		nextSelector = selectors.get(cursor);
		if (nextSelector != lastSelector) {
			this.item.setActivedSelector(nextSelector);
			this.onChanged(new ChangeSelectorEvent(lastSelector, nextSelector));
		}
	}

	protected boolean handleInput(int charCode, NonBlockInputEvent e) {
		if (charCode == VirtualKey.ENTER) {
			this.onChosen(new ChooseSelectorEvent(this.item.getActivedSelector()));
			e.stop();
		} else if (charCode == 27 && !DetectArrowKey.detecting) {
			DetectArrowKey.detect();
		} else if (DetectArrowKey.detecting) {
			VirtualKey.ArrowKey arrowKey = DetectArrowKey.update(charCode);
			if (arrowKey != null)
				this.changeActiveSelector(arrowKey);
		} else if (e.getAddedChar() == VirtualKey.EOF) {
			e.stop();
		} else if (ConsoleUtils.isWindowOS()) {
			this.handleWindowInput(charCode, e);
		}

		return e.isStop();
	}

	private void handleWindowInput(int charCode, NonBlockInputEvent e) {
		VirtualKey.ArrowKey arrowKey = null;
		switch (charCode) {
			case VirtualKey.WindowOS.DOWN:
				arrowKey = VirtualKey.ArrowKey.DOWN;
				break;
			case VirtualKey.WindowOS.UP:
				arrowKey = VirtualKey.ArrowKey.UP;
				break;
			case VirtualKey.WindowOS.LEFT:
				arrowKey = VirtualKey.ArrowKey.LEFT;
				break;
			case VirtualKey.WindowOS.RIGHT:
				arrowKey = VirtualKey.ArrowKey.RIGHT;
				break;
		}

		if (arrowKey != null)
			this.changeActiveSelector(arrowKey);
	}

	@Override
	public String toString() {
		String str = ConsoleUtils.createTitle(this.item.getTitle() + "\n");
		List<Selector> selectors = this.item.getSelectors();
		if (selectors.size() == 0)
			return str;

		if (this.item.getActivedSelector() == null)
			this.item.setActivedSelector(this.item.getSelectors().get(0));

		for (Selector selector : selectors) {
			str += ConsoleUtils.printSelector(selector);
			if (selectors.indexOf(selector) < selectors.size() - 1)
				str += "\n";
		}

		return str;
	}

	@Override
	public boolean onInput(NonBlockInputEvent e) {
		int charCode = e.getAddedChar();
//        System.out.println(charCode);
//        return false;
		return this.handleInput(charCode, e);
	}

	@Override
	public void onChanged(ChangeSelectorEvent e) {
		ConsoleUtils.renderChoiceQuestion(this);
	}

	@Override
	public void onChosen(ChooseSelectorEvent e) {
		this.setValue(e.getSelector().getValue());
		if (this.isPrintedResult()) ConsoleUtils.printResult(this);
	}
}
