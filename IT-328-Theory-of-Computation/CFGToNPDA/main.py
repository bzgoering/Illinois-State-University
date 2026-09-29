#Note that this file has been debugged by AI
#By: Ben Goering
from collections import defaultdict

#function to processes the grammar
def read_cfg(filename):
    grammar = defaultdict(list)
    start_character = None

    with open(filename, "r") as f:
        #main loop, goes line by line
        for line in f:
            #removes junk
            line = line.strip()
            if not line:
                continue
            
            #splits up rule
            variables, definition = line.split("->")
            variables = variables.strip()

            #gets each character in the definition portion
            for p in definition.split("|"):
                    p = p.strip()
                    if p == "empty":
                        grammar[variables].append("")
                    else:
                        grammar[variables].append(p)

            #gets start variable
            if start_character is None:
                start_character = variables
    #returns the processed context free grammar
    return start_character, grammar

#function to build a NPDA from grammar
def build_npda(start, grammar):
    transitions = defaultdict(list)

    #setting up actual valid character
    transitions["q0"].append(("a", "a", "", "q0"))
    transitions["q0"].append(("b", "b", "", "q0"))

    #traverse through each production of each grammar variable
    for variable, production in grammar.items():
        for character in production:
            push = "".join(reversed(character))
            transitions["q0"].append(("empty", variable, push, "q0"))

    #handles start and accept state
    transitions["q0"].append(("empty", "z", start + "z", "q0"))
    transitions["q0"].append(("empty", "z", "z", "q1"))

    return transitions

#just formats the transitions for output
def format_npda(transitions):
    output = "q0q1f, "

    grouped = defaultdict(list)
    for curr_state, transition_list in transitions.items():
        for read, pop, push, destination in transition_list:
            grouped[(curr_state, destination)].append(f"{read}-{pop}-{push}")

    parts = []
    for (curr_state, destination), lst in grouped.items():
        joined = "|".join(lst)
        parts.append(f"{curr_state}-{joined}->{destination}")

    return output + ", ".join(parts)

#The following 2 functions were partially created by AI
#function to test NPDA on a language
def cfg_accepts(start, grammar, language):
    language_size = len(language)
    can = defaultdict(lambda: [[False]*(language_size+1) for _ in range(language_size+1)]) 

    #finds possible places where empty string can be found
    for variable, production in grammar.items():
        for character in production:
            if character == "":
                for i in range(language_size+1):
                    can[variable][i][i] = True

    #finds possible places where a certain character can be found
    for i in range(language_size):
        for variable, production in grammar.items():
            for character in production:
                if character == language[i]:
                    can[variable][i][i+1] = True

    #loops through the possible characters or empty string with actual language
    changed = True
    while changed:
        changed = False
        for length in range(2, language_size + 1):
            for i in range(0, language_size - length + 1):
                j = i + length
                for variable, production in grammar.items():
                    if can[variable][i][j]:
                        continue
                    for character in production:
                        if character == "":
                            continue
                        if match_sequence_bottom_up(character, i, j, can, language):
                            can[variable][i][j] = True
                            changed = True

    return can[start][0][language_size]

#helper function for testing
def match_sequence_bottom_up(seq, i, j, can, w):
    k = len(seq)
    L = j - i  

    dp = [[False] * (L + 1) for _ in range(k + 1)]
    dp[0][0] = True

    for pos in range(k):
        sym = seq[pos]
        for x in range(L + 1):
            if not dp[pos][x]:
                continue

            if sym in ("a", "b"):
                if x < L and w[i + x] == sym:
                    dp[pos + 1][x + 1] = True

            else:

                for y in range(x, L + 1):
                    if can[sym][i + x][i + y]:
                        dp[pos + 1][y] = True


    return dp[k][L]

#testing phase
def test(file,language1,language2):
    #creates CFG from file
    start, grammar = read_cfg(file)
    #creates NPDA
    transitions = build_npda(start, grammar)

    #append to output
    with open("output1.txt", "a") as file:
        #gets NPDA transitions
        output = "NPDA:"
        output += format_npda(transitions) + "\n"
        
        #tests if languages accept or not
        if cfg_accepts(start, grammar, language1):
            output += language1 + " - accept\n" 
        else:
            output += language1 + " - reject\n"
        
        if cfg_accepts(start, grammar, language2):
            output += language2 + " - accept\n\n" 
        else:
            output += language2 + " - reject\n\n"
        file.write(output)

#erase previous output
with open("output1.txt","w") as file:
    file.flush()

#test calls for 1-5
test("test1.txt","aababb","aaaaabbbbb")
test("test2.txt","baabba","aaabbb")
test("test3.txt","bbaabaaaaa","abaaba")
test("test4.txt","baabaa","abaabab")
test("test5.txt","bbbaab","aababbb")