package com.qqhouse.dungeon18plus.gamedata;

import com.badlogic.gdx.Gdx;
import com.qqhouse.io.QQSaveGame;

public class SaveGame extends QQSaveGame {
    public SaveGame(String fileName) {
        super(fileName);
    }

    @Override
    protected void newGame() {
        Gdx.app.error("TEST", "SaveGame.newGame()");
    }

    @Override
    protected void loadGame() {
        Gdx.app.error("TEST", "SaveGame.loadGame()");
    }

    @Override
    protected void saveGame() {
        Gdx.app.error("TEST", "SaveGame.saveGame()");
    }
}
