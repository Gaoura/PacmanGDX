package com.pacmangdx.game.view;

import java.util.Objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.pacmangdx.game.model.Block;
import com.pacmangdx.game.model.GameElement;
import com.pacmangdx.game.model.Pacman;
import com.pacmangdx.game.model.Pacman.Direction;

public class TextureFactory
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
	private Texture pacman_up;
	private Texture pacman_down;
	private Texture pacman_left;
	private Texture pacman_right;
	private Texture bloc;
	
	private TextureFactory()
	{
		this.pacman_up = new Texture(Gdx.files.internal("pacmanUp.png"));
		this.pacman_down = new Texture(Gdx.files.internal("pacmanDown.png"));
		this.pacman_left = new Texture(Gdx.files.internal("pacmanLeft.png"));
		this.pacman_right = new Texture(Gdx.files.internal("pacmanRight.png"));
		this.bloc = new Texture(Gdx.files.internal("bloc.png"));
	}
	
	private static TextureFactory INSTANCE = new TextureFactory();
	
	private Texture getTexturePacman(Direction direction)
	{
		// les directions verticales sont toujours inversées, penser à changer ça
		
		if (direction == Direction.DOWN)
			return pacman_up;
		if (direction == Direction.UP)
			return pacman_down;
		if (direction == Direction.LEFT)
			return pacman_left;
		if (direction == Direction.RIGHT)
			return pacman_right;

		return pacman_right;
	}
	
	private Texture getTextureBloc()
	{
		return bloc;
	}

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
	
	public static TextureFactory getInstance()
	{
		return INSTANCE;
	}
	
	public Texture getTexture(GameElement ge)
	{
		if (Objects.hashCode(ge.getClass()) == Objects.hashCode(Pacman.class))
			return this.getTexturePacman(((Pacman)ge).getDirection());
		if (Objects.hashCode(ge.getClass()) == Objects.hashCode(Block.class))
			return this.getTextureBloc();
		return null;
	}
}
