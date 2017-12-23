package vn.locdt.event;

import vn.locdt.item.Selector;

public class ChooseSelectorEvent {
    private Selector selector;

    public ChooseSelectorEvent(Selector selector) {
        this.selector = selector;
    }

    public Selector getSelector() {
        return selector;
    }

    public void setSelector(Selector selector) {
        this.selector = selector;
    }
}
