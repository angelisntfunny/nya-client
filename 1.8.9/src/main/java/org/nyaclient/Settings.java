package org.nyaclient;

import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class Settings {
    // little wrapper class so i can make the constants in this class static
    @Getter
    public static class Setting<T> {
        protected T value;
        private final String name;
        private final Class<T> type;

        private Setting(T defaultValue, String name, Class<T> type) {
            this.type = type;
            this.value = defaultValue;
            this.name = name;
        }
    }

    public static class BooleanSetting extends Setting<Boolean> {
        private BooleanSetting(Boolean defaultValue, String name) {
            super(defaultValue, name, Boolean.class);
        }

        public void setValue(boolean value) {
            this.value = value;
        }

        public Boolean getValue() {
            return this.value;
        }
    }

    public static final BooleanSetting BACKPORT_E_TAPPING = new BooleanSetting(false, "Backport 1.12 E-Tapping");

    // java sucks
    public static final List<Setting<?>> SETTINGS = Collections.unmodifiableList(
            Arrays.asList(BACKPORT_E_TAPPING)
    );
}
