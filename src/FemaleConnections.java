
public class FemaleConnections extends Connections {

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public FemaleTypeConnections getType() {
        return type;
    }

    public void setType(FemaleTypeConnections type) {
        this.type = type;
    }

    enum FemaleTypeConnections {
        mother,
        daughter,
        wife
    }


    private FemaleTypeConnections type;

    private Person person;

    FemaleConnections (Person person,  FemaleTypeConnections typeConnections) {
        super();
        this.person = person;
        this.type = typeConnections;
    }


    //  TODO РЕАЛИЗОВАТЬ
    @Override
    public String toString() {
        return super.toString();
    }

    // TODO реализовать
    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
