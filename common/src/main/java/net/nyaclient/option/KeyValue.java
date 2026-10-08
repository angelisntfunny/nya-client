package net.nyaclient.option;

import net.nyaclient.enums.Key;

public class KeyValue implements Value<Key> {
    public final String name;
    private Key value;

    public KeyValue(String name, Key value) {
        this.name = name;
        this.value = value;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public Key get() {
        return value;
    }

    @Override
    public void set(Key value) {
        this.value = value;
    }
}
