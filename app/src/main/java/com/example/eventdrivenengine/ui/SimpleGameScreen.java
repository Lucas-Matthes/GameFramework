package com.example.eventdrivenengine.ui;

import com.example.eventdrivenengine.core.SimpleGameState;

public class SimpleGameScreen extends GameScreen<SimpleGameState> {
    PlayerPanel playerPanel;
    EnemyPanel enemyPanel;

    public SimpleGameScreen(SimpleUIFactory uiFactory) {
        super(uiFactory);
    }

    @Override
    protected void createPanels(UIFactory uiFactory) {
        SimpleUIFactory simpleUIFactory = (SimpleUIFactory) uiFactory;
        createPlayerPanel(simpleUIFactory);
        createEnemyPanel(simpleUIFactory);
    }

    private void createPlayerPanel(SimpleUIFactory simpleUIFactory) {
        playerPanel = new PlayerPanel(simpleUIFactory);
        uiObservers.add(playerPanel);
    }

    private void createEnemyPanel(SimpleUIFactory simpleUIFactory) {
        enemyPanel = new EnemyPanel(simpleUIFactory);
        uiObservers.add(enemyPanel);
    }
}
