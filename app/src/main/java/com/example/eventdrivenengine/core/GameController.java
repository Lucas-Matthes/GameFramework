package com.example.eventdrivenengine.core;

import com.example.eventdrivenengine.decisionrequests.DecisionController;
import com.example.eventdrivenengine.decisionrequests.DecisionRequest;
import com.example.eventdrivenengine.gameevents.GameEvent;
import com.example.eventdrivenengine.ui.UIObserver;
import com.example.eventdrivenengine.userinputs.UserInput;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Map;
import java.util.Queue;
import java.util.function.Consumer;

public class GameController<S> {
    private GameEngine<S> engine;
    private final Map<Integer, DecisionController<S>> controllers;
    private ArrayList<UIObserver<S>> uiObservers;

    private final Queue<UserInput> inputQueue = new ArrayDeque<>();
    private boolean processing = false;
    private Consumer<GameResult> onGameEndedListener;

    public GameController(GameEngine<S> engine, Map<Integer, DecisionController<S>> controllers, ArrayList<UIObserver<S>> uiObservers) {
        this.engine = engine;
        this.controllers = controllers;
        this.uiObservers = uiObservers;
    }

    public void setOnGameEndedListener(Consumer<GameResult> listener) {
        this.onGameEndedListener = listener;
    }

    public void startGame() {
        advance(null);
    }

    public synchronized void submitInput(UserInput input) {
        inputQueue.add(input);
        if (!processing) {
            processQueue();
        }
    }

    private void processQueue() {
        processing = true;

        try {
            UserInput input;

            while ((input = pollInput()) != null) {
                advance(input);
            }

        } finally {
            processing = false;
        }
    }

    private synchronized UserInput pollInput() {
        return inputQueue.poll();
    }

    public void advance(UserInput input) {
        while (true) {
            StepResult<S> stepResult = engine.step(input);

            if (!stepResult.isInputAccepted()) {
                throw new RuntimeException(stepResult.getDeclineReason());
            }

            GameEvent<S> event = stepResult.getAppliedEvent();
            if(event != null) {
                notifyUI(event, stepResult.getGameState());

                if(event.getGameEnded()){
                    if (onGameEndedListener != null && event.getGameResult().isPresent()) {
                        onGameEndedListener.accept(event.getGameResult().get());
                    }
                    return;
                }
            }


            if (stepResult.getDecisionRequest().isPresent()) {
                DecisionRequest<S> request = stepResult.getDecisionRequest().get();
                DecisionController<S> controller = controllers.get(request.getPlayerID());
                controller.handle(request, this::submitInput);
                return;
            }
        }
    }

    private void notifyUI(GameEvent<S> event, S gameState) {
        for(UIObserver<S> observer : uiObservers) {
            observer.notify(event, gameState);
        }
    }
}
