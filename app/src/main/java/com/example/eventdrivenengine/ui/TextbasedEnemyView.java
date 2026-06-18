package com.example.eventdrivenengine.ui;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class TextbasedEnemyView implements EnemyView {
    TextView healthText;
    ImageView defendIcon;
    TextView nextMove;

    public TextbasedEnemyView(TextView healthText, ImageView defendIcon, TextView nextMove) {
        this.healthText = healthText;
        this.defendIcon = defendIcon;
        this.nextMove = nextMove;
    }

    @Override
    public void setHealth(int health) {
        healthText.setText(String.valueOf(health));
    }

    @Override
    public void setNextMove(String move) {
        nextMove.setText(move);
    }

    @Override
    public void setDefendIconVisibility(boolean visible) {
        defendIcon.setVisibility(visible ? View.VISIBLE : View.GONE);
    }
}
