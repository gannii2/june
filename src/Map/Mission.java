package Map;

import Character.Hero;
import Character.Monster;

public class Mission {
	private boolean slimeMissionCompleted = false;
	private boolean dragonMissionCompleted = false;
	private boolean level2MissionCompleted = false;
	private boolean level3MissionCompleted = false;
	private boolean level4MissionCompleted = false;
	private boolean level5PromotionGiven = false;

	public void giveMission(Hero hero) {
		if (hero.getLevel() >= 3 && !slimeMissionCompleted) {
			System.out.println("[미션] 슬라임 한 마리를 처치하세요!");
		} else if (hero.getLevel() >= 2 && !level2MissionCompleted) {
			System.out.println("[미션] HP를 200 이상으로 회복하세요!");
		} else {
			System.out.println("현재 받을 수 있는 미션이 없습니다.");
		}
	}

	public void giveLevelMission(Hero hero, int level) {
		switch (level) {
		case 2 -> System.out.println("[미션] HP 200까지 회복하세요.");
		case 3 -> System.out.println("[미션] 슬라임 한 마리를 처치하세요!");
		case 4 -> System.out.println("[미션] 무기 상점에서 아이템을 구매하세요.");
		case 5 -> System.out.println("[승급 미션] 드래곤을 처치하세요!");
		default -> System.out.println("[미션] 레벨 " + level + " 미션은 준비 중입니다.");
		}
	}

	public void checkAndRewardMission(Hero hero, Monster monster) {
		if (monster.getName().equals("슬라임") && !slimeMissionCompleted) {
			System.out.println("[미션 완료] 슬라임 처치 성공! 경험치 +30, 골드 +50");
			hero.setExperience(hero.getExperience() + 30);
			hero.setMoney(hero.getMoney() + 50);
			hero.levelUp();
			slimeMissionCompleted = true;
		}

		if (monster.getName().equals("드래곤") && !dragonMissionCompleted) {
			System.out.println("[미션 완료] 드래곤 처치 성공! 경험치 +100, 골드 +200");
			hero.setExperience(hero.getExperience() + 100);
			hero.setMoney(hero.getMoney() + 200);
			hero.levelUp();
			dragonMissionCompleted = true;

			if (!level5PromotionGiven) {
				System.out.println("[승급 보상] 당신은 이제 '마스터'로 승급하였습니다! 모든 능력 +10");
				hero.setPower(hero.getPower() + 10);
				hero.setDefense(hero.getDefense() + 10);
				hero.setHp(hero.getHp() + 100);
				level5PromotionGiven = true;
			}
		}
	}

	public void checkGeneralMissions(Hero hero) {
		if (!level2MissionCompleted && hero.getHp() >= 200) {
			System.out.println("[미션 완료] HP 200 달성! 경험치 +20, 골드 +20");
			hero.setExperience(hero.getExperience() + 20);
			hero.setMoney(hero.getMoney() + 20);
			hero.levelUp();
			level2MissionCompleted = true;
		}
		if (!level3MissionCompleted && slimeMissionCompleted) {
			System.out.println("[추가 보상] 슬라임 미션 달성 기념 아이템 보너스 지급! 골드 +30");
			hero.setMoney(hero.getMoney() + 30);
			level3MissionCompleted = true;
		}
		if (!level4MissionCompleted && hero.getPower() > 30) {
			System.out.println("[미션 완료] 무기 구매로 힘 증가 확인! 경험치 +40, 골드 +40");
			hero.setExperience(hero.getExperience() + 40);
			hero.setMoney(hero.getMoney() + 40);
			hero.levelUp();
			level4MissionCompleted = true;
		}
	}
}