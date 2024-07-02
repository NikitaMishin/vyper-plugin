m: HashMap[uint256, uint256]

@external
def foo():
    self.m[2 ** 256] = 2
