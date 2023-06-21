import java.util.Scanner;

public class MainMenu {

    enum ActionCodes {

        CREATE(1, "Создать"),
        READ(2, "Прочитать"),
        UPDATE(3, "Изменить имя"),
        DELETE(4, "Удалить"),
        EXIT(5, "ВЫХОД");

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
