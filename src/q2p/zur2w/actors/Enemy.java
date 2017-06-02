package q2p.zur2w.actors;

import q2p.zur2w.Logic;
import q2p.zur2w.Renderer;
import q2p.zur2w.Timing;
import q2p.zur2w.engine.Assist;
import q2p.zur2w.map.Vector;

public class Enemy extends Actor {
	float atackIdleBase = 0.5f;
	float atackIdle = 0;
	
	public void spawn(byte x, byte y) {
		super.spawn(x, y);
		health = 1;
		rot = Assist.rand(360);
	}
	
	public void think() {
		super.think();
		
		if(colidedWith != -1) {
			rot = 90-90*colidedWith+Assist.rand(180);
		}
		
		if(canSee(Logic.player.position)) {
			rot = position.direction(Logic.player.position);
		}
		velocity = Vector.byDirection(rot, 4);
		atackCheck();
	}
	
	private void atackCheck() {
		if(atackIdle > 0) atackIdle -= Timing.deltaF;
		if(atackIdle <= 0) atackIdle = 0;
		
		if(position.distance(Logic.player.position) < 1 && canSee(Logic.player.position)) {
			velocity = new Vector();
			if(atackIdle == 0) {
				atackIdle = atackIdleBase;
				Logic.player.hitDamage(1);
			}
		}
	}
	
	public void hitDamage(byte damage) {
		super.hitDamage(damage);
		if(health == 0) death();
	}
	
	public void death() {
		Logic.enemies.remove(this);
		Renderer.addDeathSprite(position);
	}
}
