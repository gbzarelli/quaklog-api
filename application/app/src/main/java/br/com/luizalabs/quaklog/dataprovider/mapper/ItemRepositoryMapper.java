package br.com.luizalabs.quaklog.dataprovider.mapper;

import br.com.luizalabs.quaklog.dataprovider.entity.ItemEntity;
import br.com.luizalabs.quaklog.entity.Item;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
class ItemRepositoryMapper {

    static List<ItemEntity> toDatabaseEntity(List<Item> items) {
        return items.stream().map(ItemRepositoryMapper::toDatabaseEntity).collect(Collectors.toList());
    }

    static ItemEntity toDatabaseEntity(Item item) {
        return ItemEntity.builder().item(item.toString()).build();
    }

    static List<Item> mapItems(List<ItemEntity> items) {
        return items.stream().map(ItemRepositoryMapper::mapItems).collect(Collectors.toList());
    }

    static Item mapItems(ItemEntity item) {
        return Item.valueOf(item.getItem());
    }

}
