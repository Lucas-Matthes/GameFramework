package com.example.eventdrivenengine.ui;

import android.view.View;

import com.example.eventdrivenengine.userinputs.UserInput;

import java.util.function.Consumer;

public interface PlayerView {
    public void setHealth(int health);
    public void setDefendIconVisibility(boolean visible);
    public void setAttackCallback(Runnable callback);
    public void setDefendCallback(Runnable callback);
    public void setChoiceVisbility(boolean visible);
}
