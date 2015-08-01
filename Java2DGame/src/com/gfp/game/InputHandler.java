package com.gfp.game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

public class InputHandler implements KeyListener{
	
	public InputHandler(Game game){
		game.addKeyListener(this);
	}
	
	public class Key
	{
		int numTimesPressed = 0;
		int getTimesPressed = 0;
		public boolean Pressed = false;
		
		public boolean isPressed(){
			return Pressed;
		}
		
		public int getTimesPressed(){
			return numTimesPressed;
		}
		public void toggle (boolean isPressed){
			Pressed = isPressed;
			
			if(isPressed){numTimesPressed ++;}
		}
	}
	
	public List<Key> key = new ArrayList<Key>();
	
	public Key Up = new Key();
	public Key Down = new Key();
	public Key Left = new Key();
	public Key Right = new Key();
	public Key J = new Key();
	
	public Key Zero = new Key();
	public Key One = new Key();
	public Key Two = new Key();
	public Key Three = new Key();
	public Key Four = new Key();
	public Key Five = new Key();
	public Key Six = new Key();
	public Key Seven = new Key();
	public Key Eight = new Key();
	public Key Nine = new Key();
	
	@Override
	public void keyTyped(KeyEvent e) {
		
		
	}

	@Override
	public void keyPressed(KeyEvent e) 
	{
		toggleKey(e.getKeyCode(), true);
		
	}

	@Override
	public void keyReleased(KeyEvent e) 
	{
		toggleKey(e.getKeyCode(), false);
		
	}
	
	public void toggleKey(int keyCode, boolean isPressed){
		if(keyCode == KeyEvent.VK_W||keyCode == KeyEvent.VK_UP ){Up.toggle(isPressed);}
		if(keyCode == KeyEvent.VK_S || keyCode == KeyEvent.VK_DOWN){Down.toggle(isPressed);}
		if(keyCode == KeyEvent.VK_A || keyCode == KeyEvent.VK_LEFT){Left.toggle(isPressed);}
		if(keyCode == KeyEvent.VK_D || keyCode == KeyEvent.VK_RIGHT){Right.toggle(isPressed);}
		if(keyCode == KeyEvent.VK_0){Zero.toggle(isPressed);}
		if(keyCode == KeyEvent.VK_1){One.toggle(isPressed);}
		if(keyCode == KeyEvent.VK_2){Two.toggle(isPressed);}
		if(keyCode == KeyEvent.VK_3){Three.toggle(isPressed);}
		if(keyCode == KeyEvent.VK_4){Four.toggle(isPressed);}
		if(keyCode == KeyEvent.VK_5){Five.toggle(isPressed);}
		if(keyCode == KeyEvent.VK_6){Six.toggle(isPressed);}
		if(keyCode == KeyEvent.VK_7){Seven.toggle(isPressed);}
		if(keyCode == KeyEvent.VK_8){Eight.toggle(isPressed);}
		if(keyCode == KeyEvent.VK_9){Nine.toggle(isPressed);}
		
		
	}

}
