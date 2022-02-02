import java.util.Scanner;

public class ArmyRecruit {

	infoNode first;

	public ArmyRecruit() {
		super();
	}

	public boolean isEmpty() {
		if (first == null)
			return true;
		return false;
	}

	// inserts info at back
	public void addInfo(Object v) {
		infoNode n = new infoNode(v);
		if (first == null)
			first = n;
		else {
			infoNode current = first;
			while (current.next != null) {
				current = current.next;
			}
			current.next = n;
		}
	}

	// delete info from front
	public Object delete() {
		infoNode current = first;
		Object o1 = null;
		if (first != null) {
			o1 = current.data;
			first = current.next;
			current.next = null;
		}
		return o1;
	}
	
	public void display() {
		infoNode current = first;
		while (current != null) {
			System.out.print(current.data + " -> ");
			current = current.next;
		}
		System.out.print("null");
		System.out.println();
	}

	// searches for specific information about a certain recruit
	public void searchInfo(Scanner con) {
		System.out.println("Search for: ");
		System.out.println("1. Age\n2. Citizenship\n3. Degree\n4. "
				+ "ASVAB Percentage\n5. Physical Test\n6. Background Check Validity");
		int num = con.nextInt();
		if (num == 1) {
			System.out.println("ArmyRecruit's age is " + getAge());
		} else if (num == 2) {
			System.out.println("ArmyRecruit's type of citizenship: " + getCitizenship());
		} else if (num == 3) {
			System.out.println("ArmyRecruit's Degree: " + getDegree());
		} else if (num == 4) {
			if (first.next.next.next.next != null) {
				System.out.println("ArmyRecruit's ASVAB Percentage: " + getASVABScore() + "%");
			} else {
				System.out.println("ArmyRecruit did not pass phase one - no information available.");
			}
		} else if (num == 5) {
			if (first.next.next.next.next != null) {
				if (getPhysicalTestScore() == 'p') {
					System.out.println("ArmyRecruit's Physical test: pass");
				} else {
					System.out.println("ArmyRecruit's Physical test: fail");
				}
			} else {
				System.out.println("ArmyRecruit did not pass phase one - no information available.");
			}
		} else if (num == 6) {
			if (first.next.next.next.next != null) {
				if (getBGCheck() == 'y') {
					System.out.println("ArmyRecruit's Background Check Validity: valid");
				} else {
					System.out.println("ArmyRecruit's Background Check Validity: invalid");
				}
			} else {
				System.out.println("ArmyRecruit did not pass phase one - no information available.");
			}
		}
	}
	
	// searches if a certain recruit has a certain piece of information
	public boolean searchInfo(Object info) {
		return searchInfo(first, info);
	}
	
	public boolean searchInfo(infoNode current, Object info) {
		if (current == null) {
			return false;
		}
		if (current.data == info) {
			return true;
		} 
		return searchInfo(current.next, info);
	}

	public String getName() {
		return (String) first.data;
	}

	public int getAge() {
		return (int) first.next.data;
	}

	public String getCitizenship() {
		return (String) first.next.next.data;
	}

	public String getDegree() {
		return (String) first.next.next.next.data;
	}

	public int getPoints() {
		return (int) first.next.next.next.next.data;
	}

	public void setPoints(int p) {
		first.next.next.next.next.data = p;
	}

	public int getASVABScore() {
		return (int) first.next.next.next.next.next.data;
	}

	public char getPhysicalTestScore() {
		return (char) first.next.next.next.next.next.next.data;
	}

	public char getBGCheck() {
		return (char) first.next.next.next.next.next.next.next.data;
	}
}