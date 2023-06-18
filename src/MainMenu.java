import java.util.Scanner;

public class MainMenu {

    enum ActionCodes {
        EXIT(0, "ВЫХОД"),
        CREATE(1, "Создать"),
        DELETE(2, "Удалить"),
        READ(3, "Прочитать"),
        UPDATE(4, "Изменить имя");

        ActionCodes(int codeAction, String text) {
            this.code = codeAction;
            this.text = text;
        }

        public static ActionCodes byOrdinal(int ord) throws Exception {
            for (ActionCodes m : ActionCodes.values()) {
                if (m.code == ord) {
                    return m;
                }
            }
            throw new Exception("Не правильный код выбора");
        }

        private final int code;
        private final String text;

    }

    public ActionCodes showMainMenu() throws Exception {
        ActionCodes selectCodeAction;

        do {
            System.out.println("Выберите действие:");
            for (ActionCodes m : ActionCodes.values()) {
                System.out.println("    " + m.code + " - " + m.text);
            }

            try {
                Scanner scanner = new Scanner(System.in);
                Integer select = scanner.nextInt();
                selectCodeAction = ActionCodes.byOrdinal(select);
                return selectCodeAction;
            } catch (Exception e) {
                System.out.println("Не правильный код выбора");
                throw new Exception("Не правильный код выбора");
            }
        } while (true);
    }

}
