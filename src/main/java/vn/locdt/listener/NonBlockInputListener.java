package vn.locdt.listener;

import vn.locdt.event.NonBlockInputEvent;

@FunctionalInterface
public interface NonBlockInputListener extends Listener {
    boolean onInput(NonBlockInputEvent e);
}
