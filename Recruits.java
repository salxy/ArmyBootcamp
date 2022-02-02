import java.io.PrintStream;

public class Recruits extends ArmyRecruit {

	String recruitmentYear;

	public Recruits() {
		super();
	}

	public Recruits(String recruitmentYear) {
		super();
		this.recruitmentYear = recruitmentYear;
	}

	public boolean isEmpty() {
		return super.isEmpty();
	}

	public void addRecruit(ArmyRecruit r) {
		addInfo(r);
	}

	public void addScore(int score) {
		addInfo(score);
	}

	public Object eliminateRecruit() {
		return delete();
	}

	public Object eliminateScore() {
		return delete();
	}

	public ArmyRecruit disqualifyRecruit(String name) {
		Recruits tmp = new Recruits();
		ArmyRecruit foundArmyRecruit = new ArmyRecruit();

		while (!isEmpty()) {
			ArmyRecruit rec = (ArmyRecruit) eliminateRecruit();
			String na = rec.getName();
			if (!na.equals((String) name)) {
				tmp.addRecruit(rec);
			} else {
				foundArmyRecruit = rec;
			}
		}

		while (!tmp.isEmpty()) {
			ArmyRecruit rec = (ArmyRecruit) tmp.eliminateRecruit();
			addRecruit(rec);
		}

		if (foundArmyRecruit.isEmpty()) {
			System.out.println("Army recruit not found.");
		}
		return foundArmyRecruit;
	}

	public ArmyRecruit searchArmyRecruit(Object name) {
		Recruits tmp = new Recruits();
		ArmyRecruit foundArmyRecruit = new ArmyRecruit();
		boolean flag = true;
		while (!isEmpty()) {
			ArmyRecruit rec = (ArmyRecruit) eliminateRecruit();
			String na = rec.getName();

			if (na.equals((String) name) && flag == true) {
				System.out.println("List of all recruits named " + (String) name);
				flag = false;
			}
			if (na.equals((String) name)) {
				foundArmyRecruit = rec;
				foundArmyRecruit.display();
			}
			tmp.addRecruit(rec);
		}

		while (!tmp.isEmpty()) {
			ArmyRecruit rec = (ArmyRecruit) tmp.eliminateRecruit();
			addRecruit(rec);
		}

		if (foundArmyRecruit.isEmpty()) {
			System.out.println("Army recruit not found.");
		}
		return foundArmyRecruit;

	}

	public ArmyRecruit searchArmyRecruitInfo(Object name) {
		Recruits tmp = new Recruits();
		ArmyRecruit foundArmyRecruit = new ArmyRecruit();
		boolean flag = true;
		while (!isEmpty()) {
			ArmyRecruit rec = (ArmyRecruit) eliminateRecruit();
			String na = rec.getName();
			if (na.equals((String) name)) {
				foundArmyRecruit = rec;
			}
			tmp.addRecruit(rec);
		}

		while (!tmp.isEmpty()) {
			ArmyRecruit rec = (ArmyRecruit) tmp.eliminateRecruit();
			addRecruit(rec);
		}

		if (foundArmyRecruit.isEmpty()) {
			System.out.println("Army recruit not found.");
		}
		return foundArmyRecruit;

	}
	public void display() {
		Recruits tmp = new Recruits();

		while (!isEmpty()) {
			ArmyRecruit x = (ArmyRecruit) eliminateRecruit();
			infoNode current = x.first;
			while (current != null) {
				System.out.print(current.data + " -> ");
				current = current.next;
			}
			System.out.print("null");
			tmp.addRecruit(x);
			System.out.println();
		}
		while (!tmp.isEmpty()) {
			ArmyRecruit x = (ArmyRecruit) tmp.eliminateRecruit();
			addRecruit(x);
		}

	}
	
	public void display(PrintStream out) {
		Recruits tmp = new Recruits();

		while (!isEmpty()) {
			ArmyRecruit x = (ArmyRecruit) eliminateRecruit();
			infoNode current = x.first;
			while (current != null) {
				out.print(current.data + " -> ");
				current = current.next;
			}
			out.print("null");
			tmp.addRecruit(x);
			out.println();
		}
		while (!tmp.isEmpty()) {
			ArmyRecruit x = (ArmyRecruit) tmp.eliminateRecruit();
			addRecruit(x);
		}

	}

	public void displayScores(Recruits r, PrintStream o) {
		Recruits tmp = new Recruits();
		Recruits tmp2 = new Recruits();

		while (!isEmpty()) {
			ArmyRecruit rec = (ArmyRecruit) eliminateRecruit();
			int x = (int) r.eliminateScore();
			o.print(rec.getName() + ": " + x + " points.");
			tmp.addRecruit(rec);
			tmp2.addScore(x);
			o.println();
		}
		while (!tmp.isEmpty()) {
			ArmyRecruit x = (ArmyRecruit) tmp.eliminateRecruit();
			addRecruit(x);
		}
		while (!tmp2.isEmpty()) {
			int x = (int) tmp2.eliminateRecruit();
			r.addScore(x);
		}
	}
	


	public int getSize() {
		int count = 0;
		Recruits tmp = new Recruits();
		while (!isEmpty()) {
			count++;
			tmp.addRecruit((ArmyRecruit) eliminateRecruit());
		}
		while (!tmp.isEmpty()) {
			addRecruit((ArmyRecruit) tmp.eliminateRecruit());
		}
		return count;
	}

	public Recruits sortRecruits() {
		int size = getSize();
		ArmyRecruit[] sortedRec = new ArmyRecruit[size];
		Recruits sorted = new Recruits();

		for (int i = 0; i < size; i++) {
			sortedRec[i] = (ArmyRecruit) eliminateRecruit();
		}

		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (sortedRec[i].getPoints() > sortedRec[j].getPoints()) {
					ArmyRecruit tmp = sortedRec[i];
					sortedRec[i] = sortedRec[j];
					sortedRec[j] = tmp;
				}
			}
		}

		for (int i = 0; i < size; i++) {
			sorted.addRecruit(sortedRec[i]);
		}
		return sorted;
	}
}