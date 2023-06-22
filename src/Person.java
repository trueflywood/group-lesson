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
        return this.name + " " + this.surname + " " + this.phone + " " + this.age;
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
            case "отец" -> "муж";
            case "муж" -> "зять";
            case "сын" -> "внук";
            case "мать" -> "жена";
            case "жена" -> ""; // не бывает
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
            case "отец" -> "тесть";
            case "муж", "жена" -> ""; // не бывает
            case "сын" -> "сын";
            case "мать" -> "тёща";
            case "дочь" -> "дочь";

            // TODO третий уровень

            default -> "жена";
        };
    }

    private String motherRelations(String baseTypeConnection) {
        return switch (baseTypeConnection) {
            // Первый уровень
            case "" -> "мать";
            // второй уровоень
            case "отец" -> "дедушка";
            case "муж" -> "отец";
            case "сын" -> "брат";
            case "мать" -> "бабушка";
            case "жена" -> ""; // не бывает
            case "дочь" -> "сестра";

            // TODO третий уровень

            default -> "мать";
        };
    }

    private String sonRelations(String baseTypeConnection) {
        return switch (baseTypeConnection) {
            // Первый уровень
            case "" -> "сын";
            // второй уровоень
            case "отец" -> "муж";
            case "муж" -> ""; // ERROR
            case "сын" -> "внук";
            case "мать" -> "жена";
            case "жена" -> "невестка"; // не бывает
            case "дочь" -> "внучка";

            // TODO третий уровень

            default -> "сын";
        };
    }

    private String husbandRelations(String baseTypeConnection) {
        return switch (baseTypeConnection) {
            // Первый уровень
            case "" -> "муж";
            // второй уровоень
            case "отец" -> "Свёкор";
            case "муж" -> ""; // ERROR
            case "сын" -> "сын";
            case "мать" -> "свекровь";
            case "жена" -> "жена";
            case "дочь" -> "дочь";

            // TODO третий уровень

            default -> "муж";
        };
    }

    private String fathersRelations(String baseTypeConnection) {
        return switch (baseTypeConnection) {
            // Первый уровень
            case "" -> "отец";
            // второй уровоень
            case "отец" -> "дедушка";
            case "муж" -> ""; // ERROR
            case "сын" -> "брат";
            case "мать" -> "бабушка";
            case "жена" -> "мать";
            case "дочь" -> "сестра";

            // TODO третий уровень

            default -> "отец";
        };
    }
}

