package com.example.eventdrivenengine.gamepieces;

import com.example.eventdrivenengine.core.SimpleGameState;
import com.example.eventdrivenengine.gameevents.GameEndEvent;
import com.example.eventdrivenengine.gameevents.GameEvent;

import java.util.ArrayList;
import java.util.Collections;

public class Enemy extends Fighter{
    private ArrayList<Action> actions = new ArrayList<>();
    private Action nextAction;
    private boolean guardQueued;

    public Enemy() {
        super();
        setHealth(5);
    }

    @Override
    protected GameEvent<SimpleGameState> getDeathEvent() {
        return new GameEndEvent("Win", 1);
    }

    public void startRound() {
        if(guardQueued) {
            setGuardIsUp(true);
        }
        guardQueued = false;
    }

    @Override
    public void defend() {
        guardQueued = true;
    }

    public GameEvent<SimpleGameState> act(Player player) {
        if(nextAction == Action.ATTACK_ACTION) {
            return attack(player);
        }
        else if(nextAction == Action.DEFEND_ACTION) {
            defend();
            return null;
        }
        throw new RuntimeException("Invalid action");
    }
    public Action chooseNextAction() {
        if(actions.isEmpty()) {
            shuffleActionDeck();
        }
        nextAction = actions.get(0);
        actions.remove(0);
        return nextAction;
    }

    public Action getNextAction() {
        return nextAction;
    }

    private void shuffleActionDeck() {
        actions.add(Action.ATTACK_ACTION);
        actions.add(Action.ATTACK_ACTION);
        actions.add(Action.ATTACK_ACTION);
        actions.add(Action.DEFEND_ACTION);
        actions.add(Action.DEFEND_ACTION);
        actions.add(Action.DEFEND_ACTION);
        Collections.shuffle(actions);
    }
}
