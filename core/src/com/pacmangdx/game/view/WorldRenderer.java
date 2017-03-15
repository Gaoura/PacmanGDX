package com.pacmangdx.game.view;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.pacmangdx.game.PacmanGDX;
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
	private int ppuX;
	private int ppuY;
	private World world;

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
	}
	
	public void render()
	{
		TextureFactory tf = TextureFactory.getInstance();
		spriteBatch.begin();
			spriteBatch.disableBlending();
			for (GameElement ge : world)
				spriteBatch.draw(tf.getTexture(ge.getClass()),
								/*this.ppuX +*/ ge.getPosition().x,
								/*this.ppuY +*/ ge.getPosition().y);
		spriteBatch.end();/*
		spriteBatch.draw(tf.getTexture(ge.getClass()),
						ge.getPosition().x,
						ge.getPosition().y,
						,
						10.);*/
		
	}
	
	public SpriteBatch getSpriteBatch()
	{
		return this.spriteBatch;
	}

	public void setSpriteBatch(SpriteBatch spriteBatch)
	{
		this.spriteBatch = spriteBatch;
	}

	public int getPpuX()
	{
		return this.ppuX;
	}

	public void setPpuX(int ppuX)
	{
		this.ppuX = ppuX;
	}

	public int getPpuY()
	{
		return this.ppuY;
	}

	public void setPpuY(int ppuY)
	{
		this.ppuY = ppuY;
	}

	public void dispose()
	{
		this.spriteBatch.dispose();
	}
}
