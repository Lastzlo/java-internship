#HomeWork:
0) Подумати над зворотнім зв'язком.
1) Реалізовуємо стек та чергу в основі використати Array чи Links
   https://www.geeksforgeeks.org/array-implementation-of-queue-simple/
2) Почитати та розібратися в GOF патерн інтерпритатор
3) Пишем Parser для JSON
   {} -> Map<Sting, Object>
   [] -> List
   string -> String
   number -> Long, Double
4) Повторюємо колекції
5) Створюємо список питань для тесту та надсилаємо до Чт


1) Читаєм про анотації та рефлексію
2) Реалізовуєм свій Object/JSON mapper
3) Реалізовуєм стек та чергу.

# notes
Имя	Разрядность	Диапазон
long	64	        -9, 223, 372, 036, 854, 775, 808.. 9, 223, 372, 036, 854, 775, 807
Int	32	        -2, 147, 483, 648.. 2, 147, 483, 647
Short	16	        -32, 768.. 32, 767
byte	8	       -128.. 127   2^8 = 256
double	64	        1. 7е-308.. 1. 7е+ 308
float	32	        3. 4е-038.. 3. 4е+ 038   
char    16              				2^16  = 65 536
boolean 8 (в массивах),
32 (не в массивах используется int)

byte -> int -> long

1) Generic приклади
2) Moddel Mapper
   http://modelmapper.org/
   https://mapstruct.org/
   https://www.baeldung.com/jackson-object-mapper-tutorial
3) Exception
4) String pool о;
5) Integer pool;
6) (String...params) или (String param, string[] params) (какой метод будет иметь выше приоритет при перегрузке метода)
7) (Integer i) или (Double i) (какой метод будет иметь выше приоритет при перегрузке метода)
8) (int i) или (double i) (какой метод будет иметь выше приоритет при перегрузке метода)
9) Массив это объект??
