# Komunikator
Komunikator grupowy to aplikacja webowa, w której możemy stworzyć profil użytkownika, dodawać nowe grupy, przypisywać do nich członków i tworzyć konwersacje grupowe za pomocą przesyłanych maili.

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

![diagram](https://user-images.githubusercontent.com/72787277/120630534-397b7580-c467-11eb-89f8-062f211f8f45.png)

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
1. Dodać nową konfigurację aplikacji (`Run/Edit Configurations` a następnie `Add New Configuration`) i wybrać `Tomcat Server Local` ![config1](https://user-images.githubusercontent.com/39878361/120622054-c2da7a00-c45e-11eb-941e-0777a95aaded.png)

2. Wskazać katalog, gdzie rozpakowano zainstalowany serwer Apache Tomcat 10.0.6 po naciśnięciu na `Configure...` przy opcji `Application Server` oraz nacisnąć na przycisk `Fix` w prawym dolnym rogu przy ostrzeżeniu `Warning: No artifacts marked for deployment` i wybrać domyślną wersję war ![config2](https://user-images.githubusercontent.com/39878361/120623176-ca4e5300-c45f-11eb-8d23-57e3f9fa3a0f.png)
3. Zakładka `Deployment` powinna wyglądać w następujący sposób ![config3](https://user-images.githubusercontent.com/39878361/120623403-fd90e200-c45f-11eb-97be-de73f1151b3c.png)
4. Po zaakceptowaniu wprowadzonej konfiguracji można uruchomić aplikację ![config4](https://user-images.githubusercontent.com/39878361/120623708-4183e700-c460-11eb-801d-5ed3fe298b9d.png)

#### Modyfikacja serwera bazodanowego
Aby zmienić serwer bazodanowy do którego będzie podłączona aplikacja należy odpowiednio zmodyfikować plik [hibernate.cfg.xml](https://github.com/ksiek127/komunikator/blob/master/src/main/resources/hibernate.cfg.xml)

#### WAŻNE
Aby aplikacja działała poprawnie, należy upewnić się, że w bazie danych w tabeli User występuje użytkownik o adresie email: `deleted@account`. Polecenie dodające wspomnianego użytkownika do bazy danych:
```
INSERT INTO [User] (city, country, street, zipCode, birthDate, email, firstname, lastname) VALUES ('City', 'Country', 'Street', '11-111', '2000-01-01 12:00:00.000', 'deleted@account', 'Account', 'Deleted')
```

## Struktura kodu aplikacji

### WebServlets
Opis oparty na Servletcie [GroupsSearchServlet](src/main/java/agh/edu/pl/GroupCommunicator/servlets/groups/GroupsSearchServlet) przetwarzającym żądania Get oraz Post.  
Na początku każdego Servletu opisane jest w komentarzu jego działanie
```
/*

    On Get: Redirects to searchGroups.jsp which shows a groups search engine
    On Post: Uses group_name parameter provided by the user using the groups search engine. Creates a groups map and loads
    all groups with names matching the group_name parameter. Checks if user is already a member, requested joining or
    can request to join the group. Redirects to searchGroups.jsp with a search result.

 */
```
Następnie wykorzystujemy adnotację @WebServlet do oznaczenia, że dana klasa będzie używana jako WebServlet o danej nazwie `name` oraz ścieżce URL `urlPatterns`
```
  @WebServlet(name = "GroupsSearchServlet", urlPatterns = "/groups-search")
  public class GroupsSearchServlet extends HttpServlet {
```
Zdefiniowana klasa rozszerza klasę abstrakcyjną `HttpServlet`, co wymusza przesłanianie przynajmniej jednej metody obsługującej żądanie HTTP. W naszym przypadku przesłaniamy tylko metody doGet oraz doPost obsługujące żądania Get oraz Post  
  
Przesłanianie metody doGet i przekierowanie `request` Servleta do zasobu zdefiniowanego w wywołaniu metody `getRequestDispatcher()` (w tym przypadku do pliku jsp `searchGroup.jsp`) wygląda następująco
```
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
      request.getRequestDispatcher("searchGroups.jsp").forward(request, response);
  }
```

Przesłanianie metody doPost, pobieranie parametru `group_name` z otrzymanego żądania. Jeśli nie podano żadnej nazwy grupy, to zostaje wykonane przeniesienie z powrotem do `searchGroups.jsp` z odpowiednim atrybutem wykorzystywanym do wyświetlenia informacji o wymaganiu podania nazwy grupy, którą chcemy wyszukać.

```
  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
      String groupNameRegex = request.getParameter("group_name");
      if (groupNameRegex.equals("")) {
          request.setAttribute("no_group_name", true);
          request.getRequestDispatcher("searchGroups.jsp").forward(request, response);
      } else {
```

Jeśli podano szukaną nazwę grupy, to otwieramy sesję korzystając z metody `getSession()` z pliku [HibernateUtils](src/main/java/agh/edu/pl/GroupCommunicator/HibernateUtils), gdzie jest zaimplementowana `SessionFactory` oraz rozpoczynamy transakcję na otwartej sesji.

```
  Session session = HibernateUtils.getSession();
  List<Group> groupsList = null;
  Map<Group, String> groupsMap = new HashMap<>();
  try {
      Transaction tx = session.beginTransaction();
```

Uzyskujemy listę grup, które pasują do podanego parametru `group_name` korzystając z HQL

```
  groupsList = session
             .createQuery("from Group as group where group.name like :groupNameRegex", Group.class)
             .setParameter("groupNameRegex", '%' + groupNameRegex + '%')
             .getResultList();
```

Następnie przetwarzamy otrzymane dane z bazy sprawdzając, czy zalogowany użytkownik jest członkiem znalezionych grup, czy prosił już o dołączenie, lub czy może prosić o dołączenie. Przy tej czynności także korzystamy z HQL, aby otrzymać odpowiednie dane z tabel GroupMember oraz GroupRequest. Na końcu wywołujemy `.commit()` na transakcji.

```
for (Group group : groupsList) {
//                    check if user is already in the group
                    List<GroupMember> gm = session
                            .createQuery("from GroupMember as gm where gm.group.groupID=:gId and gm.user.userID=:uId",
                                    GroupMember.class)
                            .setParameter("gId", group.getGroupID())
                            .setParameter("uId", LoggedUser.getUser().getUserID())
                            .getResultList();
                    if (gm != null && gm.size() == 1) {
                        groupsMap.put(group, "joined");
                    } else {
//                        check if user already requested joining the group
                        List<GroupRequest> gr = session
                                .createQuery("from GroupRequest as gr where gr.group.groupID=:gId and gr.user.userID=:uId",
                                        GroupRequest.class)
                                .setParameter("gId", group.getGroupID())
                                .setParameter("uId", LoggedUser.getUser().getUserID())
                                .getResultList();
                        if (gr != null && gr.size() == 1) {
                            groupsMap.put(group, "requested");
                        } else {
                            groupsMap.put(group, "none");
                        }
                    }
                }
                tx.commit();
```

Jeśli w trakcie wykonywania transakcji pojawiły się błędy, to przekierowujemy żądanie Servleta do `searchGroups.jsp` wraz z odpowiednim atrybutem pozwalającym na wyświetlenie komunikatu o uzyskanym błędzie w aplikacji.

```
  } catch (Exception ex) {
      request.setAttribute("search_error", true);
      request.getRequestDispatcher("searchGroups.jsp").forward(request, response);
      ex.printStackTrace();
```

W każdym przypadku na końcu zamykamy sesję

```
  } finally {
    session.close();
  }
```

Jeśli nie pojawiły się żadne błędy w czasie wykonywania transakcji, to przekierowujemy żądanie Servleta do `searchGroups.jsp` wraz z odpowiednim atrybutem do którego przypisujemy otrzymane rezultaty wyszukiwania

```
  if (groupsList == null || groupsList.size() == 0) {
      request.setAttribute("no_such_group", true);
  }
  request.setAttribute("groups", groupsMap);
  request.getRequestDispatcher("searchGroups.jsp").forward(request, response);
```

### JSP
Opis oparty na [groupsSearch.jsp](src/main/webapp/searchGroups.jsp)

Definiujemy `ContentType` oraz kodowanie strony, następnie importujemy używane prefixy z biblioteki `Taglibs` oraz Bootstrapa 5.0.1

```
  <%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
  <%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
  <%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet"
      integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
```

Tworzymy strukturę HTML strony korzystając z klas Boostrapowych.  
Formularz przekierowywujący do GroupsSearchServlet opisanego wyżej z parameterm `group_name`. Jest też możliwość powrotu do strony głównej profilu użytkownika naciskając na przycisk z napisem `Return` przekierowywującym do [ReturnToMainPageServlet](src/main/java/agh/edu/pl/GroupCommunicator/servlets/ReturnToMainPageServlet).  
Tag `if` prefixu `c` pozwala przy pomocy atrybutu `test` przetestować podany warunek. (poniżej podano tylko jeden przykład użycia dla większej przejrzystości kodu)  
`requestScope` to mapa dzięki której możemy odczytywać wartości przypisane `request` na przykład w Servletach za pomocą metody `request.setAttribute(name, value)`

```
  <!DOCTYPE html>
  <html>
  <head>
      <title>Group search</title>
  </head>
  <body>
  <br/>
      <div class="container-sm">
          <div class="card">
              <div class="card-header">
                  Search for a group
              </div>
              <div class="card-body">
                  <c:if test="${requestScope.group_leave_and_admin_change_success == true}">
                      <div class="alert alert-success" role="alert">
                          Left the group with admin changed
                      </div>
                  </c:if>
                  <form action="groups-search" method="post">
                      <div class="input-group mb-3">
                          <input type="text" class="form-control" placeholder="Group name" aria-label="Email"
                                 aria-describedby="basic-addon1" name="group_name" id="group_name"
                                 value="${fn:escapeXml(param.group_name)}">
                      </div>
                      <button type="submit" class="btn btn-outline-primary">Search</button>
                      <a href="returnToMainPage" class="btn btn-outline-primary">Return</a>
                  </form>
              </div>
          </div>
      </div>
```

Jeśli zostało wykonane wyszukiwanie po podanej nazwie grupy, to do `requestScope` w Servletcie GroupsSearchServlets zostanie przypisana mapa z otrzymanymi grupami. Odczytujemy je oraz jej klucze i wartości korzystając z przefixu `c` oraz tagów `if` oraz `forEach`, gdzie tag `forEach` ma atrybut `items` wskazujący na obiekt, po którym będziemy iterować oraz `var` do którego zostaną przypisane kolejne wyniki iteracji po obiekcie `items`. Dzięki temu tworzymy strukturę HTML do wyświetlania danych pojedynczej grupy, ale będzie ona powtarzana tyle razy, ile grup otrzymaliśmy w wyniku wyszukiwania po nazwie grupy. Rozróżniamy grupy, które są kluczami w mapie po wartościach tych kluczów, czyli `none` -> użytkownik nie jest memeberem grupy oraz nie prosił o dołączenie, `requested` -> użytkownik nie jest memeberem grupy, ale prosił o dołączenie, `joined` -> użytkownik jest memeberem grupy

```
  <c:if test="${requestScope.groups != null}">
          <div class="container-sm">
              <div class="card">
                  <div class="card-header">
                      Search results
                  </div>
                  <div class="card-body">
                      <ul class="list-group list-group-flush">
                          <c:forEach var="group" items="${requestScope.groups}">
                              <li class="list-group-item"><b>Group Name: </b> ${group.key.name}
                                  <b>Description: </b> ${group.key.description}
                                  <br/>
                                  <c:if test="${group.value == 'none'}">
                                      <form action="sendRequest" method="post">
                                          <input type="hidden" name="group_id" value="${group.key.groupID}">
                                          <button type="submit" class="btn btn-outline-primary">Join group</button>
                                      </form>
                                  </c:if>
                                  <c:if test="${group.value == 'requested'}">
                                      <form action="deleteRequest" method="post">
                                          <input type="hidden" name="group_id" value="${group.key.groupID}">
                                          <button type="submit" class="btn btn-outline-danger">Delete request</button>
                                      </form>
                                  </c:if>
                                  <c:if test="${group.value == 'joined'}">
                                      <form action="leaveGroup" method="post">
                                          <input type="hidden" name="group_id" value="${group.key.groupID}">
                                          <input type="hidden" name="returnPage" value="searchgroup.jsp">
                                          <button type="submit" class="btn btn-outline-danger">Leave group</button>
                                      </form>
                                  </c:if>
                              </li>
                          </c:forEach>
                      </ul>
                  </div>
              </div>
          </div>
      </c:if>
  </body>
  </html>
```

### Hibernate Entity

Stosowane do tworzenia klas będących potem mapowanych przez Hibernate do odpowiednich obiektów bazy danych  
Dla przykładu opisujemy klasę [Mail](src/main/java/agh/edu/pl/GroupCommunicator/tables/Mail)

Adnotacja `@Entity` opisuje, że definioawna klasa jest encją bazy danych.
```
  @Entity
  public class Mail {
```
Adnotacja `@Id` oznacza klucz główny encji, a `@GeneratedValue` określa strategię generowania kolejnych wartości tego klucza głównego.
```
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int mailID;
```
Definiujemy kolejne atrybuty encji podając przy pomocy adnotacji `@Column`, czy mogą one zawierać wartości null oraz jaka może być ich maksymalna długość  
`created` potrzebuje specjalnych adnotacji, które określają odpowiedni typ tego atrybutu i sposób tworzenia jego wartości
```
    @Column(nullable = false, length = 1000)
    private String message;

    @Column(nullable = false, length = 32)
    private String title;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date created;
```

Adnotacja `@ManyToOne` określa relację pomiędzy `Mail` a `Group`, gdzie jedna grupa z tabeli `Group` może mieć przypisane wiele maili z tabeli `Mail`. Dodatkowo zastosowana konfiguracja adnotacji `@OnDelete` pozwala na kaskadowe usuwanie encji `Mail` powiązanych z usuwaną encją z tabeli `Group`

```
    @ManyToOne
    @JoinColumn(name = "groupID", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Group group;
```

Tworzymy wymagany konstruktor bezparametrowy oraz drugi z wybranymi parametrami

```
    public Mail() {

    }

    public Mail(String message, String title, Group group) {
        this.message = message;
        this.title = title;
        this.group = group;
    }
```

Tworzymy odpowiednie metody `get` oraz `set` ułatwiające operacje na encjach.

```
    public int getMailID() {
        return mailID;
    }

    public void setMailID(int mailID) {
        this.mailID = mailID;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Group getGroup() {
        return group;
    }

    public int getGroupId() {
        return this.group.getGroupID();
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Date getCreated() {
        return created;
    }

    public String getCreatedFormatted() {
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss a");
        return formatter.format(created);
    }
```
