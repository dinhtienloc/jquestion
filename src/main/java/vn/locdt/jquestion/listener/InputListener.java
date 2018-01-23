package vn.locdt.jquestion.listener;

import vn.locdt.jquestion.answer.Answer;
import vn.locdt.jquestion.event.InputEvent;

@FunctionalInterface
public interface InputListener extends Listener {
    Answer onInput(InputEvent e);
}
