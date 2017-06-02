package q2p.zur2w.weapons;

import q2p.zur2w.Logic;
import q2p.zur2w.Renderer;
import q2p.zur2w.Timing;
import q2p.zur2w.actors.Actor;
import q2p.zur2w.actors.Enemy;
import q2p.zur2w.map.Map;
import q2p.zur2w.map.Physics;

public class Weapon {
	// consts
	float reloadTime; // secounds
	float shotCoolDown; // secounds
	float muzzleFlashTime = 0.05f; // secounds
	byte magazineSize;
	short reserveSize;
	byte baseDamage;
	float movementScale;
	
	// dynamic
	float leftReloadTime = 0; // secounds
	float leftShotCoolDown = 0; // secounds
	public float leftMuzzleFlash = 0; // secounds
	public byte bulletsInMagazine;
	short bulletsInReserve; // TODO: выкидывать патроны оставшиеся в магазине при перезарядке
	boolean reloading = false;
	private boolean doShot = false;
	private boolean doReload = false;
	private Actor actor = null; 
	
	public Weapon(WeaponPreset weponPreset) {
		reloadTime = weponPreset.reloadTime;
		shotCoolDown = weponPreset.shotCoolDown;
		magazineSize = weponPreset.magazineSize;
		reserveSize = weponPreset.reserveSize;
		baseDamage = weponPreset.baseDamage;
		movementScale = weponPreset.movementScale;
		
		bulletsInMagazine = magazineSize;
		bulletsInReserve = reserveSize; // TODO: bulletsInReserve = magazineSize
	}
	
	public void startReload() {
		if(bulletsInReserve <= 0) return; // TODO: звук когда патронов нет
		reloading = true;
		bulletsInMagazine = 0;
		leftShotCoolDown = 0;
		leftReloadTime = reloadTime;
	}
	
	public void think() {
		shotCheck();
		reloadCheck();
	}
	
	private void reloadCheck() {
		if(reloading) {
			leftReloadTime -= Timing.deltaF;
			if(leftReloadTime <= 0) {
				leftReloadTime = 0;
				byte swap = (byte) Math.min(bulletsInReserve, magazineSize);
				bulletsInMagazine = swap;
				bulletsInReserve -= swap;
			}
		}
		if(doReload && !reloading) {
			reloading = true;
			leftReloadTime = reloadTime;
			bulletsInMagazine = 0;
		}
		doReload = false;
	}
	
	private void shotCheck() {
		//shotSprite = false;
		if(leftShotCoolDown > 0) {
			leftShotCoolDown -= Timing.deltaF;
			if(leftShotCoolDown <= 0) leftShotCoolDown = 0;
		}
		if(leftMuzzleFlash > 0) {
			leftMuzzleFlash -= Timing.deltaF;
			if(leftMuzzleFlash <= 0) leftMuzzleFlash = 0;
		}
		if(doShot && leftShotCoolDown == 0 && bulletsInMagazine > 0) {
			//bulletsInMagazine--; // TODO:
			leftMuzzleFlash = muzzleFlashTime;
			leftShotCoolDown = shotCoolDown;
			
			Renderer.addShell(actor.position.getX(), actor.position.getY(), (byte)0, actor.rot+90, actor.velocity.getX(), actor.velocity.getY()); // TODO: разные типы гильз
			
			shotSomeOne();
		}
		doShot = false;
	}
	
	private void shotSomeOne() {
		float dirX = (float)Math.cos(Math.toRadians(actor.rot))*Map.size*Map.size+actor.position.getX();
		float dirY = (float)Math.sin(Math.toRadians(actor.rot))*Map.size*Map.size+actor.position.getY();
		float[][] coll = null;

		byte[][] goesThrough = null;

		goesThrough = Physics.goesTrough(actor.position.getX(),actor.position.getY(),dirX,dirY);

		for(byte[] g : goesThrough) {
			if(!Map.tiles[g[1]][g[0]].solid)
				continue;
			float[][] ncoll = Physics.lineXaabbBox(new float[]{actor.position.getX(),actor.position.getY(),dirX,dirY},new float[]{g[0],g[1],1,1});
			if(ncoll == null) continue;
			if(coll == null) coll = ncoll;
			else {
				float xd = coll[0][0] - actor.position.getX();
				float yd = coll[0][1] - actor.position.getY();
				float oDist = xd*xd + yd*yd;
				xd = ncoll[0][0] - actor.position.getX();
				yd = ncoll[0][1] - actor.position.getY();
				float nDist = xd*xd + yd*yd;
				if (nDist < oDist) coll = ncoll;
			}
		}
		
		float[] shotLine = new float[]{actor.position.getX(), actor.position.getY(), coll[0][0], coll[0][1]};
		
		coll = null;
		Enemy hited = null;
		
		for(Enemy e : Logic.enemies) {
			float[][] ncoll = Physics.lineXaabbHitBox(shotLine, new float[]{e.position.getX(),e.position.getY()});
			if(ncoll == null) continue;
			if(coll == null) {
				coll = ncoll;
				hited = e;
			}
			else {
				float xd = coll[0][0] - actor.position.getX();
				float yd = coll[0][1] - actor.position.getY();
				float oDist = xd*xd + yd*yd;
				xd = ncoll[0][0] - actor.position.getX();
				yd = ncoll[0][1] - actor.position.getY();
				float nDist = xd*xd + yd*yd;
				if (nDist < oDist) {
					coll = ncoll;
					hited = e;
				}
			}
		}
		
		if(hited != null) {
			hited.hitDamage(baseDamage);
			float rotat = (float)Math.toDegrees(Math.atan2(hited.position.getY() - actor.position.getY(), hited.position.getX() - actor.position.getX()));
			Renderer.addBloodBurst(coll[0][0], coll[0][1], (byte)1, rotat); // TODO: hard hit sprite
		}
	}
	
	public void doReload() {
		doReload = true;
	}
	
	public void doShot() {
		doShot = true;
		// TODO:
	}
	
	public void drop() {
		// TODO:
	}

	public void pickUp(Actor actor) {
		this.actor = actor;
		// TODO:
	}
}
