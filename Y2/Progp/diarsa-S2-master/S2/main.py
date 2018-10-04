# Authors: Kevin Nordwall & Diar Sabri

import lexer
import parser
from leonardo import Leonardo
from wrongsyntax import WrongSyntax

def execute(p,leo):
    string = []
    if not p:
        return []
    if p.down:
        for i in range(p.inst.num):
            string += execute(p.down,leo)
        if p.next:
            return string + execute(p.next,leo)
        else:
            return string
    else:
        return p.inst.evaluate(leo) + execute(p.next,leo)


def main():
    leo = Leonardo()
    lex = lexer.Lexer()
    # EXECUTE
    try:
        parse_tree = parser.Parser(lex).tree
        string = execute(parse_tree,leo)
        for i in string:
            print(i)
    # except AttributeError:
    #     pass
    except WrongSyntax as e:
        print("Syntaxfel på rad " + str(e))



if __name__ == '__main__':
    main()
