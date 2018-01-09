package vn.locdt.element.item;

import java.util.ArrayList;
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
        updateRenderHeight();
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
            updateRenderHeight();
        }
    }

    public void addSelector(String selector) {
        if (selector != null) {
            Selector newSelector = new Selector(selector);
            newSelector.setPrefix(deactivedPrefix);
            choiceList.add(newSelector);
            updateRenderHeight();
        }
    }

    public void addSelectors(List<Selector> selectors) {
        choiceList.addAll(selectors);
        updateRenderHeight();
    }

    public void addSelectors(String[] selectors) {
        for (String s : selectors)
            addSelector(s);
        updateRenderHeight();
    }

    @Override
    public void updateRenderHeight() {
        setRenderHeight(choiceList.size());
    }
}
