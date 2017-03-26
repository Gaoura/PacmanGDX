package com.pacmangdx.game.view;

import java.util.Objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.pacmangdx.game.model.Block;
import com.pacmangdx.game.model.Direction;
import com.pacmangdx.game.model.GameElement;
import com.pacmangdx.game.model.Pacman;

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
	private static TextureFactory INSTANCE = new TextureFactory();
	
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
	
	private Texture getTexturePacman(Direction direction)
	{
		switch (direction)
		{
		case UP :
			return pacman_up;
		case DOWN :
			return pacman_down;
		case LEFT :
			return pacman_left;
		case RIGHT :
		case NONE :
		default :
			return pacman_right;
		}
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
