package br.com.luizalabs.quaklog.dataprovider.entity;

import org.springframework.data.annotation.Id;

import java.time.LocalDate;

public class GameEntity {
    @Id
    String uuid;
    LocalDate date;
    //TODO
}
