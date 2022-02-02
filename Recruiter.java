import java.util.Scanner;

public class Recruiter {

	String name;

	public Recruiter(String name) {
		super();
		this.name = name;
	}

	public Recruits[] reviewPhaseOne(Recruits[]arr, Recruits r) {

		Recruits tmp = new Recruits();

		while (!r.isEmpty()) {
			ArmyRecruit rec = (ArmyRecruit) r.eliminateRecruit();

			int points = 0;
			int age = rec.getAge();
			if (age >= 17 && age <= 23) {
				points += 3;
			} else if (age > 23 && age < 29) {
				points += 2;
			} else if (age >= 29 && age <= 35) {
				points += 1;
			} else {
				points = (int) Integer.MIN_VALUE;
			}
			String citizenship = rec.getCitizenship();
			if (points != Integer.MIN_VALUE) {
				if (citizenship.charAt(0) == '1') {
					points += 2;
				} else if (citizenship.charAt(0) == '2') {
					points += 1;
				} else {
					points = (int) Integer.MIN_VALUE;
				}
			}
			String degree = rec.getDegree();
			if (points != Integer.MIN_VALUE) {
				if (degree.charAt(0) == '1') {
					points += 1;
				} else {
					points = (int) Integer.MIN_VALUE;
				}
			}
			if (points >= 3 && points <= 6) {
				arr[0].addRecruit(rec);
			} else {
				arr[2].addRecruit(rec);
			}
			if (points != Integer.MIN_VALUE) {
				rec.addInfo(points);
				arr[1].addInfo(points);
			}
			tmp.addRecruit(rec);
		}
		while (!tmp.isEmpty()) {
			r.addRecruit((ArmyRecruit) tmp.eliminateRecruit());
		}
		return arr;
	}

	public Recruits takeExamScores(Recruits r, Scanner con) {
		Recruits withScores = new Recruits();
		Recruits tmp = new Recruits();
		while (!r.isEmpty()) {
			ArmyRecruit rec = (ArmyRecruit) r.eliminateRecruit();
			
			int score1;
			System.out.println("Please enter " + rec.getName() + "'s ASVAB test score out of 100: ");
			score1 = con.nextInt();
			rec.addInfo(score1);
			
			System.out.println("Did he/she pass each of the 2 mile run, 2 minute situps, and 2 minute pushups?");
			System.out.println("Type p/f: ");
			char answer = con.next().charAt(0);
			rec.addInfo(answer);

			System.out.println("Is their background check valid?");
			System.out.println("Type y/n: ");
			answer = con.next().charAt(0);
			rec.addInfo(answer);

			withScores.addRecruit(rec);
			tmp.addRecruit(rec);
		}
		while (!tmp.isEmpty()) {
			r.addRecruit((ArmyRecruit) tmp.eliminateRecruit());
		}

		return withScores;

	}

	public Recruits[] reviewPhaseTwo(Recruits[] r) {

		Recruits tmp = new Recruits();

		while (!r[0].isEmpty()) {
			ArmyRecruit rec = (ArmyRecruit) r[0].eliminateRecruit();

			int points = 0;

			int score = rec.getASVABScore();
			if (score >= 50) {
				points += 1;
			} else {
				points = (int) Integer.MIN_VALUE;
			}

			char answer = rec.getPhysicalTestScore();
			if (points != Integer.MIN_VALUE) {
				if (answer == 'p') {
					points += 1;
				} else {
					points = (int) Integer.MIN_VALUE;
				}
			}
			answer = rec.getBGCheck();
			if (points != Integer.MIN_VALUE) {
				if (answer == 'y') {
					points += 1;
				} else {
					points = (int) Integer.MIN_VALUE;
				}
			}
			if (points == 3) {
				int x = rec.getPoints();
				x += points;
				rec.setPoints(x);
				tmp.addRecruit(rec);
				int y = (int) r[1].eliminateScore();
				y += points;
				r[1].addScore(y);
			} else {
				r[2].addRecruit(rec);
				r[1].eliminateScore();
			}

		}
		while (!tmp.isEmpty()) {
			r[0].addRecruit((ArmyRecruit) tmp.eliminateRecruit());
		}
		return r;
	}

	public String disqualifiedRecruits(Recruits r) {
		String str = "Reasons for Disqualification:\n";
		while (!r.isEmpty()) {
			ArmyRecruit rec = (ArmyRecruit) r.eliminateRecruit();
			String name = rec.getName();
			str += name + ":\n";
			int age = rec.getAge();
			String citizenship = rec.getCitizenship();
			String degree = rec.getDegree();

			if (age < 17 || age > 35) {
				str += "Age - too young/too old.\n";
			}
			if (citizenship.charAt(0) != '1' && citizenship.charAt(0) != '2') {
				str += "No citizenship/residency.\n";
			}
			if (degree.charAt(0) != '1') {
				str += "No degree.\n";
			}
			try {
				int score = rec.getASVABScore();
				char answer = rec.getPhysicalTestScore();
				char ans2 = rec.getBGCheck();
				if (score < 50) {
					str += "ASVAB Score below 50%.\n";
				}
				if (answer != 'p') {
					str += "Didnt pass physical test.\n";
				}
				if (ans2 != 'y') {
					str += "Background check not compatible.\n";
				}
			} catch (Exception e) {

			}

		}
		return str;
	}

}