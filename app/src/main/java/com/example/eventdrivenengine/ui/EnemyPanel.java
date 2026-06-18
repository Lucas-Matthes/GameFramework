package com.example.eventdrivenengine.ui;

import com.example.eventdrivenengine.core.SimpleGameState;
import com.example.eventdrivenengine.decisionrequests.DecisionRequest;
import com.example.eventdrivenengine.gameevents.EnemyActionChoiceEvent;
import com.example.eventdrivenengine.gameevents.GameEvent;
import com.example.eventdrivenengine.gameevents.GameStartEvent;
import com.example.eventdrivenengine.gameevents.PlayerActionEvent;
import com.example.eventdrivenengine.gameevents.RoundEndEvent;
import com.example.eventdrivenengine.userinputs.UserInput;

import java.util.function.Consumer;

public class EnemyPanel implements UIObserver<SimpleGameState> {
    private final EnemyView enemyView;

    public EnemyPanel(SimpleUIFactory uiFactory) {
        this.enemyView = uiFactory.createEnemyView();
    }


    @Override
    public void notify(GameEvent<SimpleGameState> event, SimpleGameState simpleGameState) {
        if(event instanceof RoundEndEvent) {
            enemyView.setDefendIconVisibility(simpleGameState.getEnemy().isGuardUp());
        }
        else if(event instanceof PlayerActionEvent) {
            enemyView.setHealth(simpleGameState.getEnemy().getHealth());
        }
        else if(event instanceof EnemyActionChoiceEvent) {
            enemyView.setNextMove(simpleGameState.getEnemy().getNextAction().toString());
            enemyView.setDefendIconVisibility(simpleGameState.getEnemy().isGuardUp());
        }
        else if(event instanceof GameStartEvent) {
            enemyView.setHealth(simpleGameState.getEnemy().getHealth());
            enemyView.setDefendIconVisibility(simpleGameState.getEnemy().isGuardUp());
        }
    }

    @Override
    public void handle(DecisionRequest<SimpleGameState> request, Consumer<UserInput> callback) {

    }
}
