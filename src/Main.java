import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

public class Main {
    MainMenu mainMenu;
    public static void main(String[] args) {
        HashSet<Person> people = new HashSet<Person>();
<<<<<<< HEAD
        people.add(new Person("Такеши", "Китано","88005553535","Мужчина", 76,"Брат"));
        people.add(new Person("Алексей", "Абрамович","3743437863478","Мужчина", 63,"Дальний родственник"));
        people.add(new Person("Аяко", "Саичиро","478546567878","Женщина", 32,"Супруга"));
        people.add(new Person("Милана", "Янковки","56474637656","Женщина", 35,"Сестра"));

        System.out.println("""
                Изменить имя  -> Press 0
                Прочитать     -> Press 1
                Создать       -> Press 2
                Удалить       -> Press 3
                Выход         -> Press 4
                """);
=======
        people.add(new Male("Такеши", "Китано","88005553535", 76,"Брат"));
        people.add(new Male("Алексей", "Абрамович","3743437863478", 63,"Дальний родственник"));
        people.add(new Female("Аяко", "Саичиро","478546567878",32,"Супруга"));
        people.add(new Female("Милана", "Янковки","56474637656", 35,"Сестра"));

        System.out.println(people);
>>>>>>> 64bad57e66d282c73b57a1d4256472d5bf5a6d72
    }
}

