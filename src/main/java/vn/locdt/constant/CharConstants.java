package vn.locdt.constant;

/**
 * @author Kantenkugel (Michael Ritter)
 */
public final class CharConstants {
    public static final char CHAR_CTRL_C = (char) 3;
    public static final char CHAR_CTRL_D = (char) 4;
    public static final char CHAR_CTRL_Z = (char) 26;
    public static final char CHAR_ENTER = (char) 10;

    public static final char CHAR_BACKSPACE = '\b';
    public static final char CHAR_TAB = '\t';

    private CharConstants() {}

    public enum ArrowKey {
        UP,
        DOWN,
        LEFT,
        RIGHT
    }
}
