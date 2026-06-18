package com.example.eventdrivenengine.gameevents;

import com.example.eventdrivenengine.core.SimpleGameState;
import com.example.eventdrivenengine.decisionrequests.ChoosePlayerAction;
import com.example.eventdrivenengine.gamepieces.Action;
import com.example.eventdrivenengine.gamepieces.Enemy;

import java.util.Collections;

public class EnemyActionChoiceEvent implements GameEvent<SimpleGameState> {
    Action action;
    @Override
    public EventResult<SimpleGameState> apply(SimpleGameState simpleGameState) {
        Enemy enemy = simpleGameState.getEnemy();
        action = enemy.chooseNextAction();
        enemy.startRound();
        return new EventResult<SimpleGameState>(Collections.emptyList(), new ChoosePlayerAction(1));
    }
}
