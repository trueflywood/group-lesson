import java.util.*;

public class Male extends Person {

    final Gender gender = Gender.MALE;

    @Override
    public String inFo() {
        return super.inFo();
    }

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
                String differentTypeConnection = connectionType.toString();

                // Добавить вычисление связи в зависимости от базовой
                String fullConnectionName = getFullConnectionName(baseTypeConnection, differentTypeConnection);

                if(!kindredList.containsKey(person)) {
                    kindredList.put(person, fullConnectionName);
                }
            }

            if(person instanceof Female) {
                FemaleConnections.FemaleTypeConnections connectionType = ((Female) person).getRevertConnection(this);
                String differentTypeConnection = connectionType.toString();

                // Добавить вычисление связи в зависимости от базовой
                String fullConnectionName = getFullConnectionName(baseTypeConnection, differentTypeConnection);

                if(!kindredList.containsKey(person)) {
                    kindredList.put(person, fullConnectionName);
                }

            }
        }

        HashMap<Person, String> subList = new HashMap<Person, String>();
        if(level - 1 > 0) {
            System.out.println("второй уровень");
            for (Map.Entry<Person, String> kindred:
                    kindredList.entrySet()) {
                if(kindred.getKey() instanceof  Male){

                    HashMap<Person, String> subKindredList = ((Male) kindred.getKey()).getKindredByKinship(level - 1, kindred.getValue());


                    for (Map.Entry<Person, String> relationSet:
                            subKindredList.entrySet()) {
                        subList.put(relationSet.getKey(),relationSet.getValue());
                    }
                }

                // TODO разобраться
//                if(kindred.getKey() instanceof  Female){
//                    HashMap<Person, String> subKindredList = ((Female) kindred.getKey()).getKindredByKinship(level - 1, kindred.getValue());
//
//                    for (Map.Entry<Person, String> relationSet:
//                            subKindredList.entrySet()) {
//
//                        System.out.println( relationSet.getKey().getName() + " - " + relationSet.getValue());
//
//                        subList.put(relationSet.getKey(),relationSet.getValue());
//                    }
//                }

            }
        }
        for (Map.Entry<Person, String> relationSet:
                subList.entrySet()) {

            System.out.println( relationSet.getKey().getName() + " - " + relationSet.getValue());
            kindredList.put(relationSet.getKey(),relationSet.getValue());
        }

        return kindredList;
    }

    protected String getFullConnectionName(String baseTypeConnection, String differentTypeConnection) {
        return super.getFullConnectionName(baseTypeConnection, differentTypeConnection);
    }



    public MaleConnections.MaleTypeConnections getRevertConnection(Person person) {
        return this.connections.get(person);
    }
}


