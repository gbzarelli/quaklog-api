package br.com.helpdev.quaklog.dataprovider.mapper;

import br.com.helpdev.quaklog.dataprovider.entity.ItemEntity;
import br.com.helpdev.quaklog.entity.Item;
import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
class ItemRepositoryMapper {

    List<ItemEntity> toDatabaseEntity(final List<Item> items) {
        return items.stream().map(ItemRepositoryMapper::toDatabaseEntity).collect(Collectors.toList());
    }

    ItemEntity toDatabaseEntity(final Item item) {
        return ItemEntity.builder().item(item.toString()).build();
    }

    List<Item> mapItems(final List<ItemEntity> items) {
        return items.stream().map(ItemRepositoryMapper::mapItems).collect(Collectors.toList());
    }

    Item mapItems(final ItemEntity item) {
        return Item.valueOf(item.getItem());
    }

}
