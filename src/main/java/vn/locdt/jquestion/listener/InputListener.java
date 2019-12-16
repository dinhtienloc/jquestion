package vn.locdt.jquestion.listener;

import vn.locdt.jquestion.event.InputEvent;

@FunctionalInterface
public interface InputListener<V> extends Listener {
    V onInput(InputEvent e);
}
