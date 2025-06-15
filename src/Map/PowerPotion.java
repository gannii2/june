package Map;

import Character.Hero;

public class PowerPotion extends PotionStore {
	public PowerPotion() {
		super("힘 증가 포션", 30);
	}

	@Override
	public void applyEffect(Hero hero) {
		if (hero.getMoney() >= price) {
			hero.setPower(hero.getPower() + 3);
			hero.setMoney(hero.getMoney() - price);
			System.out.println("힘 +3 증가!");
		} else {
			System.out.println("골드가 부족합니다.");
		}
	}
}