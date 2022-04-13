----------------------------------------Projekt do predmetu Seminár Java----------------------------------------

Dátum: 12.04.2022

Členovia tímu:  Viliam Holík - xholik14 (vedúci)
                Pavol Babjak - xbabja03

Zadanie: Navrhnite a implementujte aplikáciu pre zobrazenie a editáciu diagramov tried a sekvenčného diagramu.

Preloženie aplikácie: mvn clean package javadoc:javadoc

Spustenie aplikácie (zo zložky dest): java -jar ija-app.jar

Životný cyklus:
    - vyčistenie: mvn clean
    - kompilácia: mvn compile
    - spustenie: mvn javafx:run
    - vytvorenie jar-archívu: mvn package
    - spustenie jar-archívu (zo zložky dest): java -jar ija-app.jar

Plán sa nám nepodarilo dodržať, kvôli venovaniu sa bakalárskej práce. Nepodarilo sa vytvoriť zakladnú grafickú reprezentáciu diagramov.
Základná funkcionalita je aktuálne načítanie súboru pomocou import-u z GUI, parsovanie, vytvorenie objektov tried a výpis na štandardný výstup.
