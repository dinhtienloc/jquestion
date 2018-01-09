package vn.locdt.element.item;

public class SingleChoice extends Choice {
    private Selector activedSelector;

    public SingleChoice(String title, String name) {
        super(title, name);
    }

    public Selector getActivedSelector() {
        return activedSelector;
    }

    public void setActivedSelector(Selector actived) {
        if (activedSelector != null)
            activedSelector.setPrefix(deactivedPrefix);

        activedSelector = actived;
        actived.setPrefix(activedPrefix);
    }

    public int indexOfActivedSelector() {
        if (activedSelector == null || choiceList.size() == 0)
            return -1;

        return choiceList.indexOf(activedSelector);
    }
}
