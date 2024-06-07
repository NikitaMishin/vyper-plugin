
x: constant(uint256) = max(uint256)
y: immutable(address)

@public
def __init__():
    self.y = empty(address)

@public
def test()->bool:
    return True

@external
def ternary(a: uint256, b: uint256) -> uint256:
    return a if a > b else b

@external
def nested_constant_list_accessor() -> bool:
    f: uint256 = 1
    a: bool = 1 == [1,2,4][f] + -1
    return a
