import java.util.Objects;

public class MaleConnections extends Connections {
    public MaleTypeConnections getType() {
        return type;
    }

    public void setType(MaleTypeConnections type) {
        this.type = type;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    enum MaleTypeConnections {
        father,
        husband,
    }
    private MaleTypeConnections type;

    private Person person;

    MaleConnections (Person person,  MaleTypeConnections typeConnections) {
        super();
        this.person = person;
        this.type = typeConnections;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MaleConnections that)) return false;
        return getType() == that.getType() && Objects.equals(getPerson(), that.getPerson());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getType(), getPerson());
    }

    @Override
    public String toString() {
        return "MaleConnections{" +
                "type=" + type +
                ", person=" + person +
                "} " + super.toString();
    }
}
