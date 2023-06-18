import java.util.List;
import java.util.Objects;

public class Male extends Person {

    final Gender gender = Gender.MALE;

    private List<MaleConnections> connections;
    public Male(String name, String surname, String phone, int age, String type) {
        super(name, surname, phone, age, type);
    }

    public List<MaleConnections> getConnections() {
        return connections;
    }

    public void setConnections(List<MaleConnections> connections) {
        this.connections = connections;
    }

   @Override
    public String toString() {
        return super.toString()+  "=" + gender;
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
    public void addRelative(Person person) {


    }


}
