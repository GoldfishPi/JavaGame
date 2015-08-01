package com.gfp.game.level.Tiles;

import com.gfp.game.gfx.Screen;
import com.gfp.game.level.Level;

public class AnimatedTile extends BasicTile {
	private int currentAnimationIndex;
	private int[][] animationTileCords;
	private long lastIterationTime;
	private int animationSwitchDelay;

	public AnimatedTile(int id, int[][] animationCords,int tileColour, int levelColour, int animationSwitchDelay) {
		super(id, animationCords[0][0], animationCords[0][1], tileColour,levelColour);
		this.animationTileCords = animationCords;
		this.currentAnimationIndex = 0;
		this.lastIterationTime = System.currentTimeMillis();
		this.animationSwitchDelay = animationSwitchDelay;

	}

	@Override
	public void tick() {
			if((System.currentTimeMillis() - lastIterationTime) >= (animationSwitchDelay)){
				lastIterationTime = System.currentTimeMillis();
				currentAnimationIndex = (currentAnimationIndex +1) % animationTileCords.length;
				this.tileId = (animationTileCords[currentAnimationIndex][0] + animationTileCords[currentAnimationIndex][1] * 32);
			}
	}
}
