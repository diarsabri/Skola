% Diar Sabri
% Lab3 DD1351

% Load model, initial state and formula from file.
verify(Input) :-
  see(Input), read(T), read(L), read(S), read(F), seen,
  check(T,      % T - The transitions in form of adjacency lists
        L,      % L - The labeling
        S,      % S - Current state
        [],     % U - Currently recorded states (empty at first)
        F).     % F - CTL Formula to check.
% Should evaluate to true iff the sequent below is valid.
% (T,L), S |- F
%           U

% Returns the list for given state
list([[S, P]|_], S, P) :- !.          % Base 
list([_|T], S, P) :- list(T, S, P).   % Recursive

% Basic
check(_, L, S, [], F) :-
  list(L, S, Fres),              % Calls function above to retreive the list
  member(F, Fres).               % Control for membership

% Iteration over all states, recursive
checkAll(_, _, [], _, _).                 % Base case
checkAll(T, L, [S|States], U, F) :-
  check(T, L, S, U, F), !,                    % Controls that the formula holds in current state.
  checkAll(T, L, States, U, F).           % Recursive

% Negation
check(_, L, S, [], neg(F)) :-
  list(L, S, Fres),             % Returns a list with formulas to the current state
  \+ member(F, Fres).           % Controls that the contents of F are not a part of the list from above

% And
check(T, L, S, [], and(F1, F2)) :-
  check(T, L, S, [], F1),
  check(T, L, S, [], F2).

% Or 1
% Controls whether F is among the states
check(T, L, S, [], or(F, _)) :-
  check(T, L, S, [], F), !.

% Or 2
% Same
check(T, L, S, [], or(_, F)) :-
  check(T, L, S, [], F), !.


% AX
check(T, L, S, U, ax(F)) :-
  list(T, S, P),             % Gets list with paths from current to all other states
  checkAll(T, L, P, U, F).   % Controls that F is valid in all these states

% AG (I)
check(_, _, S, U, ag(_)) :-
  member(S, U).                      % Quit if current already visited
% AG (II)
check(T, L, S, U, ag(F)) :-
  check(T, L, S, [], F),             % Check F holds in current state
                                     % Add current state as visited, check rest of states
  check(T, L, S, [S|U], ax(ag(F))).  % from current. AG should hold there as well

% AF (I)
check(T, L, S, U, af(F)) :-
  \+ member(S, U),                   % Check current state not visited
  check(T, L, S, [], F).             % Check F holds in current state
% AF (II)
check(T, L, S, U, af(F)) :-
  \+ member(S, U),                   % Check current state not visited
  check(T, L, S, [S|U], ax(af(F))).  


% EX
check(T, L, S, U, ex(F)) :-
  list(T, S, States),          % Gets list with paths from current to all other states
  member(S1, States),          % Finds a state where F holds
  check(T, L, S1, U, F).       % Loops through all states until satisfactory (F holds)

% EG (I)
check(_, _, S, U, eg(_)) :-
  member(S, U), !.               % Quit if current already visited
% EG (II)
check(T, L, S, U, eg(F)) :-
  check(T, L, S, [], F),         % Check F holds in current state
  list(T, S, P),                 % Gets list with paths from current to all other states
  member(S1, P),                 % Finds a state where F holds
                                 % Loops through all states until satisfactory (F holds)
  check(T, L, S1, [S|U], eg(F)). % Mark current state as visited

% EF (I)
check(T, L, S, U, ef(F)) :-
  \+ member(S, U),                   % Check current state not visited
  check(T, L, S, [], F).             % Check F holds in current state
% EF (II)
check(T, L, S, U, ef(F)) :-
  \+ member(S, U),                   % Check current state not visited
  list(T, S, P),                     % Gets list with paths from current to all other states
  member(S1, P),                     % Finds a state where F holds
                                     % Loops through all states until satisfactory (F holds)
  check(T, L, S1, [S|U], ef(F)).     % Mark current state as visited
