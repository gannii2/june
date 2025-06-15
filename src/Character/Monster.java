package Character;

import java.util.Random;

public abstract class Monster extends Character {
	protected int expDrop;
	protected int moneyDrop;
	protected boolean isAllyJoined = false;

	public Monster(String name, int level, int hp, int mp, int power, int defense) {
		super(name, level, hp, mp, power, defense);
	}

	public int getExpDrop() {
		return expDrop;
	}

	public int getMoneyDrop() {
		return moneyDrop;
	}

	// 추가 기능 - 치명타 시스템 (20% 확률로 2배 피해)
	@Override
	public void attacked(int damage) {
		int realDamage = Math.max(0, damage - defense);
		hp -= realDamage;
		if (hp < 0)
			hp = 0;
		System.out.printf("%s가 %d의 피해를 입었습니다. [남은 HP: %d]\n", name, realDamage, hp);
	}

	@Override
	public int getPower() {
		Random rand = new Random();
		boolean isCritical = rand.nextInt(100) < 20;
		int finalPower = power;
		if (isCritical) {
			finalPower *= 2;
			System.out.println("[치명타 발생!] 몬스터의 공격력이 2배로 증가합니다!");
		}
		return finalPower;
	}

	// 추가 기능2 - 몬스터의 동료 NPC 참전 (25% 확률로 참전)
	public void callReinforcement() {
		if (!isAllyJoined) {
			Random rand = new Random();
			boolean joined = rand.nextInt(100) < 25;
			if (joined) {
				isAllyJoined = true;
				System.out.println("[경고] 몬스터의 동료가 전투에 참전했습니다! 추가 공격을 받습니다!");
			}
		}
	}

	public boolean isReinforced() {
		return isAllyJoined;
	}

	public int extraAttack() {
		return isAllyJoined ? power / 2 : 0;
	}

	@Override
	public void printStatus() {
		System.out.printf("[몬스터] %s Lv.%d | HP: %d\n", name, level, hp);
	}
}