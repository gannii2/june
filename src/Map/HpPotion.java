package Map;

import Character.Hero;

public class HpPotion extends PotionStore {
	public HpPotion() {
		super("HP 포션", 10);
	}

	@Override
	public void applyEffect(Hero hero) {
		if (hero.getMoney() >= price) {
			hero.setHp(hero.getHp() + 50);
			hero.setMoney(hero.getMoney() - price);
			System.out.println("HP +50 회복되었습니다!");
		} else {
			System.out.println("골드가 부족합니다.");
		}
	}
}
