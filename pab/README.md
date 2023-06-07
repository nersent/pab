COJONES
Dokumentacja dla programistów

Eryk Rakowski, Olimpia Lewińska, Mikołaj Palkiewicz
Spis treści
Strona tytułowa
Spis treści
Historia zmian
Wprowadzenie
Dokumentacja właściwa
Słowniczek
Załączniki

Historia zmian
Commit
Opis zmian
spring setup
Setup springa z konfiguracja + templaty HTML
implement auth
Implementacja logowania i rejestracji
add admin view
Dodany widok admina
add baskets
Dodany widok koszyka
add listing products
Dodany widok listy produktow

Wprowadzenie
Dokumentacja przedstawia opis sklepu internetowego napisanego w języku Java przy użyciu frameworku Spring. Sklep internetowy jest aplikacją, która umożliwia użytkownikom przeglądanie, wyszukiwanie i zakup produktów online.

Dokumentacja właściwa
Uruchomienie
Aby uruchomić sklep internetowy, wykonaj następujące kroki:
Sklonuj repozytorium z kodem źródłowym sklepu internetowego.
Upewnij się, że masz zainstalowane wymagane narzędzia, takie jak Java Development Kit (JDK) w wersji 17 (moze byc AdoptiumJDK).
Zainstaluj docker
Uruchom docker engine
Wejdz w folder /pab
Uruchom docker-compose up -d
Zainstaluj VSCODE
Zainstaluj rozszerzenie do VSCODE
https://marketplace.visualstudio.com/items?itemName=vscjava.vscode-java-pack docker-

- d
  ocker-compose up -dcompose up -d
  Otworz project /pab w VSCode
  Wejdz w dowolny plik w Vscode .java i kliknij f5 w VSCode, aby uruchomic serwer
  Sklep internetowy powinien być dostępny pod adresem http://localhost:8080 w przeglądarce internetowej.

Informacje o bibliotekach
Sklep internetowy wykorzystuje następujące biblioteki i frameworki:
Spring Framework - framework do tworzenia aplikacji Java.
Spring Boot - narzędzie ułatwiające konfigurację i uruchomienie aplikacji opartych na Spring.
Hibernate - biblioteka ORM (Object-Relational Mapping) do obsługi bazy danych.
MySQL Connector/J - sterownik JDBC (Java Database Connectivity) do komunikacji z bazą danych MySQL.
Thymeleaf - silnik szablonów do generowania interfejsu użytkownika.
Bootstrap - framework CSS i JavaScript do tworzenia responsywnego i atrakcyjnego wyglądu aplikacji.
Opis modułów, klas i zmiennych
Sklep internetowy składa się z następujących modułów, klas i zmiennych:
Moduł użytkowników:
Klasa User: reprezentuje użytkownika sklepu. Zawiera informacje takie jak identyfikator, imię, nazwisko, adres e-mail itp.
Klasa UserService: dostarcza funkcje do zarządzania użytkownikami, takie jak rejestracja, logowanie, edycja profilu itp.
Moduł produktów:
Klasa Product: reprezentuje produkt dostępny w sklepie. Zawiera informacje takie jak identyfikator, nazwa, opis, cena, dostępność itp.
Klasa ProductService: dostarcza funkcje do zarządzania produktami, takie jak dodawanie, edycja, usuwanie itp.
Moduł zamówień:
Klasa Order: reprezentuje zamówienie złożone przez użytkownika. Zawiera informacje takie jak identyfikator, lista produktów, dane użytkownika, status zamówienia itp.
Klasa OrderService: dostarcza funkcje do zarządzania zamówieniami, takie jak składanie zamówienia, aktualizacja statusu itp.
Słowniczek
JDK: Java Development Kit
ORM: Object-Relational Mapping
JDBC: Java Database Connectivity
Załączniki
Link do figmy: https://www.figma.com/file/XhNytYKxga73GWIyuUUTKq/PAB?type=design&node-id=0%3A1&t=beDJRz0UENQZx6DQ-1
Link do repozytorium: https://github.com/xnerhu/pab
