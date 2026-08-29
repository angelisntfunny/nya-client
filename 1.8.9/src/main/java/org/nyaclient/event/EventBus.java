package org.nyaclient.event;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventBus {
    private final Map<Object, List<Method>> SUBSCRIBERS = new HashMap<>();

    public EventBus() {}

    public void call(Event event) {
        SUBSCRIBERS.forEach((object, methods) -> {
            for (Method m : methods) {
                m.setAccessible(true);
                try {
                    m.invoke(object, event);
                } catch (IllegalAccessException | InvocationTargetException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    public void remove(Object obj) {
        SUBSCRIBERS.remove(obj);
    }

    public void register(Object object) {
        List<Method> methods = new ArrayList<>();
        for (Method m : object.getClass().getDeclaredMethods()) {
            System.out.println();
            if (m.isAnnotationPresent(Subscribe.class) && m.getParameterCount() == 1 && Event.class.isAssignableFrom(m.getParameterTypes()[0])) {
                methods.add(m);
            }
        }
        SUBSCRIBERS.put(object, methods);
    }
}
