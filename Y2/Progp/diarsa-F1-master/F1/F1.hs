--Author: Diar Sabri & Kevin Nordwall
--Laboration F1, Programmeringsparadigm, CDATE 2

module F1 where
import Data.Char
import Data.List

{-
Fibonacci-funktion som är definierad enligt den matematiska formeln skriven på uppgiften
Den är rekursivt skriven med två basfall för 1 och 0.
-}
fib :: Integer -> Integer
fib 0 = 0
fib 1 = 1
fib n = fib(n-1)+fib(n-2)

{-
rovarsprak tar en string och delar upp den i x:xs för head och tail.
Den utnyttjar en hjälpfunktion isCons för att ta reda på om head är en konsonant
om ja, sätt ett 'o' mellan två x och ropa på funktionen igen
om nej, fortsätt direkt med den rekursiva delen så att den fortsätter längs strängen
-}
rovarsprak :: String -> String
rovarsprak [] = []
rovarsprak (x:xs) = if isCons x
                      then x:'o':x: rovarsprak xs
                      else x:rovarsprak xs

{-
karpsravor är i princip likadan som rovarsprak. Skillnaden är i then-uttrycket
om konsonant, kör funktionen igen men droppa två på tail
-}
karpsravor :: String -> String
karpsravor [] = []
karpsravor (x:xs) = if isCons x
                      then x:karpsravor (drop 2 xs)
                      else x:karpsravor xs

{-
isCons är en hjälpfunktion för rovarsprak samt karpsravor.
Den tar en character, kollar om den är en del av "bcdfghjklmnpqrstvwxz"
och returnerar true/false baserat på svaret.
-}
isCons :: Char -> Bool
isCons n = if n `elem` "bcdfghjklmnpqrstvwxz"
          then True
          else False

{-
medellangd gör egentligen inget annat än att dela svaret vi får från de två
hjälpfunktionerna countLetters/Words samt omvandlar svaret till en double.
-}
medellangd :: String -> Double
medellangd xs = fromIntegral (countLetters xs) / fromIntegral (countWords xs)

{-
countLetters utnyttjar isAlpha och filtrerar bort allt som inte stämmer in
så att vi bara har antalet bokstäver kvar.
-}
countLetters :: String -> Int
countLetters = length . filter isAlpha

{-
countWords delar först upp strängen i ord och kallar sen på hjälpfunktionen numOfWords
-}
countWords :: String -> Int
countWords = length . words . numOfWords

{-
numOfWords delar upp input i head:tail. den kollar om head är en bokstav
om ja, rekursivt anrop för att fortsätta längs strängen
om nej, splita strängen där med ett whitespace och fortsätt längs strängen
den splitar alltså upp strängen varje gång då head inte är en bokstav
-}
numOfWords :: String -> String
numOfWords [] = []
numOfWords (x:xs) = if isAlpha x
                    then x:numOfWords xs
                    else ' ':numOfWords xs


{-
skyffla utnyttjar en hjälpfunktoin takeEvery.
skyffla tar först huvudet av input och kör takeEvery på det andra elementet
den lägger sedan till den rekursiva delen med ++ som kör skyffla med den resterande listan
-}
skyffla :: [a] -> [a]
skyffla [] = []
skyffla xs = head xs : takeEvery (drop 1 xs) ++ skyffla (takeEvery xs)

{-
takeEvery tar en sträng, droppar huvudet och sätter det i en case-funktion
om xs är ett element eller mer kör den funktionen igen (droppar en igen osv)
om xs är tom returnerar den en tom ( basfall ),
-}
takeEvery :: [a] -> [a]
takeEvery xs = case drop 1 xs of
                (y:ys) -> y : takeEvery ys
                [] -> []
