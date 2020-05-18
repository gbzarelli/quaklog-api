package br.com.helpdev.quaklog.entity;

import java.util.*;

public abstract class Notifiable {

    private final List<String> notifications = new ArrayList<>();

    void addNotification(String message) {
        notifications.add(message);
    }

    void addNotifications(Collection<String> notifications) {
        this.notifications.addAll(notifications);
    }

    void addNotifiable(Notifiable notifiable) {
        notifications.addAll(notifiable.getNotifications());
    }

    public boolean hasNotifications() {
        return !notifications.isEmpty();
    }

    public List<String> getNotifications() {
        return Collections.unmodifiableList(notifications);
    }
}
