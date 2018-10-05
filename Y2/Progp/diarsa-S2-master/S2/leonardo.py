import math


class Leonardo:

    def __init__(self):
        self.x = 0
        self.y = 0
        self.angle = 0  # Leonardo kollar rakt åt höger från början.
        self.color = "#0000FF"
        self.pen_mode = "UP"

    def change_position(self, token, num):
        #       x+dcos(πv/180)              y+dsin(πv/180)
        x1 = self.x
        y1 = self.y

        if token == "FORW":
            d = num
        else:
            d = -1*num
        distance_x = d*math.cos(math.pi*self.angle/180)
        distance_y = d*math.sin(math.pi*self.angle/180)
        self.x = self.x + distance_x
        self.y = self.y + distance_y

        if self.pen_mode == "DOWN":
            return ["{} {:.4f} {:.4f} {:.4f} {:.4f}".format(self.color, x1, y1, self.x, self.y)]
        return []

    def change_pen_mode(self, token):
        if token == "UP":
            self.pen_mode = "UP"
        else:
            self.pen_mode = "DOWN"
        return []

    def change_color(self, hex_code):
        self.color = hex_code.data
        return []

    def change_angle(self, token, angle):
        if token == "LEFT":
            self.angle = self.angle + angle
        else:
            self.angle = self.angle - angle
        return []
