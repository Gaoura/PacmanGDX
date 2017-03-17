package com.pacmangdx.game.model;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

public class World implements Iterable<GameElement>
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

	private Pacman pac;
	private Maze laby;

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

	public World()
	{
		this.laby = new Maze(this);
		Point p = newPacman();
		if (p != null)
			this.pac = new Pacman(p, this);			
		else
			System.out.println("Erreur création Pacman");
		
	}
	
	public Point newPacman()
	{
		FileHandle fh = Gdx.files.internal("map2.txt");
		BufferedReader br = new BufferedReader(fh.reader());
		String line;
		ArrayList<String> list = new ArrayList<String>();
		try
		{
			while((line = br.readLine()) != null)
				list.add(line);

			int j = 0;
			while(j < list.size())
			{
				line = list.get(j);
				int i;
				for (i = 0; i < line.length(); i++)
					if (line.charAt(i) == 'C')
						return new Point(i, j);
				j++;
			}	
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
		return null;
	}
	
	public int getHeight()
	{
		return laby.getHeight();
		
	}
	
	public int getWidth()
	{
		return laby.getWidth();
		
	}
	
	public Maze getMaze()
	{
		return this.laby;
	}
	
	public Pacman getPacman()
	{
		return this.pac;
	}
	
	@Override
	public Iterator<GameElement> iterator()
	{
		return  new Iterator<GameElement>()
				{					
					private int i = 0;
					private Iterator<GameElement> iterator = laby.iterator();
					
					@Override
					public boolean hasNext()
					{
						// si on a pas encore retourner pacman
						if (i == 0)
							return pac != null;
						// sinon on regarde si laby a un suivant
						else
							return iterator.hasNext();
					}
					
					@Override
					public GameElement next()
					{
						if (!hasNext())
							throw new NoSuchElementException("Plus de GameElement");
						
						switch (i)
						{
						case 0 :
							i++;
							return pac;
						default : return iterator.next();
						}
					}
				};
	}
}