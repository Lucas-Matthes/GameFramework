package com.example.eventdrivenengine.decisionrequests;

import com.example.eventdrivenengine.core.SimpleGameState;
import com.example.eventdrivenengine.gameevents.PlayerActionEvent;
import com.example.eventdrivenengine.gamepieces.Action;
import com.example.eventdrivenengine.gameevents.GameEvent;
import com.example.eventdrivenengine.userinputs.UserInput;
import com.example.eventdrivenengine.userinputs.UserInputKey;

import java.util.ArrayList;
import java.util.Collections;

public class ChoosePlayerAction extends DecisionRequest<SimpleGameState> {
    public ChoosePlayerAction(int controllerTargetID) {
        super(controllerTargetID);
    }

    @Override
    public boolean acceptsInput(UserInput input) {
        return input.contains(UserInputKey.SELECTED_ACTION);
    }

    @Override
    public ArrayList<GameEvent<SimpleGameState>> handleInput(UserInput input) {
        if (!acceptsInput(input)) {
            throw new RuntimeException("Invalid input");
        }

        Action action = input.get(UserInputKey.SELECTED_ACTION, Action.class);

        return new ArrayList<>(Collections.singletonList(new PlayerActionEvent(action)));
    }
}
