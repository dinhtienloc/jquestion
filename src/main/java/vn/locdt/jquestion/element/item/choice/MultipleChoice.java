package vn.locdt.jquestion.element.item.choice;

import java.util.List;

public class MultipleChoice extends Choice {
    private List<Selector> activedSelector;
    public MultipleChoice(String title, String name) {
        super(title, name);
    }
}
