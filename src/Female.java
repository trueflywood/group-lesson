import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;

public class Female extends Person {
    final Gender gender = Gender.FEMALE;

    private HashMap<Person, FemaleConnections.FemaleTypeConnections> connections = new HashMap<>();

    public Female(String name, String surname, String phone, int age) {
        super(name, surname, phone, age);
    }

    @Override
    public String toString() {
        return super.toString()+  "=" + gender + "=" + this.connections.size();
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




    public void addConnection(FemaleConnections.FemaleTypeConnections connection, Person person) {
        System.out.println("++++++++++++++");
        this.connections.put(person, connection);
        System.out.println(this);
        if (person instanceof  Male) {
            System.out.println("++++++++++++++");
            if (!((Male) person).getConnections().containsKey(this)) {
                System.out.println("test 1 female");
                ((Male) person).addConnection(MaleConnections.MaleTypeConnections.father, this);
            }
        }
        if (person instanceof  Female) {
            if (!((Female) person).getConnections().containsKey(this)) {
                System.out.println("test 2 female");
                ((Female) person).addConnection(connection, this);
            }
        }



    }


    public HashMap<Person, FemaleConnections.FemaleTypeConnections> getConnections() {
        return connections;
    }


}
