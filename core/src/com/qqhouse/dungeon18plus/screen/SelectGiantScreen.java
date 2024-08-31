package com.qqhouse.dungeon18plus.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.qqhouse.dungeon18plus.Assets;
import com.qqhouse.dungeon18plus.Game;
import com.qqhouse.dungeon18plus.core.GiantRace;
import com.qqhouse.dungeon18plus.gamedata.SaveGame;
import com.qqhouse.dungeon18plus.view.PreviewView3;
import com.qqhouse.tools.QRGenerator;
import com.qqhouse.ui.QQImage;
import com.qqhouse.ui.QQLinear;
import com.qqhouse.ui.QQList1;
import com.qqhouse.ui.QQListAdapter;
import com.qqhouse.ui.QQPressAdapter;
import com.qqhouse.ui.QQScreen;
import com.qqhouse.ui.QQText;
import com.qqhouse.ui.QQView;

import org.jetbrains.annotations.NotNull;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;

import javax.imageio.ImageIO;

public class SelectGiantScreen extends QQScreen {

    public interface SelectGiantCallback {
        void onLegionTrainer();
        void onSoulMaster();
        void onSelectGiant(GiantRace giant);
    }

    private final SelectGiantCallback callback;
    private ArrayList<GiantRace> availableGiants;

    public SelectGiantScreen(SaveGame savedGame, Viewport viewport, Assets assets, @NotNull SelectGiantCallback callback) {
        super(savedGame, viewport, assets);
        this.callback = callback;
    }

    @Override
    public void onEnter() {
        availableGiants = savedGame.getAvailableGiant();

        // group of background.
        QQLinear group = new QQLinear(Game.Size.WIDGET_MARGIN);
        group.setBackground(assets.getNinePatchBG("help"));
        group.setSize(Game.Size.WIDTH - 12 - 12, QQView.WRAP_CONTENT);//Game.Size.HEIGHT * 0.9f);
        group.setPadding(8);
        addChild(group);

        // legion trainer
        PreviewView3 legionTrainer = new PreviewView3(assets);
        legionTrainer.reset("crusader","crusader","legion_trainer_intro", "lawful");
        legionTrainer.setSize(QQView.MATCH_PARENT, QQView.WRAP_CONTENT);
        legionTrainer.setQQPressListener(new QQPressAdapter() {
            @Override
            public void onPress(int index) {
                if (null != callback)
                    callback.onLegionTrainer();
            }
        });
        group.addChild(legionTrainer);

        // soul master if open
        if (savedGame.openSoulMaster()) {
            PreviewView3 soulMaster = new PreviewView3(assets);
            soulMaster.reset("valkyrie", "valkyrie", "soul_master_intro", "neutral");
            soulMaster.setSize(QQView.MATCH_PARENT, QQView.WRAP_CONTENT);
            soulMaster.setQQPressListener(new QQPressAdapter() {
                @Override
                public void onPress(int index) {
                    if (null != callback)
                        callback.onSoulMaster();
                }
            });
            group.addChild(soulMaster);
        }

        // info
        QQText info = new QQText(assets.getFont(Game.Font.NAME20));// fight_giant
        info.setSize(QQView.MATCH_PARENT, 28);
        info.setPadding(0, 0, 4, 0);
        info.setText(assets.geti18n("fight_giant"));
        info.setAlign(Align.left);
        group.addChild(info);

        // list of available giants ...
        QQList1 list = new QQList1(getViewport(), Game.Size.WIDGET_MARGIN);
        //list.setBackground(new NinePatch(assets.getBackground("help"), 4, 4, 4, 4));
        list.setMaxHeight(Game.Size.HEIGHT * 0.5f); // 680 * 0.9 - 48 - 4
        list.setSize(QQView.MATCH_PARENT, QQView.WRAP_CONTENT);
        list.setAdapter(availableGiantsAdapter);
        list.addListener(new QQList1.PressAdapter() {
            @Override
            public void onPress(int index) {
                callback.onSelectGiant(availableGiants.get(index));
            }
        });
        group.addChild(list);

        // group set size
        group.setPosition(12, (Game.Size.HEIGHT - group.getHeight()) / 2);

        // add am QQImage with QRCode ...???
        //Long t = TimeUtils.millis();
        //QQImage image = new QQImage(testQRCode());
        //image.setPosition(0, 0);
        //addChild(image);
        //Gdx.app.error("SelectGiantScreen", "QRCode time : " + (TimeUtils.millis() - t));
    }

    @Override
    public void onLeave() {
        removeAllChildren();
    }

    private TextureRegion testQRCode() {
        String input = "http://Zeroed.techadfdddddddddddddddddddddddddddddddddddsdffffffffffffffffffffffffffffffffkljfhskldjlfhsaiurwklbelkrh9o3kljsdbfiusaydfioweksdfkljsldfiuy32oprklsjhfiowu3lkdskljfhiouwekljdsfflksdjhflksajhdfiouelkajhfdluaslufhlwuhlkajsfhlksjdfluhelkasdflksdfkjfffffffffffffffffffffffffffffffffffffffffffffffffffffffffddddddd";
        // Generate a basic QR code
        return new QRGenerator().generate(input);
    }

    private final QQListAdapter availableGiantsAdapter = new QQListAdapter() {
        @Override
        public int getSize() {
            return availableGiants.size();
        }

        @Override
        public QQView getView(int index) {
            GiantRace giant = availableGiants.get(index);
            PreviewView3 v = new PreviewView3(assets);
            v.setSize(QQView.MATCH_PARENT, QQView.WRAP_CONTENT);
            v.reset(giant.iconKey, giant.nameKey, giant.helpKey, giant.alignment.key);
            // update extra value...
            return v;
        }
    };
}
