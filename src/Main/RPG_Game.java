package Main;

import java.util.Scanner;

import Character.Archer;
import Character.BossMonster;
import Character.Hero;
import Character.Mage;
import Character.Monster;
import Character.Racoon;
import Character.Slime;
import Character.Warrior;
import Character.WildCat;
import Map.Axe;
import Map.Bow;
import Map.DefensePotion;
import Map.ExpPotion;
import Map.HpPotion;
import Map.Mission;
import Map.PotionStore;
import Map.PowerPotion;
import Map.Sword;
import Map.WeaponStore;

public class RPG_Game {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Hero hero = null;

		System.out.println("*******RPG GAME*******");
		System.out.println("1. 전사 \n2. 마법사 \n3. 궁수");
		System.out.print("직업의 번호를 하세요: ");
		int job = sc.nextInt();
		if (job == 1)
			System.out.println("전사가 선택되었습니다.");
		else if (job == 2)
			System.out.println("마법사가 선택되었습니다.");
		else if (job == 3)
			System.out.println("궁수가 선택되었습니다.");

		System.out.print("영웅의 이름을 입력하세요: ");
		String name = sc.next();

		if (job == 1)
			hero = new Warrior(name);
		else if (job == 2)
			hero = new Mage(name);
		else if (job == 3)
			hero = new Archer(name);
		else {
			System.out.println("잘못된 선택입니다. 기본 전사로 시작합니다.");
			hero = new Warrior(name);
		}
		System.out.println("이름이 입력되었습니다.");

		Mission mission = new Mission();
		WeaponStore[] weapons = { new Sword(), new Axe(), new Bow() };
		PotionStore[] potions = { new HpPotion(), new ExpPotion(), new PowerPotion(), new DefensePotion() };

