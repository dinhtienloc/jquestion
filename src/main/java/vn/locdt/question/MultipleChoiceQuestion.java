package vn.locdt.question;

import vn.locdt.item.Choice;
import vn.locdt.item.Selector;

import java.util.ArrayList;
import java.util.List;

public class MultipleChoiceQuestion extends Choice {
    private List<Integer> activedIndexes;

    public MultipleChoiceQuestion(String title, String name) {
        super(title, name);
        this.activedIndexes = new ArrayList<>();
    }
}
