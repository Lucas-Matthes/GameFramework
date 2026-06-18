package com.example.eventdrivenengine.gameevents;

import com.example.eventdrivenengine.core.SimpleGameState;
import com.example.eventdrivenengine.gamepieces.Enemy;

import java.util.ArrayList;

public class EnemyActionEvent implements GameEvent<SimpleGameState> {

    @Override
    public EventResult<SimpleGameState> apply(SimpleGameState simpleGameState) {
        Enemy enemy = simpleGameState.getEnemy();
        ArrayList<GameEvent<SimpleGameState>> events = new ArrayList<>();
        events.add(new RoundEndEvent());
        GameEvent<SimpleGameState> playerEvent = enemy.act(simpleGameState.getPlayer());
        if(playerEvent != null) {
            events.add(playerEvent);
        }
        return new EventResult<>(events, null);
    }
}
