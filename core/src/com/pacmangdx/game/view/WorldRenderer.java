package com.pacmangdx.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Align;
import com.pacmangdx.game.controllers.ClavierEcouteur;
import com.pacmangdx.game.controllers.FantomesController;
import com.pacmangdx.game.controllers.PacmanController;
import com.pacmangdx.game.model.GameElement;
import com.pacmangdx.game.model.World;

public class WorldRenderer
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

	private SpriteBatch spriteBatch;
	private OrthographicCamera camera;
	private World world;
	private PacmanController pacman;
	private FantomesController fantomes;
	private BitmapFont font; // guide : http://www.programmingmoney.com/custom-bitmap-font-for-libgdx/

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

	public WorldRenderer(World w)
	{
		this.world = w;
		this.spriteBatch = new SpriteBatch();
		this.font = new BitmapFont(Gdx.files.internal("ScoreBoardFont2.fnt"));

		this.camera = new OrthographicCamera();
		this.pacman = new PacmanController(this.world.getPacman());
		this.fantomes = new FantomesController();
		this.fantomes.add(this.world.getFantome1());
		this.fantomes.add(this.world.getFantome2());
		this.fantomes.add(this.world.getFantome3());
		Gdx.input.setInputProcessor(new ClavierEcouteur(this.pacman));
	}

	public void render(float delta)
	{
		TextureFactory tf = TextureFactory.getInstance();
		this.spriteBatch.setProjectionMatrix(this.camera.combined);
		float largeur = ((float)Gdx.graphics.getWidth()) / this.world.getWidth();
		float hauteur = ((float)Gdx.graphics.getHeight()) / (this.world.getHeight()+1);
		this.pacman.update(delta);
		this.fantomes.update(delta);
		
		this.spriteBatch.begin();
			this.spriteBatch.disableBlending();
			
			String s = "Score : " + new Integer(this.world.getScore()).toString();
						
			for (GameElement ge : this.world)
			{
				this.spriteBatch.draw(
					tf.getTexture(ge, delta),
					(ge.getPosition().x * largeur),
					(ge.getPosition().y * (hauteur)),
					largeur,
					hauteur);
			}
			
			font.draw(spriteBatch, s, Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight()-1, 0f, Align.center, false);
		this.spriteBatch.end();
	}

	public void dispose()
	{
		this.spriteBatch.dispose();
		this.font.dispose();
	}

	public void resize(int width, int height)
	{
		this.camera.setToOrtho(false, width, height);
		this.camera.position.set(width / 2, height / 2, 0);
		this.camera.update();
	}
}
