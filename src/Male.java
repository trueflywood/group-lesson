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
        return super.toString()+  "=" + gender + "=" + this.connections.size();
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


        if (person instanceof  Male) {

            switch (connection) {
                case father -> {
                    this.connections.put(person, connection);
                    if (((Male) person).getConnections().get(this) != MaleConnections.MaleTypeConnections.son) {
                        ((Male) person).addConnection(MaleConnections.MaleTypeConnections.son, this);
                    }
                }
                case husband -> {
                    throw new Exception("У мужчины не может быть мужа");
                }
                case son -> {
                    this.connections.put(person, connection);
                    if (((Male) person).getConnections().get(this) != MaleConnections.MaleTypeConnections.father) {
                        ((Male) person).addConnection(MaleConnections.MaleTypeConnections.father, this);
                    }
                }
            }
        }
        if (person instanceof  Female) {
            this.connections.put(person, connection);
            try {
                switch (connection) {
                    case father -> {
                        if(((Female) person).getConnections().get(this) != FemaleConnections.FemaleTypeConnections.daughter) {
                            ((Female) person).addConnection(FemaleConnections.FemaleTypeConnections.daughter, this);
                        }
                    }
                    case husband -> {
                        if(((Female) person).getConnections().get(this) != FemaleConnections.FemaleTypeConnections.wife) {
                            ((Female) person).addConnection(FemaleConnections.FemaleTypeConnections.wife, this);
                        }
                    }
                    case son -> {
                        if(((Female) person).getConnections().get(this) != FemaleConnections.FemaleTypeConnections.mother) {
                            ((Female) person).addConnection(FemaleConnections.FemaleTypeConnections.mother, this);
                        }
                    }
                }
            } catch (Exception e) {
                System.out.println("Ошибка: " + e.getMessage());
                this.connections.remove(person);
                ((Female) person).getConnections().remove(this);

            }
        }
    }

    public HashMap<Person, String> getKindredByKinship(Integer level, String baseTypeConnection) {

        HashMap<Person, String> kindredList = new HashMap<Person, String>();

        for (Map.Entry<Person, MaleConnections.MaleTypeConnections> connectionSet:
                this.connections.entrySet()) {
            Person person = connectionSet.getKey();
            if(person instanceof Male) {
                MaleConnections.MaleTypeConnections connectionType = ((Male) person).getRevertConnection(this);
                String relation = connectionType.toString();
                // Добавить вычисление связи в зависимости от базовой
                kindredList.put(person, relation);
            }

            if(person instanceof Female) {
                FemaleConnections.FemaleTypeConnections connectionType = ((Female) person).getRevertConnection(this);
                String relation = connectionType.toString();
                // Добавить вычисление связи в зависимости от базовой
                kindredList.put(person, relation);
            }
            
        }
        return kindredList;
    }

    public MaleConnections.MaleTypeConnections getRevertConnection(Person person) {
        return this.connections.get(person);
    }
}


