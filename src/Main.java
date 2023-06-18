import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        HashSet<Person> people = new HashSet<Person>();
        people.contains(new Person("Такеши", "Китано","88005553535","Мужчина", 76,"Брат"));
        people.contains(new Person("Алексей", "Абрамович","3743437863478","Мужчина", 63,"Дальний родственник"));
        people.contains(new Person("Аяко", "Саичиро","478546567878","Женщина", 32,"Супруга"));
        people.contains(new Person("Милана", "Янковки","56474637656","Женщина", 35,"Сестра"));
    }
}

