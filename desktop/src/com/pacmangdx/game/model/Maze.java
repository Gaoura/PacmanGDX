package com.pacmangdx.game.model;

import java.awt.Point;
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
	private HashMap<Point, Block> blocs;
	private World world;
	
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

			Point p;
			
			int j = 0;
			while(j < list.size())
			{
				line = list.get(j);
				int i;
				for (i = 0; i < line.length(); i++)
				{
					if (line.charAt(i) == 'X')
					{
						p = new Point(i, j);
						blocs.put(p, new Block(p));
					}
				}
				if (i > maxX)
					maxX = i;
				j++;
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
		this.world = w;
		blocs = new HashMap<Point, Block>();
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
	
	public Block get(int x, int y)
	{
		
		return blocs.get(new Point(x,y));
	}

	@Override
	public Iterator<GameElement> iterator()
	{
		return  new Iterator<GameElement>()
				{
					private Iterator<Entry<Point,Block>> iterator = blocs.entrySet().iterator();
					
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