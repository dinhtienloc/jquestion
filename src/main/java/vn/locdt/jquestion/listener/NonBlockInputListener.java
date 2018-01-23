package vn.locdt.jquestion.listener;

import vn.locdt.jquestion.event.NonBlockInputEvent;

@FunctionalInterface
public interface NonBlockInputListener extends Listener {
    boolean onInput(NonBlockInputEvent e);
}
