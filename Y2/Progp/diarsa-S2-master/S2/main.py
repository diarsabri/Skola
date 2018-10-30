# Authors: Kevin Nordwall & Diar Sabri

import parser
import lexer
from leonardo import Leonardo
from wrongsyntax import WrongSyntax


def execute(p, leo):
    string = []
    if not p:
        return []
    if p.down:
        for i in range(p.inst.num):
            string += execute(p.down, leo)
        if p.next:
            return string + execute(p.next, leo)
        else:
            return string
    else:
        return p.inst.evaluate(leo) + execute(p.next, leo)


def main():
    # Skapar Leonardo objektet som håller koll på position, om pennan är uppe eller nere
    # och vilken färg som det ska målas med
    leo = Leonardo()

    # Skapar objektet som ska bryta ner textkommandon till riktiga kommandon
    # som sen ska skickas till Leonardo objektet
    # Läser in från stdin när den initieras och gör om stdin till kommandon
    # lex = innehåller en lista av tokens
    lex = lexer.Lexer()

    # EXECUTE
    try:
        # Skickar in lex objektet som innehåller listan av tokens
        # Får tillbaka ett träd av instruktioner
        parse_tree = parser.Parser(lex).tree

        # Kör igenom trädet och instruktionerna i rätt ordning
        # Får tillbaka en lista av strängar som innehåller färg och position
        string = execute(parse_tree, leo)
        for i in string:
            print(i)

    except WrongSyntax as e:
        print("Syntaxfel på rad " + str(e))


if __name__ == '__main__':                         
    main()
