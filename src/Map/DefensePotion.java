package Map;

import Character.Hero;

public class DefensePotion extends PotionStore {
	public DefensePotion() {
		super("방어력 증가 포션", 30);
	}

	@Override
	public void applyEffect(Hero hero) {
		if (hero.getMoney() >= price) {
			hero.setDefense(hero.getDefense() + 3);
			hero.setMoney(hero.getMoney() - price);
			System.out.println("방어력 +3 증가!");
		} else {
			System.out.println("골드가 부족합니다.");
		}
	}
}