package com.pacmangdx.game.model;

import java.awt.geom.Point2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

import java.util.NoSuchElementException;

public class Maze implements Iterable<GameElement>
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

	private int height;
	private int width;
	private HashMap<Point2D.Float, Block> blocs;
	
	private void loadDemoLevel()
	{
		FileHandle fh = Gdx.files.internal("map2.txt");
		BufferedReader br = new BufferedReader(fh.reader());
		String line;
		ArrayList<String> list = new ArrayList<String>();
		int maxX = 0;
		int maxY = 0;
		
		try
		{
			while((line = br.readLine()) != null)
				list.add(line);

			Point2D.Float p;

			int i;
			int j = 0;
			int k = list.size() - 1;
			while(j < list.size())
			{
				line = list.get(j);
				for (i = 0; i < line.length(); i++)
				{
					if (line.charAt(i) == 'X')
					{
						p = new Point2D.Float(i, k);
						blocs.put(p, new Block(p));
					}
				}
				if (i > maxX)
					maxX = i;
				j++;
				k--;
			}
			
			if (j > maxY)
				maxY = j;
			this.width = maxX;
			this.height = maxY;
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
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

	public Maze(World w)
	{
		blocs = new HashMap<Point2D.Float, Block>();
		loadDemoLevel();
	}
	
	public int getWidth()
	{
		return this.width;
	}
	
	public int getHeight()
	{
		return this.height;
	}
	
	public Block get(float x, float y)
	{
		// les valeurs sont castées pour être sûr de l'alignement avec un bloc
		return blocs.get(new Point2D.Float((int)x,(int)y));
	}

	@Override
	public Iterator<GameElement> iterator()
	{
		return  new Iterator<GameElement>()
				{
					private Iterator<Entry<Point2D.Float,Block>> iterator = blocs.entrySet().iterator();
					
					@Override
					public boolean hasNext()
					{
						return iterator.hasNext();
					}
					
					@Override
					public GameElement next()
					{
						if (!hasNext())
							throw new NoSuchElementException("Plus de GameElement");
						return iterator.next().getValue();
					}
				};
	}
}