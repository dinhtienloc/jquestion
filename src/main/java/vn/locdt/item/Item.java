package vn.locdt.item;

public abstract class Item {
    private int renderHeight;
    protected String name;
    protected String value;
    protected String title;

    public Item(String title, String name, String value) {
        this.title = title;
        this.name = name;
        this.value = value;
    }

    public Item(String title, String name) {
        this.title = title;
        this.name = name;
        this.value = "";
    }

    public int getRenderHeight() {
        return renderHeight;
    }

    public void setRenderHeight(int renderHeight) {
        this.renderHeight = renderHeight;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
