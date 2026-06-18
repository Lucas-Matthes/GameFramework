package com.example.eventdrivenengine.ui;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class TextbasedPlayerView implements PlayerView{
    TextView healthText;
    ImageView defendIcon;
    Button attackButton;
    Button defendButton;

    public TextbasedPlayerView(TextView healthText, ImageView defendIcon, Button attackButton, Button defendButton) {
        this.healthText = healthText;
        this.defendIcon = defendIcon;
        this.attackButton = attackButton;
        this.defendButton = defendButton;
    }

    public void setHealth(int health) {
        healthText.setText(String.valueOf(health));
    }

    public void setDefendIconVisibility(boolean visible) {
        defendIcon.setVisibility(visible ? View.VISIBLE : View.GONE);
    }

    public void setAttackCallback(Runnable callback) {
        attackButton.setOnClickListener(createListener(callback));
    }

    public void setDefendCallback(Runnable callback) {
        defendButton.setOnClickListener(createListener(callback));
    }

    @Override
    public void setChoiceVisbility(boolean visible) {
        attackButton.setVisibility(visible ? View.VISIBLE : View.GONE);
        defendButton.setVisibility(visible ? View.VISIBLE : View.GONE);
    }

    private View.OnClickListener createListener(Runnable callback) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callback.run();
            }
        };
    }
}
