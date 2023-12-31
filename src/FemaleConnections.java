import java.util.Objects;

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

    // TODO Добавить "дочь"
    enum FemaleTypeConnections {
        mother,
        wife,
        daughter
    }


    private FemaleTypeConnections type;

    private Person person;

    FemaleConnections (Person person,  FemaleTypeConnections typeConnections) {
        super();
        this.person = person;
        this.type = typeConnections;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FemaleConnections that)) return false;
        return getType() == that.getType() && Objects.equals(getPerson(), that.getPerson());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getType(), getPerson());
    }
}
