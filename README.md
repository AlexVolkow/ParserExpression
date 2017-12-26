# ParserExpression
Парсер выражений, реализующий [метод рекурсивного спуска](https://ru.wikibooks.org/wiki/%D0%A0%D0%B5%D0%B0%D0%BB%D0%B8%D0%B7%D0%B0%D1%86%D0%B8%D0%B8_%D0%B0%D0%BB%D0%B3%D0%BE%D1%80%D0%B8%D1%82%D0%BC%D0%BE%D0%B2/%D0%9C%D0%B5%D1%82%D0%BE%D0%B4_%D1%80%D0%B5%D0%BA%D1%83%D1%80%D1%81%D0%B8%D0%B2%D0%BD%D0%BE%D0%B3%D0%BE_%D1%81%D0%BF%D1%83%D1%81%D0%BA%D0%B0) 
(Курс Парадигмы программирования(Итмо, 2017))

Parser
---
* Парсер выражений с целочисленными константами
* Поддерживаются переменные с именами `x`, `y`, `z`
* Класс [ExpressionParser](Parser/src/expression/parser/ExpressionParser.java) реализует интерфейс [Parser](Parser/src/expression/parser/Parser.java).
Возвращяет объект, реализующий интерфейс [TripleExpression](Parser/src/expression/TripleExpression.java)
с единственным методом `int evaluate(int x, int y, int z)`, через который происходит подсчет значения выражения. 
* Пример использования
```java
ExpressionParser parser = new ExpressionParser();
String expr = "1000000*x*x*y*y*z/(x-1)";
TripleExpression res = parser.parse(expr);
int res = expr.evaluate(2, 7, 4);
```

Generic
---
* Парсер выражений, поддерживающий произвольные типы
* Все сторонние типы должны наследоваться от класса `AbstractType`
* *GenericTabulator*
    * Класс `GenericTabulator` реализует интерфейс 
      [Tabulator](Generic/src/expression/generic/Tabulator.java) и
      сроит трехмерную таблицу значений заданного выражения.
        * `mode` — режим вычислений:
           * `i` — вычисления в `int` с проверкой на переполнение;
           * `d` — вычисления в `double` без проверки на переполнение;
           * `bi` — вычисления в `BigInteger`.
        * `expression` — выражение, для которого надо построить таблицу;
        * `x1`, `x2` — минимальное и максимальное значения переменной `x` (включительно)
        * `y1`, `y2`, `z1`, `z2` — аналогично для `y` и `z`.
        * Результат: элемент `result[i][j][k]` содержит
          значение выражения для `x = x1 + i`, `y = y1 + j`, `z = z1 + k`.
          Если значение не определено (например, по причине переполнения),
          то соответствующий элемент равен `null`.
