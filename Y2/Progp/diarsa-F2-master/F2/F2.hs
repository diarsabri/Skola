--Authors: Diar Sabri & Kevin Nordwall
module F2 where
import Data.List
import Data.Char
data MolSeq = MolSeq {name :: String
                    , sequence' :: String
                    , dorp :: DorP
                    } deriving (Show)

data DorP = DNA | PROTEIN deriving (Show, Eq)

{-
string2seq tar två strängar. Den första blir namnet och den andra blir sekvensen.
Den returnerar en MolSeq av dessa två strängar.
-}

string2seq :: String -> String -> MolSeq
string2seq name seqq
  | all isDna seqq = MolSeq name seqq DNA
  | otherwise = MolSeq name seqq PROTEIN

{-
Nedanstående fyra funktioner returnerar helt enkelt true/false eller det namnet säger.
-}
isDna :: Char -> Bool
isDna n = n `elem` "ACGT"

seqName :: MolSeq -> String
seqName (MolSeq x _ _) = x

seqSequence :: MolSeq -> String
seqSequence (MolSeq _ y _) = y

seqLength :: MolSeq -> Int
seqLength (MolSeq _ y _) = length y

{-
seqDistance tar två MolSeq's och returnerar avståndet mellan de.
Beräkningar enligt formel. Använder sex hjälpfunktioner.
-}
seqDistance :: MolSeq -> MolSeq -> Double
seqDistance (MolSeq _ y1 z1) (MolSeq _ y2 z2) = if z1 == z2
                                                 then if z1 == DNA
                                                       then dnaHelper y1 y2
                                                       else proteinHelper y1 y2
                                                 else error "Du försöker jämföra DNA med Protein!"

{-
Ifall protein, kör alphaCounter på strängarna. om alpha större än 0.94 -> 3.7
Annars, kör proteinformeln på strängarna
-}
proteinHelper :: String -> String -> Double
proteinHelper x y
  | alphaCounter x y > 0.94 = 3.7
  | otherwise = formelProtein x y
{-
Likadan som proteinHelper men för dna.
-}
dnaHelper :: String -> String -> Double
dnaHelper x y
  | alphaCounter x y > 0.74 = 3.3
  | otherwise = formelDNA x y

{-
Nedanstående två funktioner är formlerna för ifall det är dna eller protein.
-}
formelDNA :: String -> String -> Double
formelDNA x y = (-3/4) * log(1-(4/3) * alphaCounter x y)

formelProtein :: String -> String -> Double
formelProtein x y = (-19/20) * log(1-(20/19) * alphaCounter x y)

{-
alphaCounter alphaCounter tar två strängar och räknar andelen platser där de skiljer sig åt.
Utnyttjar zipWith där den filtrerar bort det som inte är likadant.
Därefter tar den längden på det, dividerat på längden av den kortaste strängen
-}
alphaCounter :: String -> String -> Double
alphaCounter x y = fromIntegral (length (filter not (zipWith (==) x y))) / fromIntegral (shortestString x y)

shortestString :: String -> String -> Int
shortestString x y
  | length x < length y = length x
  | otherwise = length y

{-
Profile är en datatyp som lagrar profiler av dna eller protein sekvenser.
Den sparar en lista av listor av (char,double) där det står en character,
och hur många sådana characters det finns i den kolumnen.
-}
data Profile = Profile {matrix :: [[(Char, Double)]]
                      , numSeq :: Int
                      , profName :: String} deriving (Show)


{-
molseqs2profile skapar en profil med namn (sträng) och en lista av molseqs.
-}
molseqs2profile :: String -> [MolSeq] -> Profile
molseqs2profile x y = Profile matrix numSeq profName
  where
    matrix = makeProfileMatrix y
    numSeq = length y
    profName = x


seqType :: MolSeq -> DorP
seqType (MolSeq _ _ z) = z

{-
nedanstående kod är tagen från uppgiften
-}
nucleotides = "ACGT"
aminoacids = sort "ARNDCEQGHILKMFPSTWYVX"

