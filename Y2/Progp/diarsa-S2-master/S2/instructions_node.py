from leonardo import *


class Move:

    def __init__(self, token, num):
        self.token = token
        self.num = num

    def evaluate(self, leo):
        return leo.change_position(self.token.token, self.num.data)


class Turn:

    def __init__(self, token, num):
        self.token = token
        self.num = num

    def evaluate(self, leo):
        return leo.change_angle(self.token.token, self.num.data)


class Color:

    def __init__(self, token, hex_code):
        self.token = token
        self.hex_code = hex_code

    def evaluate(self, leo):
        return leo.change_color(self.hex_code)


class Pen:

    def __init__(self, token):
        self.token = token

    def evaluate(self, leo):
        return leo.change_pen_mode(self.token.token)


class Rep:

    def __init__(self, token, num):
        self.token = token
        self.num = num.data
