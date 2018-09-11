%Authors: Diar Sabri & Kevin Nordwall
spider(X) :-
  person(X),
  isSpider(X).

isSpider(X) :-
  setof(Z,knowledge(X,Z),RelatedToX),   %All of X's friends
  setof(All,person(All),Everybody),   %Everybody who is a person
  relatedSomeWay(Everybody,[X|RelatedToX]),   %To make the conspiracy reach everybody, either the spider or his friends have to know everybody.
  subtract(Everybody,[X|RelatedToX],NoRelationToX),   %Subtract to get a list of people with no relation to X
  generateAccusedList(RelatedToX,[],NoRelationToX,AccusedConsp),  %Get the conspirators
  relatedSomeWay(NoRelationToX,AccusedConsp),!.   %Check if those who have no relation to X know at least one conspirator

generateAccusedList([],AccusedConsp,_,AccusedConsp).
generateAccusedList([H|T],List,NoRelationToX,AccusedConsp) :-
  (\+ relatedSomeWay(H,List)),  %There can be no relation between H and List
  setof(Friends,knowledge(H,Friends),FriendsOfH), %Create list of H's friends
  subtract(T,FriendsOfH,List2), %Subtract to get a list of people who don't know H
  generateAccusedList(List2,[H|List],NoRelationToX,AccusedConsp).
  %Return:
    %List2 = List of those who don't know H (P')
    %[H|List] = List of the accused conspirators (K')
    %Same as previous, people who don't know X
    %The to-be list of accused conspirators

generateAccusedList([H|T],List,NoRelationToX,AccusedConsp) :-
  append(T,List,KnowsOneOrMore),  %Append list of those who dont know H and the accused conspirators
  relatedSomeWay(NoRelationToX,KnowsOneOrMore),   %Check so that there is a relation between K|P and K'|P'
  generateAccusedList(T,List,[H|NoRelationToX],AccusedConsp).
  %Return:
    %T = List2 = Those who don't know H
    %List = List = accused conspirators
    %[H|NoRelationToX] = Those who don't know X, plus H (which has no connection to X,now proven)
    %The to-be list of accused conspirators

relatedSomeWay([],_) :- !.
relatedSomeWay([X|[]],List) :- friendsInList(X,List).   %If one element, check if any connection
relatedSomeWay([X|Xt],List) :- friendsInList(X,List), relatedSomeWay(Xt,List),!.  %For more than one element, do the same recursively

friendsInList(X, List) :- knowledge(X, Y), member(Y, List), !.  %Check if there is any connection between X and the people in List
friendsInList(X, List) :- member(X,List).

knowledge(X,Y) :- knows(X,Y); knows(Y,X).
