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
        final_string = ""
        for row in stdin:
            final_string = final_string + row

        # final_string = "% Det här är en kommentar\n% Nu ritar vi en kvadrat\nDOWN.\nFORW 1. LEFT 90.\nFORW 1. LEFT 90.\nFORW 1. LEFT 90.\nFORW 1. LEFT 90."
        return final_string

    def lexer(self):
        token_list = []
        matches = re.finditer(self._regex, self._final_string)
        inputPos = 0
        currentRow = 1

        for match in matches:
            # print(match)
            if match.start() != inputPos:
                token_list.append(Token("ERROR", currentRow))
            if match.group().upper() == "FORW\n" or match.group().upper()[0:5] == "FORW%":
                token_list.append(Token("FORW", currentRow))
                currentRow += 1
            elif match.group().upper() == "FORW" or match.group().upper() == "FORW " or match.group().upper() == "FORW\t":
                token_list.append(Token("FORW", currentRow))
            elif match.group().upper() == "BACK\n" or match.group().upper()[0:5] == "BACK%":
                token_list.append(Token("BACK", currentRow))
                currentRow += 1
            elif match.group().upper() == "BACK" or match.group().upper() == "BACK " or match.group().upper() == "BACK\t":
                token_list.append(Token("BACK", currentRow))
            elif match.group().upper() == "RIGHT\n" or match.group().upper()[0:6] == "RIGHT%":
                token_list.append(Token("RIGHT", currentRow))
                currentRow += 1
            elif match.group().upper() == "RIGHT" or match.group().upper() == "RIGHT " or match.group().upper() == "RIGHT\t":
                token_list.append(Token("RIGHT", currentRow))
            elif match.group().upper() == "LEFT\n" or match.group().upper()[0:5] == "LEFT%":
                token_list.append(Token("LEFT", currentRow))
                currentRow += 1
            elif match.group().upper() == "LEFT" or match.group().upper() == "LEFT " or match.group().upper() == "LEFT\t":
                token_list.append(Token("LEFT", currentRow))
            elif match.group().upper() == "COLOR\n" or match.group().upper()[0:6] == "COLOR%":
                token_list.append(Token("COLOR", currentRow))
                currentRow += 1
            elif match.group().upper() == "COLOR" or match.group().upper() == "COLOR " or match.group().upper() == "COLOR\t":
                token_list.append(Token("COLOR", currentRow))
            elif match.group().upper() == "REP\n" or match.group().upper()[0:4] == "REP%":
                token_list.append(Token("REP", currentRow))
                currentRow += 1
            elif match.group().upper() == "REP" or match.group().upper() == "REP " or match.group().upper() == "REP\t":
                token_list.append(Token("REP", currentRow))
            elif match.group().upper() == "DOWN":
                token_list.append(Token("DOWN", currentRow))
            elif match.group().upper() == "UP":
                token_list.append(Token("UP", currentRow))
            elif match.group()[0] == "%":
                currentRow += 1
            elif match.group() == ".":
                token_list.append(Token("DOT", currentRow))
            elif match.group() == "\"":
                token_list.append(Token("QUOTE", currentRow))
            elif match.group()[0] == "#":
                token_list.append(Token("HEX", currentRow, match.group()))
            elif match.group() == "\n":
                currentRow += 1
            elif match.group()[0].isdigit() and match.group()[0] != "0":
                s = ""
                for i in match.group():
                    if i.isdigit():
                        s = s + i
                token_list.append(Token("NUM", currentRow, int(s)))
                if i == "%":
                    currentRow += 1
                elif i == ".":
                    token_list.append(Token("DOT", currentRow))
                elif i == "\n":
                    currentRow += 1
            inputPos = match.end()

        if inputPos != len(self._final_string):
            token_list.append(Token("ERROR", currentRow))

        token_list.append(Token("EOF", currentRow))

        return token_list

    def peek(self):
        return self.token_list[self.current_token]

    def get(self):
        token = self.peek()
        self.current_token += 1
        return token

    def get_last(self):
        return self.token_list[self.current_token-1]

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
