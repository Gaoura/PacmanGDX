package com.pacmangdx.game.model;

public enum Direction
{
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

	NONE,
	UP, 
	DOWN, 
	LEFT, 
	RIGHT;
	
	public boolean estDirectionOpposee(Direction d)
	{
        switch (this)
        {
        case LEFT :
        	if (d == RIGHT) return true;
        	else       		return false;
        case RIGHT :
        	if (d == LEFT) return true;
        	else       	   return false;
        case DOWN :
        	if (d == UP) return true;
        	else       	 return false;
        case UP :
        	if (d == DOWN) return true;
        	else       	   return false;
        case NONE :
        default :
        	return false;
        }
    }
	
	public Direction getDirectionOpposee()
	{
		switch (this)
        {
        case LEFT :
        	return RIGHT;
        case RIGHT :
        	return LEFT;
        case DOWN :
        	return UP;        	
        case UP :
        	return DOWN;
        case NONE :
        default :
        	return NONE;
        }
	}
	
	public boolean estDirectionPerpendiculaire(Direction d)
	{
        switch (this)
        {
        case LEFT :
        case RIGHT :
        	if (d == DOWN || d == UP) return true;
        	else       		          return false;
        case DOWN :
        case UP :
        	if (d == LEFT || d == RIGHT) return true;
        	else       	                 return false;
        case NONE :
        default :
        	return false;
        }
    }
} 

