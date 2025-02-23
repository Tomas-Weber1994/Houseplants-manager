# Aplikace pro správu pokojových rostlin

## Popis
Tato aplikace slouží k evidenci pokojových rostlin. Umožňuje správu seznamu rostlin, jejich přidávání, zalévání, odebírání a úpravu, včetně načítání a ukládání dat ze souborů. Aplikace také loguje operace do souboru pro snadnou sledovatelnost.

## Struktura aplikace

### Hlavní třídy:
- **Plant** – Třída pro modelování rostliny, která obsahuje informace o názvu, poznámkách, datu zasazení, datu poslední zálivky a frekvenci zálivky.
- **PlantManager** – Třída pro správu seznamu rostlin, která umožňuje přidávání, odebírání, řazení a zalévání rostlin. Volá třídy **PlantFileReader** pro načítání a **PlantFileWriter** pro zápis rostlin do souborů.
- **PlantFileReader** – Třída pro načítání rostlin ze souboru ve formátu TXT. Při čtení souboru automaticky přeskočí řádky s chybami a loguje je do souboru.
- **PlantFileWriter** – Třída pro zápis rostlin do souboru ve formátu TXT. 

## Struktura souborů:
- **/resources** – Složka obsahující soubory s daty o rostlinách, které se načítají při spuštění aplikace.
- **/logs** – Složka pro logovací soubory aplikace, které zaznamenávají operace a případné chyby během činnosti programu.

## Použití:
1. Načítání a zápis rostlin se provádí pomocí tříd **PlantFileReader** a **PlantFileWriter**.
2. Aplikace automaticky loguje všechny operace (např. čtení souboru, přidání nové rostliny) do souboru ve složce **/logs**.
3. Aplikaci spustíte jednoduše přes metodu `main`, která načte seznam rostlin a provede požadované operace.
