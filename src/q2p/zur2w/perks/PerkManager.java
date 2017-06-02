package q2p.zur2w.perks;

public class PerkManager {
	private Perk[] perks = new Perk[] {
		new Perk("akimbo", "Dual wielding"),
		new Perk("damage", "More damage"),
		new Perk("health", "More health")
	};
	
	public boolean isUnlocked(String name) {
		for(int i = 0; i < perks.length; i++) if(perks[i].name.equals(name)) return perks[i].unlocked;
		return false;
	}
}