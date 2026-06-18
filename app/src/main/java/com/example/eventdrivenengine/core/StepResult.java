package com.example.eventdrivenengine.core;

import com.example.eventdrivenengine.decisionrequests.DecisionRequest;
import com.example.eventdrivenengine.gameevents.GameEvent;

import java.util.Optional;

public class StepResult<S> {
    private boolean inputAccepted;
    private S gameState;
    private String declineReason;
    private DecisionRequest<S> request;
    private GameEvent<S> appliedEvent;

    public StepResult(String declineReason, DecisionRequest<S> failedRequest, S gameState) {
        this.inputAccepted = false;
        this.declineReason = declineReason;
        this.request = failedRequest;
        this.appliedEvent = null;
        this.gameState = gameState;
    }

    public StepResult(GameEvent<S> appliedEvent, DecisionRequest<S> request, S gameState) {
        this.inputAccepted = true;
        this.declineReason = null;
        this.request = request;
        this.appliedEvent = appliedEvent;
        this.gameState = gameState;
    }

    public StepResult(GameEvent<S> appliedEvent, S gameState) {
        this.inputAccepted = true;
        this.declineReason = null;
        this.request = null;
        this.appliedEvent = appliedEvent;
        this.gameState = gameState;
    }

    public Optional<DecisionRequest<S>> getDecisionRequest() {
        return Optional.ofNullable(request);
    }

    public boolean isInputAccepted() {
        return inputAccepted;
    }

    public String getDeclineReason() {
        return declineReason;
    }

    public GameEvent<S> getAppliedEvent() {
        return appliedEvent;
    }

    public S getGameState() {
        return gameState;
    }
}