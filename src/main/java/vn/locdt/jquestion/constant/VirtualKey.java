package vn.locdt.jquestion.constant;

import vn.locdt.jquestion.util.OsUtils;

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
        public static final char LEFT = 57419;
        public static final char RIGHT = 57421;
        public static final char DOWN = 57424;
    }

    public static char[] getArrowCharCode(ArrowKey arrow) {
        if (OsUtils.isWindowOS()) {
            switch (arrow) {
                case UP: return new char[]{WindowOS.UP};
                case LEFT: return new char[]{WindowOS.LEFT};
                case RIGHT: return new char[]{WindowOS.RIGHT};
                case DOWN: return new char[]{WindowOS.DOWN};
            }
        }
        else if (OsUtils.isUnix()) {
            switch (arrow) {
                case UP: return new char[]{27, 91, 65};
                case DOWN: return new char[]{27, 91, 66};
                case RIGHT: return new char[]{27, 91, 67};
                case LEFT: return new char[]{27, 91, 68};
            }
        }

        return null;
    }
}
