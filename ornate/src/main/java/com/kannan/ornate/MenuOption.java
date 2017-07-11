package com.kannan.ornate;

/**
 * Created by kannan on 9/7/17.
 */

public class MenuOption {

    private String mLabel;
    private String mDesctiptio;

    public MenuOption(String label) {
        mLabel = label;
        mDesctiptio = "";
    }

    public MenuOption withDescription(String desc) {
        mDesctiptio = desc;
        return this;
    }

    public String getLabel() {
        return mLabel;
    }

    public String getDescriptio() {
        return mDesctiptio;
    }

}
