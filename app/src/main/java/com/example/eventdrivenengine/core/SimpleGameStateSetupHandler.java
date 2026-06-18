package com.example.eventdrivenengine.core;

import com.example.eventdrivenengine.gamepieces.Enemy;
import com.example.eventdrivenengine.gamepieces.Player;

public class SimpleGameStateSetupHandler {
    public SimpleGameState setupGame() {
        SimpleGameState simpleGameState = new SimpleGameState();

        simpleGameState.setEnemy(new Enemy());
        simpleGameState.setPlayer(new Player());

        return simpleGameState;
    }
}
