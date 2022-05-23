----------------------------------------Projekt do predmetu Seminár Java----------------------------------------

Dátum: 12.04.2022

Autor:  Viliam Holík - xholik14

Zadanie: Navrhnite a implementujte aplikáciu pre zobrazenie a editáciu diagramov tried a sekvenčného diagramu.

Predispozície k prekladu a spusteniu projektu:
    - Java SE 11
    - Apache Maven 3

Preloženie aplikácie: mvn clean package javadoc:javadoc

Spustenie aplikácie (zo zložky dest): java -jar ija-app.jar

Životný cyklus:
    - vyčistenie: mvn clean
    - kompilácia: mvn compile
    - spustenie: mvn javafx:run
    - vytvorenie jar-archívu: mvn package
    - spustenie jar-archívu (zo zložky dest): java -jar ija-app.jar