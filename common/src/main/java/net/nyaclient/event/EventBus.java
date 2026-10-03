/*
 * This file is part of Nya Client.
 *
 * Nya Client is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 *
 * Nya Client is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with Nya Client. If not, see <https://www.gnu.org/licenses/>.
 */

package net.nyaclient.event;

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

        for (SubscriberData data : new ArrayList<>(dataList)) {
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

    private record SubscriberData(Object instance, Method method) {}
}