# -*- coding: utf-8 -*-


class Game (object):
    """
    Represents a bowling game.
    """
    _max_number_of_frames = 10

    def __init__ (self):
        self.score = 0
        self.frames = []
        self.__current_frame = []
        self.__multiplier = [1 for i in range (2)]
        self.__frame_rolls = 2

    def is_last_frame (self):
        """
        Returns if the current frame is the last one.
        """
        return len (self.frames) == Game._max_number_of_frames - 1

    def roll (self, pins_down):
        """
        The ball is rolling and some pins are knocked down.
        """
        if pins_down < 0 or pins_down > 10:
            raise PinsDownError ('the number of pins down must be between' \
                                 ' zero and ten')

        elif not self.is_last_frame () and len (self.__current_frame) > 0 \
             and self.__current_frame[0] + pins_down > 10:
            raise PinsDownError ('the number of pins down in a frame can\'t ' \
                                 'be greater than ten')

        elif len (self.frames) == Game._max_number_of_frames:
            raise NumberOfFramesError ('the maximum number of frames must be' \
                                       ' %d' % Game._max_number_of_frames)

        # Add roll score to final score and to the current frame
        self.score += self.__multiplier.pop (0) * pins_down
        self.__multiplier.append (1)
        self.__current_frame.append (pins_down)

        # An strike or an spare multiplies the following scores when they aren't
        # in the last frame
        if not self.is_last_frame ():
            # An strike
            if pins_down == 10:
                self.__multiplier = [i + 1 for i in self.__multiplier]
            # An spare
            elif sum (self.__current_frame) == 10:
                self.__multiplier[0] += 1

        # An strike or an spare in the last frame gives extra rolls
        elif sum (self.__current_frame) == 10:
            self.__frame_rolls += 1

        # The current frame is over
        if len (self.__current_frame) == self.__frame_rolls \
           or (pins_down == 10 and not self.is_last_frame ()):
            self.frames.append (self.__current_frame)
            self.__current_frame = []


class NumberOfFramesError (Exception):
    pass

class PinsDownError (Exception):
    pass

