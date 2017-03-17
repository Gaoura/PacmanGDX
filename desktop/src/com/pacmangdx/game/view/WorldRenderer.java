package com.pacmangdx.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.pacmangdx.game.PacmanGDX;
import com.pacmangdx.game.controllers.ClavierEcouteur;
import com.pacmangdx.game.model.GameElement;
import com.pacmangdx.game.model.Pacman;
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
	private int ppuX;
	private int ppuY;
	private World world;
    private OrthographicCamera camera;

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
		camera = new OrthographicCamera();
		Gdx.input.setInputProcessor(new ClavierEcouteur(this.world));
	}
	
	public void render(float delta)
	{
		TextureFactory tf = TextureFactory.getInstance();
		spriteBatch.setProjectionMatrix(camera.combined);
		spriteBatch.begin();
			spriteBatch.disableBlending();
			for (GameElement ge : world)
			{
				spriteBatch.draw(
					tf.getTexture(ge),
					(ge.getPosition().x / (float)this.world.getWidth()) * Gdx.graphics.getWidth(),
					(ge.getPosition().y / (float)this.world.getHeight()) * Gdx.graphics.getHeight(),
					(1.f / (float)this.world.getWidth()) * Gdx.graphics.getWidth(),
					(1.f / (float)this.world.getHeight()) * Gdx.graphics.getHeight());

			}
				
		spriteBatch.end();
		
	}
	
	public SpriteBatch getSpriteBatch()
	{
		return this.spriteBatch;
	}

	public void setSpriteBatch(SpriteBatch spriteBatch)
	{
		this.spriteBatch = spriteBatch;
	}

	public void dispose()
	{
		this.spriteBatch.dispose();
	}

	public void resize(int width, int height)
	{
		camera.setToOrtho(true, width, height);
	    camera.position.set(width / 2, height / 2, 0);
	    camera.update();
	}
}
