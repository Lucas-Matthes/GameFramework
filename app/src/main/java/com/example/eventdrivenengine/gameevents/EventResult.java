package com.example.eventdrivenengine.gameevents;

import com.example.eventdrivenengine.decisionrequests.DecisionRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EventResult<S> {
    DecisionRequest<S> DecisionRequest;
    ArrayList<GameEvent<S>> newEvents;

    public EventResult(List<GameEvent<S>> newEvents, DecisionRequest<S> DecisionRequest) {
        this.newEvents = new ArrayList<>(newEvents);
        this.DecisionRequest = DecisionRequest;
    }

    public List<GameEvent<S>> getNewEvents() {
        return newEvents;
    }

    public Optional<DecisionRequest<S>> getDecisionRequest() {
        return Optional.ofNullable(DecisionRequest);
    }
}
