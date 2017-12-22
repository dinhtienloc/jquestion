package vn.locdt.item;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class Choice extends Item {
    public static String activedPrefix = "> ";
    public static String deactivedPrefix = "  ";
    protected List<Selector> choiceList;

    public Choice(String title, String name) {
        super(title, name, "");
        choiceList = new ArrayList<>();
    }

    public Choice(String title, String name, String[] selectors) {
        super(title, name, "");
        choiceList = new ArrayList<>();
        for (String s : selectors) {
            choiceList.add(new Selector(s));
        }
        setRenderHeight(choiceList.size() + 2);
    }

    public List<Selector> getChoiceList() {
        return choiceList;
    }

    public void setChoiceList(List<Selector> choiceList) {
        choiceList = choiceList;
    }

    public void addSelector(Selector selector) {
        if (selector != null) {
            selector.setPrefix(deactivedPrefix);
            choiceList.add(selector);
            setRenderHeight(choiceList.size() + 2);
        }
    }

    public void addSelector(String selector) {
        if (selector != null) {
            Selector newSelector = new Selector(selector);
            newSelector.setPrefix(deactivedPrefix);
            choiceList.add(newSelector);
            setRenderHeight(choiceList.size() + 2);
        }
    }

    public void addSelectors(List<Selector> selectors) {
        choiceList.addAll(selectors);
        setRenderHeight(choiceList.size() + 2);
    }

    public void addSelectors(String[] selectors) {
        for (String s : selectors)
            addSelector(s);
        setRenderHeight(choiceList.size() + 2);
    }
}
