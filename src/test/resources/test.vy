interface ERC20:
    def balanceOf(who: address) -> uint256: view

@external
def myBalance(coin: address) -> uint256:
    return ERC20(coin).balanceOf(self)
