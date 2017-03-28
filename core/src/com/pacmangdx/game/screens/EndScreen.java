package com.pacmangdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Align;

public class EndScreen implements Screen
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

	public EndScreen()
	{
		this.spriteBatch = new SpriteBatch();
		this.font = new BitmapFont(Gdx.files.internal("ScoreBoardFont2.fnt"));
		this.camera = new OrthographicCamera();
	}

	@Override
	public void render(float delta)
	{
		Gdx.gl.glClearColor(0f, 0f, 0f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		this.spriteBatch.setProjectionMatrix(this.camera.combined);

		this.spriteBatch.begin();
			this.spriteBatch.disableBlending();
			font.draw(spriteBatch, "Félicitations", Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight()/2, 0f, Align.center, false);
		this.spriteBatch.end();
	}

	@Override
	public void resize(int width, int height)
	{
		this.camera.setToOrtho(false, width, height);
		this.camera.position.set(width / 2, height / 2, 0);
		this.camera.update();
	}

	@Override
	public void dispose()
	{
		this.spriteBatch.dispose();
		this.font.dispose();
	}



	@Override
	public void show() {}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

}
