import java.util.*;

public class Male extends Person {

    final Gender gender = Gender.MALE;

    private HashMap<Person, MaleConnections.MaleTypeConnections> connections = new HashMap<>();
    public Male(String name, String surname, String phone, int age) {
        super(name, surname, phone, age);
    }


    public HashMap<Person, MaleConnections.MaleTypeConnections> getConnections() {
        return connections;
    }



   @Override
    public String toString() {
        return super.toString()+  "=" + gender + "=" + this.connections;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Male male)) return false;
        if (!super.equals(o)) return false;
        return gender == male.gender;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), gender);
    }

    /**
     * Кем я являюсь другому человеку PERSON
     * @param person другой человек
     */

    public void addConnection(MaleConnections.MaleTypeConnections connection, Person person) {

         this.connections.put(person, connection);
        if (person instanceof  Male) {
            if (!((Male) person).getConnections().containsKey(this)) {
                System.out.println("test 1 male");
                ((Male) person).addConnection(connection, this);
            }
        }
        if (person instanceof  Female) {
            if (!((Female) person).getConnections().containsKey(this)) {
                System.out.println("test 2 male");
                ((Female) person).addConnection(FemaleConnections.FemaleTypeConnections.wife, this);
            }
        }
    }

}
