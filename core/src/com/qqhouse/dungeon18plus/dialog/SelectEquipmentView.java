package com.qqhouse.dungeon18plus.dialog;

import com.badlogic.gdx.utils.viewport.Viewport;
import com.qqhouse.dungeon18plus.Assets;
import com.qqhouse.dungeon18plus.Game;
import com.qqhouse.dungeon18plus.core.Item;
import com.qqhouse.dungeon18plus.struct.EquipmentMastery;
import com.qqhouse.dungeon18plus.struct.hero.Veteran;
import com.qqhouse.dungeon18plus.view.AssetGroup;
import com.qqhouse.dungeon18plus.view.TitleBarView2;
import com.qqhouse.dungeon18plus.view.UniqueSkillButton;
import com.qqhouse.dungeon18plus.view.VeteranButton;
import com.qqhouse.ui.QQLinear;
import com.qqhouse.ui.QQList1;
import com.qqhouse.ui.QQListAdapter;
import com.qqhouse.ui.QQPressAdapter;
import com.qqhouse.ui.QQView;

import java.util.ArrayList;

public class SelectEquipmentView extends AssetGroup {

    public interface SelectEquipmentCallback {
        void SelectEquipmentDone(Veteran veteran);
    }

    // data
    private Veteran veteran;
    private ArrayList<EquipmentMastery> backpack;
    private VeteranButton vv;
    private final Viewport viewport;
    private final QQLinear group;

    public SelectEquipmentView(Assets assets, Viewport viewport) {
        super(assets);
        this.viewport = viewport;
        setPadding(8);
        bgNormal = assets.getNinePatch("dialog");

        group = new QQLinear(Game.Size.WIDGET_MARGIN);
        group.setSize(QQView.MATCH_PARENT, QQView.WRAP_CONTENT);
        group.setPosition(leftPadding, bottomPadding);
        addChild(group);
    }

    public void reset(Veteran veteran, ArrayList<EquipmentMastery> backpack, SelectEquipmentCallback callback) {
        this.veteran = veteran;
        this.backpack = backpack;

        TitleBarView2 master = new TitleBarView2(assets);
        master.reset("sword_master", "colosseum_master");
        master.setSize(QQView.MATCH_PARENT, 48);
        master.setPadding(8);
        master.setBackground(assets.getNinePatch("neutral"));
        group.addChild(master);

        // list
        QQList1 equips = new QQList1(viewport, Game.Size.INNER_MARGIN);
        equips.setSize(QQView.MATCH_PARENT, QQView.WRAP_CONTENT);
        equips.setMaxHeight(400);
        equips.setAdapter(adapter);
        equips.addListener(new QQList1.PressAdapter() {
            @Override
            public void onPress(int index) {
                // set UniqueSkill
                veteran.equipment = backpack.get(index).equipment;
                veteran.mastery = backpack.get(index).mastery;
                vv.updateUniqueSkill(veteran);
            }
        });
        group.addChild(equips);

        // veteran
        vv = new VeteranButton(assets);
        vv.setPadding(8);
        vv.setSize(QQView.MATCH_PARENT, 64);
        vv.setQQPressListener(new QQPressAdapter() {
            @Override
            public void onPress(int index) {
                if (Item.NONE != veteran.equipment) {
                    callback.SelectEquipmentDone(veteran);
                }
            }
        });
        vv.reset(veteran);
        group.addChild(vv);
    }

    @Override
    public void resetWrapHeight() {
        height = group.getHeight() + topPadding + bottomPadding;
        if (null != parent)
            parent.onChildSizeChanged(this);
    }

    @Override
    public void onParentSizeChanged(float width, float height) {
        if (0 < width)
            group.setSize(width - leftPadding - rightPadding, group.getHeight());
    }



    /*
        list view of boss kill....
     */
    private final QQListAdapter adapter = new QQListAdapter() {
        @Override
        public int getSize() {
            return backpack.size();
        }

        @Override
        public QQView getView(int index) {
            final UniqueSkillButton v = new UniqueSkillButton(assets);
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
    };
}
