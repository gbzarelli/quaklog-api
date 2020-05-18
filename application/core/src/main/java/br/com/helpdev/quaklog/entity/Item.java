package br.com.helpdev.quaklog.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode
public class Item {

    public static Item valueOf(String itemName) {
        return new Item(itemName);
    }

    private final String name;

    @Override
    public String toString() {
        return name;
    }
}
