package com.qqhouse.dungeon18plus.core;

public enum GameAlignment {
    // alignment 類似 D&D 的陣營
    // 平凡 : 雜魚小怪
    ORDINARY("ordinary"),
    // 守序 : 英雄, 牧師
    LAWFUL("lawful"),
    // 混亂 : 骷髏王, 惡魔
    CHAOTIC("chaotic"),
    // 中立 : 小仙女, 劍聖, 魔法師, 四大騎士
    NEUTRAL("neutral"),
    // 特殊 : 商人
    SPECIAL("special");

    public final String key;

    GameAlignment(String key) {
        this.key = key;
    }

}
