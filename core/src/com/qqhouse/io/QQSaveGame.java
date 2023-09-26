package com.qqhouse.io;

public abstract class QQSaveGame {

    private String fileName;
    public QQSaveGame(String fileName) {
        this.fileName = fileName;
        newGame();
    }

    public final void load() {
        loadGame();

    }

    public final void save() {
        saveGame();
    }
    protected abstract void newGame();
    protected abstract void loadGame();

    protected abstract void saveGame();


}
