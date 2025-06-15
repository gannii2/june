package Map;

import Character.Hero;

public class ExpPotion extends PotionStore {
	public ExpPotion() {
		super("경험치 포션", 100);
	}

	@Override
	public void applyEffect(Hero hero) {
		if (hero.getMoney() >= price) {
			hero.setExperience(hero.getExperience() + 50);
			hero.setMoney(hero.getMoney() - price);
			System.out.println("경험치 +50 증가!");
			hero.levelUp();
		} else {
			System.out.println("골드가 부족합니다.");
		}
	}
}