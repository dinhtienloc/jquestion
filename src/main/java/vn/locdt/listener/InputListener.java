package vn.locdt.listener;

import vn.locdt.answer.Answer;
import vn.locdt.event.InputEvent;

@FunctionalInterface
public interface InputListener extends Listener {
    Answer onInput(InputEvent e);
}
