package com.example.eventdrivenengine.gamepieces;

import com.example.eventdrivenengine.core.SimpleGameState;
import com.example.eventdrivenengine.gameevents.GameEvent;

public abstract class Fighter {
    private int health;
    private boolean guardIsUp = false;

    public Fighter() {
        this.health = 3;
    }

    public GameEvent<SimpleGameState> attack(Fighter target) {
        return target.takeDamage();
    }

    public void defend() {
        guardIsUp = true;
    }

    public GameEvent<SimpleGameState> takeDamage() {
        if(guardIsUp) {
            return null;
        }
        health--;
        if(health <= 0) {
            return getDeathEvent();
        }
        return null;
    }

    public int getHealth() {
        return health;
    }

    protected abstract GameEvent<SimpleGameState> getDeathEvent();

    public void lowerGuard() {
        guardIsUp = false;
    }

    public boolean isGuardUp() {
        return guardIsUp;
    }

    public void setGuardIsUp(boolean guardIsUp) {
        this.guardIsUp = guardIsUp;
    }

    protected void setHealth(int health) {
        this.health = health;
    }
}
