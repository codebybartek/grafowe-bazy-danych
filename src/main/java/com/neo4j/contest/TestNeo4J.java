package com.neo4j.contest;

import java.util.Map;
import java.util.Scanner;

import org.neo4j.ogm.config.Configuration;
import org.neo4j.ogm.session.Session;
import org.neo4j.ogm.session.SessionFactory;

public class TestNeo4J {

	public static void main(String[] args) {

		Configuration configuration = new Configuration.Builder().uri("bolt://localhost:7687").credentials("neo4j", "contest").build();
		SessionFactory sessionFactory = new SessionFactory(configuration, "com.neo4j.contest");

		Session session = sessionFactory.openSession();

		session.purgeDatabase();

		PlayerService playerService = new PlayerService(session);
		ContestService contestService = new ContestService(session);
		contestService.deleteAll();


		do {
			Scanner scanner = new Scanner(System.in);
			System.out.println("");
			System.out.println("<--------------Liga sportowa-------------->");
			System.out.println("-- 1. Dodaj zawodnika");
			System.out.println("-- 2. Wyświetl wszystkie zawody");
			System.out.println("-- 3. Wyświetl po id");
			System.out.println("-- 4. Przesun w gore pozycje");
			System.out.println("-- 5. Przesun w dol pozycje");
			System.out.println("-- 6. Aktualizuj");
			System.out.println("-- 7. Wyświetl zawodnika z określonym wiekiem");
			System.out.println("-- 8. Zakończ");

			System.out.println("Podaj numer operacji do wykonania: ");
			String option = scanner.nextLine();
			switch (option) {
				case "1":
					Player player1 = new Player("Kondradzki Tomasz", 15, 12.34, 12, 2);
					Player player2 = new Player("Piotrowski Jan", 32, 10.00, 10, 1);
					Player player3 = new Player("Koziel Bartek", 23, 15.04,  14, 3);
					Player player4 = new Player("Duda Andrzej", 45, 20.34,  25, 4);
					Player player5 = new Player("Trzaskowski Rafal", 55, 32.14,  41, 6);
					Player player6 = new Player("Domagala Patryk", 26, 45.26,  34, 5);

					Contest contest1 = new Contest("Zawody w Ustronu", "2020-01-12", "12:30", "Ustron");
					Contest contest2 = new Contest("Zawody w Kielcach", "2020-02-16", "16:20", "Kielce");
					Contest contest3 = new Contest("Warzawa 2020", "2020-06-05", "17:00", "Warszawa");
					contest1.addTeam(player1);
					contest1.addTeam(player2);
					contest1.addTeam(player3);
					contest1.addTeam(player4);
					contest2.addTeam(player5);
					contest2.addTeam(player6);
					contestService.createOrUpdate(contest1);
					contestService.createOrUpdate(contest2);
					contestService.createOrUpdate(contest3);
				break;

				case "2":
				System.out.println("Wszystkie Zawody:");
					for(Contest b : contestService.readAll())
					System.out.println(b);
	
				for(Map<String, Object> map : contestService.getContestRelationships())
					System.out.println(map);
				break;

				case "3":
					System.out.println("Podaj id zawodow do wyświetlenia");
					Long ids =scanner.nextLong();
					System.out.println(contestService.read(ids));
					break;

				case "4":
					System.out.println("Podaj id zawodnika do zwiekszenia pozycji");
					Long id22 =scanner.nextLong();
					playerService.moveUp(id22);
					break;

				case "5":
					System.out.println("Podaj id zawodnika do zminiejszenia pozycji");
					Long id33 =scanner.nextLong();
					playerService.moveDown(id33);
					break;


				case "6":
					System.out.println("Podaj id zawodnika do aktualizacji");
					Long id =Long.valueOf(scanner.nextLine());
					System.out.println("Podaj nowe imie i nazwisko");
					String newName = scanner.nextLine();
					playerService.update(id, newName);
					break;

				case "7":

					System.out.println("Podaj minimalny wiek zawodnika(x>)");
					int age = scanner.nextInt();
					for(Player player : playerService.getPlayers(age))
						System.out.println(player);
					break;

				case "8":

					sessionFactory.close();

					break;

				default:
					System.out.println("Błędna instrukcja");
			}
		} while (true);
	}
}
