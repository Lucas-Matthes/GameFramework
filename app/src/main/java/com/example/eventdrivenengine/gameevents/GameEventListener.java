package com.example.eventdrivenengine.gameevents;

import java.util.List;

public interface GameEventListener<S> {
    public List<GameEvent<S>> notify(GameEvent<S> gameEvent, S gameState);
}
