class Person{
    private String name;
    private String surname;
    private long phone;
    private String gender;
    private int age;

    public Person(String name, String surname, long phone, String gender, int age, String type) {
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.gender = gender;
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

    public long getPhone() {
        return phone;
    }

    public void setPhone(long phone) {
        this.phone = phone;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
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
        return this.name + "=" + this.surname + "=" + this.phone + "=" + this.gender + "=" + this.age + "=" + this.type;
    }
    public String inFo(){
        return this.name + " " + this.surname + " " + this.phone + " " + this.gender + " " + this.age + " " + this.type;}
}
public class Main {
    public static void main(String[] args) {}
}

