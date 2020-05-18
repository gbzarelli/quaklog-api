package br.com.helpdev.quaklog.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NotifiableTest {

    @Test
    void shouldCreateNotificationAndAddNotificationsWithSuccess() {
        Notifiable myNotifiable = new Notifiable() {
        };
        myNotifiable.addNotification("A");
        myNotifiable.addNotification("B");
        assertTrue(myNotifiable.getNotifications().contains("A"));
        assertTrue(myNotifiable.getNotifications().contains("B"));
        assertEquals(2, myNotifiable.getNotifications().size());
    }

    @Test
    void shouldCreateNotificationWithOtherNotificationWithSuccess() {
        Notifiable myNotifiable = new Notifiable() {
        };
        myNotifiable.addNotification("A");
        myNotifiable.addNotification("B");

        Notifiable myNotifiable2 = new Notifiable() {
        };
        myNotifiable2.addNotification("C");
        myNotifiable2.addNotification("D");
        myNotifiable2.addNotifiable(myNotifiable);

        assertTrue(myNotifiable2.getNotifications().contains("A"));
        assertTrue(myNotifiable2.getNotifications().contains("B"));
        assertTrue(myNotifiable2.getNotifications().contains("C"));
        assertTrue(myNotifiable2.getNotifications().contains("D"));
        assertEquals(4, myNotifiable2.getNotifications().size());
    }

    @Test
    void shouldHaveNotification() {
        Notifiable myNotifiable = new Notifiable() {
        };
        myNotifiable.addNotification("A");
        assertTrue(myNotifiable.getNotifications().contains("A"));
        assertEquals(1, myNotifiable.getNotifications().size());
        assertTrue(myNotifiable.hasNotifications());

        Notifiable myNotifiable2 = new Notifiable() {
        };
        assertFalse(myNotifiable2.hasNotifications());
    }

}