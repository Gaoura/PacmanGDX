package com.pacmangdx.game.controllers;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.pacmangdx.game.model.Direction;

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
	
	private PacmanController pacman;
	
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
	
	public ClavierEcouteur(PacmanController p)
	{
		this.pacman = p;
	}

	@Override
	public boolean keyDown(int keycode)
	{
		switch (keycode)
		{
		case Keys.UP :
		case Keys.Z :
			this.pacman.setDerniereCommande(Direction.UP);
			break;
		case Keys.DOWN :
		case Keys.S :
			this.pacman.setDerniereCommande(Direction.DOWN);
			break;
		case Keys.LEFT :
		case Keys.Q :
			this.pacman.setDerniereCommande(Direction.LEFT);
			break;
		case Keys.RIGHT :
		case Keys.D :
			this.pacman.setDerniereCommande(Direction.RIGHT);
			break;
		default :
			return false;
		}
		
		//update();
		return true;
	}

	// verifie qu'il n'y aura pas d'obstacle pour changer la direction de pacman
	/*private void update()
	{
		Point2D.Float p = new Point2D.Float(this.pacman.getPosition().x, this.pacman.getPosition().y);
		float x = p.x;
		float y = p.y;
		
		switch (this.derniere_commande)
		{
		case UP :
			p = new Point2D.Float(x, y + 1);
			break;
		case DOWN :
			p = new Point2D.Float(x + 0, y - 1);
			break;
		case LEFT :
			p = new Point2D.Float(x - 1, y + 0);
			break;
		case RIGHT :
			p = new Point2D.Float(x + 1, y + 0);
			break;
		default:
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

	}*/
	
	

	
	
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
