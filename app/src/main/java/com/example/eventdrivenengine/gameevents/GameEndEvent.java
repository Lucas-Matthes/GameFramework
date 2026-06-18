package com.example.eventdrivenengine.gameevents;

import com.example.eventdrivenengine.core.GameResult;
import com.example.eventdrivenengine.core.SimpleGameState;
import com.example.eventdrivenengine.core.SimpleGameResult;

import java.util.Collections;
import java.util.Optional;

public class GameEndEvent implements GameEvent<SimpleGameState> {
    private String reason;
    private int winningPlayer;
    public GameEndEvent(String reason, int winningPlayer) {
        this.winningPlayer = winningPlayer;
        this.reason = reason;
    }
    @Override
    public EventResult<SimpleGameState> apply(SimpleGameState simpleGameState) {
        return new EventResult<>(Collections.emptyList(), null);
    }

    @Override
    public boolean getGameEnded() {
        return true;
    }

    @Override
    public Optional<GameResult> getGameResult() {
        GameResult result = new SimpleGameResult(winningPlayer, reason);
        return Optional.of(result);
    }
}
