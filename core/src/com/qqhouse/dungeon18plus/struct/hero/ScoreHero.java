package com.qqhouse.dungeon18plus.struct.hero;

import com.qqhouse.dungeon18plus.G;
import com.qqhouse.dungeon18plus.core.HeroClass;
import com.qqhouse.dungeon18plus.struct.Ability;

public class ScoreHero extends Ability implements Comparable<ScoreHero> {

	public HeroClass heroClass;
	public int level;
	public int score;
	// with bluetooth support, can add leaderboard data from another dungeon.
	public long fromAnotherDungeon;
	
	public ScoreHero(DungeonHero hero, int highScore) {
		this.copy(hero);
		this.heroClass = hero.heroClass;
		this.level = hero.level;
		this.score = highScore;
		this.fromAnotherDungeon = G.DEFAULT_DUNGEON_NUMBER;
	}

	public ScoreHero() {}
	
	// for array list contains check
	@Override
	public boolean equals(Object other) {
		if (other == null) return false;
		if (other == this) return true;
		if (!(other instanceof ScoreHero)) return false;
		
		ScoreHero otherHero = (ScoreHero) other;
		
		if (this.heroClass != otherHero.heroClass)
			return false;
		if (this.score != otherHero.score)
			return false;
		if (this.life != otherHero.life)
			return false;
		if (this.attack != otherHero.attack)
			return false;
		if (this.magic != otherHero.magic)
			return false;
		if (this.combo != otherHero.combo)
			return false;
		if (this.defense != otherHero.defense)
			return false;
		if (this.speed != otherHero.speed)
			return false;
		if (this.level != otherHero.level)
			return false;
		if (this.fromAnotherDungeon != otherHero.fromAnotherDungeon)
			return false;
		
		return true;
	}
		
	@Override
	public int compareTo(ScoreHero another) {
		if (another.score == this.score) {
			if (another.life == this.life)
			    // compare my or other.
				if (this.fromAnotherDungeon < another.fromAnotherDungeon)
					return -1;
				else if (this.fromAnotherDungeon > another.fromAnotherDungeon)
					return 1;
				else
					return 0;
			else
				return another.life - this.life;
		} else
			return another.score - this.score;
	}

}
