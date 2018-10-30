import sys

from instructions_node import Move, Turn, Color, Pen, Rep
from wrongsyntax import WrongSyntax

sys.setrecursionlimit(400000)


class ParseTree:
    def __init__(self):
        self.inst = None
        self.next = None
        self.down = None


class Parser:

    def __init__(self, lex):
        self.lex = lex
        self.tree = self.parse() or []
        eof = self.lex.get()
        if eof.token != "EOF":
            raise WrongSyntax(self.lex.get_last().row)

    def parse(self):
        if self.lex.end_symbol(): # Kollar om det är en slutsymbol ex. "FORW"
            parse = self.instructions()
            while self.lex.end_symbol():
                parse.next = self.parse()
            return parse
        elif self.lex.token_type() == "ERROR":
            raise WrongSyntax(self.lex.get().row)

    def instructions(self):
        p = ParseTree()  # Skapar ett instruktionsträd
        if self.lex.token_type() == "FORW" or self.lex.token_type() == "BACK":
            token = self.lex.get()
            if self.lex.end_of_file():
                eof = self.lex.get()
                raise WrongSyntax(token.row)
            else:
                if self.lex.token_type() == "NUM":
                    num = self.lex.get()
                    if self.lex.token_type() == "DOT":
                        self.lex.get()
                        # När vi kommer till DOT vet vi att instruktionen är klar
                        # Då kan vi skapa instruktionen
                        # och sätta instruktionen i trädet till instruktionen vi har kommit fram till
                        p.inst = Move(token, num)
                    else:
                        if self.lex.token_type() == "EOF":
                            raise WrongSyntax(self.lex.get_last().row)
                        else:
                            not_dot = self.lex.get()
                            raise WrongSyntax(not_dot.row)

                else:
                    not_num = self.lex.get()
                    raise WrongSyntax(not_num.row)

        elif self.lex.token_type() == "RIGHT" or self.lex.token_type() == "LEFT":
            token = self.lex.get()
            if self.lex.end_of_file():
                eof = self.lex.get()
                raise WrongSyntax(token.row)
            else:
                if self.lex.token_type() == "NUM":
                    num = self.lex.get()
                    if self.lex.token_type() == "DOT":
                        self.lex.get()
                        p.inst = Turn(token, num)
                    else:
                        if self.lex.token_type() == "EOF":
                            raise WrongSyntax(self.lex.get_last().row)
                        else:
                            not_dot = self.lex.get()
                            raise WrongSyntax(not_dot.row)
                else:
                    not_num = self.lex.get()
                    raise WrongSyntax(not_num.row)

        elif self.lex.token_type() == "COLOR":
            token = self.lex.get()
            if self.lex.end_of_file():
                eof = self.lex.get()
                raise WrongSyntax(token.row)
            else:
                if self.lex.token_type() == "HEX":
                    num = self.lex.get()
                    if self.lex.token_type() == "DOT":
                        self.lex.get()
                        p.inst = Color(token, num)
                    else:
                        if self.lex.token_type() == "EOF":
                            raise WrongSyntax(self.lex.get_last().row)
                        else:
                            not_dot = self.lex.get()
                            raise WrongSyntax(not_dot.row)
                else:
                    not_hex = self.lex.get()
                    raise WrongSyntax(not_hex.row)

        elif self.lex.token_type() == "UP" or self.lex.token_type() == "DOWN":
            token = self.lex.get()
            if self.lex.end_of_file():
                eof = self.lex.get()
                raise WrongSyntax(token.row)
            else:
                if self.lex.token_type() == "DOT":
                    self.lex.get()
                    p.inst = Pen(token)
                else:
                    if self.lex.token_type() == "EOF":
                        raise WrongSyntax(self.lex.get_last().row)
                    else:
                        not_dot = self.lex.get()
                        raise WrongSyntax(not_dot.row)

        # Vi vill hantera REP instruktioner lite annorlunda eftersom den instruktionen ska upprepa andra instruktioner
        elif self.lex.token_type() == "REP":
            token = self.lex.get()
            if self.lex.end_of_file():
                eof = self.lex.get()
                raise WrongSyntax(token.row)
            else:
                if self.lex.token_type() == "NUM":
                    num = self.lex.get()
                    p.inst = Rep(token, num)

                    # När vi kommer till första quoten vill vi gå ner i trädet
                    # För sen när vi ska gå igenom trädet kommer vi att köra djupet först exekvering
                    if self.lex.token_type() == "QUOTE":
                        quote = self.lex.get()
                        p.down = self.parse()  # Här går vi då ner och skapar ett nytt parsetree
                        if p.down:  # Om vi lyckats få nåt tillbaka tar vi bort slut-quoten
                            if self.lex.token_type() == "QUOTE":
                                quote = self.lex.get()
                            else:
                                if self.lex.end_of_file():
                                    raise WrongSyntax(self.lex.get_last().row)
                                else:
                                    raise WrongSyntax(self.lex.get().row)
                        else:
                            if self.lex.end_of_file():
                                raise WrongSyntax(self.lex.get_last().row)
                            else:
                                raise WrongSyntax(self.lex.get().row)
                    else:  # Kommer hit om det är en REP funktion utan quotes, dvs en enkel REP funktion
                        p.down = self.instructions()
                else:
                    not_num = self.lex.get()
                    raise WrongSyntax(not_num.row)

        else:
            if self.lex.end_of_file():
                raise WrongSyntax(self.lex.get_last().row)
            else:
                raise WrongSyntax(self.lex.get().row)
        return p
