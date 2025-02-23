# Aplikace pro správu pokojových rostlin

## Popis
Tato aplikace slouží k evidenci pokojových rostlin. Umožňuje správu seznamu rostlin, jejich přidávání, zalévání, odebírání a úpravu, včetně načítání a ukládání dat ze souborů.

## Struktura aplikace

### Hlavní Třídy:
- **Plant** – Třída pro modelování rostliny, která obsahuje informace o názvu, poznámkách, datu zasazení, datu poslední zálivky a frekvenci zálivky.
- **PlantManager** – Třída, která spravuje seznam rostlin a umožňuje manipulaci s nimi (přidávání, odebírání, řazení atd.). Třída volá PlantFileReader a PlantFileWriter.
- **PlantFileReader** – Třída pro načítání rostlin ze souboru txt. Zajišťuje, že při čtení souboru dojde k přeskočení chybných řádků, kdy aplikace zaloguje chyby a načte pouze řádky bez chyb.
- **PlantFileWriter** – Třída pro zápis rostlin do souboru. Umožňuje exportovat seznam rostlin do formátu txt.
