import java.util.Scanner;

/**
 * Класс представляющий игру Крестики-Нолики
 */
public class GameXO {

  /**
   * Константа размера игрового поля
   */
  private static final byte FieldSizeInLengthAndWidth = 3;
  /**
   * Игровое поле
   */
  private static String[] field = new String[FieldSizeInLengthAndWidth * FieldSizeInLengthAndWidth];
  /**
   * Номер текущего игрока
   */
  private static byte PlayerNumber = 0;

  /**
   * Основной метод, запускающий игру
   */
  public static void main() {
    int number = 0;
    Scanner scanner = new Scanner(System.in);
/**
 * Инициализация поля
 */
    for (int i = 0; i < FieldSizeInLengthAndWidth * FieldSizeInLengthAndWidth; i++) {
      field[i] = Integer.toString(++number);
    }
/**
 * Главный игровой цикл
 */
    while (!isGameEnd()) {
      nextPlayer();
      while (true) {
        System.out.println("\nХод игрока " + PlayerNumber);//Отображение хода игрока и поля
        showPole();
        System.out.print("Наберите число, куда вы хотите вставить " + (1 == PlayerNumber ? "крестик" : "нолик") + ": ");
/**
 * Проверка некорректного ввода от игрока
 */
        if (scanner.hasNextInt()) {
          number = scanner.nextInt() - 1;
          if (isValidInput(number)) {
            break;
          }

        }else {
          scanner.next();
        }
        System.out.println("Некорректный ввод. Введите новое число от 1 до 9, которое не было использовано!");
      }
      try {
        putX(number);
      }catch (Exception e) {
        System.out.println("Что-то пошло не так ;(");
      }
    }
  }

  /**
   * Проверяем корректность ввода. Введенное число должно быть по размеру поля от1 до 9
   * и поле должно быть в этом месте еще не заполнено
   */
  static boolean isValidInput(int iIn) {
    if (iIn >= FieldSizeInLengthAndWidth * FieldSizeInLengthAndWidth|| iIn < 0)
      return false;

    switch (getX(iIn)) {
      case 'O':
      case 'X':
        return false;
    }
    return true;
  }

  /**
   * Смена текущего игрока
   */
  static void nextPlayer() {
    PlayerNumber = (byte) (1 == PlayerNumber ? 2 : 1);
  }

  /**
   * Определяем, наступил конец игры или нет Условия: 1) Победили крестики 2) Победили нолики 3)
   * Кончились ходы
   */
  static boolean isGameEnd() {
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
      System.out.println("Игра окончена,все ходы использованы. Ничья");
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
   * Вывод игрового поля
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

  public int getPlayerNumber() {
    return 0;
  }

  public void setFieldState(String[] strings) {
  }
}

