package br.com.luizalabs.quaklog.dataprovider.mapper;

import br.com.luizalabs.quaklog.dataprovider.entity.ItemEntity;
import br.com.luizalabs.quaklog.entity.Item;

import java.util.List;
import java.util.stream.Collectors;

public class ItemRepositoryMapper {

    public static List<ItemEntity> toDatabaseEntity(List<Item> items) {
        return items.stream().map(ItemRepositoryMapper::toDatabaseEntity).collect(Collectors.toList());
    }

    public static ItemEntity toDatabaseEntity(Item item) {
        return ItemEntity.builder().item(item.toString()).build();
    }
}
