package Character;

public class Mage extends Hero {
	public Mage(String name) {
		super(name, 1, 80, 100, 15, 8);
		this.job = "Mage";
	}

	@Override
	public void specialAttack(Monster m) {
		if (mp >= 30) {
			System.out.println("파이어볼 시전!");
			m.attacked(power * 4);
			mp -= 30;
		} else {
			System.out.println("MP가 부족합니다.");
		}
	}
}