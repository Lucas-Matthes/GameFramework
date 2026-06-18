package com.example.eventdrivenengine.core;

import com.example.eventdrivenengine.decisionrequests.DecisionController;
import com.example.eventdrivenengine.gameevents.GameEvent;
import com.example.eventdrivenengine.ui.UIObserver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class GameBuilder<S> {
    private GameEngine<S> gameEngine;
    private final ArrayList<UIObserver<S>> uiObservers = new ArrayList<>();
    private final Map<Integer, DecisionController<S>> controllers = new HashMap<>();
    GameController<S> gameController;

    public GameBuilder(S gameState, GameEvent<S> startEvent) {
        gameEngine = new GameEngine<>(gameState, startEvent);
        gameController = new GameController<>(gameEngine, controllers, uiObservers);
    }

    public GameBuilder<S> addDecisionController(DecisionController<S> controller) {
        controllers.put(controllers.size()+1, controller);
        return this;
    }

    public GameBuilder<S> addUIObserver(UIObserver<S> observer) {
        uiObservers.add(observer);
        return this;
    }

    public void onGameEnd(Consumer<GameResult> listener) {
        gameController.setOnGameEndedListener(listener);
    }

    public GameController<S> build() {
        return gameController;
    }
}
