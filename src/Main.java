import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

public class Main {
    MainMenu mainMenu;
    public static void main(String[] args) {
        HashSet<Person> people = new HashSet<Person>();

        people.add(new Male("Такеши", "Китано","88005553535",76));
        people.add(new Male("Алексей", "Абрамович","3743437863478",63));
        people.add(new Female("Аяко", "Саичиро","478546567878",32));
        people.add(new Female("Милана", "Янковки","56474637656",35));

        // Пример создания людей
        Person testPerson1 = new Male("Алекс", "Тестович", "4535345345", 45);
        Person testPerson2 = new Female("Алекса", "Тестовая", "453532432445", 45);
        Female testPerson3 = new Female("Екатерина", "Чужая", "453532432445", 22);
        Male testPerson4 = new Male("Сергий", "Висарионович", "453532432445", 63);
        people.add(testPerson1);
        people.add(testPerson2);

        // Пример поиска людей в списке
        Person pp = people.stream()
                .filter(object -> object.getName().equals("Алекс"))
                .findFirst().get();


        try {
            // Пример добавления связи. сразу устанавливаются 2 связи от testPerson1 к testPerson2 и от testPerson2 к  testPerson1
            ((Male) pp).addConnection(MaleConnections.MaleTypeConnections.father, testPerson2);

            //при повторной установке связь изменится
            ((Male) pp).addConnection(MaleConnections.MaleTypeConnections.husband, testPerson2);


        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
        }

        // примеры генерации ошибокдобавления связей
        try {
            testPerson3.addConnection(FemaleConnections.FemaleTypeConnections.wife, testPerson2); // генерируем ошибку
        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
        }

        try {
            testPerson4.addConnection(MaleConnections.MaleTypeConnections.husband, testPerson1); // генерируем ошибку
        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
        }


        //System.out.println(people);

        System.out.println("--");
        System.out.println(pp);
        System.out.println("==");

        System.out.println("--+++++");
        System.out.println(testPerson2);
        System.out.println("==");



        // Пример вызова меню
        MainMenu menu = new MainMenu();

        try {
            MainMenu.ActionCodes mainChose = menu.showMainMenu();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }
}

