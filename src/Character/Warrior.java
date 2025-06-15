package Character;

public class Warrior extends Hero {
	public Warrior(String name) {
		super(name, 1, 120, 30, 20, 10);
		this.job = "Warrior";
	}

	@Override
	public void specialAttack(Monster m) {
		if (level >= 3) {
			System.out.println("삼단 베기 발동!");
			m.attacked(power * 3);
		} else {
			System.out.println("레벨이 부족하여 사용할 수 없습니다.");
		}
	}
}