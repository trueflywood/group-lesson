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
        Male testPerson1 = new Male("Алекс", "Тестович", "4535345345", 45); // муж
        Female testPerson2 = new Female("Алекса", "Тестовая", "453532432445", 45); // жена
        Female testPerson3 = new Female("Екатерина", "Чужая", "453532432445", 22); // дочь
        Male testPerson4 = new Male("Сергий", "Висарионович", "453532432445", 63); // сын
        Male testPerson5 = new Male("Юрий", "Сергеевич", "453532432445", 3); // внук
        Male testPerson6 = new Male("василий", "Сергеевич", "453532432445", 87); // тесть
        Female testPerson7 = new Female("василиса", "Игоревна", "453532432445", 98); // теща
        Person testPerson8 = new Female("Светлана", "Егоровна", "453532432445", 98); // мать
        Person testPerson9 = new Male("Егор", "Александрович", "453532432445", 98); // отец

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

            // устанавливаем вторую связь отец жены = тесть
            testPerson6.addConnection(MaleConnections.MaleTypeConnections.father, testPerson2);
            testPerson7.addConnection(FemaleConnections.FemaleTypeConnections.mother, testPerson2);
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

        HashMap<Person, String> list2 = testPerson2.getKindredByKinship(2, "");
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
            case EXIT -> exit();
        }
    }

    private static void delete() {
        System.out.println("Удалить контакт");
        Scanner scanner =new Scanner(System.in);
        System.out.println("Введите имя контакта: ");
        String name = scanner.nextLine();
        System.out.println("Введите мобильный номер: ");
        String number = scanner.nextLine();
        Person person = people.stream()
                .filter(object -> object.getName().equals(name)&&object.getPhone().equals(number))
                .findAny().orElse(null);
        System.out.println(person.inFo());
        System.out.println("Чтобы удалить контакт нажмите 1 " +" ; " + " Отменить удаление нажмите 2");
        Scanner scanner1 = new Scanner(System.in);
        Integer selector = scanner1.nextInt();
        switch (selector){
            case 1 : people.remove(person);
                System.out.println("Контакт удален");
                read();
                break;
            case 2 : System.out.println("Отмена удаления");
                read();
                break;
            default:
                break;
        }
    }

    private static void update() {
        System.out.println("Редактировать контакт");
        Scanner scanner =new Scanner(System.in);
        System.out.println("Введите имя контакта: ");
        String name = scanner.nextLine();
        System.out.println("Введите мобильный номер: ");
        String number = scanner.nextLine();
        Person person = people.stream()
                .filter(object -> object.getName().equals(name)&&object.getPhone().equals(number))
                .findAny().orElse(null);
        System.out.println(person.inFo());
        System.out.println("Что вы хотите изменить?");
        System.out.println("""
                                1 - Имя
                                2 - Фамилию
                                3 - Номер телефона
                                4 - Возраст
                                """);
        scanner = new Scanner(System.in);
        Scanner scanner1 = new Scanner(System.in);
        int change = scanner1.nextInt();
        switch (change){
            case 1:
                System.out.println("Обновите имя : " + person.getName());
                String changeName = scanner.nextLine();
                person.setName(changeName);
                System.out.println("Обновлено");
                read();
                break;
            case 2:
                System.out.println("Обновите фамилию : " + person.getSurname());
                String changeSurname = scanner.nextLine();
                person.setSurname(changeSurname);
                System.out.println("Обновлено");
                read();
                break;
            case 3:
                System.out.println("Обновите номер телефона : " + person.getPhone());
                String changePhone = scanner.nextLine();
                person.setPhone(changePhone);
                System.out.println("Обновлено");
                read();
                break;
            case 4:
                System.out.println("Обновите возраст : " + person.getAge());
                Integer changeAge = scanner.nextInt();
                person.setAge(changeAge);
                System.out.println("Обновлено");
                read();
                break;
            default:
                break;
        }
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

