package com.example.eventdrivenengine.decisionrequests;

import com.example.eventdrivenengine.userinputs.UserInput;

public interface DecisionController<S> {
    void handle(DecisionRequest<S> request, java.util.function.Consumer<UserInput> callback);
}
