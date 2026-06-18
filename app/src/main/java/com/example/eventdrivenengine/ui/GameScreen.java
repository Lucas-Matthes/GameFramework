package com.example.eventdrivenengine.ui;

import com.example.eventdrivenengine.core.SimpleGameState;
import com.example.eventdrivenengine.decisionrequests.DecisionRequest;
import com.example.eventdrivenengine.gameevents.GameEvent;
import com.example.eventdrivenengine.userinputs.UserInput;

import java.util.ArrayList;
import java.util.function.Consumer;

public abstract class GameScreen<S> implements UIObserver<S>{
    ArrayList<UIObserver<S>> uiObservers = new ArrayList<>();

    public GameScreen(UIFactory uiFactory) {
        createPanels(uiFactory);
    }

    protected abstract void createPanels(UIFactory uiFactory);

    @Override
    public void notify(GameEvent<S> event, S gameState) {
        for(UIObserver<S> observer : uiObservers) {
            observer.notify(event, gameState);
        }
    }

    @Override
    public void handle(DecisionRequest<S> request, Consumer<UserInput> callback) {
        for (UIObserver<S> observer : uiObservers) {
            observer.handle(request, callback);
        }
    }
}
