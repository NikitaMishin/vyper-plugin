### Press F or not to press F contract example
initial_distribution:constant(int128) = 100

creator : address
address_to_tokens : map(address,int128)

address_is_vote : map(address,bool)

current_proposal : bytes[100]

vote_for_yes:int128
vote_for_no:int128

all_tokens:int128

is_finished: bool
is_accepted: bool


@public
def __init__(proposal : bytes[100]):
    self.creator = msg.sender
    self.current_proposal = proposal

# test address ='0x7E5F4552091A69125d5DfCb7b8C2659029395Bdf'
@public
def register()->int128:
    """
       register @new_user and provide token for him in amount of @initial_distribution

    """
    assert self.address_to_tokens[msg.sender] == 0, "Already registered"
    self.address_to_tokens[msg.sender] = initial_distribution
    self.all_tokens += initial_distribution
    return self.all_tokens

@public
def vote(for_yes:bool):
    assert not self.address_is_vote[msg.sender], "Already voted"
    assert self.address_to_tokens[msg.sender] > 0, "No tokens"
    if for_yes:
        self.vote_for_yes += self.address_to_tokens[msg.sender]
    else:
        self.vote_for_no += self.address_to_tokens[msg.sender]

    self.address_is_vote[msg.sender] = True

@public
def get_result()->bool:
    assert self.is_finished, "Not finished"
    return self.is_accepted

@public
def finish_vote():
    assert msg.sender == self.creator, "Not creator"


    if self.vote_for_no < self.vote_for_yes:
        self.is_accepted = True
    else:
        self.is_accepted = False

    self.vote_for_yes = 0
    self.vote_for_no = 0
    self.is_finished = True

@public
def get_current_proposal()->bytes[100]:
    return self.current_proposal