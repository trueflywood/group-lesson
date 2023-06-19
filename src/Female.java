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




    /**
     * Кем я являюсь другому человеку PERSON
     * @param person другой человек
     */
    public void addConnection(FemaleConnections.FemaleTypeConnections connection, Person person) throws Exception {  // TODO Добавить проверку на правильность добавления связи
        this.connections.put(person, connection);
        if (person instanceof  Male) {
            if (!((Male) person).getConnections().containsKey(this)) {
                try {
                    switch (connection) {
                        case mother -> {
                            ((Male) person).addConnection(MaleConnections.MaleTypeConnections.son, this);
                        }
                        case wife -> {
                            ((Male) person).addConnection(MaleConnections.MaleTypeConnections.husband, this);
                        }
                        case daughter -> {
                            ((Male) person).addConnection(MaleConnections.MaleTypeConnections.father, this);
                        }
                    }
                } catch (Exception e) {
                    System.out.println("Ошибка: " + e.getMessage());
                }

            }
        }
        if (person instanceof  Female) {
            if (!((Female) person).getConnections().containsKey(this)) {
                System.out.println("test 2 female");

                switch (connection) {
                    case mother -> {
                        ((Female) person).addConnection(FemaleConnections.FemaleTypeConnections.daughter, this);
                    }
                    case wife -> {
                        throw new Exception("У женщины не может быть жены");
                    }
                    case daughter -> {
                        ((Female) person).addConnection(FemaleConnections.FemaleTypeConnections.mother, this);
                    }
                }
            }
        }

    }


    public HashMap<Person, FemaleConnections.FemaleTypeConnections> getConnections() {
        return connections;
    }


}
