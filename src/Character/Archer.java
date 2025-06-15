package Character;

public class Archer extends Hero {
	public Archer(String name) {
		super(name, 1, 100, 50, 18, 9);
		this.job = "Archer";
	}

	@Override
	public void specialAttack(Monster m) {
		System.out.println("헤드샷 발사!");
		m.attacked(power * 2 + 10);
	}
}