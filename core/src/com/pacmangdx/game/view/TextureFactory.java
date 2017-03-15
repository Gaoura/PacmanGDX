package com.pacmangdx.game.view;

import java.util.Objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.pacmangdx.game.model.Block;
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
	
	private TextureFactory() {}
	
	private static TextureFactory INSTANCE = new TextureFactory();
	
	private Texture getTexturePacman()
	{
		return new Texture(Gdx.files.internal("pacmanRight.png"));
	}
	
	private Texture getTextureBloc()
	{
		return new Texture(Gdx.files.internal("bloc.png"));
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
	
	public Texture getTexture(/*GameElement ge*/ Class<? extends GameElement> c)
	{
		if (Objects.hashCode(c /*ge.getClass()*/) == Objects.hashCode(Pacman.class))
			return this.getTexturePacman();
		if (Objects.hashCode(c /*ge.getClass()*/) == Objects.hashCode(Block.class))
			return this.getTextureBloc();
		return null;
	}
}
