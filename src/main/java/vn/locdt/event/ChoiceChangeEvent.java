package vn.locdt.event;

public class ChoiceChangeEvent extends CharacterInputEvent {
    public ChoiceChangeEvent(StringBuilder currentBuffer, char addedChar) {
        super(currentBuffer, addedChar);
    }

}
