package com.example.eventdrivenengine.ui;

public interface SimpleUIFactory extends UIFactory{
    public PlayerView createPlayerView();
    public EnemyView createEnemyView();
}