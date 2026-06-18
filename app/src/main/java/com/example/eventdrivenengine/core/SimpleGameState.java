package com.example.eventdrivenengine.core;

import com.example.eventdrivenengine.gamepieces.Enemy;
import com.example.eventdrivenengine.gamepieces.Player;

public class SimpleGameState {
    private Enemy enemy;
    private Player player;

    public Enemy getEnemy() {
        return enemy;
    }

    public Player getPlayer() {
        return player;
    }

    public void setEnemy(Enemy enemy) {
        this.enemy = enemy;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
