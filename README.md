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

W folderze [src/main/webapp](./src/main/webapp) znajdują się pliki .jsp, które tworzą interfejs.
Za komunikację z bazą danych odpowiedzialne są servlety znajdujące się w [src/main/java/agh/edu/pl/servlets](src/main/java/agh/edu/pl/GroupCommunicator/servlets). Każdy servlet ma w komentarzu nad kodem napisane, za co jest odpowiedzialny.
Tabele generujące schemat bazy danych są w [src/main/java/agh/edu/pl/tables](src/main/java/agh/edu/pl/GroupCommunicator/tables).

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

## Uruchamianie aplikacji
#### Importowanie i budowa aplikacji
```
git clone https://github.com/ksiek127/komunikator GroupCommunicator
cd GroupCommunicator
gradle build
```
#### Uruchamianie aplikacji z użyciem serwera Apache Tomcat 10.0.6 przy pomocy IDE IntelliJ IDEA 2021.1.1 (Ultimate Edition)
Po otwarciu folderu z zaimportowanym projektem w IntelliJ należy zainstalować serwer [Apache Tomcat 10.0.6](https://tomcat.apache.org/download-10.cgi), a następnie zastosować następujące kroki:
1. Dodać nową konfigurację aplikacji (`Run/Edit Configurations` a następnie `Add New Configuration`) i wybierz `Tomcat Server Local` ![config1](https://user-images.githubusercontent.com/39878361/120622054-c2da7a00-c45e-11eb-941e-0777a95aaded.png)

2. Wskazać katalog, gdzie rozpakowano zainstalowany serwer Apache Tomcat 10.0.6 po naciśnięciu na `Configure...` przy opcji `Application Server` oraz nacisnąć na przycisk `Fix` w prawym dolnym rogu przy ostrzeżeniu `Warning: No artifacts marked for deployment` ![config2](https://user-images.githubusercontent.com/39878361/120623176-ca4e5300-c45f-11eb-8d23-57e3f9fa3a0f.png)
3. Zakładka `Deployment` powinna wyglądać w następujący sposób ![config3](https://user-images.githubusercontent.com/39878361/120623403-fd90e200-c45f-11eb-97be-de73f1151b3c.png)
4. Po zaakceptowaniu wprowadzonej konfiguracji można uruchomić aplikację ![config4](https://user-images.githubusercontent.com/39878361/120623708-4183e700-c460-11eb-801d-5ed3fe298b9d.png)

#### Modyfikacja serwera bazodanowego
Aby zmienić serwer bazodanowy do którego będzie podłączona aplikacja należy odpowiednio zmodyfikować plik [hibernate.cfg.xml](https://github.com/ksiek127/komunikator/blob/master/src/main/resources/hibernate.cfg.xml)

#### WAŻNE
Aby aplikacja działała poprawnie, należy upewnić się, że w bazie danych w tabeli User występuje użytkownik o adresie email: `deleted@account`. Polecenie dodające wspomnianego użytkownika do bazy danych:
```
INSERT INTO [User] (city, country, street, zipCode, birthDate, email, firstname, lastname) VALUES ('City', 'Country', 'Street', '11-111', '2000-01-01 12:00:00.000', 'deleted@account', 'Account', 'Deleted')
```
