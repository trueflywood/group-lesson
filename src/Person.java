import java.util.HashSet;
import java.util.List;
import java.util.Objects;

public abstract class Person {

    /**
     * идентификатор человека
     */
    static Integer id;

    private String name;
    private String surname;
    private String phone;

    private int age;

    private HashSet<Person>  relatives =  new HashSet<Person>();

    public Person(String name, String surname, String phone, int age, String type) {
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.age = age;
        this.type = type;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    private String type;

    @Override
    public String toString() {
        return this.name + "=" + this.surname + "=" + this.phone + "=" + this.age + "=" + this.type;
    }

    public String inFo() {
        return this.name + " " + this.surname + " " + this.phone + " " + this.age + " " + this.type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person person)) return false;
        return getAge() == person.getAge() && Objects.equals(getName(), person.getName()) && Objects.equals(getSurname(), person.getSurname()) && Objects.equals(getPhone(), person.getPhone()) && Objects.equals(getType(), person.getType());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getSurname(), getPhone(), getAge(), getType());
    }

    public  void addConnection(Connections connection, Person person) {

    };

    public void addConnection(FemaleConnections.FemaleTypeConnections connection, Person person) {

    };

    public void addConnection(MaleConnections.MaleTypeConnections connection, Person person) {

    };

    public HashSet<Person> getRelatives() {
        return relatives;
    }


    public void addRelative(Person person) {
        this.relatives.add(person);
    }
}

