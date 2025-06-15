package Map;

import Character.Hero;

public class Sword extends WeaponStore {
	public Sword() {
		super("강철 검", 50, 5);
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
