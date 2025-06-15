package Map;

import Character.Hero;

public abstract class WeaponStore {
	protected String weaponName;
	protected int price;
	protected int powerBoost;

	public WeaponStore(String weaponName, int price, int powerBoost) {
		this.weaponName = weaponName;
		this.price = price;
		this.powerBoost = powerBoost;
	}

	public String getWeaponName() {
		return weaponName;
	}

	public int getPrice() {
		return price;
	}

	public int getPowerBoost() {
		return powerBoost;
	}

	public abstract void buyWeapon(Hero hero);
}