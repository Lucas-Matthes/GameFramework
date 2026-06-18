package com.example.eventdrivenengine.core;

public class SimpleGameResult implements GameResult {
    private final int winningPlayer;
    private final String reason;
    public SimpleGameResult(int winningPlayer, String reason) {
        this.winningPlayer = winningPlayer;
        this.reason = reason;
    }
    public int getWinningPlayer() {
        return winningPlayer;
    }
    public String getReason() {
        return reason;
    }
}
