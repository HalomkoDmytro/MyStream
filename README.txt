Завдання:

1. Реалізувати наступні методи класу AsIntStream по роботі з потоком
(stream) цілочисельних даних:
static IntStream of(int... values)
створює початковий потік на основі масиву цілих чисел

- Double average()
середнє значення чисел в потоці. Термінальній метод.
IllegalArgumentException - if empty

- Integer max()/min()
максимальне / мінімальне значення числа в потоці. Термінальній метод.
IllegalArgumentException - if empty

- long count()
кількість значень (елементів) в потоці. Термінальній метод.

- Integer sum()
сума всіх значень в потоці. Термінальній метод. IllegalArgumentException -
if empty

- int[] toArray()
повертає потік у вигляді масиву. Термінальній метод.

- void forEach(IntConsumer action)
для кожного значення з потоку виконує операцію зазначену в реалізації
IntConsumer. Даний метод є термінальним і нічого не повертає

- IntStream filter(IntPredicate predicate)
для кожного значення з потоку перевіряє його на предмет чи задовольняє
воно умові в реалізації IntPredicate, якщо так - повертає його в результуючий
потік, якщо ні - викидає

- IntStream map(IntUnaryOperator mapper)
застосовує до кожного зі значень потоку реалізацію IntUnaryOperator і
повертає його в результуючий потік

- IntStream flatMap(IntToIntStreamFunction func)
застосовує до кожного зі значень потоку реалізацію
IntToIntStreamFunction, яка на основі кожного зі значення створює новий потік
значень, які потім об'єднуються в один результуючий потік

- int reduce(int identity, IntBinaryOperator op)
виконує згортку значень потоку в ціле число, початкове значення
задається identity, функція згортки - в реалізації IntBinaryOperator. Термінальній
метод.

При реалізації можна використовувати стандартні колекції з Java, але не можна
використовувати Java Stream API.