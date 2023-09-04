import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GameXOTest {

  private GameXO gameXO;

  /**
   * будет выполняться перед каждым тестом в GameXOTest.
   * Предотвращает взаимное влияние тестов друг на друга.
   */
  @BeforeEach
  public void setUp() {
    gameXO = new GameXO();
  }

  @Test
  /**
   * Проверка правильности ввода номера клетки
   */

  public void testIsValidInput() {
    // Номер в допустимом диапазоне
    assertTrue(gameXO.isValidInput(2));
    // Номер за пределами диапазона
    assertFalse(gameXO.isValidInput(-1));
    assertFalse(gameXO.isValidInput(9));
  }

  @Test
  /**
   * Проверка функции задания номера следующего игрока
   */

  public void testNextPlayer() {
    gameXO.nextPlayer();
    assertEquals(2, gameXO.getPlayerNumber());
    gameXO.nextPlayer();
    assertEquals(1, gameXO.getPlayerNumber());
  }

  @Test
  /**
   * Проверка окончания игры при победе одного из игроков
   */

  public void testIsGameEndForWin() {
    gameXO.setFieldState(new String[]{"X", "X", "X",
        "4", "O", "6",
        "7", "O", "9"});
    assertTrue(gameXO.isGameEnd());
  }

  @Test
  /**
   * Проверка окончания игры при ничьей
   */

  public void testIsGameEndForDraw() {
    gameXO.setFieldState(new String[]{"X", "O", "X",
        "O", "X", "O",
        "O", "X", "O"});
    assertTrue(gameXO.isGameEnd());
  }


}