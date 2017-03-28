package com.pacmangdx.game.view;

import java.util.Objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.pacmangdx.game.model.Block;
import com.pacmangdx.game.model.Fantome;
import com.pacmangdx.game.model.GameElement;
import com.pacmangdx.game.model.PacGomme;
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
	
	private PacmanDisplayer pacman_displayer;
	private FantomesDisplayer fantomes_displayer;
	
	private Texture pacman[];
	private Texture fantomes[];
	
	private Texture bloc;
	private Texture pac_gomme;
	private Texture vide;
	
	private TextureFactory()
	{	
		this.pacman = new Texture[8];
		this.fantomes = new Texture[6];
		
		this.pacman_displayer = new PacmanDisplayer(this.pacman);
		this.fantomes_displayer = new FantomesDisplayer(this.fantomes);
			
		this.pacman[0] = new Texture(Gdx.files.internal("pacmanLeft.png"));
		this.pacman[1] = new Texture(Gdx.files.internal("pacmanLeft-2.png"));
		this.pacman[2] = new Texture(Gdx.files.internal("pacmanRight.png"));
		this.pacman[3] = new Texture(Gdx.files.internal("pacmanRight-2.png"));
		this.pacman[4] = new Texture(Gdx.files.internal("pacmanDown.png"));
		this.pacman[5] = new Texture(Gdx.files.internal("pacmanDown-2.png"));
		this.pacman[6] = new Texture(Gdx.files.internal("pacmanUp.png"));
		this.pacman[7] = new Texture(Gdx.files.internal("pacmanUp-2.png"));
		
		this.fantomes[0] = new Texture(Gdx.files.internal("ghost1.png"));
		this.fantomes[1] = new Texture(Gdx.files.internal("ghost2.png"));
		this.fantomes[2] = new Texture(Gdx.files.internal("ghost3.png"));
		this.fantomes[3] = new Texture(Gdx.files.internal("ghost4.png"));
		this.fantomes[4] = new Texture(Gdx.files.internal("ghostEscaping.png"));
		this.fantomes[5] = new Texture(Gdx.files.internal("ghostDead.png"));
		
		this.bloc = new Texture(Gdx.files.internal("bloc.png"));
		this.pac_gomme = new Texture(Gdx.files.internal("pellet.png"));
		this.vide = new Texture(Gdx.files.internal("dark.png"));
	}
	
	private Texture getTexturePacman(Pacman p, float delta)
	{
		this.pacman_displayer.augmenterCompteur(delta);
		return this.pacman_displayer.getTexturePacman(p);
		
	}
	
	private Texture getTextureBloc()
	{
		return this.bloc;
	}

	private Texture getTexturePacGomme(PacGomme ge)
	{
		if (!ge.estMangee())
			return this.pac_gomme;
		else 
			return this.vide;
	}

	private Texture getTextureFantome(Fantome ge)
	{
		return this.fantomes_displayer.getTextureFantome(ge);
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
	
	public Texture getTexture(GameElement ge, float delta)
	{
		if (Objects.hashCode(ge.getClass()) == Objects.hashCode(Pacman.class))
			return this.getTexturePacman((Pacman)ge, delta);
		
		if (Objects.hashCode(ge.getClass()) == Objects.hashCode(Block.class))
			return this.getTextureBloc();
		
		if (Objects.hashCode(ge.getClass()) == Objects.hashCode(PacGomme.class))
			return this.getTexturePacGomme((PacGomme)ge);
		
		if (Objects.hashCode(ge.getClass()) == Objects.hashCode(Fantome.class))
			return this.getTextureFantome((Fantome)ge);
		
		return null;
	}
}
