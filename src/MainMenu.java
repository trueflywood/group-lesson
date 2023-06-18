import java.util.Scanner;

public class MainMenu {

    enum ActionСodes {
        EXIT(0, "ВЫХОД"),
        CREATE(1, "Создать"),
        DELETE(2, "Удалить"),
        READ(3, "Прочитать"),
        UPDATE(4, "Изменить имя");

        ActionСodes(int codeAction, String text) {
            this.code = codeAction;
            this.text = text;
        }

        public static ActionСodes byOrdinal(int ord) throws Exception {
            for (ActionСodes m : ActionСodes.values()) {
                if (m.code == ord) {
                    return m;
                }
            }
            throw new Exception("Не правильный код выбора");
        }

        private final int code;
        private final String text;

    }

    public ActionСodes showMainMenu() throws Exception {
        ActionСodes selectCodeAction;

        do {
            System.out.println("Выберите действие:");
            for (ActionСodes m : ActionСodes.values()) {
                System.out.println("    " + m.code + " - " + m.text);
            }

            try {
                Scanner scanner = new Scanner(System.in);
                Integer select = scanner.nextInt();
                selectCodeAction = ActionСodes.byOrdinal(select);
                return selectCodeAction;
            } catch (Exception e) {
                System.out.println("Не правильный код выбора");
                throw new Exception("Не правильный код выбора");
            }
        } while (true);
    }

}
