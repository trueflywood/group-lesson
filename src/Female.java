import java.util.HashSet;
import java.util.List;
import java.util.Objects;

public class Female extends Person {
    final Gender gender = Gender.FEMALE;

    private List<FemaleConnections.FemaleTypeConnections> connections;

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



    @Override
    public void addConnection(FemaleConnections.FemaleTypeConnections connection, Person person) {
        this.connections.add(connection);
        person.addRelative(this);
    }

    @Override
    public HashSet<Person> getRelatives() {
        return super.getRelatives();
    }

}
