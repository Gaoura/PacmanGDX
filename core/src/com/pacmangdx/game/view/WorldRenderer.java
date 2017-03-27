package com.pacmangdx.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.pacmangdx.game.controllers.ClavierEcouteur;
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

		this.camera = new OrthographicCamera();
		this.pacman = new PacmanController(this.world.getPacman());
		Gdx.input.setInputProcessor(new ClavierEcouteur(this.pacman));
	}

	public void render(float delta)
	{
		TextureFactory tf = TextureFactory.getInstance();
		this.spriteBatch.setProjectionMatrix(this.camera.combined);
		float largeur = ((float)Gdx.graphics.getWidth()) / this.world.getWidth();
		float hauteur = ((float)Gdx.graphics.getHeight()) / this.world.getHeight();
		this.pacman.update(delta);
		
		this.spriteBatch.begin();
			this.spriteBatch.disableBlending();
			for (GameElement ge : this.world)
			{
				this.spriteBatch.draw(
					tf.getTexture(ge, delta),
					(ge.getPosition().x * largeur),
					(ge.getPosition().y * hauteur),
					largeur,
					hauteur);
			}

		this.spriteBatch.end();

	}

	public void dispose()
	{
		this.spriteBatch.dispose();
	}

	public void resize(int width, int height)
	{
		this.camera.setToOrtho(false, width, height);
		this.camera.position.set(width / 2, height / 2, 0);
		this.camera.update();
	}
}
