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
        son,
        husband,
    }
    private MaleTypeConnections type;

    private Person person;

    MaleConnections (Person person,  MaleTypeConnections typeConnections) {
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
