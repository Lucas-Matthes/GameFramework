package com.example.eventdrivenengine.ui;

import com.example.eventdrivenengine.decisionrequests.DecisionRequest;
import com.example.eventdrivenengine.gameevents.GameEvent;
import com.example.eventdrivenengine.userinputs.UserInput;

import java.util.function.Consumer;

public interface UIObserver<S> {
    public void notify(GameEvent<S> event, S gameState);
    public void handle(DecisionRequest<S> request, Consumer<UserInput> callback);
}
