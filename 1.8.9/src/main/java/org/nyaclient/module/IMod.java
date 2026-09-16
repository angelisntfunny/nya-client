package org.nyaclient.module;

@SuppressWarnings("unused")
public interface IMod {
    void toggle();
    int getKey();
    String getName();
    String getIconPath();
    ModCategory getCategory();
    void setKey(int key);
    void setEnabled(boolean enabled);
    boolean isEnabled();
    int getIconHandle();
    void cleanup();
}
