# komunikator
Komunikator grupowy, dzięki któremu możemy stworzyć profil, grupy, dodawać osoby do grup i tworzyć konwersacje grupowe za pomocą maili. Realizacja z użyciem relacyjnej bazy danych Microsoft SQL Server, języka Java oraz technologii Hibernate.

Skład grupy:

Gabriel Cyganek

Krzysztof Siekierzyński

Joanna Klimek

-----

Aplikacja pozwala na tworzenie grup, w których każdy członek może wysłać wiadomość do całej grupy. Jeśli użytkownik chce dołączyć do grupy, musi ją wyszukać w aplikacji i złożyć wniosek o dołączenie. Administrator oraz moderator może zaakceptować lub odrzucić taki wniosek. Każdy użytkownik może przeglądać wiadomości danej grupy oraz wszystkie wiadomości z grup, do których należy.
Każdy użytkownik ma jedną z trzech ról w grupie, zwykły członek może tylko wysyłać i przeglądać wiadomości, moderator może akceptować wnioski o dołączenie do grupy oraz usuwać zwykłych członków z niej, a administrator może to co moderator, oraz usuwać moderatorów i nadawać rangi.

W folderze src/main/webapp znajdują się pliki .jsp, które tworzą interfejs.
Za komunikację z bazą danych odpowiedzialne są servlety znajdujące się w src/main/java/agh/edu/pl/servlets. Każdy servlet ma w komentarzu nad kodem napisane, za co jest odpowiedzialny.
Tabele generujące schemat bazy danych są w src/main/java/agh/edu/pl/tables.
