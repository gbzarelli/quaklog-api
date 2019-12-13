package br.com.luizalabs.quaklog.dataprovider.mapper;

import br.com.luizalabs.quaklog.dataprovider.entity.ItemEntity;
import br.com.luizalabs.quaklog.entity.Item;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
class ItemRepositoryMapper {

    static List<ItemEntity> toDatabaseEntity(final List<Item> items) {
        return items.stream().map(ItemRepositoryMapper::toDatabaseEntity).collect(Collectors.toList());
    }

    static ItemEntity toDatabaseEntity(final Item item) {
        return ItemEntity.builder().item(item.toString()).build();
    }

    static List<Item> mapItems(final List<ItemEntity> items) {
        return items.stream().map(ItemRepositoryMapper::mapItems).collect(Collectors.toList());
    }

    static Item mapItems(final ItemEntity item) {
        return Item.valueOf(item.getItem());
    }

}
