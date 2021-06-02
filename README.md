# Komunikator
Komunikator grupowy to projekt aplikacji webowej, w której możemy stworzyć profil użytkownika, dodawać nowe grupy, przypisywać do nich członków i tworzyć konwersacje grupowe za pomocą przesyłanych maili.

Projekt realizowany jest w języku Java z wykorzystaniem frameworka Hibernate do realizacji warstwy dostępu do danych oraz relacyjnej bazy danych Microsoft SQL Server w celu ich przechowywania.

Interfejs tworzą strony JSP napisane w formacie HTML z wykorzystaniem servletów przetwarzających żądania HTTP.


### Skład grupy:

- Gabriel Cyganek

- Krzysztof Siekierzyński

- Joanna Klimek

## Działanie aplikacji

Aplikacja pozwala na tworzenie grup, w których każdy członek może wysłać wiadomość do całej grupy. Jeśli użytkownik chce dołączyć do grupy, musi ją wyszukać w aplikacji i złożyć wniosek o dołączenie. Administrator oraz moderator może zaakceptować lub odrzucić taki wniosek. Każdy użytkownik może przeglądać wiadomości danej grupy oraz wszystkie wiadomości z grup, do których należy.

Każdy użytkownik ma jedną z trzech ról w grupie, zwykły członek może tylko wysyłać i przeglądać wiadomości, moderator może akceptować wnioski o dołączenie do grupy oraz usuwać zwykłych członków z niej, a administrator może to co moderator, oraz usuwać moderatorów i nadawać rangi.

Możliwa jest również edycja danych użytkownika i grupy, a także usunięcie profilu danej osoby.

## Struktura projektu

W folderze [./src/main/webapp](./src/main/webapp) znajdują się pliki .jsp, które tworzą interfejs.
Za komunikację z bazą danych odpowiedzialne są servlety znajdujące się w [src/main/java/agh/edu/pl/servlets](src/main/java/agh/edu/pl/servlets). Każdy servlet ma w komentarzu nad kodem napisane, za co jest odpowiedzialny.
Tabele generujące schemat bazy danych są w [src/main/java/agh/edu/pl/tables](src/main/java/agh/edu/pl/tables).

## Schemat bazy danych

* jpg *

Tabela | Znaczenie
------------ | -------------
User | Przechowuje dane użytkowników, takie jak imie, nazwisko, adres, e-mail, data urodzenia
Group | Przechowuje podstawowe dane na temat grup, czyli nazwę i opis
GroupRequest | Realizując relację wiele-do-wielu, informuje którzy użytkownicy wysłali prośbę o dołączenie do poszczególnych grup
GroupMember | Realizując relację wiele-do-wielu, informuje jacy użytkownicy aktualnie przynależą do poszczególnych grup oraz jaką posiadają w rangę w danej grupie (admin/moderator/member)
Mail | Przechowuje podstawowe informacje dotyczące wysłanych maili
Inbox | Realizując relację wiele-do-wielu, przypisuje mailom ich odbiorców z tabeli User oraz przechowuje informację, czy mail został przeczytany przez danego użytkownika
Inbox | Realizując relację wiele-do-wielu, przypisuje mailom ich nadawców z tabeli User oraz przechowuje informację, czy mail został usunięty przez danego użytkownika

