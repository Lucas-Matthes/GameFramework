package com.example.eventdrivenengine.gameevents;

import com.example.eventdrivenengine.core.SimpleGameState;
import com.example.eventdrivenengine.gamepieces.Action;
import com.example.eventdrivenengine.gamepieces.Player;

import java.util.ArrayList;

public class PlayerActionEvent implements GameEvent<SimpleGameState>{
    private Action action;

    public PlayerActionEvent(Action action) {
        this.action = action;
    }
    @Override
    public EventResult<SimpleGameState> apply(SimpleGameState simpleGameState) {
        Player player = simpleGameState.getPlayer();
        ArrayList<GameEvent<SimpleGameState>> events = new ArrayList<>();
        events.add(new EnemyActionEvent());
        if(action == Action.ATTACK_ACTION) {
            GameEvent<SimpleGameState> result = player.attack(simpleGameState.getEnemy());
            if(result != null) {
                events.add(result);
            }
        }
        else if(action == Action.DEFEND_ACTION) {
            player.defend();
        }

        return new EventResult<>(events, null);
    }
}
