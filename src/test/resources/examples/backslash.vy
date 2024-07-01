x: constant(int256) =\
    100

@public
def set_x(new_x: int256) -> None:
    self.x = \
        new_x + \
        100
    self.x = (
        self.x /
        (100 / 100)
    )

ab: uint256
@public
def foo() -> uint256:
    return self.ab