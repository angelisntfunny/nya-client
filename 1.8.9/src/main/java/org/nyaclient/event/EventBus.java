package org.nyaclient.event;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventBus {
    private final Map<Class<? extends Event>, List<SubscriberData>> SUBSCRIBERS = new HashMap<>();

    public EventBus() {}

    public void call(Event event) {
        List<SubscriberData> dataList = SUBSCRIBERS.get(event.getClass());
        if (dataList == null) return;

        for (SubscriberData data : dataList) {
            try {
                data.method.invoke(data.instance, event);
            } catch (IllegalAccessException | InvocationTargetException e) {
                throw new RuntimeException("Failed to invoke event handler", e);
            }
        }
    }

    public void register(Object object) {
        for (Method m : object.getClass().getDeclaredMethods()) {
            if (m.isAnnotationPresent(Subscribe.class) && m.getParameterCount() == 1) {
                Class<?> paramType = m.getParameterTypes()[0];

                if (Event.class.isAssignableFrom(paramType)) {
                    @SuppressWarnings("unchecked")
                    Class<? extends Event> eventClass = (Class<? extends Event>) paramType;

                    m.setAccessible(true);
                    SUBSCRIBERS.computeIfAbsent(eventClass, k -> new ArrayList<>())
                            .add(new SubscriberData(object, m));
                }
            }
        }
    }

    public void remove(Object obj) {
        SUBSCRIBERS.values().forEach(list -> list.removeIf(data -> data.instance == obj));
    }

    private static class SubscriberData {
        final Object instance;
        final Method method;

        SubscriberData(Object instance, Method method) {
            this.instance = instance;
            this.method = method;
        }
    }
}
