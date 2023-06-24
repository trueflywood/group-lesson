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
        return "\n|Имя: "+ this.name + "\n|Фамилия: " + this.surname + "\n|Номер телефона: " + this.phone + "\n|Полных лет: " + this.age;
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

            // третий уровень
            case "бабушка" -> "тётя";
            case "внучка" -> "правнучка";
            case "сестра" -> "племянница";
            case "невестка"-> "внучка";
            case "свекровь" -> "золовка";
            case "теща" -> "своячница";
            case "брат" -> "племянница";
            case "внук" -> "правнучка";
            case "зять" -> "внучка";
            case "дедушка" -> "тётя";
            case "свекр" -> "золовка";
            case "тесть" -> "своячница";

            default -> "дальний родственник";
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


            // третий уровень
            case "бабушка" -> "";
            case "внучка" -> "";
            case "сестра" -> "";
            case "невестка"-> "";
            case "свекровь" -> "";
            case "теща" -> "";
            case "брат" -> "невестка";
            case "внук" -> "невестка";
            case "зять" -> "дочь";
            case "дедушка" -> "бабушка";
            case "свекр" -> "Свекровь";
            case "тесть" -> "теща";

            default -> "дальний родственник";
        };
    }

    private String motherRelations(String baseTypeConnection) {
        return switch (baseTypeConnection) {
            // Первый уровень
            case "" -> "мать";
            // второй уровоень
            case "отец" -> "бабушка";
            case "муж" -> "свекровь";
            case "сын" -> "жена";
            case "мать" -> "бабушка";
            case "жена" -> "теща";
            case "дочь" -> "жена";

            // TODO третий уровень
            case "бабушка" -> "прабабушка";
            case "дедушка" -> "прабабушка";
            // case "внучка" -> "невестка"; // неоднозначность
            // case "внук" -> "дочь"; // не одно значность
            case "сестра" -> "мать";
            case "брат" -> "мать";
            case "невестка"-> "сваха";
            case "зять" -> "сваха";
            case "свекровь" -> "дальний родственник";
            case "свекр" -> "дальний родственник";
            case "теща" -> "дальний родственник";
            case "тесть" -> "дальний родственник";

            default -> "дальний родственник";
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

            // третий уровень
            case "бабушка" -> "дядя";
            case "дедушка" -> "дядя";
            case "внучка" -> "правнук"; // неоднозначность
            case "внук" -> "правнук"; // не одно значность
            case "сестра" -> "племмяник";
            case "брат" -> "племянник";
            case "невестка"-> "внук";
            case "зять" -> "внук";
            case "свекровь" -> "деверь";
            case "свекр" -> "деверь";
            case "теща" -> "шурин";
            case "тесть" -> "шурин";

            default -> "дальний родственник";
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
            case "бабушка" -> "дедушка";
            case "дедушка" -> "";
            case "внучка" -> "дальний родственник";
            case "внук" -> "";
            case "сестра" -> "зять";
            case "брат" -> "";
            case "невестка"-> "сын";
            case "зять" -> "";
            case "свекровь" -> "свекр";
            case "свекр" -> "";
            case "теща" -> "тесть";
            case "тесть" -> "";

            default -> "дальний родственник";
        };
    }


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

            //  третий уровень
            case "бабушка" -> "прадедушка";
            case "дедушка" -> "прадедушка";
            // case "внучка" -> "";
            // case "внук" -> "";
            case "сестра" -> "отец";
            case "брат" -> "отец";

            case "невестка"-> "сват";
            case "зять" -> "сват";
            case "свекровь" -> "дальний родственник";
            case "свекр" -> "дальний родственник";
            case "теща" -> "дальний родственник";
            case "тесть" -> "дальний родственник";

            default -> "дальний родственник";
        };
    }
}

