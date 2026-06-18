package com.example.eventdrivenengine.gamepieces;

import com.example.eventdrivenengine.core.SimpleGameState;
import com.example.eventdrivenengine.gameevents.GameEndEvent;
import com.example.eventdrivenengine.gameevents.GameEvent;
public class Player extends Fighter{

    public Player() {
        super();
    }

    @Override
    protected GameEvent<SimpleGameState> getDeathEvent() {
        return new GameEndEvent("Defeat", 0);
    }

}