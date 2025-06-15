package Map;

import Character.Hero;

public class Axe extends WeaponStore {
	public Axe() {
		super("전투 도끼", 80, 8);
	}

	@Override
	public void buyWeapon(Hero hero) {
		if (hero.getMoney() >= price) {
			hero.setPower(hero.getPower() + powerBoost);
			hero.setMoney(hero.getMoney() - price);
			System.out.printf("%s을 구매하여 공격력이 %d 증가했습니다!\n", weaponName, powerBoost);
		} else {
			System.out.println("골드가 부족합니다.");
		}
	}
}
