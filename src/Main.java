import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

public class Main {
    MainMenu mainMenu;
    public static void main(String[] args) {
        HashSet<Person> people = new HashSet<Person>();
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
    }
}

