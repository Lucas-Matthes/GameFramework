package com.example.eventdrivenengine.core;

import android.util.Log;

import com.example.eventdrivenengine.gameevents.EventResult;
import com.example.eventdrivenengine.gameevents.GameEvent;
import com.example.eventdrivenengine.gameevents.GameEventListener;
import com.example.eventdrivenengine.decisionrequests.DecisionRequest;
import com.example.eventdrivenengine.userinputs.UserInput;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Stack;

public class GameEngine<S> {
    private final S gameState;
    Stack<GameEvent<S>> eventStack = new Stack<>();
    ArrayList<GameEventListener<S>> eventListeners = new ArrayList<>();
    public GameEngine(S gameState, GameEvent<S> startEvent) {
        this.gameState = gameState;
        eventStack.add(startEvent);
    }
    private DecisionRequest<S> currentRequest = null;

    public StepResult<S> step(UserInput input) {
        if(currentRequest != null) {
            if(currentRequest.acceptsInput(input)) {
                eventStack.addAll(currentRequest.handleInput(input));
                currentRequest = null;
            } else {
                Log.e("GameEngine", "Invalid input received");
                return new StepResult<S>("Invalid input", currentRequest, gameState);

            }
        }

        if(eventStack.isEmpty()) {
            Log.e("GameEngine", "No events to process");
            return new StepResult<S>("No events to process", currentRequest, gameState);
        }

        GameEvent<S> currentEvent = eventStack.pop();

        EventResult<S> eventResult = currentEvent.apply(gameState);                                            //Only part that applies effects, event is done after this
        Log.e("GameEngine", "Event applied: " + currentEvent.getClass().getSimpleName());
        eventStack.addAll(eventResult.getNewEvents());                                                      //Direct automatic follow up events added
        Optional<DecisionRequest<S>> decisionRequest = eventResult.getDecisionRequest();

        for(GameEventListener<S> observer : eventListeners) {
            eventStack.addAll(observer.notify(currentEvent, gameState));                                    //Indirect automatic follow up events added
        }

        if(decisionRequest.isPresent()) {
            currentRequest = decisionRequest.get();
            return new StepResult<S>(currentEvent, currentRequest, gameState);
        }

        return new StepResult<S>(currentEvent, gameState);
    }
}