makeProfileMatrix :: [MolSeq] -> [[(Char, Double)]]
makeProfileMatrix [] = error "Empty sequence list"
makeProfileMatrix sl = res
  where
    t = seqType (head sl)
    defaults =
      if (t == DNA) then
        zip nucleotides (replicate (length nucleotides) 0) -- Rad (i)
    --zippar DNA bokstäverna med fyra nollor
      else
        zip aminoacids (replicate (length aminoacids) 0)   -- Rad (ii)
    --zippar protein bokstäverna med 21 nollor
    strs = map seqSequence sl                              -- Rad (iii)
    --tar ut strängarna från input listan av molseqs och sätter den till strs
    tmp1 = map (map (\x -> ((head x), fromIntegral(length x))) . group . sort)
               (transpose strs)                            -- Rad (iv)
    --transponerar strs ( sekvenserna) och sortera varje sekvens.
    --gruppera dna/proteinsekvenserna. Skapar en tuple ('A',0)
    --den sorterar , grupperar strängarna. sen kör den den inre funktionen på alla strängar
    equalFst a b = (fst a) == (fst b)
    res = map sort (map (\l -> unionBy equalFst l defaults) tmp1)

profileName :: Profile -> String
profileName (Profile _ _ z) = z

{-
utnyttjar hjälpfunktion checkIdx på matris idx i profilen. den delar även på antalet sekvenser i profilen
-}
profileFrequency :: Profile -> Int -> Char -> Double
profileFrequency (Profile matrx n _) idx chr = checkIdx (matrx !! idx) chr / fromIntegral n

{-
checkIdx tar en lista av tuples (matrisen) och kollar rekursivt om charactern finns i matrisen.
returnerar då hur många char's det finns som finns i double-värdet i tuplen
-}
checkIdx :: [(Char, Double)] -> Char -> Double
checkIdx (x:xs) chr = if fst x == chr
                      then snd x
                      else checkIdx xs chr

{-
profileDistance concatar matriserna i profilerna (för att få en lång lista ist. för lista av listor).
Ifall längderna inte är lika långa på profilerna, returnera ett error
Annars, concata matriserna och kör pfHelper vars funktion är att ta antalet chars, på båda matriserna
pfdCalc är en till hjälpfukntoin som körs på listorna av antalet chars vi nu har fått.
pfdCalc tar även två ints vilka representerar antalet sekvenser i varje profil.
-}
profileDistance :: Profile -> Profile -> Double
profileDistance p1 p2
  |length (concat (matrix p1)) /= length (concat (matrix p2)) = error "Profiles don't match in length"
  |otherwise = pfdCalc (pfHelper (concat (matrix p1))) (pfHelper (concat (matrix p2))) (fromIntegral (numSeq p1)) (fromIntegral (numSeq p2))

pfHelper :: [(Char,Double)] -> [Double]
pfHelper = map snd

{-
pfdCalc tar två listor av doubles (antalet chars) och två ints (antalet sekvenser i profilerna)
den dividerar listorna på antalet sekvenser och subtraherar sedan listorna med varandra.
därefter mappar den absolutbeloppet på varje element i listan, och slutligen adderar den hela listan
-}
pfdCalc :: [Double] -> [Double] -> Double -> Double -> Double
pfdCalc d1 d2 a1 a2 = sum $ map abs (zipWith (-) (map (/a1) d1) (map (/a2) d2))


class Evol obj where

  evolName :: obj -> String
  distance :: obj -> obj -> Double
  distanceMatrix :: [obj] -> [(String,String,Double)]

instance Evol MolSeq where
  evolName = seqName
  distance = seqDistance
  distanceMatrix = distanceMatrixHelper

instance Evol Profile where
  evolName = profileName
  distance = profileDistance
  distanceMatrix = distanceMatrixHelper

{-
distanceMatrixHelper har ett basfall för tomma listan
annars kör den dMatrixHelper på listan och 0 concatat med distanceMatrixHelper på allt förutom första elementet
dMatrixHelper tar en lista och en int. om n är mindre än längden på listan tar den namnen på första elementet, namnen på elementet på plats n och längden på första samt plats n
den gör sedan ett rekursivt anrop på dMatrixHelper på listan och skickar in nästa element som int n
-}
distanceMatrixHelper [] = []
distanceMatrixHelper a = dMatrixHelper a 0 ++ distanceMatrixHelper (tail a)
dMatrixHelper a n
  |n < length a = (evolName(head a), evolName (a !! n), distance (head a) (a!!n) ) : dMatrixHelper a (n + 1)
  |otherwise = []
