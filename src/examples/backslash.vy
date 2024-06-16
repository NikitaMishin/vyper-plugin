x: constant(int256) =\
    100

@external
def set_x(new_x: int256) -> None:
    self.x = \
        new_x + \
        100
    self.x = (
        self.x /
        (100 / 100)
    )
