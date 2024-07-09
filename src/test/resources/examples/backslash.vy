x: constant(int256) =\
    100
ab: uint256

@internal
def set_x(new_val: uint256):
    self.ab = \
        new_val + \
        100
    self.ab = (
        self.ab /
        (100 / 100)
    )

@external
def foo() -> uint256:
    return self.ab
