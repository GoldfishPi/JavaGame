package com.gfp.game;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;

import com.gfp.game.entities.Player;
import com.gfp.game.gfx.Colours;
import com.gfp.game.gfx.Font;
import com.gfp.game.gfx.Screen;
import com.gfp.game.gfx.SpriteSheet;
import com.gfp.game.level.Level;
import com.gfp.game.level.Tiles.Tile;

public class Game extends Canvas implements Runnable
{
//
	private static final long serialVersionUID = 1L;

	public static final int WIDTH = 160;
	public static final int HEIGHT = WIDTH / 12 * 9;
	public static final int SCALE = 3;
	public static final String NAME = "Game";

	public JFrame frame;

	public boolean Running = false;
	public int TickCount = 0;

	private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
	private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
	private int[] colours = new int[6 * 6 * 6]; // or
												// 216

	private Screen screen;
	public InputHandler input;
	public Level level;
	public Player player;

	public Game()
	{
		setMinimumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		setMaximumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));

		frame = new JFrame(NAME);
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());

		frame.add(this, BorderLayout.CENTER);
		frame.pack();

		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	public void init()
	{
		// red green blue.
		int index = 0;
		for (int r = 0; r < 6; r++)
		{
			for (int g = 0; g < 6; g++)
			{
				for (int b = 0; b < 6; b++)
				{
					int rr = (r * 255 / 5);
					int gg = (g * 255 / 5);
					int bb = (b * 255 / 5);
					colours[index++] = rr << 16 | gg << 8 | bb;

				}
			}
		}

		screen = new Screen(WIDTH, HEIGHT, new SpriteSheet("/SpriteSheet.png"));
		input = new InputHandler(this);
		level = new Level("/levels/Level.png");
		player = new Player(level, 0, 0, input);
		level.addEntity(player);

	}

	private synchronized void start()
	{
		Running = true;
		new Thread(this).start();

	}

	private synchronized void stop()
	{
		Running = false;

	}

	@Override
	public void run()
	{

		long LastTime = System.nanoTime();
		double nsPerTick = 1000000000D / 60;

		int Ticks = 0;
		int Frames = 0;

		long LastTimer = System.currentTimeMillis();
		double Delta = 0;

		init();

		while (Running)
		{

			long Now = System.nanoTime();
			Delta += (Now - LastTime) / nsPerTick;
			LastTime = Now;
			boolean ShouldRender = true;

			while (Delta >= 1)
			{

				Ticks++;
				tick();
				Delta -= 1;
				ShouldRender = true;

			}

			try
			{
				Thread.sleep(2);
			} catch (InterruptedException e)
			{
				e.printStackTrace();
			}

			if (ShouldRender)
			{
				Frames++;
				render();

			}

			if (System.currentTimeMillis() - LastTimer >= 1000)
			{

				LastTimer += 1000;
				System.out.println(Ticks + " Ticks, " + Frames + " Frames");
				Frames = 0;
				Ticks = 0;

			}

		}

	}

	public void tick()
	{
		level.tick();
		TickCount++;

		for (Tile t : Tile.tiles)
		{
			if (t == null)
			{
				break;
			}
			t.tick();
		}
	}

	public void render()
	{

		// Organizes data on canvas.
		BufferStrategy bs = getBufferStrategy();
		if (bs == null)
		{
			// The higher number here the better it will be at reducing tearing
			// in the image.
			createBufferStrategy(3);
			return;
		}

		int xOffset = player.x - (screen.width / 2);
		int yOffset = player.y - (screen.height / 2);

		level.renderTiles(screen, xOffset, yOffset);

		String wowmsg = "Wow custom font!";

		int colour = Colours.get(500, 000, 500, 543);

		level.renderEntities(screen);

		for (int y = 0; y < screen.height; y++)
		{
			for (int x = 0; x < screen.width; x++)
			{
				int ColourCode = screen.pixels[x + y * screen.width];
				if (ColourCode < 255)
				{
					pixels[x + y * WIDTH] = colours[ColourCode];

				}
			}
		}

		Font.render("hello?", screen, 1, 1, colour, 1);

		Graphics g = bs.getDrawGraphics();

		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);

		// frees up any resources the graphics objects is using.
		g.dispose();
		// show conttence of buffer.
		bs.show();

	}

	public static void main(String[] args)
	{
		new Game().start();

	}

}
