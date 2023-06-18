import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        int select = 0;
        Scanner scanner;
        List<Person> list = new ArrayList<>();
        if (select == 0){
            scanner =  new Scanner(System.in);
            System.out.println("Create");
            System.out.println("Enter name : ");
            String name = scanner.nextLine();
            System.out.println("Enter surname : ");
            String surname = scanner.nextLine();
            System.out.println("Enter phone : ");
            String phone = scanner.next();
            System.out.println("Enter gender : ");
            String gender = scanner.next();
            System.out.println("Enter age : ");
            int age = scanner.nextInt();
            System.out.println("Enter type : ");
            String type = scanner.next();
            Person person = new Person(name,surname,phone, gender, age, type);
            list.add(person);
        }
    }
}

