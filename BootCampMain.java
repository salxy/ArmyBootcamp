import java.io.File;
import java.io.PrintStream;
import java.util.InputMismatchException;
import java.util.Scanner;

public class BootCampMain {

	public static void main(String[] args) throws Exception {

		Scanner co = new Scanner(System.in);

		System.out.print("Please enter your name: ");
		String name = co.nextLine();

		System.out.print("Please enter academic year: ");
		String year = co.next();

		Recruiter rec = new Recruiter(name); // recruiter
		Recruits armyRecs = new Recruits(year); // queue of army recruits
		Recruits armyRecCopy = new Recruits(year);
		
		String str = "AcademicYear" + year + ".txt";

		System.out.println("\n\t\t\tWelcome to Academic Year " + year);

		Recruits[] arrOfRecruits = new Recruits[3];
		arrOfRecruits[0] = new Recruits(); // recruits who passed phases
		arrOfRecruits[1] = new Recruits(); // scores of recruits who passed phases
		arrOfRecruits[2] = new Recruits(); // recruits who got disqualified
		int choice;
		boolean flag = true;
		do {
			Scanner con = new Scanner(System.in);
			System.out.println("\nPlease select an option of your choice: ");
			System.out.println("1. Add Recruit\n2. Disqualify Recruit\n3. Search for a Recruit");
			System.out.println(
					"4. Search for Information of a Recruit\n5. Display All Recruits\n6. Display Qualified Recruits");
			System.out
					.println("7. Display Disqualified Recruits\n8. Review Recruits\n9. Print Report\n10. Exit Program");
			choice = con.nextInt();

			switch (choice) {
			case 1:
				try {
					if (flag) {
						ArmyRecruit r = new ArmyRecruit();
						System.out.println("Enter recruit's name: ");
						con.nextLine();
						r.addInfo(con.nextLine());
						System.out.println("Enter recruit's age: ");
						r.addInfo(con.nextInt());
						System.out.println("Enter: \n1. U.S Citizen\n2. U.S Residenct + Valid Greencard\n3. None ");
						con.nextLine();
						r.addInfo(con.nextLine());
						System.out.println("Enter: \n1. Valid Degree \n2. Not Available ");
						r.addInfo(con.nextLine());
						armyRecs.addRecruit(r);
						armyRecCopy.addRecruit(r);
						System.out.println("ArmyRecruit added successfully.");
					} else {
						System.err.println("Recruitment phase is over. You cannot add recruits anymore.");
					}
				} catch (InputMismatchException e) {
					System.out.println("That is not an integer. Please try again.");
				}
				break;
				
			case 2:
				System.out.println("Enter name of disqualified recruit: ");
				con.nextLine();
				String nameRec = con.nextLine();
				ArmyRecruit disqualified = armyRecCopy.disqualifyRecruit(nameRec);
				if (!disqualified.isEmpty()) {
					arrOfRecruits[2].addRecruit(disqualified);
					System.out.println(nameRec + " has been disqualified.");
				}
				break;
				
			case 3:
				System.out.println("Enter name of recruit you want to search for: ");
				con.nextLine();
				armyRecs.searchArmyRecruit(con.nextLine());
				break;
				
			case 4:
				System.out.println("Enter name of recruit: ");
				con.nextLine();
				ArmyRecruit m = armyRecs.searchArmyRecruitInfo(con.nextLine());
				if (!m.isEmpty())
					m.searchInfo(con);
				break;
				
			case 5:
				if (!armyRecs.isEmpty()) {
					System.out.println("Name -> Age -> Citizenship -> Degree -> null");
					armyRecs.display();
				} else
					System.out.println("No recruits yet.");
				break;
				
			case 6:
				if (!arrOfRecruits[0].isEmpty()) {
					System.out.println("Name -> Age -> Citizenship -> Degree -> Bootcamp Score -> ASVAB Percentage -> Physical Test -> Background Check -> null");
					arrOfRecruits[0].display();
				} else
					System.out.println("No qualified recruits yet.");
				break;
				
			case 7:
				if (!arrOfRecruits[2].isEmpty())
					arrOfRecruits[2].display();
				else
					System.out.println("No disqualified recruits yet.");
				break;
				
			case 8:
				if (flag) {
					System.out.println("Are you sure you want to start the review phase? (y/n)");
					System.err.println("Disclaimer: You cannot add recruits when this phase starts.");
					con.nextLine();
					char a = con.next().charAt(0);
					if (a == 'y') {
						flag = false;
						arrOfRecruits = rec.reviewPhaseOne(arrOfRecruits, armyRecCopy);
						arrOfRecruits[0] = rec.takeExamScores(arrOfRecruits[0], con);
						arrOfRecruits = rec.reviewPhaseTwo(arrOfRecruits);
						System.out.println("Reviewing phase done. You can now view the final report in the main menu.");
					}
				} else {
					System.out.println("You've already reviewed recruits. Academic year " + year + " is over.");
				}
				break;
				
			case 9:
				File f = new File(str);
				f.createNewFile();
				PrintStream out = new PrintStream(f);
				arrOfRecruits[0] = arrOfRecruits[0].sortRecruits();
				writeReport(name, rec, armyRecs, arrOfRecruits, out);
				System.out.println("Report Printed Successfully!");
				break;
				
			case 10:
				try {
					System.err.print("System is shutting down");
					char dot;
					for (int j = 0; j < 3; j++) {
						Thread.sleep(500);
						dot = '.';
						System.err.print(dot);
						Thread.sleep(500);
					}

					System.out.println();
					Thread.sleep(2000);

					System.out.println("Shutdown complete, have a nice day!");

					System.exit(0);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			default:
				break;
			}
			try {
				Thread.sleep(1500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		} while (choice != 10);
	}

	public static void writeReport(String name, Recruiter m, Recruits army, Recruits[] arr, PrintStream o) {

		o.println("\t\tAcademic Year Report");

		o.println("\nUnder supervision of: " + name);

		o.println("\nTotal number of recruits: " + army.getSize());

		o.println("\nTotal number of qualified recruits: " + arr[0].getSize());

		o.println("\nTotal number of disqualified recruits: " + arr[2].getSize());

		o.println("\nAll recruits of the year: ");
		army.display(o);

		o.println("\nQualified recruits of the year: ");
		arr[0].display(o);

		o.println("\nScores of Qualified recruits: ");
		arr[0].displayScores(arr[1], o);

		o.println("\nDisqualified recruits of the year: ");
		arr[2].display(o);

		String str = m.disqualifiedRecruits(arr[2]);
		o.print(str);

	}
}