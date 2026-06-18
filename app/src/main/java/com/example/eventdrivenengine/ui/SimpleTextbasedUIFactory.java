package com.example.eventdrivenengine.ui;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.eventdrivenengine.R;

public class SimpleTextbasedUIFactory implements SimpleUIFactory{
    private final View root;

    public SimpleTextbasedUIFactory(View root) {
        this.root = root;
    }

    @Override
    public PlayerView createPlayerView() {
        TextView healthText = root.findViewById(R.id.tvPlayerHealth);
        ImageView defendIcon = root.findViewById(R.id.imgPlayerDefend);
        Button attackButton = root.findViewById(R.id.btnAttack);
        Button defendButton = root.findViewById(R.id.btnDefend);
        return new TextbasedPlayerView(healthText, defendIcon, attackButton, defendButton);
    }

    @Override
    public EnemyView createEnemyView() {
        TextView healthText = root.findViewById(R.id.tvEnemyHealth);
        ImageView defendIcon = root.findViewById(R.id.imgEnemyDefend);
        TextView nextMove = root.findViewById(R.id.tvNextMove);
        return new TextbasedEnemyView(healthText, defendIcon, nextMove);
    }
}
