package vn.locdt.item;

import vn.locdt.result.ChoiceResultHandler;

import java.util.ArrayList;
import java.util.List;

public class Choice extends Item<ChoiceResultHandler> {
    public static String activedPrefix = "> ";
    public static String deactivedPrefix = "  ";
    private List<Selector> choiceList;
    private Selector activedSelector;

    public Choice(String title, String name) {
        super(title, name, "");
        this.choiceList = new ArrayList<>();
    }

    public List<Selector> getChoiceList() {
        return choiceList;
    }

    public void setChoiceList(List<Selector> choiceList) {
        this.choiceList = choiceList;
    }

    public Selector getActivedSelector() {
        return activedSelector;
    }

    public void setActivedSelector(Selector activedSelector) {
        if (this.activedSelector != null)
            this.activedSelector.setPrefix(deactivedPrefix);

        this.activedSelector = activedSelector;
        activedSelector.setPrefix(activedPrefix);
    }

    public int indexOfActivedSelector() {
        if (activedSelector == null || choiceList.size() == 0)
            return -1;

        return choiceList.indexOf(activedSelector);
    }

    public void addSelector(Selector selector) {
        if (selector != null) {
            selector.setPrefix(deactivedPrefix);
            choiceList.add(selector);
            this.setRenderHeight(choiceList.size() + 2);
        }
    }

    public void addSelectorList(List<Selector> selectorList) {
        choiceList.addAll(selectorList);
        this.setRenderHeight(choiceList.size() + 2);
    }
}
