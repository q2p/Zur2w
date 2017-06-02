package q2p.zur2w.weapons;

public class WeaponPresetsManager {
	public static final WeaponPreset pistol = new WeaponPreset(2,0.1f,(byte)10,(short)100,(byte)1,0.8f);
	// TODO: load weapons from files
	// TODO

	public static Weapon getWeapon(WeaponPreset weaponPreset) {
		return new Weapon(weaponPreset);
	}
}
