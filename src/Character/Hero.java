package Character;

public abstract class Hero extends Character {
	protected String job;
	protected int experience = 0;
	protected int money = 0;

	public Hero(String name, int level, int hp, int mp, int power, int defense) {
		super(name, level, hp, mp, power, defense);
	}

	public int getExperience() {
		return experience;
	}

	public int getMoney() {
		return money;
	}

	public void setExperience(int exp) {
		this.experience = exp;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public void levelUp() {
		while (experience >= level * 80) {
			experience -= level * 80;
			level++;
			power += 5;
			defense += 3;
			hp += 20;
			money += level * 50;
			System.out.printf("레벨업! %s가 %d로 레벨업 했습니다!\n", name, level);
		}
	}

	public abstract void specialAttack(Monster m);

	@Override
	public void printStatus() {
		System.out.println("**********************");
		System.out.printf("현재 %s의 이름: %s\n", name, name);
		System.out.printf("현재 %s의 레벨: %d\n", name, level);
		System.out.printf("현재 %s의 힘: %d\n", name, power);
		System.out.printf("현재 %s의 방어력: %d\n", name, defense);
		System.out.printf("현재 %s의 HP: %d\n", name, hp);
		System.out.printf("현재 %s의 MP: %d\n", name, mp);
		System.out.printf("현재 %s의 경험치: %d\n", name, experience);
		System.out.printf("현재 %s의 돈: %d\n", name, money);
		System.out.println("**********************");
	}
}