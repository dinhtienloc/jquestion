package vn.locdt.jquestion.constant;

public final class VirtualKey {
    public static final char CTRL_C = 3;
    public static final char EOF = 4;               // Ctrl + D
    public static final char CTRL_Z = 26;
    public static final char ENTER = 13;

    public static final char BACK_SPACE = '\b';
    public static final char TAB = '\t';

    public enum ArrowKey {
        UP,
        DOWN,
        LEFT,
        RIGHT
    }

    public static final class WindowOS {
        public static final char UP = 57416;
        public static final char DOWN = 57424;
        public static final char LEFT = 57419;
        public static final char RIGHT = 57421;
    }
}
