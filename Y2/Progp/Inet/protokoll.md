# Protokoll

Nedan följer en beskrivning över hur programmet fungerar. Det går att återskapa vår lösning genom att följa logiken bakom hur programmet är uppbygt baserat på detta protokoll.

## Kommunikation mellan server och klient

Klienten och servern kommunicerar med varandra med siffror, antingen long eller integer. Det sker inga överföringar av strängar eller filer eller något annat. Programmet inleds med en kontroll av kort och pinkods-information. Kunden begärs trycka in kortnummer och därefter pinkod. Denna information skickas sedan till servern som gör en koll mot en användarfil. Varje användare har en egen fil, namnet på filen är kortnummret. Första raden är pinkoden och andra raden är användarens saldo. Det görs en kontroll om det är korrekt värden, om ja så går programmet vidare, om nej så begärs kunden trycka in kortnummer och pinkod ända tills det går genom. När man har lyckats logga in skrivs det ut ett välkomstmeddelande samt alternativen.

Kunden får då 5 val:

1. Saldo.       Kundens nuvarande saldo skrivs ut.
2. Uttag.       Kunden begärs trycka in ännu en siffra, har kunden tillräckligt med saldo går uttaget genom. Om inte så skrivs kundens saldo ut.
3. Insättning.  Kunden begärs trycka in en siffra, det läggs till på kundens saldo.
4. Byt språk.   Kunden begärs trycka in en siffra från 0-n där n är antalet olika språk. Exempelvis står 0 just nu för svenska. Då byts språket på klienten till svenska och välkomstmeddelandet skrivs ut återigen.
5. Avsluta.     Klienten avslutar och det skrivs ut ett "Hej då" exempelvis.

Efter 1-4 skrivs alternativen ut igen, fast utan välkomstmeddelandet. Alternativ 4 och 5 sker helt på klientsidan, de kommunicerar inte alls med servern. Att byta språk görs med en funktion. Funktionen funkar på det sättet att den läser in en språkfil och styckar upp språkfilen en lista av listor där varje lista innehåller ett språk med det språkets alla meddelanden. Svenska ser exempelvis ut på det här sättet:

    Välkommen till Banken! (1)Saldo, (2)Uttag, (3)Insättning, (4)Byt språk, (5)Avsluta
    (1)Saldo, (2)Uttag, (3)Insättning, (4)Byt språk, (5)Avsluta
    Ange värde:
    Saldo:
    SEK
    Hej då
    Ogiltig inmatning. Tryck i en siffra från 1-5.
    Inte tillräckligt med pengar
    SWE

Varje språk avslutas med en rad som indikerar vilket språk det är. Funktionen skapar alltså en lista av språk som innehåller 9 element var. När vi anropar funktionen görs det med en siffra som motsvarar vilket språk i listan vi ska sätta som nuvarande språk. Funktionen returnerar alltså en lista på 9 element, och varje gång vi vill skriva ut något skriver vi ut det genom att välja vilket element ur denna lista vi ska skriva ut. Således behöver inga strängar skickas över till servern, utan endast vilken siffra från listan vi ska skriva ut.

Alternativ 1-3 kommunicerar med servern med ints eller long. När kunden trycker in 1-3 som menyval går klienten in i en slinga som inte terminerar förrän det returneras en nolla från servern. Vi har i denna slinga olika "koder" som kommunikation som låter klienten veta vad den ska göra. Det första exemplet är noll, där den ska bryta slingan. Det andra är då servern skickar -2, det indikerar att kundens saldo följer efter -2 och då ska det skrivas ut. Det tredje är -3 där kunden ska trycka in en siffra (vid inmatning eller uttag). Då -3 kommer från servern vet klienten att den ska läsa in en siffra från kunden som återigen ska skickas till servern. Det fjärde är -4 vilket skickas då kunden försöker dra ut mer pengar än vad som finns på kontot. Det sista alternativet är är alla koder > 0 som motsvarar en siffra från servern som ska skriva ut den siffran från listan av strängar vi har. Efter varje menyval skickas en nolla då det är helt klart med slingan och då skriver vi återigen ut alternativen 1-5.

Varje input kunden skickar in har vi två funktioner för som läser in strängen och kollar om strängen är en giltig int eller long. Om inte skrivs ett felmeddelande ut.

På serversidan inleds det på samma sätt med kortnummer och pinkod med en slinga som tar input från klienten och kollar mot "databasen" ifall kortnummret och pinkoden överensstämmer. Den gör detta tills en inloggning lyckas. Då det är klart går server, likt klienten, in i en slinga som inte avslutas förrän kunden loggar ut. Slingan läser in menyval som kunden gör. Det är tre koll som behöver göras, 1 - saldo, 2 - uttag, 3 - insättning. Varje alternativ går genom en viss kommunikation med den inre slingan i klienten och avslutas till slut med en nolla som indikerar att det menyvalet är klart och då kan klienten återigen skriva ut alternativen 1-5 igen. Då servern behöver skicka värden till klienten meddelar servern klienten detta med hjälp av "koderna" vi tog upp innan (-2,-3,-4 eller avslutningskoden 0). Då menyalternativ 1-3 alla behöver läsa/skriva till kundens saldo görs detta på serversidan med hjälp av en funktion. Funktionen läser in kundens fil (kortnummer) och läser/skriver rad två på filen.

## State diagram

Följande bild beskriver i vilka tillstånd programmet kan vara i. Vi börjar i ifylld cirkel och avslutar i halvt ifylld cirkel.

![alt text][logo]

[logo]: https://i.imgur.com/ZlDvax7.png
