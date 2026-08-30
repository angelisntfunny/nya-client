package org.nyaclient.event.impl;

import org.nyaclient.event.Event;

public class EventKey implements Event {
    private final int key;

    public EventKey(int key) {
        this.key = key;
    }

    public int getKey() {
        return key;
    }
}
