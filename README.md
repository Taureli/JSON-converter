# JSON-converter
Autor: Jakub Karolczak

##Krótki opis programu:
[Główny kod programu](https://github.com/Taureli/JSON-converter/blob/master/src/Converter.java).

Prosty program konwertujący klasy Javy na obiekty JSON. Konwerter obsługuje typy proste (rozróżniając typy znakowe i liczbowe) oraz tablice wielowymiarowe typów prostych. Program wykrywa wszystkie pola, niezależnie czy są publiczne czy prywatne.

#####Przykład wywołania konwertera:
```Java
Converter c = new Converter();
Person p = new Person();
c.toJSON(p);
```
