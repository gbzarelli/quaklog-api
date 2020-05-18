package br.com.helpdev.quaklog.entity;

import br.com.helpdev.quaklog.entity.vo.GameTime;
import br.com.helpdev.quaklog.entity.vo.Mod;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class PlayerKiller implements Player {

    @FunctionalInterface
    interface KillListener {
        void kill(Player player);
    }

    private final AtomicInteger kills;
    private final List<KillHistory> kdHistory;
    private KillListener killListener;

    PlayerKiller() {
        kills = new AtomicInteger();
        kdHistory = new ArrayList<>();
    }

    PlayerKiller(final Integer kills, final List<KillHistory> kdHistory) {
        this.kills = kills == null ? new AtomicInteger(0) : new AtomicInteger(kills);
        this.kdHistory = kdHistory == null ? Collections.emptyList() : kdHistory;
    }

    public void kill(GameTime gameTime, PlayerInGame client, Mod mod) {
        if (killListener != null) killListener.kill(client);
        client.deadBy(gameTime, this, mod);
        kdHistory.add(KillHistory.killed(gameTime, client, mod));
        kills.incrementAndGet();
    }

    void setKillListener(KillListener killListener) {
        this.killListener = killListener;
    }

    void decrementKill() {
        kills.decrementAndGet();
    }

    @Override
    public Integer getKills() {
        return kills.get();
    }

    @Override
    public List<KillHistory> getKdHistory() {
        return Collections.unmodifiableList(kdHistory);
    }

    void addHistory(KillHistory history) {
        kdHistory.add(history);
    }

}
