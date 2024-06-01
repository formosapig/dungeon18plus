package com.qqhouse.dungeon18plus.view;

import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.qqhouse.dungeon18plus.Assets;
import com.qqhouse.dungeon18plus.Game;
import com.qqhouse.dungeon18plus.core.Item;
import com.qqhouse.dungeon18plus.struct.EquipmentMastery;
import com.qqhouse.dungeon18plus.struct.hero.Veteran;
import com.qqhouse.ui.QQGroup;
import com.qqhouse.ui.QQList;
import com.qqhouse.ui.QQPressListener;
import com.qqhouse.ui.QQView;

import java.util.ArrayList;

public class SelectEquipmentView extends QQGroup {

    public interface SelectEquipmentCallback {
        void SelectEquipmentDone(Veteran veteran);
    }

    // data
    private Veteran veteran;
    private ArrayList<EquipmentMastery> backpack;

    private VeteranButton vv;
    private SelectEquipmentCallback callback;

    private final Assets assets;
    private final Viewport viewport;
    public SelectEquipmentView(Assets assets, Viewport viewport) {
        super(DIRECT_VERTICAL, Game.Size.WIDGET_MARGIN);
        this.assets = assets;
        this.viewport = viewport;
        setPadding(Game.Size.BLOCKEE_PADDING);
        bgNormal = new NinePatch(assets.getBackground("dialog"), 4, 4, 4, 4);
    }

    public void reset(Veteran veteran, ArrayList<EquipmentMastery> backpack, SelectEquipmentCallback callback) {
        this.veteran = veteran;
        this.backpack = backpack;
        this.callback = callback;

        // veteran
        vv = new VeteranButton(assets);
        vv.setPadding(8);
        vv.setSize(QQView.MATCH_PARENT, 64);
        vv.addQQClickListener(new QQPressListener() {
            @Override
            public void onPress(int index) {
                if (Item.NONE != veteran.equipment) {
                    callback.SelectEquipmentDone(veteran);
                }
            }
            @Override
            public void onLongPress(QQView view) {}
        }, 0);
        vv.reset(veteran);
        addChild(vv);

        // list
        QQList equips = new QQList(viewport);
        //scores.setSize(QQView.MATCH_PARENT, QQView.MATCH_PARENT);
        equips.setSize(QQView.MATCH_PARENT, QQView.WRAP_CONTENT);
        equips.setMaxHeight(400);
        equips.setAdapter(adapter);
        equips.addListener(new QQList.PressListener() {
            @Override
            public void onPress(int index) {
                // set UniqueSkill
                veteran.equipment = backpack.get(index).equipment;
                veteran.mastery = backpack.get(index).mastery;
                vv.updateUniqueSkill(veteran);
            }

            @Override
            public void onLongPress(int index) {

            }
        });
        addChild(equips);
        //scores.setBackground(new NinePatch(assets.getBackground("white"), 4, 4, 4, 4));

        TitleBarView2 master = new TitleBarView2(assets);
        master.reset("sword_master", "colosseum_master");
        master.setPosition(leftPadding + 4, height - 40 - topPadding);
        master.setSize(QQView.MATCH_PARENT, 48);
        master.setPadding(8);
        master.setBackground(assets.getNinePatchBG("neutral"));
        addChild(master);

        resetWrapHeight();
        if (null != parent)
            parent.onChildSizeChanged(this);
    }

    /*
        list view of boss kill....
     */
    private final QQList.Adapter adapter = new QQList.Adapter() {
        @Override
        public int getSize() {
            return backpack.size();
        }

        @Override
        public QQView getView(int index) {
            final UniqueSkillView v = new UniqueSkillView(assets);
            EquipmentMastery em = backpack.get(index);
            // check isMastery
            boolean isMastery = false;

            for (Item equip: veteran.heroClass.masteryEquipment) {
                if (em.equipment.equals(equip)) {
                    isMastery = true;
                    break;
                }
            }

            v.reset(em, isMastery, veteran);
            v.setSize(QQView.MATCH_PARENT, 48);
            return v;
        }

        @Override
        public void updateView(int index, QQView view) {

        }
    };
}
