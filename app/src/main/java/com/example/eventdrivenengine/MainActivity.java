package com.example.eventdrivenengine;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.eventdrivenengine.core.GameBuilder;
import com.example.eventdrivenengine.core.GameController;
import com.example.eventdrivenengine.core.GameResult;
import com.example.eventdrivenengine.core.SimpleGameState;
import com.example.eventdrivenengine.core.SimpleGameStateSetupHandler;
import com.example.eventdrivenengine.core.SimpleGameResult;
import com.example.eventdrivenengine.decisionrequests.LocalController;
import com.example.eventdrivenengine.gameevents.GameStartEvent;
import com.example.eventdrivenengine.ui.GameScreen;
import com.example.eventdrivenengine.ui.SimpleGameScreen;
import com.example.eventdrivenengine.ui.SimpleTextbasedUIFactory;

import java.util.function.Consumer;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ConstraintLayout mainLinearLayout = findViewById(R.id.main);
        View rootView = getLayoutInflater()
                .inflate(R.layout.screen, mainLinearLayout, false);
        mainLinearLayout.addView(rootView);

        SimpleTextbasedUIFactory factory = new SimpleTextbasedUIFactory(rootView);

        SimpleGameStateSetupHandler setupHandler = new SimpleGameStateSetupHandler();
        SimpleGameState simpleGameState = setupHandler.setupGame();

        GameBuilder<SimpleGameState> gameBuilder = new GameBuilder<>(simpleGameState, new GameStartEvent());
        GameScreen gameScreen = new SimpleGameScreen(factory);
        gameBuilder.addUIObserver(gameScreen)
                .addDecisionController(new LocalController(gameScreen))
                .onGameEnd(listener);

        GameController<SimpleGameState> gameController = gameBuilder.build();
        gameController.startGame();
    }

    private final Consumer<GameResult> listener = result -> {
        runOnUiThread(() -> {
            showEndScreen((SimpleGameResult) result);
        });
    };

    private void showEndScreen(SimpleGameResult result) {
        ConstraintLayout mainLayout = findViewById(R.id.main);
        mainLayout.removeAllViews();
        // You would inflate your end screen layout here:
        // View endView = getLayoutInflater().inflate(R.layout.end_screen, mainLayout, false);
        // mainLayout.addView(endView);

        // Temporary feedback
        TextView tv = new TextView(this);
        tv.setText(result.getReason());
        tv.setTextSize(32);
        mainLayout.addView(tv);
    }
}