import java.util.*;

public class Person {

    private String name;
    private String surname;
    private String phone;
    private int age;


    public Person(String name, String surname, String phone, int age) {
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }


    @Override
    public String toString() {
        return this.name + "=" + this.surname + "=" + this.phone + "=" + this.age;
    }

    public String inFo() {
        return "|Имя: "+ this.name + " |Фамилия: " + this.surname + " |Номер телефона: " + this.phone + " |Полных лет: " + this.age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person person)) return false;
        return getAge() == person.getAge() && Objects.equals(getName(), person.getName()) && Objects.equals(getSurname(), person.getSurname()) && Objects.equals(getPhone(), person.getPhone());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getSurname(), getPhone(), getAge());
    }

    protected String getFullConnectionName(String baseTypeConnection, String differentTypeConnection) {
        return switch (differentTypeConnection) {
            case "father" -> fathersRelations(baseTypeConnection);

            case "husband" -> husbandRelations(baseTypeConnection);
            case "son" -> sonRelations(baseTypeConnection);
            case "mother" -> motherRelations(baseTypeConnection);
            case "wife" -> wifeRelations(baseTypeConnection);
            case "daughter" -> daughterRelations(baseTypeConnection);

            default -> throw new IllegalStateException("Unexpected value: " + differentTypeConnection);
        };
    }

    private String daughterRelations(String baseTypeConnection) {
        return switch (baseTypeConnection) {
            // Первый уровень
            case "" -> "дочь";
            // второй уровоень
            case "отец" -> "сестра";
            case "муж" -> "дочь";
            case "сын" -> "внучка";
            case "мать" -> "сестра";
            case "жена" -> "дочь";
            case "дочь" -> "внучка";

            // TODO третий уровень

            default -> "дочь";
        };
    }

    private String wifeRelations(String baseTypeConnection) {
        return switch (baseTypeConnection) {
            // Первый уровень
            case "" -> "жена";
            // второй уровоень
            case "отец" -> "мать";
            case "муж", "жена" -> ""; // не бывает
            case "сын" -> "невестка";
            case "мать" -> "жена";
            case "дочь" -> ""; // не бывает


            // TODO третий уровень

            default -> "жена";
        };
    }

    private String motherRelations(String baseTypeConnection) {
        return switch (baseTypeConnection) {
            // Первый уровень
            case "" -> "мать";
            // второй уровоень
            case "отец" -> "Бабушка";
            case "муж" -> "Свекровь";
            case "сын" -> "жена";
            case "мать" -> "бабушка";
            case "жена" -> "теща";
            case "дочь" -> "жена";

            // TODO третий уровень

            default -> "мать";
        };
    }

    private String sonRelations(String baseTypeConnection) {
        return switch (baseTypeConnection) {
            // Первый уровень
            case "" -> "сын";
            // второй уровоень
            case "отец" -> "брат";
            case "муж" -> "сын";
            case "сын" -> "внук";
            case "мать" -> "брат";
            case "жена" -> "сын";
            case "дочь" -> "внук";

            // TODO третий уровень

            default -> "сын";
        };
    }

    private String husbandRelations(String baseTypeConnection) {
        return switch (baseTypeConnection) {
            // Первый уровень
            case "" -> "муж";
            // второй уровоень
            case "отец" -> "";
            case "муж" -> "";
            case "сын" -> "";
            case "мать" -> "отец";
            case "жена" -> "";
            case "дочь" -> "зять";

            // TODO третий уровень

            default -> "муж";
        };
    }

    /**
     * проверено
     * @param baseTypeConnection
     * @return
     */
    private String fathersRelations(String baseTypeConnection) {
        return switch (baseTypeConnection) {
            // Первый уровень
            case "" -> "отец";
            // второй уровоень
            case "отец" -> "дедушка";
            case "муж" -> "свекр";
            case "сын" -> "";
            case "мать" -> "отец";
            case "жена" -> "тесть";
            case "дочь" -> "муж";

            // TODO третий уровень

            default -> "отец";
        };
    }
}

