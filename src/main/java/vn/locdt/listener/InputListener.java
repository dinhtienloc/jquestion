package vn.locdt.listener;

import vn.locdt.event.InputEvent;

@FunctionalInterface
public interface InputListener extends Listener {
    void onInput(InputEvent e);
}
