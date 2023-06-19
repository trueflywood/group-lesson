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
    public void addConnection(MaleConnections.MaleTypeConnections connection, Person person) throws Exception {   // TODO Добавить проверку на правильность добавления связи

         this.connections.put(person, connection);
        if (person instanceof  Male) {
            if (!((Male) person).getConnections().containsKey(this)) {
                System.out.println("test 1 male");
                switch (connection) {
                    case father -> {
                        ((Male) person).addConnection(MaleConnections.MaleTypeConnections.son, this);
                    }
                    case husband -> {
                        throw new Exception("У мужчины не может быть мужа");
                    }
                    case son -> {
                        ((Male) person).addConnection(MaleConnections.MaleTypeConnections.father, this);
                    }
                }

            }
        }
        if (person instanceof  Female) {
            if (!((Female) person).getConnections().containsKey(this)) {

                try {
                    switch (connection) {
                        case father -> {
                            ((Female) person).addConnection(FemaleConnections.FemaleTypeConnections.daughter, this);
                        }
                        case husband -> {
                            ((Female) person).addConnection(FemaleConnections.FemaleTypeConnections.wife, this);
                        }
                        case son -> {
                            ((Female) person).addConnection(FemaleConnections.FemaleTypeConnections.mother, this);
                        }
                    }
                } catch (Exception e) {
                    System.out.println("Ошибка: " + e.getMessage());
                }
            }
        }
    }

}
