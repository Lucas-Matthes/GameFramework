package com.example.eventdrivenengine.gameevents;

import com.example.eventdrivenengine.core.SimpleGameState;

import java.util.Collections;

public class GameStartEvent implements GameEvent<SimpleGameState>{

    @Override
    public EventResult<SimpleGameState> apply(SimpleGameState simpleGameState) {
        return new EventResult<>(Collections.singletonList(new EnemyActionChoiceEvent()), null);
    }
}
