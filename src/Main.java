import java.util.*;

public class Main {
    private static int step;
    MainMenu mainMenu;

    static HashSet<Person> people = new HashSet<Person>();
    static boolean Exit = true;
    public static void main(String[] args) {

        people.add(new Male("Такеши", "Китано","88005553535",76));
        people.add(new Male("Алексей", "Абрамович","3743437863478",63));
        people.add(new Female("Аяко", "Саичиро","478546567878",32));
        people.add(new Female("Милана", "Янковки","56474637656",35));

        // Пример создания людей
        Male testPerson1 = new Male("1 Алекс", "Тестович", "4535345345", 45); // муж
        Person testPerson2 = new Female("2 Алекса", "Тестовая", "453532432445", 45); // жена
        Female testPerson3 = new Female("3 Екатерина", "Чужая", "453532432445", 22); // дочь
        Male testPerson4 = new Male("4 Сергий", "Висарионович", "453532432445", 63); // сын
        Male testPerson5 = new Male("5 Юрий", "Сергеевич", "453532432445", 3); // внук
        Male testPerson6 = new Male("6 василий", "Сергеевич", "453532432445", 87); // тесть
        Person testPerson7 = new Female("7 василиса", "Игоревна", "453532432445", 98); // теща
        Person testPerson8 = new Female("8 Светлана", "Егоровна", "453532432445", 98); // мать
        Person testPerson9 = new Male("9 Егор", "Александрович", "453532432445", 98); // отец

        people.add(testPerson1);
        people.add(testPerson2);

        // Пример поиска людей в списке
        Person pp = people.stream()
                .filter(object -> object.getName().equals("1 Алекс"))
                .findFirst().get();


        try {
            // Пример добавления связи. сразу устанавливаются 2 связи от testPerson1 к testPerson2 и от testPerson2 к  testPerson1
            ((Male) pp).addConnection(MaleConnections.MaleTypeConnections.father, testPerson2);

            //при повторной установке связь изменится муж-жена
            ((Male) pp).addConnection(MaleConnections.MaleTypeConnections.husband, testPerson2);

            // устанавливаем вторую связь отец-дочь
            ((Male) pp).addConnection(MaleConnections.MaleTypeConnections.father, testPerson3);
            // устанавливаем вторую связь отец-сын
            ((Male) pp).addConnection(MaleConnections.MaleTypeConnections.father, testPerson4);

            // устанавливаем вторую связь отец-сын для сына = внук
            testPerson4.addConnection(MaleConnections.MaleTypeConnections.father, testPerson5);

            // устанавливаем вторую связь отец жены = тесть
            testPerson6.addConnection(MaleConnections.MaleTypeConnections.father, testPerson2);
            ((Female)testPerson7).addConnection(FemaleConnections.FemaleTypeConnections.mother, testPerson2);
            testPerson1.addConnection(MaleConnections.MaleTypeConnections.son, testPerson8);
            testPerson1.addConnection(MaleConnections.MaleTypeConnections.son, testPerson9);




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


         System.out.println(people);

        // Полученеи родственников (Пока второго уровня)

        System.out.println();
        HashMap<Person, String> list = testPerson1.getKindredByKinship(2, "");
        list.remove(testPerson1);

        System.out.println("родственники человека " + testPerson1.getName());
        for (Map.Entry<Person, String> relationSet:
                list.entrySet()) {
            System.out.println( relationSet.getKey().getName() + " - " + relationSet.getValue());
        }
        System.out.println();


        HashMap<Person, String> list2 = ((Female)testPerson2).getKindredByKinship(2, "");
        list2.remove(testPerson2);

        System.out.println("родственники человека " + testPerson2.getName());
        for (Map.Entry<Person, String> relationSet:
                list2.entrySet()) {
            System.out.println( relationSet.getKey().getName() + " - " + relationSet.getValue());
        }
        System.out.println();



//        System.out.println("--");
//        System.out.println(pp);
//        System.out.println("==");
//
//        System.out.println("--+++++");
//        System.out.println(testPerson2);
//        System.out.println("==");



        // Пример вызова меню
        MainMenu menu = new MainMenu();


//        do {
//            try {
//                MainMenu.ActionCodes mainChose = menu.showMainMenu();
//                selectAction(mainChose);
//            } catch (Exception e) {
//                throw new RuntimeException(e);
//            }
//        }while (Exit);


    }
    public static void selectAction(MainMenu.ActionCodes step1){
        switch (step1){
            case CREATE -> create();
            case READ -> read();
            case UPDATE -> update();
            case DELETE -> delete();
            case EXIT -> exit();
        }
    }

    private static void delete() {
        System.out.println("Delete");
    }

    private static void update() {
        System.out.println("Update");
    }

    private static void read() {
        System.out.println("Read");

        for (Person s : people) {
            System.out.println(s.inFo());
        }

    }

    private static void create() {
        Scanner addscaner;
        addscaner =  new Scanner(System.in);
        System.out.println("Create");
        System.out.println("Enter name : ");
        String name = addscaner.nextLine();
        System.out.println("Enter surname : ");
        String surname = addscaner.nextLine();
        System.out.println("Enter phone : ");
        String phone = addscaner.nextLine();
        System.out.println("Enter age : ");
        Integer age = addscaner.nextInt();
        Person person = new Person(name,surname,phone, age);
        people.add(person);
        System.out.println("Create");
    }

    private static void exit() {
        System.out.println("Exit");
        Exit = false;
    }
}

