package com.example.eventdrivenengine.decisionrequests;

import com.example.eventdrivenengine.core.SimpleGameState;
import com.example.eventdrivenengine.gamepieces.Action;
import com.example.eventdrivenengine.userinputs.UserInput;
import com.example.eventdrivenengine.userinputs.UserInputKey;

import java.util.HashMap;
import java.util.function.Consumer;

public class KIController implements DecisionController<SimpleGameState>{
    SimpleGameState simpleGameState;
    public KIController(SimpleGameState simpleGameState) {
        this.simpleGameState = simpleGameState;
    }
    @Override
    public void handle(DecisionRequest<SimpleGameState> request, Consumer<UserInput> callback) {
        if(request instanceof ChoosePlayerAction) {
            Action nextEnemyAction = simpleGameState.getEnemy().getNextAction();
            Action nextPlayerAction = null;

            switch (nextEnemyAction) {
                case ATTACK_ACTION:
                    nextPlayerAction = Action.DEFEND_ACTION;
                    break;
                case DEFEND_ACTION:
                    nextPlayerAction = Action.ATTACK_ACTION;
                    break;
            }

            if(nextPlayerAction == null) {
                throw new RuntimeException("Invalid action");
            }

            HashMap<UserInputKey, Object> input = new HashMap<>();
            input.put(UserInputKey.SELECTED_ACTION, nextPlayerAction);
            callback.accept(new UserInput(input));
        }
    }
}
