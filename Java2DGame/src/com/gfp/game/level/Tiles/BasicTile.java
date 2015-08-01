package com.gfp.game.level.Tiles;

import com.gfp.game.gfx.Screen;
import com.gfp.game.level.Level;

public class BasicTile extends Tile {

	protected int tileId;
	protected int tileColour;

	public BasicTile(int id, int x, int y, int tileColour, int levelColour) {
		super(id, false, false, levelColour);

		this.tileId = x + y * 32;
		this.tileColour = tileColour;

	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub

	}

	@Override
	public void render(Screen screen, Level level, int x, int y) {
		screen.render(x, y, tileId, tileColour, 0x00, 1);

	}

}
