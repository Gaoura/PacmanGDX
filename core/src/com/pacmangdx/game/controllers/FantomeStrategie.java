package com.pacmangdx.game.controllers;

import com.pacmangdx.game.model.Direction;
import com.pacmangdx.game.model.Fantome;

public interface FantomeStrategie
{
	public Direction choixDirection(Fantome f);
}
