package net.nyaclient.event.impl;

import net.nyaclient.event.Event;

public final class EventFOV implements Event {
    private double fov;
    private boolean cancelled;

    public EventFOV() {
        this.fov = 0;
        this.cancelled = false;
    }

    public void setFov(double fov) {
        this.fov = fov;
    }

    public double fov() {
        return fov;
    }

    public boolean cancelled() {
        return cancelled;
    }

    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }
}
