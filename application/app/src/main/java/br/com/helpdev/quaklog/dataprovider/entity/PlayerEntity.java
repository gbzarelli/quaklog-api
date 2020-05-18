package br.com.helpdev.quaklog.dataprovider.entity;

import lombok.*;

import java.util.List;
import java.util.Map;

@Data
@Builder
public class PlayerEntity {
    private int id;
    private String name;
    private int kills;
    private final List<KillHistoryEntity> kdHistory;
    private final List<ItemEntity> items;
    private final List<PlayerStatusEntity> status;
    private Map<String, String> parameters;
}
