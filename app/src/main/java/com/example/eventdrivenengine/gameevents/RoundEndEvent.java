package com.example.eventdrivenengine.gameevents;

import com.example.eventdrivenengine.core.SimpleGameState;

import java.util.Collections;

public class RoundEndEvent implements GameEvent<SimpleGameState> {
    @Override
    public EventResult<SimpleGameState> apply(SimpleGameState simpleGameState) {
        simpleGameState.getEnemy().lowerGuard();
        simpleGameState.getPlayer().lowerGuard();
        return new EventResult<>(Collections.singletonList(new EnemyActionChoiceEvent()), null);
    }
}
