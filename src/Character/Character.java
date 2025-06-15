package Character;

public abstract class Character {
	protected String name;
	protected int level;
	protected int hp;
	protected int mp;
	protected int power;
	protected int defense;

	public Character(String name, int level, int hp, int mp, int power, int defense) {
		this.name = name;
		this.level = level;
		this.hp = hp;
		this.mp = mp;
		this.power = power;
		this.defense = defense;
	}

	public String getName() {
		return name;
	}

	public int getLevel() {
		return level;
	}

	public int getHp() {
		return hp;
	}

	public int getMp() {
		return mp;
	}

	public int getPower() {
		return power;
	}

	public int getDefense() {
		return defense;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public void setMp(int mp) {
		this.mp = mp;
	}

	public void setPower(int power) {
		this.power = power;
	}

	public void setDefense(int defense) {
		this.defense = defense;
	}

	public boolean isAlive() {
		return hp > 0;
	}

	public void attacked(int damage) {
		int realDamage = Math.max(0, damage - defense);
		hp -= realDamage;
		if (hp < 0)
			hp = 0;
		System.out.printf("%s가 %d의 피해를 입었습니다. [남은 HP: %d]\n", name, realDamage, hp);
	}

	public abstract void printStatus();
}