import java.util.*;

public class Main {
    private static int step;
    MainMenu mainMenu;

    static HashSet<Person> people = new HashSet<Person>();
    public static void main(String[] args) {

        people.add(new Male("Такеши", "Китано","88005553535",76));
        people.add(new Male("Алексей", "Абрамович","3743437863478",63));
        people.add(new Female("Аяко", "Саичиро","478546567878",32));
        people.add(new Female("Милана", "Янковки","56474637656",35));

        // Пример создания людей
        Male testPerson1 = new Male("Алекс", "Тестович", "4535345345", 45);
        Person testPerson2 = new Female("Алекса", "Тестовая", "453532432445", 45);
        Female testPerson3 = new Female("Екатерина", "Чужая", "453532432445", 22);
        Male testPerson4 = new Male("Сергий", "Висарионович", "453532432445", 63);
        Male testPerson5 = new Male("Юрий", "Сергеевич", "453532432445", 3);
        Male testPerson6 = new Male("василий", "Сергеевич", "453532432445", 3);

        people.add(testPerson1);
        people.add(testPerson2);

        // Пример поиска людей в списке
        Person pp = people.stream()
                .filter(object -> object.getName().equals("Алекс"))
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
            // устанавливаем вторую связь отец жены
            testPerson5.addConnection(MaleConnections.MaleTypeConnections.father, testPerson2);


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

        // Полученеи родственников первого уровня (Пока только первого и для мужчин)
        // TODO
        System.out.println();
        HashMap<Person, String> list = testPerson1.getKindredByKinship(2, "");
        list.remove(testPerson1);

        System.out.println("родственники человека " + testPerson1.getName());
        for (Map.Entry<Person, String> relationSet:
                list.entrySet()) {
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

        try {
            MainMenu.ActionCodes mainChose = menu.showMainMenu();
            selectAction(mainChose);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
    public static void selectAction(MainMenu.ActionCodes step1){
        switch (step1){
            case CREATE -> create();
            case READ -> read();
            case UPDATE -> update();
            case DELETE -> delete();
            case SEARCH -> search();
            case EXIT -> exit();
        }
    }

    private static void search() {
        Scanner scanner =new Scanner(System.in);
        System.out.println("Введите имя контакта: ");
        String name = scanner.nextLine();
        Person person = people.stream()
                .filter(object -> object.getName().equals(name))
                .findFirst().get();
        System.out.println(person.inFo());
    }

    private static void delete() {
        System.out.println("Delete");
    }

    private static void update() {
        System.out.println("Update");
    }

    private static void read() {
        System.out.println("Read");
        people.forEach( person -> {
            System.out.println(person.inFo());
        });
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
    }
}

