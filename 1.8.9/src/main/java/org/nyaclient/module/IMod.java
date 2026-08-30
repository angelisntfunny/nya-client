package org.nyaclient.module;

public interface IMod {
    void toggle();
    int getKey();
    String getName();
    String getDescription();
    ModCategory getCategory();
    void setKey(int key);
    void setEnabled(boolean enabled);
}
