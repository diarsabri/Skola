import re
from sys import stdin

from token_class import Token


class Lexer:

    def __init__(self):
        self.current_token = 0
        self._regex = r"(?i)FORW(\s|%.*\n)|BACK(\s|%.*\n)|RIGHT(\s|%.*\n)|LEFT(\s|%.*\n)|DOWN|UP|REP(\s|%.*\n)|COLOR(\s|%.*\n)|%.*\n|\.|\"|#[A-Fa-f0-9]{6}|[1-9]\d*(\s|%.*\n|\.)|\n|\040|\t"
        self._final_string = self.input_to_string()
        self.token_list = self.lexer()

    def input_to_string(self):
        """
        Läser från stdin, vilket är kommandona som ska utföras
        :return: String
        """

        final_string = ""
        for row in stdin:
            final_string = final_string + row

        # final_string = "% Det här är en kommentar\n% Nu ritar vi en kvadrat\nDOWN.\nFORW 1. LEFT 90.\nFORW 1. LEFT 90.\nFORW 1. LEFT 90.\nFORW 1. LEFT 90."
        return final_string

    def lexer(self):
        """
        Bryter ned strängen med regex och skapar Tokens av de alla olika kommandosarna.
        :return: List of Tokens
        """
        token_list = []
        matches = re.finditer(self._regex, self._final_string)
        input_pos = 0
        current_row = 1

        for match in matches:
            # print(match)
            if match.start() != input_pos:
                token_list.append(Token("ERROR", current_row))
            if match.group().upper() == "FORW\n" or match.group().upper()[0:5] == "FORW%":
                token_list.append(Token("FORW", current_row))
                current_row += 1
            elif match.group().upper() == "FORW" or match.group().upper() == "FORW " or match.group().upper() == "FORW\t":
                token_list.append(Token("FORW", current_row))
            elif match.group().upper() == "BACK\n" or match.group().upper()[0:5] == "BACK%":
                token_list.append(Token("BACK", current_row))
                current_row += 1
            elif match.group().upper() == "BACK" or match.group().upper() == "BACK " or match.group().upper() == "BACK\t":
                token_list.append(Token("BACK", current_row))
            elif match.group().upper() == "RIGHT\n" or match.group().upper()[0:6] == "RIGHT%":
                token_list.append(Token("RIGHT", current_row))
                current_row += 1
            elif match.group().upper() == "RIGHT" or match.group().upper() == "RIGHT " or match.group().upper() == "RIGHT\t":
                token_list.append(Token("RIGHT", current_row))
            elif match.group().upper() == "LEFT\n" or match.group().upper()[0:5] == "LEFT%":
                token_list.append(Token("LEFT", current_row))
                current_row += 1
            elif match.group().upper() == "LEFT" or match.group().upper() == "LEFT " or match.group().upper() == "LEFT\t":
                token_list.append(Token("LEFT", current_row))
            elif match.group().upper() == "COLOR\n" or match.group().upper()[0:6] == "COLOR%":
                token_list.append(Token("COLOR", current_row))
                current_row += 1
            elif match.group().upper() == "COLOR" or match.group().upper() == "COLOR " or match.group().upper() == "COLOR\t":
                token_list.append(Token("COLOR", current_row))
            elif match.group().upper() == "REP\n" or match.group().upper()[0:4] == "REP%":
                token_list.append(Token("REP", current_row))
                current_row += 1
            elif match.group().upper() == "REP" or match.group().upper() == "REP " or match.group().upper() == "REP\t":
                token_list.append(Token("REP", current_row))
            elif match.group().upper() == "DOWN":
                token_list.append(Token("DOWN", current_row))
            elif match.group().upper() == "UP":
                token_list.append(Token("UP", current_row))
            elif match.group()[0] == "%":
                current_row += 1
            elif match.group() == ".":
                token_list.append(Token("DOT", current_row))
            elif match.group() == "\"":
                token_list.append(Token("QUOTE", current_row))
            elif match.group()[0] == "#":
                token_list.append(Token("HEX", current_row, match.group()))
            elif match.group() == "\n":
                current_row += 1
            elif match.group()[0].isdigit() and match.group()[0] != "0":
                s = ""
                for i in match.group():
                    if i.isdigit():
                        s = s + i
                token_list.append(Token("NUM", current_row, int(s)))
                if i == "%":
                    current_row += 1
                elif i == ".":
                    token_list.append(Token("DOT", current_row))
                elif i == "\n":
                    current_row += 1
            input_pos = match.end()

        if input_pos != len(self._final_string):
            token_list.append(Token("ERROR", current_row))

        token_list.append(Token("EOF", current_row))

        return token_list

    def peek(self):
        return self.token_list[self.current_token]

    def get(self):
        token = self.peek()
        self.current_token += 1
        return token

    def get_last(self):
        return self.token_list[self.current_token - 1]

    def token_type(self):
        return self.peek().token

    def end_symbol(self):
        if self.token_type() == "FORW" or self.token_type() == "BACK" or self.token_type() == "RIGHT" or self.token_type() == "LEFT" or self.token_type() == "COLOR" or self.token_type() == "REP" or self.token_type() == "UP" or self.token_type() == "DOWN":
            return True
        else:
            return False

    def end_of_file(self):
        if self.token_type() == "EOF":
            return True
        else:
            return False


def main():
    lex = Lexer()
    for i in lex.token_list:
        print(i.token, i.row)


if __name__ == '__main__':
    main()
