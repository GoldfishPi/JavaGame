package com.gfp.game.level.Tiles;

import com.gfp.game.gfx.Colours;
import com.gfp.game.gfx.Screen;
import com.gfp.game.level.Level;

public abstract class Tile {

	public static final Tile[] tiles = new Tile[256];
	public static final Tile VOID = new BasicSolidTile(0, 0, 0, Colours.get(000, -1, -1, -1), 0xff000000);
	public static final Tile STONE = new BasicSolidTile(1, 1, 0, Colours.get(100, 333, 000, -1),0xFF555555);
	public static final Tile GRASS = new BasicTile(2, 2, 0, Colours.get(-1, 131, 141, -1), 0xFF00FF00);
	public static final Tile WATER = new AnimatedTile(3, new int[][] { {0, 5}, {1, 5}, {2, 5}, {3, 5}, {2, 5}, {1, 5} }, Colours.get(-1, 004, 115, -1), 0xFF0000FF, 600);
	public static final Tile SAND = new BasicTile(4, 3, 0, Colours.get(-1, 537, 547, -1), 0xFFe8e663);
	public static final Tile BUSHGRASS  = new BasicSolidTile(5, 0, 1, Colours.get(131, 151, 121, 527),0xFF007a00);
	public static final Tile BUSHSAND  = new BasicSolidTile(6, 0, 1, Colours.get(537, 151, 121, 527),0xFF637a00);
	
	protected byte id;
	protected boolean solid;
	protected boolean emitter;
	private int levelColour;

	public Tile(int id, boolean isSolid, boolean isEmitter, int levelColour) {

		this.id = (byte) id;
		if (tiles[id] != null)
			throw new RuntimeException("THER IS A DUP but seriusly dublicate id at" + id);

		this.solid = isSolid;
		this.emitter = isEmitter;
		this.levelColour = levelColour;
		tiles[id] = this;
	}
	
	public byte getid(){
		return id;
	}

	public boolean isSolid(){
		return solid;
	}
	
	public boolean isEmitter(){
		return emitter;
	}
	
	public int getLevelColour(){
		return levelColour;
	}
	
	public abstract void tick();
	
	public abstract void render(Screen screen, Level level, int x, int y);

}
