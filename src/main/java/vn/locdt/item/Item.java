package vn.locdt.item;

import vn.locdt.result.ResultHandler;

public abstract class Item<T extends ResultHandler> {
    private int renderHeight;
    protected String name;
    protected String value;
    protected String title;
    protected T resultHandler;

    public Item(String title, String name, String value) {
        this.title = title;
        this.name = name;
        this.value = value;
    }

    public int getRenderHeight() {
        return renderHeight;
    }

    public void setRenderHeight(int renderHeight) {
        this.renderHeight = renderHeight;
    }

    public T getResultHandler() {
        return resultHandler;
    }

    public void setResultHandler(T resultHandler) {
        this.resultHandler = resultHandler;
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
