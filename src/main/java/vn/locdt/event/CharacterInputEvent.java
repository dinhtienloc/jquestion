package vn.locdt.event;

/**
 * @author Kantenkugel (Michael Ritter)
 */
public class CharacterInputEvent {
    protected final StringBuilder currentBuffer;
    protected final char addedChar;

    protected boolean shouldCancel;

    public CharacterInputEvent(StringBuilder currentBuffer, char addedChar) {
        this.currentBuffer = currentBuffer;
        this.addedChar = addedChar;
    }

    public StringBuilder getCurrentBuffer() {
        return currentBuffer;
    }

    public char getAddedChar() {
        return addedChar;
    }

    public void clearBuffer() {
        currentBuffer.setLength(0);
    }

    public void cancelLoop() {
        shouldCancel = true;
    }

    public boolean isShouldCancel() {
        return shouldCancel;
    }
}
