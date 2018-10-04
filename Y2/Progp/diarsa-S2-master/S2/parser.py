from instructions_node import *
from wrongsyntax import WrongSyntax
import sys
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
        if self.lex.end_symbol():
            parse = self.instructions()
            while self.lex.end_symbol():
                parse.next = self.parse()
            return parse
        elif self.lex.token_type() == "ERROR":
            raise WrongSyntax(self.lex.get().row)
        
    def instructions(self):
        p = ParseTree()
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

        elif self.lex.token_type() == "REP":
            token = self.lex.get()
            if self.lex.end_of_file():
                eof = self.lex.get()
                raise WrongSyntax(token.row)
            else:
                if self.lex.token_type() == "NUM":
                    num = self.lex.get()
                    p.inst = Rep(token,num)
                    if self.lex.token_type() == "QUOTE":
                        quote = self.lex.get()
                        p.down = self.parse()
                        if p.down:
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
                    else:
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
