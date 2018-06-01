package vn.locdt.jquestion.event;

import vn.locdt.jquestion.constant.VirtualKey;

public class NonBlockInputEvent {
    protected final VirtualKey key;

    protected boolean stop;

    public NonBlockInputEvent(VirtualKey key) {
        this.key = key;
    }

    public VirtualKey getKey() {
        return key;
    }

    public void stop() {
        stop = true;
    }

    public boolean isStop() {
        return stop;
    }
}
