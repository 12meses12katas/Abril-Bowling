# -*- coding: utf-8 -*-


class Game (object):
    """
    Represents a bowling game.
    """

    def __init__ (self):
        self.score = 0
        self.frames = []
        self.__current_frame = []

    def roll (self, pins_down):
        """
        The ball is rolling and some pins are knocked down.
        """
        if pins_down < 0 or pins_down > 10:
            raise PinsDownError ('the number of pins down must be between' \
                                 ' zero and ten')

        elif len (self.__current_frame) > 0 \
             and self.__current_frame[0] + pins_down > 10:
            raise PinsDownError ('the number of pins down in a frame can\'t ' \
                                 'be greater than ten')

        # Add roll score to final score and to the current frame
        self.score += pins_down
        self.__current_frame.append (pins_down)

        if len (self.__current_frame) == 2:
            self.frames.append (self.__current_frame)
            self.__current_frame = []


class PinsDownError (Exception):
    pass

