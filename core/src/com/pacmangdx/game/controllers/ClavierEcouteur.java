package com.pacmangdx.game.controllers;

import java.awt.Point;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.pacmangdx.game.model.Pacman;
import com.pacmangdx.game.model.Pacman.Direction;
import com.pacmangdx.game.model.World;

public class ClavierEcouteur implements InputProcessor
{

/*
/////////////////////////////////////////////////////////////////////////////////////////////

                ########  ########  #### ##     ##    ###    ######## ########
                ##     ## ##     ##  ##  ##     ##   ## ##      ##    ##
                ##     ## ##     ##  ##  ##     ##  ##   ##     ##    ##
                ########  ########   ##  ##     ## ##     ##    ##    ######
                ##        ##   ##    ##   ##   ##  #########    ##    ##
                ##        ##    ##   ##    ## ##   ##     ##    ##    ##
                ##        ##     ## ####    ###    ##     ##    ##    ########

/////////////////////////////////////////////////////////////////////////////////////////////
*/
	
	private World world;
	private Pacman pacman;
	private Direction derniere_commande;
	
/*
/////////////////////////////////////////////////////////////////////////////////////////////

                     ########  ##     ## ########  ##       ####  ######
                     ##     ## ##     ## ##     ## ##        ##  ##    ##
                     ##     ## ##     ## ##     ## ##        ##  ##
                     ########  ##     ## ########  ##        ##  ##
                     ##        ##     ## ##     ## ##        ##  ##
                     ##        ##     ## ##     ## ##        ##  ##    ##
                     ##         #######  ########  ######## ####  ######

/////////////////////////////////////////////////////////////////////////////////////////////
*/
	
	public ClavierEcouteur(World w)
	{
		this.world = w;
		this.pacman = this.world.getPacman();
	}

	public Direction getDerniere_commande()
	{
		return this.derniere_commande;
	}

	@Override
	public boolean keyDown(int keycode)
	{
		switch (keycode)
		{
		case Keys.UP :
		case Keys.Z :
			this.derniere_commande = Direction.UP;
			break;
		case Keys.DOWN :
		case Keys.S :
			this.derniere_commande = Direction.DOWN;
			break;
		case Keys.LEFT :
		case Keys.Q :
			this.derniere_commande = Direction.LEFT;
			break;
		case Keys.RIGHT :
		case Keys.D :
			this.derniere_commande = Direction.RIGHT;
			break;
		default :
			return false;
		}
		update();
		return true;
	}

	// update la position du pacman
	private void update()
	{
		Point p = this.pacman.getPosition();
		int x = p.x;
		int y = p.y;
		
		switch (this.derniere_commande)
		{
		case UP :
			p = new Point(x, y - 1);
			break;
		case DOWN :
			p = new Point(x + 0, y + 1);
			break;
		case LEFT :
			p = new Point(x - 1, y + 0);
			break;
		case RIGHT :
			p = new Point(x + 1, y + 0);
			break;
		}
		this.pacman.setDirection(this.derniere_commande);
		// detection de collision
		if (this.world.getMaze().get(p.x, p.y) == null)
		{
			// detection de sortie normale de carte
			if (p.x >= this.world.getWidth())
				p.x = 0;
			else
				if (p.x < 0)
					p.x = this.world.getWidth() - 1;
			
			this.pacman.setPosition(p);
		}

	}
	
	

	
	
	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}
	
}
