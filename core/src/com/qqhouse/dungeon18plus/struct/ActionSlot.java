package com.qqhouse.dungeon18plus.struct;

import com.qqhouse.dungeon18plus.core.Action;

import java.util.Locale;

public class ActionSlot {
    public Action action; // may change because Novice.learning.
    public int count;

    public ActionSlot(Action action) {
        this.action = action;
        this.count = 0;
    }

    public ActionSlot(Action action, int count) {
        this.action = action;
        this.count = count;
    }

    @Override
    public String toString() {
        return String.format(Locale.US, "%s x %d", action, count);
    }
}
