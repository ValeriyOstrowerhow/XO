import java.util.Scanner;
public class GameXO {

  private static final byte FieldSizeInLengthAndWidth = 3;

  private static String[] field = new String[FieldSizeInLengthAndWidth * FieldSizeInLengthAndWidth];

  private static byte PlayerNumber = 0;

  public static void main() {
    int iTmp = 0;
    Scanner scanner = new Scanner(System.in);

    for (int i = 0; i < FieldSizeInLengthAndWidth * FieldSizeInLengthAndWidth; i++) {
      field[i] = Integer.toString(++iTmp);
    }

    while (!isGameEnd()) {
      nextPlayer();
      while (true) {
        System.out.println("\nХод игрока " + PlayerNumber);
        showPole();
        System.out.print("Наберите число, куда вы хотите вставить " + (1 == PlayerNumber ? "крестик" : "нолик") + ": ");

        if (scanner.hasNextInt()) {
          iTmp = scanner.nextInt() - 1;
          if (isValidInput(iTmp)) {
            break;
          }
        }
        System.out.println("Введенное число уже было использовано. Введите новое число!");
      }
      try {
        putX(iTmp);
      } catch (Exception e) {
        System.out.println("Что-то пошло не так ;(");
      }
    }
  }

  /**
   * Проверяем корректность ввода. Введенное число должно быть по размеру поля и поле должно быть в
   * этом месте еще не заполнено
   */
  private static boolean isValidInput(int iIn) {
    if (iIn >= FieldSizeInLengthAndWidth * FieldSizeInLengthAndWidth)
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
    PlayerNumber = (byte) (1 == PlayerNumber ? 2 : 1);
  }

  /**
   * Определяем, наступил конец игры или нет Условия: 1) Победили крестики 2) Победили нолики 3)
   * Кончились ходы
   */
  private static boolean isGameEnd() {
    int i, j;
    boolean bRowWin = false, bColWin = false;

    for (i = 0; i < FieldSizeInLengthAndWidth; i++) {
      bRowWin = true;
      bColWin = true;
      for (j = 0; j < FieldSizeInLengthAndWidth - 1; j++) {
        bRowWin &= (getXY(i, j).charAt(0) == getXY(i, j + 1).charAt(0));
        bColWin &= (getXY(j, i).charAt(0) == getXY(j + 1, i).charAt(0));
      }
      if (bColWin || bRowWin) {
        System.out.println("Игра окончена. Победил игрок " + PlayerNumber);
        return true;
      }
    }

    bRowWin = true;
    bColWin = true;
    for (i = 0; i < FieldSizeInLengthAndWidth - 1; i++) {
      bRowWin &= (getXY(i, i).charAt(0) == getXY(i + 1, i + 1).charAt(0));
      bColWin &= (getXY(i, FieldSizeInLengthAndWidth - i - 1).charAt(0) == getXY(i + 1, FieldSizeInLengthAndWidth
          - i - 2).charAt(
          0));
    }
    if (bColWin || bRowWin) {
      System.out.println("Игра окончена. Победил игрок " + PlayerNumber);
      return true;
    }

    for (i = 0; i < FieldSizeInLengthAndWidth * FieldSizeInLengthAndWidth; i++) {
      switch (getX(i)) {
        case 'O':
        case 'X':
          break;
        default:
          return false;
      }
    }
    if (FieldSizeInLengthAndWidth * FieldSizeInLengthAndWidth <= i) {
      System.out.println("Игра окончена,все ходы испольхованы. Ничья");
      return true;
    }
    return false;
  }

  /**
   * Получает значение координаты на поле
   */
  private static String getXY(int x, int y) {
    return field[x * FieldSizeInLengthAndWidth + y];
  }

  /**
   * Получает значение координаты на поле
   */
  private static char getX(int x) {
    return field[x].charAt(0);
  }

  /**
   * Вставляет в элементы поля крестик или нолик
   */
  private static void putX(int x) {
    field[x] = (1 == PlayerNumber ? "X" : "O");
  }

  /**
   * Вывести игровое поле
   */
  private static void showPole() {

    for (int i = 0; i < FieldSizeInLengthAndWidth; i++) {
      System.out.print(" ");
      for (int j = 0; j < FieldSizeInLengthAndWidth; j++) {
        System.out.print(getXY(i, j) + "   ");
      }
      System.out.println();
    }
  }
}
