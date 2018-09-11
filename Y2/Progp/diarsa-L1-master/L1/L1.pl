%Authors: Kevin Nordwall, Diar Sabri

% Fibonacci, svansrekursiv
% Går ner från N till N>1, sparar senaste två värden i anropet.
% Ex. fib(3,F).
fib(N,F) :-
  fib(N,0,1,F),!.       % 1. fib(3,0,1,F)             5.fib(2,1,1,F)          9.fib(1,1,2,F)
fib(0,A,_,A).
fib(1,_,B,B).                                                               % 10. fib(1,_,2,2). F = 2
fib(N,A,B,F) :-
  N > 1,                % 2. 3>1,                     6. 2 > 1
  N1 is N-1, B1 is A+B, % 3. N1 = 3-1, B1 = 0+1       7. N1 = 2-1, B1 = 1+1
  fib(N1,B,B1,F).       % 4. fib(2,1,1,F)             8. fib(1,1,2,F)

% Rövarspråk
% Om bokstaven är en vokal, fortsätt med resten av listan
% Om inte, lägger till ett 'O' emellan.
% Ex. rovarsprak([104,101,106], X).
% L19, H ! member, klipp av
% L19, E member, spara, Exita
% L19, J ! member, klipp av
% Fortsätter till L24, lägger till ett 'O' mellan de avklippta bokstäverna
% Gör inget med de sparade. Exitar alla anrop
rovarsprak([], []).
rovarsprak([H|T], [H|R]) :-
    member(H,[97,101,105,111,117,121]),         % Om det är en vokal fortsätt med resten av listan. [a,e,i,o,u,y].
    rovarsprak(T,R),!.
rovarsprak([H|T],[H,111,H|R]):-                 % Kommer hit ner om den failar på vowel i funktionen ovan.
    rovarsprak(T,R),!.

% Medellangd
% ropar på countWords och countLetters.
% Delar sedan, precis som i haskell
medellangd([], 0).
medellangd(Text, AvgLen) :-
    countWords(Text, Result1),
    countLetters(Text, Result2),
    AvgLen is Result2/Result1.

countWords(Text, Result) :-
    countWords(Text, 0, Result).
countWords([],N,N).
countWords([H],N,Result) :-
    char_type(H,alpha)->              %if-sats, om tecknet är en bokstav, plussa
    N1 is N + 1,
    countWords([], N1, Result),!;     %basfall, om tom lista, returnera det plussade
    countWords([], N , Result),!.     %basfall, om tom lista, returnera det icke-plussade
countWords([H1,H2 | T], N, Result) :-
    (char_type(H1,alpha),
    not(char_type(H2,alpha))) ->      %if-sats, om tecknet efter H1 inte är en bokstav så är det en delare.
    N1 is N + 1,
    countWords(T, N1, Result);        %fortsätt med bokstavn efter H2, och plussa
    countWords([H2|T], N, Result).    %annars, fortsätt med H2 och plussa ej

countLetters(Text, Result) :-
    countLetters(Text, 0 ,Result).
countLetters([],N,N).
countLetters([H | T], N, Result) :-
    (char_type(H,alpha) ->            %if-sats, om tecknet är en bokstav, plussa
    N1 is N + 1,
    countLetters(T,N1,Result);        %fortsätt längs listan med det plussade värdet,
    countLetters(T, N, Result)).      %annars fortsätt med listan med det icke-plussade värdet.



% Skyffla
% Ex. skyffla([1,2,3,4,5],X).
% L70, Iter1 = 1,3,5
% L71, Result2 = 2,4
% L72, skyffla([2,4], Rec). -> Rec = [2,4]
% L73, Result = [1,3,5,2,4]
skyffla([],[]) :- !.
skyffla([X],[X]) :- !.
skyffla([H|R], Result) :-
    takeSeconds([H|R],Iter1),          %Första iterationen på input, sparas i Iter1
    takeSeconds(R,Result2),          %Kör takeSeconds på svansen,då sparas de jämna
    skyffla(Result2,Rec),          %skyfflar de jämna, rekursivt anrop
    append(Iter1,Rec,Result).      %lägger till resultatet från första iterationen och det rekursiva anropet

%hjälpfunktion, tar vartannat element från input-listan.
takeSeconds([],[]).
takeSeconds([H],[H|R]):-
    skyffla([],R).
takeSeconds([H,_|T],[H|R]) :-
    takeSeconds(T,R).
