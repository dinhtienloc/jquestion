package vn.locdt.jquestion.element.question;

import vn.locdt.jquestion.element.item.Choice;

import java.util.ArrayList;
import java.util.List;

public class MultipleChoiceQuestion extends Choice {
    private List<Integer> activedIndexes;

    public MultipleChoiceQuestion(String title, String name) {
        super(title, name);
        this.activedIndexes = new ArrayList<>();
    }
}
