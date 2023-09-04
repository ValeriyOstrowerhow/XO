import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Меню команд для игры Крестики Нолики
 */
public enum MenuCommand {
  /**
   * Определение элементов меню
   */
  READ(1, "Правила и история игры"),
  START(2, "Старт игры"),
  EXIT(3, "Выйти из игры"),
  UNEXPECTED(0, "");

  /**
   * Номер пункта меню
   */
  public final int num;
  final String string;

  /**
   * Конструктор для инициализации элемента меню
   */
  MenuCommand(int num, String string) {
    this.num = num;
    this.string = string;
  }

  /**
   * Метод для вывода главного меню игры
   */
  public static void menu() {
    System.out.println();
    System.out.println(" ═-═-═-═-═-═ Добро пожаловать в Игру Крестики Нолики ═-═-═-═-═-═ ");
    System.out.println(" ===================  〚 МЕНЮ 〛 ===================\n");

    for (MenuCommand command : values()) {
      if (command != UNEXPECTED) {
        System.out.println(command.num + ". " + command.string);
      }
    }
  }

  /**
   * Метод для обработки выбора пункта меню
   */
  public static MenuCommand commandList() throws FileNotFoundException {

    Scanner scanner = new Scanner(System.in);
    boolean isRun = true;
    MenuCommand selectedCommand = null;
    while (isRun) {
      menu();
      System.out.print("Введите номер пункта меню: ");
      if (scanner.hasNextInt()) {
        int command = scanner.nextInt();
        System.out.println(
            "------------------------------------------------------------------------------------------------------------------");

        switch (command) {
          case 1:
            System.out.println("Вы выбрали: " + READ.string);
            System.out.println("------------------------------------------------------");
            File gofile = new File("res/Conditions.txt");
            Scanner scannerFile = new Scanner(gofile);

            while (scannerFile.hasNextLine()) {
              String line = scannerFile.nextLine();
              System.out.println(line);
            }
            scannerFile.close();
            selectedCommand = READ;
            break;

          case 2:
            System.out.println("Вы выбрали: " + START.string);
            System.out.println("------------------------------------------------------");
            GameXO.main();
            selectedCommand = START;
            return selectedCommand;

          case 3:
            System.out.println("Вы выбрали: " + EXIT.string);
            System.out.println("------------------------------------------------------");
            System.out.println("До скорой встречи");
            selectedCommand = EXIT;
            isRun = false;
            break;
/**
 * Если введенная команда не соответствует ни одному из случаев, выбирается команда UNEXPECTED.
 */
          default:
            selectedCommand = UNEXPECTED;
            break;

        }
/**
 * Если ввод пользователя не является целым числом, программа выводит сообщение об ошибке.
 */
      } else {
        System.err.println("Некорректный выбор. Введите корректное значение.\n");
        scanner.nextLine();
      }
    }
    return selectedCommand;
  }
}