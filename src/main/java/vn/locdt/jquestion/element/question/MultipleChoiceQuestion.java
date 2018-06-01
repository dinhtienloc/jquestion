package vn.locdt.jquestion.element.question;

import vn.locdt.jquestion.constant.VirtualKey;
import vn.locdt.jquestion.element.attribute.ChoiceAttribute;
import vn.locdt.jquestion.element.item.Selector;
import vn.locdt.jquestion.event.ChangeSelectorEvent;
import vn.locdt.jquestion.event.ChooseSelectorEvent;
import vn.locdt.jquestion.event.NonBlockInputEvent;

import java.util.ArrayList;
import java.util.List;

public class MultipleChoiceQuestion extends ChoiceQuestion<MultipleChoiceQuestion, List<String>> {
	private ChoiceAttribute activeAttribute = new ChoiceAttribute(ChoiceAttribute.Status.ACTIVE, "(*)");
	private VirtualKey activeKey = VirtualKey.RIGHT;
	private VirtualKey deactiveKey = VirtualKey.LEFT;

	public MultipleChoiceQuestion(String title) {
		super(title);
		this.answer.setResult(new ArrayList<>());
	}

	public MultipleChoiceQuestion activeKey(VirtualKey key) {
		this.activeKey = key;
		return getThis();
	}

	public MultipleChoiceQuestion deactiveKey(VirtualKey key) {
		this.deactiveKey = key;
		return getThis();
	}

	public MultipleChoiceQuestion activePrefix(String prefix) {
		this.activeAttribute.setPrefix(prefix);
		return getThis();
	}


	public MultipleChoiceQuestion addActiveSelector(String value) {
		Selector newSelector = new Selector(value);
		newSelector.setActive(true);
		newSelector.setAttribute(activeAttribute);
		answer.getResult().add(value);
		return addSelector(newSelector);
	}

	public MultipleChoiceQuestion addActiveSelector(String value, boolean firstChosen) {
		Selector newSelector = new Selector(value);
		newSelector.setActive(true);
		newSelector.setAttribute(activeAttribute);
		answer.getResult().add(value);
		return addSelector(newSelector, firstChosen);
	}

	@Override
	public MultipleChoiceQuestion addSelector(Selector selector, boolean firstChosen) {
		if (!selector.isActive() && !firstChosen)
			selector.setAttribute(normalAttribute);

		if (firstChosen) {
			ChoiceAttribute attr;
			if (chosenSelector != null) {
				attr = chosenSelector.isActive() ? activeAttribute : normalAttribute;
				chosenSelector.setAttribute(attr);
			}

			chosenSelector = selector;
			if (!selector.isActive()) {
				attr = selectedAttribute;
				chosenSelector.setAttribute(attr);
			}
		}

		this.selectors.add(selector);
		updateRenderHeight();
		return getThis();
	}

	@Override
	protected MultipleChoiceQuestion getThis() {
		return this;
	}

	@Override
	public void setUpQuestion() {
		if (selectors.size() > 0) {
			if (chosenSelector == null) {
				Selector firstSelector = selectors.get(0);
				if (!firstSelector.hasAttribute(ChoiceAttribute.Status.ACTIVE)) {
					chosenSelector = firstSelector;
					chosenSelector.setAttribute(selectedAttribute);
				}
			}
		}
	}

	@Override
	public boolean validateSelectors() {
		if (this.selectors.size() == 0)
			return false;
		else return true;
	}

	@Override
	protected void handleInputKeyEvent(VirtualKey key, NonBlockInputEvent e) {
		if (key == activeKey)
			handleActiveChosenSelector();
		else if (key == deactiveKey)
			handleDeactiveChosenSelector();
		else super.handleInputKeyEvent(key, e);
	}

	@Override
	protected void handleFinishQuestion(NonBlockInputEvent e) {
		if (this.isPrintedResult()) printResult();
		notifyChooseListener(new ChooseSelectorEvent(chosenSelector));
		e.stop();
	}

	protected void handleActiveChosenSelector() {
		if (chosenSelector != null) {
			this.answer.getResult().add(chosenSelector.getValue());
			chosenSelector.setActive(true);
			chosenSelector.setAttribute(activeAttribute);
			reRender();
		}
	}

	protected void handleDeactiveChosenSelector() {
		if (chosenSelector != null) {
			this.answer.getResult().remove(chosenSelector.getValue());
			chosenSelector.setActive(false);
			chosenSelector.setAttribute(selectedAttribute);
			reRender();
		}
	}

	@Override
	protected void updateSelectorPrefixes(Selector lastChosenSelector, Selector newChosenSelector) {
		ChoiceAttribute attr;
		if (lastChosenSelector != null) {
			attr = lastChosenSelector.isActive() ? activeAttribute : normalAttribute;
			lastChosenSelector.setAttribute(attr);
		}

		attr = newChosenSelector.isActive() ? activeAttribute : selectedAttribute;
		newChosenSelector.setAttribute(attr);
		chosenSelector = newChosenSelector;
		publishChangeEvent(new ChangeSelectorEvent(lastChosenSelector, newChosenSelector));
	}
}
