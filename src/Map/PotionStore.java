package Map;

import Character.Hero;

public abstract class PotionStore {
	protected String potionName;
	protected int price;

	public PotionStore(String potionName, int price) {
		this.potionName = potionName;
		this.price = price;
	}

	public String getPotionName() {
		return potionName;
	}

	public int getPrice() {
		return price;
	}

	public abstract void applyEffect(Hero hero);
}