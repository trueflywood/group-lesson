class Person{
    private String name;
    private String surname;
    private String phone;
    private String gender;
    private int age;

    public Person(String name, String surname, String phone, String gender, int age, String type) {
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
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
    public static void main(String[] args) {
        
    }
}

