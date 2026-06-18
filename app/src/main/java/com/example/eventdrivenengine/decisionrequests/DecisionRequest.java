package com.example.eventdrivenengine.decisionrequests;

import com.example.eventdrivenengine.gameevents.GameEvent;
import com.example.eventdrivenengine.userinputs.UserInput;

import java.util.ArrayList;

public abstract class DecisionRequest <S>{
    int controllerTargetID;

    public int getPlayerID() {
        return controllerTargetID;
    }

    public DecisionRequest(int controllerTargetID) {
        this.controllerTargetID = controllerTargetID;
    }
    public abstract boolean acceptsInput(UserInput input);
    public abstract ArrayList<GameEvent<S>> handleInput(UserInput input);
}
