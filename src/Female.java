import java.util.Objects;

public class Female extends Person {
    final Gender gender = Gender.FEMALE;

    public Female(String name, String surname, String phone, int age, String type) {
        super(name, surname, phone, age, type);
    }

    @Override
    public String toString() {
        return super.toString()+  "=" + gender;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Female female)) return false;
        if (!super.equals(o)) return false;
        return gender == female.gender;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), gender);
    }
}
