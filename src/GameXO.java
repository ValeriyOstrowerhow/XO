import java.util.Scanner;
public class GameXO {

  private static final byte fieldSize = 3;

  private static String[] field = new String[fieldSize * fieldSize];

  private static byte bPlayerNum = 0;

  public static void main(String[] args) {
    int iTmp = 0;
    Scanner scanner = new Scanner(System.in);
    System.out.println("            [ ИГРА ]");
    System.out.println("======= КРЕСТИКИ / НОЛИКИ =======");

    for (int i = 0; i < fieldSize * fieldSize; i++)
      field[i] = Integer.toString(++iTmp);

    while (!isGameEnd()) {
      nextPlayer();
      while (true) {
        System.out.println("\nХод игрока " + bPlayerNum);
        showPole();
        System.out.print(
            "Наберите число, куда вы хотите вставить " + (1 == bPlayerNum ? "крестик" : "нолик")
                + ": ");
        if (scanner.hasNextInt()) {
          iTmp = scanner.nextInt() - 1;
          if (isValidInput(iTmp))
            break;
        }
        System.out.println("Введенное число уже было использовано. Введите новое число");
        scanner.next();
      }
      try {
        putX(iTmp);
      } catch (Exception e) {
        System.out.println("Что-то пошло не так ;(");
      }
      showPole();
    }
  }

  /**
   * Проверяем корректность ввода. Введенное число должно быть по размеру поля и поле должно быть в
   * этом месте еще не заполнено
   */
  private static boolean isValidInput(int iIn) {
    if (iIn >= fieldSize * fieldSize)
      return false;
    if (iIn < 0)
      return false;
    switch (getX(iIn)) {
      case 'O':
      case 'X':
        return false;
    }
    return true;
  }

  /**
   * Функция задает номер следующего игрока
   */
  private static void nextPlayer() {
    bPlayerNum = (byte) (1 == bPlayerNum ? 2 : 1);
  }

  /**
   * Определяем, наступил конец игры или нет Условия: 1) Победили крестики 2) Победили нолики 3)
   * Кончились ходы
   */
  private static boolean isGameEnd() {
    int i, j;
    boolean bRowWin = false, bColWin = false;

    for (i = 0; i < fieldSize; i++) {
      bRowWin = true;
      bColWin = true;
      for (j = 0; j < fieldSize - 1; j++) {
        bRowWin &= (getXY(i, j).charAt(0) == getXY(i, j + 1).charAt(0));
        bColWin &= (getXY(j, i).charAt(0) == getXY(j + 1, i).charAt(0));
      }
      if (bColWin || bRowWin) {
        System.out.println("Игра окончена. Победил игрок " + bPlayerNum);
        return true;
      }
    }

    bRowWin = true;
    bColWin = true;
    for (i = 0; i < fieldSize - 1; i++) {
      bRowWin &= (getXY(i, i).charAt(0) == getXY(i + 1, i + 1).charAt(0));
      bColWin &= (getXY(i, fieldSize - i - 1).charAt(0) == getXY(i + 1, fieldSize - i - 2).charAt(
          0));
    }
    if (bColWin || bRowWin) {
      System.out.println("Игра окончена. Победил игрок " + bPlayerNum);
      return true;
    }

    for (i = 0; i < fieldSize * fieldSize; i++) {
      switch (getX(i)) {
        case 'O':
        case 'X':
          break;
        default:
          return false;
      }
    }
    if (fieldSize * fieldSize <= i) {
      System.out.println("Игра окончена,все ходы испольхованы. Ничья");
      return true;
    }

    return false;
  }

  /**
   * Получает значение координаты на поле
   */
  private static String getXY(int x, int y) {
    return field[x * fieldSize + y];
  }

  /**
   * Получает значение координаты на поле
   */
  private static char getX(int x) {
    return field[x].charAt(0);
  }

  /**
   * Вставляет на поле крестик или нолик
   */
  private static void putX(int x) {
    field[x] = 1 == bPlayerNum ? "X" : "O";
  }

  /**
   * Вывести игровое поле
   */
  private static void showPole() {
    for (int i = 0; i < fieldSize; i++) {
      for (int j = 0; j < fieldSize; j++) {
        System.out.printf("%4s", getXY(i, j));
      }
      System.out.print("\n");
    }
  }
}
