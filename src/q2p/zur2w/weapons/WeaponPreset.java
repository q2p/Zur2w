package q2p.zur2w.weapons;

public class WeaponPreset {
	float reloadTime; // secounds
	float shotCoolDown; // secounds
	byte magazineSize;
	short reserveSize;
	byte baseDamage;
	float movementScale; // final speed = regular speed * movementScale
	float kickBack; // TODO: adds velocity after shot
	
	public WeaponPreset(float reloadTime, float shotCoolDown, byte magazineSize, short reserveSize, byte baseDamage, float movementScale) {
		this.reloadTime = reloadTime;
		this.shotCoolDown = shotCoolDown;
		this.magazineSize = magazineSize;
		this.reserveSize = reserveSize;
		this.baseDamage = baseDamage;
		this.movementScale = movementScale;
	}
}