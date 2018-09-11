### Övning 1

T(n) står för antalet operationer algoritmen gör (i värsta fallet). En operation är :

* Aritmetiska: +, -, *, /
* Jämförelser: <,<=,=,>,>=
* Tilldelning: := (kopiering)

Ex.

a)  f()
    x := 1              1
    y := (x+1)*2        3
    return x+y          1

T(n) = 1+3+1 = 5

b)  f(m)
    if (m=0)            1
        x := 0          1
    else
        x := 7*m+4      3
    return x

T(n) = 1+3 = 4

c)  f(m)
    x := 1              1
    s := 0              1
    while (x<=m)        1
        s := s+x        2
        x := x+1        2
    return s

T(m) = 1+1+5m+1 = 5m+3

log2m = antal bitar i m = n

2^n = m

Ordonotation:

f(n) O(g(n))

* f(n) ∈ Ω (g(n)) - Värsta fall (undre gräns)
* f(n) ∈ O (g(n)) - Bästa fall  (övre gräns)
* f(n) ∈ Θ (g(n)) - Om båda föregående gäller, dvs alltid.

a)
T(n) = 5 
T(n) ∈ ΩOΘ (1) - konstant
b)
T(n) = 5m+3 = 5*2^n+3
T(n) ∈ ΩOΘ (2^n)



### F4

