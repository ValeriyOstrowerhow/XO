import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class MenuCommandTest {

  /**
   * Тестирование значений перечисления MenuCommand
   */

  @Test
  public void testEnumValues() {
    /**
     * Проверка значений для команды READ
     */
    assertEquals(1, MenuCommand.READ.num);
    assertEquals("Правила и история игры", MenuCommand.READ.string);

    /**
     * Проверка значений для команды START
     */
    assertEquals(2, MenuCommand.START.num);
    assertEquals("Старт игры", MenuCommand.START.string);

    /**
     * Проверка значений для команды EXIT
     */
    assertEquals(3, MenuCommand.EXIT.num);
    assertEquals("Выйти из игры", MenuCommand.EXIT.string);

    /**
     * Проверка значений для некорректной команды
     */
    assertEquals(0, MenuCommand.UNEXPECTED.num);
    assertEquals("", MenuCommand.UNEXPECTED.string);
  }

}