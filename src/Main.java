import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

public class Main {
    MainMenu mainMenu;
    public static void main(String[] args) {
        HashSet<Person> people = new HashSet<Person>();

        people.add(new Male("Такеши", "Китано","88005553535", 76,"Брат"));
        people.add(new Male("Алексей", "Абрамович","3743437863478", 63,"Дальний родственник"));
        people.add(new Female("Аяко", "Саичиро","478546567878",32,"Супруга"));
        people.add(new Female("Милана", "Янковки","56474637656", 35,"Сестра"));

        System.out.println(people);

    }
}

