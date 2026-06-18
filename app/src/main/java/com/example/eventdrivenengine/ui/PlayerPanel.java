package com.example.eventdrivenengine.ui;

import com.example.eventdrivenengine.core.SimpleGameState;
import com.example.eventdrivenengine.decisionrequests.ChoosePlayerAction;
import com.example.eventdrivenengine.decisionrequests.DecisionRequest;
import com.example.eventdrivenengine.gameevents.EnemyActionEvent;
import com.example.eventdrivenengine.gameevents.GameEvent;
import com.example.eventdrivenengine.gameevents.GameStartEvent;
import com.example.eventdrivenengine.gameevents.PlayerActionEvent;
import com.example.eventdrivenengine.gameevents.RoundEndEvent;
import com.example.eventdrivenengine.gamepieces.Action;
import com.example.eventdrivenengine.userinputs.UserInput;
import com.example.eventdrivenengine.userinputs.UserInputKey;

import java.util.HashMap;
import java.util.function.Consumer;

public class PlayerPanel implements UIObserver<SimpleGameState> {
    private final PlayerView playerView;

    public PlayerPanel(SimpleUIFactory uiFactory) {
        this.playerView = uiFactory.createPlayerView();
    }

    @Override
    public void notify(GameEvent<SimpleGameState> event, SimpleGameState simpleGameState) {
        if(event instanceof EnemyActionEvent) {
            playerView.setHealth(simpleGameState.getPlayer().getHealth());
        }
        if(event instanceof PlayerActionEvent) {
            playerView.setDefendIconVisibility(simpleGameState.getPlayer().isGuardUp());
        }
        if(event instanceof RoundEndEvent) {
            playerView.setDefendIconVisibility(simpleGameState.getPlayer().isGuardUp());
        }
        if(event instanceof GameStartEvent) {
            playerView.setHealth(simpleGameState.getPlayer().getHealth());
            playerView.setDefendIconVisibility(simpleGameState.getPlayer().isGuardUp());
        }
    }

    @Override
    public void handle(DecisionRequest<SimpleGameState> request, Consumer<UserInput> callback) {
        if(request instanceof ChoosePlayerAction) {
            playerView.setAttackCallback(() -> {
                HashMap<UserInputKey, Object> data = new HashMap<>();
                data.put(UserInputKey.SELECTED_ACTION, Action.ATTACK_ACTION);
                UserInput input = new UserInput(data);
                playerView.setChoiceVisbility(false);
                callback.accept(input);
            });

            playerView.setDefendCallback(() -> {
                HashMap<UserInputKey, Object> data = new HashMap<>();
                data.put(UserInputKey.SELECTED_ACTION, Action.DEFEND_ACTION);
                UserInput input = new UserInput(data);
                playerView.setChoiceVisbility(false);
                callback.accept(input);
            });

            playerView.setChoiceVisbility(true);
        }
    }
}
