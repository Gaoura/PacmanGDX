package com.pacmangdx.game;

import com.badlogic.gdx.Game;
import com.pacmangdx.game.screens.GameScreen;

public class PacmanGDX extends Game {

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
	public PacmanGDX() {}
	
	@Override
	public void create ()
	{
		setScreen(new GameScreen());
	}
}
