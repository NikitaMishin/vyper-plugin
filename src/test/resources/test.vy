struct Foo:
    ab: uint256

@external
def foo() -> Foo:
    ab: uint256 = 1
    return Foo({ab: ab})