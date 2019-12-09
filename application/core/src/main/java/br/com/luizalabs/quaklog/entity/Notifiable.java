package br.com.luizalabs.quaklog.entity;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public abstract class Notifiable {

    private final Set<String> notifications = new HashSet<>();

    void addNotification(String message) {
        notifications.add(message);
    }

    void addNotifiable(Notifiable notifiable) {
        notifications.addAll(notifiable.getNotifications());
    }

    public boolean hasNotifications() {
        return !notifications.isEmpty();
    }

    public Set<String> getNotifications() {
        return Collections.unmodifiableSet(notifications);
    }
}
