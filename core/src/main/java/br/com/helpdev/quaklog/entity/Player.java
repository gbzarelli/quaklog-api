package br.com.helpdev.quaklog.entity;

import java.util.List;
import java.util.Map;

public interface Player {

    Integer getId();

    List<Item> getItems();

    Integer getKills();

    String getName();

    Map<String, String> getParameters();

    List<KillHistory> getKdHistory();

    List<PlayerStatus> getStatus();

}
