### Notes

•Tokenization (1.1)
•Basic indexing (1.2)
•Intersection search (1.3)
•Phrase search (1.4)
•Evaluation (1.5)
•Query construction (1.6)
•Large indexes on disk (1.7)
•Merging indexes (1.8)


<!-- 
positionalintersect function taken from book at page 42.

 -->



1.1: Regex
Trial & error, try to generalize regex to fit all kinds of situations for that specifik part.

1.3: Inverted index
Altered files:
HashedIndex, PostingsList, PostingEntry, Query, Searcher

The indexing is constructed as a sparse matrix. A hashtable is used for the dictionary and the rows in the hashtable are sorted arraylists, called postings lists. Each entry in the list is linked to a document id.

HashedIndex:
Implements an inverted index as a hashtable.
Functions: insert entry (token-entry), 

PostingsList:
struct: docMap (hashmap of the documents)
funcs: printpostings, addtopostingslist (for three inputs)

PostingsEntry:
struct: offsets (arraylist)
funcs: get/add offsets

