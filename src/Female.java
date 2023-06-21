import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;

public class Female extends Person {
    final Gender gender = Gender.FEMALE;

    @Override
    public String inFo() {
        return super.inFo();
    }

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


    /**
     * Кем я являюсь другому человеку PERSON
     * @param person другой человек
     */
    public void addConnection(FemaleConnections.FemaleTypeConnections connection, Person person) throws Exception {  // TODO Добавить проверку на правильность добавления связи

        if (person instanceof  Male) {
            this.connections.put(person, connection);
            try {
                switch (connection) {
                    case mother -> {
                        if (((Male) person).getConnections().get(this) != MaleConnections.MaleTypeConnections.son) {
                            ((Male) person).addConnection(MaleConnections.MaleTypeConnections.son, this);
                        }
                    }
                    case wife -> {
                        if (((Male) person).getConnections().get(this) != MaleConnections.MaleTypeConnections.husband) {
                            ((Male) person).addConnection(MaleConnections.MaleTypeConnections.husband, this);
                        }
                    }
                    case daughter -> {
                        if (((Male) person).getConnections().get(this) != MaleConnections.MaleTypeConnections.father) {
                            ((Male) person).addConnection(MaleConnections.MaleTypeConnections.father, this);
                        }
                    }
                }
            } catch (Exception e) {
                System.out.println("Ошибка: " + e.getMessage());
                this.connections.remove(person);
                ((Male) person).getConnections().remove(this);
            }
        }
        if (person instanceof  Female) {

            switch (connection) {
                case mother -> {
                    this.connections.put(person, connection);
                    if(((Female) person).getConnections().get(this) != FemaleConnections.FemaleTypeConnections.daughter) {
                        ((Female) person).addConnection(FemaleConnections.FemaleTypeConnections.daughter, this);
                    }
                }
                case wife -> {
                    throw new Exception("У женщины не может быть жены");
                }
                case daughter -> {
                    this.connections.put(person, connection);
                    if(((Female) person).getConnections().get(this) != FemaleConnections.FemaleTypeConnections.mother) {
                        ((Female) person).addConnection(FemaleConnections.FemaleTypeConnections.mother, this);
                    }
                }
            }


        }

    }


    public HashMap<Person, FemaleConnections.FemaleTypeConnections> getConnections() {
        return connections;
    }

    public FemaleConnections.FemaleTypeConnections getRevertConnection(Person person) {
        return this.connections.get(person);
    }


}
