package com.example.eventdrivenengine.decisionrequests;

import com.example.eventdrivenengine.core.SimpleGameState;
import com.example.eventdrivenengine.ui.GameScreen;
import com.example.eventdrivenengine.userinputs.UserInput;

import java.util.function.Consumer;

public class LocalController<S> implements DecisionController<S> {
    GameScreen<S> gameScreen;

    public LocalController(GameScreen<S> gameScreen) {
        this.gameScreen = gameScreen;
    }

    @Override
    public void handle(DecisionRequest<S> request, Consumer<UserInput> callback) {
        gameScreen.handle(request, callback);
    }
}
