package q2p.zur2w.actors;

import q2p.zur2w.Events;
import q2p.zur2w.Logic;
import q2p.zur2w.map.Vector;
import q2p.zur2w.weapons.Weapon;
import q2p.zur2w.weapons.WeaponPresetsManager;

public class Player extends Actor {
	public Weapon currentWeaponSlot = null;
	
	public Weapon weaponSlots[] = {WeaponPresetsManager.getWeapon(WeaponPresetsManager.pistol),WeaponPresetsManager.getWeapon(WeaponPresetsManager.pistol)};
	
	public void spawn(byte x, byte y) {
		super.spawn(x, y);
		health = 8;
		weaponSlots[0].pickUp(this);
		weaponSlots[1].pickUp(this);
		currentWeaponSlot = weaponSlots[0];
	}
	
	public void think() {
		super.think();
		if(currentWeaponSlot != null) currentWeaponSlot.think();
		if(Events.bindState[0][0] >= 2) currentWeaponSlot.doShot();
		rot -= Events.mouseDX*0.07;
		while(rot>360) rot-=360;
		while(rot<0) rot+=360;

		byte offX = 0;
		byte offY = 0;
		
		if(Events.bindState[7][0] > 1) offX -= 1; // a
		if(Events.bindState[9][0] > 1) offX += 1; // d
		if(Events.bindState[6][0] > 1) offY += 1; // w
		if(Events.bindState[8][0] > 1) offY -= 1; // s
		
		if(offX != 0 || offY != 0) {
			float rotat = (float) Math.toRadians(Math.toDegrees(Math.atan2(offY, offX))-90+rot);
			velocity = Vector.byDirection(rotat, 6);
			// TODO: Добавить эффект скольжения при прекращении движения и замедлении при повороте персонажа на 180 градусов
		} else {
			velocity = new Vector();
		}
		//shotCheck
	}
	
	public void deathCheck() {
		if(health != 0) return;
		Logic.player = null;
		Logic.gameOver();
	}
}
