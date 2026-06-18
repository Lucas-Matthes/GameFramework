package com.example.eventdrivenengine.gameevents;

import com.example.eventdrivenengine.core.GameResult;

import java.util.Optional;

public interface GameEvent<S> {
    public EventResult<S> apply(S gameState);
    default Optional<GameResult> getGameResult() {
        return Optional.empty();
    }
    default boolean getGameEnded() {
        return false;
    }
}
