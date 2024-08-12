package com.qqhouse.dungeon18plus.screen;

import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.qqhouse.dungeon18plus.Assets;
import com.qqhouse.dungeon18plus.Game;
import com.qqhouse.dungeon18plus.core.HeroClass;
import com.qqhouse.dungeon18plus.gamedata.SaveGame;
import com.qqhouse.dungeon18plus.struct.HeroClassRecord;
import com.qqhouse.dungeon18plus.view.PreviewView3;
import com.qqhouse.ui.QQLinear;
import com.qqhouse.ui.QQList1;
import com.qqhouse.ui.QQListAdapter;
import com.qqhouse.ui.QQPressListener;
import com.qqhouse.ui.QQScreen;
import com.qqhouse.ui.QQText;
import com.qqhouse.ui.QQView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class SelectHeroScreen extends QQScreen implements QQPressListener {

    public interface SelectHeroCallback {
        void onSelectHero(int gameMode, HeroClass hero);
    }

    private final SelectHeroCallback callback;
    private int gameMode;
    private ArrayList<HeroClassRecord> availableHeroes;

    public SelectHeroScreen(SaveGame savedGame, Viewport viewport, Assets assets, @NotNull SelectHeroCallback callback) {
        super(savedGame, viewport, assets);
        this.callback = callback;
    }

    public void setGameMode(int gameMode) {this.gameMode = gameMode;}

    @Override
    public void onEnter() {

        // initial hero class adapter ...
        savedGame.checkUnlockHeroClass();

        availableHeroes = savedGame.getAvailableHeroClassRecord(gameMode);

        // group of background.
        QQLinear group = new QQLinear(Game.Size.WIDGET_MARGIN);
        group.setPadding(8);
        group.setSize(Game.Size.WIDTH - 12 - 12, QQView.WRAP_CONTENT);
        group.setBackground(assets.getNinePatchBG("help"));
        addChild(group);

        // title
        QQText title = new QQText(assets.getFont(Game.Font.NAME20));
        title.setPadding(0, 0, 4, 0);
        title.setSize(QQView.MATCH_PARENT, 28);
        title.setText(assets.geti18n("select_hero"));
        title.setAlign(Align.left);
        group.addChild(title);

        // list of available heroes ...
        QQList1 list = new QQList1(getViewport(), Game.Size.WIDGET_MARGIN);
        //list.setBackground(new NinePatch(assets.getBackground("help"), 4, 4, 4, 4));
        list.setMaxHeight(Game.Size.HEIGHT * 0.9f - 28 - 4 - 8 - 8); // 680 * 0.9 - 48 - 4
        list.setSize(QQView.MATCH_PARENT, QQView.WRAP_CONTENT);
        list.setAdapter(availableHeroesAdapter);
        list.addListener(new QQList1.PressAdapter() {
            @Override
            public void onPress(int index) {
                callback.onSelectHero(gameMode, availableHeroes.get(index).heroClass);
            }
        });
        group.addChild(list);

        // group set size
        group.setPosition(12, (Game.Size.HEIGHT - group.getHeight()) / 2);
    }

    @Override
    public void onLeave() {removeAllChildren();}

    /*
        click to select hero class...
     */
    @Override
    public void onPress(int code) {
        //Gdx.app.error("TEST", "click. @" + TimeUtils.millis());
        //Gdx.app.error("TEST", "click");
        //Gdx.app.error("TEST", "click " + HeroClass.find(code) + "@" + TimeUtils.millis());
        // 時間上來看沒有什麼特別的問題. 差 1 ms 而已.
        callback.onSelectHero(gameMode, HeroClass.find(code));
    }

    @Override
    public void onLongPress(QQView view) {

    }

    private final QQListAdapter availableHeroesAdapter = new QQListAdapter() {
        @Override
        public int getSize() {return availableHeroes.size();}

        @Override
        public QQView getView(int index) {
            HeroClassRecord record = availableHeroes.get(index);

            PreviewView3 v = new PreviewView3(assets);
            v.setSize(QQView.MATCH_PARENT, QQView.WRAP_CONTENT);
            v.reset(record.heroClass.key, record.heroClass.key, record.heroClass.key + "_help", record.heroClass.alignment.key);
            //v.reset(record.heroClass.key, record.heroClass.key, record.heroClass.alignment.key);
            if (Game.Mode.DUNGEON == gameMode) {
                if (0 < record.highLevel)
                    v.resetLevel(Integer.toString(record.highLevel));
                if (0 < record.highScore)
                    v.resetExtra("rank", Integer.toString(record.highScore), Game.Colour.RANK);
            } else {
                if (0 < record.maxRound)
                    v.resetExtra("cost_soul", Integer.toString(record.maxRound), Game.Colour.ROUND);
            }
            return v;
        }
    };
}
