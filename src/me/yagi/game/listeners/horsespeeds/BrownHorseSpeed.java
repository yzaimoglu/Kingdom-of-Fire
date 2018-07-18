package me.yagi.game.listeners.horsespeeds;

import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;

import net.citizensnpcs.api.event.NPCRightClickEvent;
import net.citizensnpcs.trait.Controllable.MovementController;

public class BrownHorseSpeed implements MovementController {

    private int jumpTicks = 0;
    private double speed = 0.5D;

    private static final float AIR_SPEED = 1.5F;
    private static final float GROUND_SPEED = 12F;
    private static final float JUMP_VELOCITY = 0.6F;
	@Override
	public void leftClick(PlayerInteractEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void rightClick(PlayerInteractEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void rightClickEntity(NPCRightClickEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void run(Player arg0) {
		// TODO Auto-generated method stub
		
	}
	
}