		System.out.println("게임에 입장하였습니다.");
		while (true) {
			hero.printStatus();
			System.out.println("1. 사냥터 입장\n2. 포션 상점\n3. 무기 상점\n4. 종료");
			System.out.print("메뉴 선택: ");
			int choice = sc.nextInt();

			if (choice == 1) {
				System.out.println("**********************");
				System.out.println("사냥터에 입장했습니다.");
				System.out.println("1. 너구리\n2. 살쾡이\n3. 슬라임\n4. 드래곤");
				System.out.print("전투할 몬스터 선택: ");
				int mChoice = sc.nextInt();

				Monster monster;
				if (mChoice == 1)
					monster = new Racoon();
				else if (mChoice == 2)
					monster = new WildCat();
				else if (mChoice == 3)
					monster = new Slime();
				else if (mChoice == 4)
					monster = new BossMonster();
				else
					monster = new Racoon();

				System.out.printf("%s와 전투를 시작합니다.\n", monster.getName());

				monster.callReinforcement(); // 추가기능2

				boolean heroturn = true;
				while (monster.isAlive() && hero.isAlive()) {
					if (heroturn) {
						System.out.println("\n1. 일반 공격");
						if (job == 1)
							System.out.println("2. 전사의 삼단 베기");
						else if (job == 2)
							System.out.println("2. 궁수의 헤드샷");
						else if (job == 3)
							System.out.println("2. 마법사의 파이어볼");
						else
							System.out.println("2. 전사의 삼단 베기");

						System.out.print("공격 번호를 입력하세요: ");
						int attack = sc.nextInt();
						if (attack == 1) {
							monster.attacked(hero.getPower());
						} else {
							hero.specialAttack(monster);
						}
					} else {
						System.out.printf("%s의 공격\n", monster.getName());
						hero.attacked(monster.getPower());
						if (monster.isReinforced()) {
							System.out.println("[동료 추가 공격] 몬스터의 동료가 추가 피해를 입힙니다!");
							hero.attacked(monster.extraAttack());
						}
						heroturn = !heroturn;
					}

					if (!hero.isAlive()) {
						System.out.println("히어로가 쓰러졌습니다. 부활하시겠습니다?");
						System.out.println("1. 부활 \n2. 게임종료");
						System.out.print("선택: ");
						int revivalChoice = sc.nextInt();
						if (revivalChoice == 1) {
							System.out.println("히어로가 부활합니다...\n히어로의 능력치가 초기화됩니다.");
							hero.setHp(100);
							hero.setLevel(1);
							hero.setExperience(0);
							hero.setMoney(0);
							hero.printStatus();
						} else {
							System.out.println("게임을 종료합니다.");
							sc.close();
							return;
						}
					} else {
						System.out.printf("%s를 처치했습니다!\n", monster.getName());
						System.out.println("보상으로 경험치와 돈이 올라갑니다!");
						hero.setExperience(hero.getExperience() + monster.getExpDrop());
						hero.setMoney(hero.getMoney() + monster.getMoneyDrop());

						int oldLevel = hero.getLevel();
						hero.levelUp();
						hero.printStatus();

						if (hero.getLevel() > oldLevel) {
							for (int i = oldLevel + 1; i <= hero.getLevel(); i++) {
								System.out.printf("[새로운 미션] 레벨 %d 달성! 미션을 확인하세요.\n", i);
								mission.giveLevelMission(hero, i);
							}
						}
						mission.checkAndRewardMission(hero, monster);
					}
				}
			} else if (choice == 2) {
				System.out.println("**********************");
				System.out.println("포션 상점에 입장하였습니다.\n");
				for (int i = 0; i < potions.length; i++) {
					System.out.printf("%d. %s (%d 골드)\n", i + 1, potions[i].getPotionName(), potions[i].getPrice());
				}
				System.out.println("0. 나가기");
				System.out.printf("구매할 포션을 선택하세요[현재 돈 %d골드]: ", hero.getMoney());
				int select = sc.nextInt();
				while (select != 0) {
					if (select >= 1 && select <= potions.length) {
						potions[select - 1].applyEffect(hero);
					} else
						break;
					System.out.println();
					for (int i = 0; i < potions.length; i++) {
						System.out.printf("%d. %s (%d 골드)\n", i + 1, potions[i].getPotionName(), potions[i].getPrice());
					}
					System.out.println("0. 나가기");
					System.out.printf("구매할 포션을 선택하세요[현재 돈 %d골드]: ", hero.getMoney());
					select = sc.nextInt();
				}
				System.out.println("상점에서 나왔습니다.");
			} else if (choice == 3) {
				System.out.println("**********************");
				System.out.println("무기 상점에 입장하였습니다.\n");
				for (int i = 0; i < weapons.length; i++) {
					System.out.printf("%d. %s (%d 골드, 공격력 +%d)\n", i + 1, weapons[i].getWeaponName(),
							weapons[i].getPrice(), weapons[i].getPowerBoost());
				}
				System.out.println("0. 나가기");
				System.out.printf("구매할 무기를 선택하세요[현재 돈 %d골드]: ", hero.getMoney());
				int select = sc.nextInt();
				while (select != 0) {
					if (select >= 1 && select <= weapons.length) {
						weapons[select - 1].buyWeapon(hero);
					} else
						break;
					System.out.println();
					for (int i = 0; i < weapons.length; i++) {
						System.out.printf("%d. %s (%d 골드, 공격력 +%d)\n", i + 1, weapons[i].getWeaponName(),
								weapons[i].getPrice(), weapons[i].getPowerBoost());
					}
					System.out.println("0. 나가기");
					System.out.printf("구매할 무기를 선택하세요[현재 돈 %d골드]: ", hero.getMoney());
					select = sc.nextInt();
				}
				System.out.println("상점에서 나왔습니다.");
			} else if (choice == 4) {
				System.out.println("게임을 종료합니다.");
				sc.close();
				return;
			} else {
				System.out.println("잘못된 입력입니다.");
			}
		}
	}
}
