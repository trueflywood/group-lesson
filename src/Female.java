import java.util.*;

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



    public HashMap<Person, String> getKindredByKinship(Integer level, String baseTypeConnection) {

        HashMap<Person, String> kindredList = new HashMap<Person, String>();

        for (Map.Entry<Person, FemaleConnections.FemaleTypeConnections> connectionSet:
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


            for (Map.Entry<Person, String> kindred:
                    kindredList.entrySet()) {
                if(!kindred.getValue().equals("дальний родственник")) {
                    if(kindred.getKey() instanceof  Male ){

                        HashMap<Person, String> subKindredList = ((Male) kindred.getKey()).getKindredByKinship(level - 1, kindred.getValue());

                        for (Map.Entry<Person, String> relationSet:
                                subKindredList.entrySet()) {
                            if (!subList.containsKey(relationSet.getKey())) {
                                subList.put(relationSet.getKey(),relationSet.getValue());
                            }
                        }

                    }

                    if(kindred.getKey() instanceof  Female){

                        HashMap<Person, String> subKindredList = ((Female) kindred.getKey()).getKindredByKinship(level - 1, kindred.getValue());

                        for (Map.Entry<Person, String> relationSet:
                                subKindredList.entrySet()) {
                            if(!subList.containsKey(relationSet.getKey())) {
                                subList.put(relationSet.getKey(),relationSet.getValue());
                            }
                        }
                    }
                }
            }
        }

        for (Map.Entry<Person, String> relationSet:
                subList.entrySet()) {
            if(!kindredList.containsKey(relationSet.getKey())) {
                kindredList.put(relationSet.getKey(),relationSet.getValue());
            }
        }
        return kindredList;
    }

    protected String getFullConnectionName(String baseTypeConnection, String differentTypeConnection) {
        return super.getFullConnectionName(baseTypeConnection, differentTypeConnection);
    }

    public FemaleConnections.FemaleTypeConnections getRevertConnection(Person person) {
        return this.connections.get(person);
    }
}
