package br.com.luizalabs.quaklog.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode
public class Item {
    private final String name;

    public static Item valueOf(String itemName) {
        return new Item(itemName);
    }

    @Override
    public String toString() {
        return name;
    }
}
