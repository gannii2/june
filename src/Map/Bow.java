package Map;

import Character.Hero;

public class Bow extends WeaponStore {
	public Bow() {
		super("마법 활", 70, 6);
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