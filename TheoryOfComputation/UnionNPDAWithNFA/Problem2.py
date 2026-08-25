#Note that this file has been debugged by AI
#By: Ben Goering

from collections import defaultdict, deque

#function for handling the NPDA
#summary: breaks up the string so we can gather the important data for the NPDA
def parse_npda_M(line):
    first, *rest = line.split(",")

    #gets states from the first chunk
    states = []
    index = 0
    while index < len(first):
        if first[index] == 'q':
            state_num = index + 1
            #gets number associated with state (q)
            while state_num < len(first) and first[state_num].isdigit():
                state_num += 1

            #adds state to our list
            states.append(first[index:state_num])
            index = state_num
        else:
            index += 1

    start_state = states[0]
    accept_state = states[-1]
    transitions = defaultdict(list)

    #main loop for parsing transition
    for section in rest:
        #removes junk characters
        section = section.strip()
        if not section:
            continue

        #breaks line up by state and transition
        curr_state, curr_state_dest = section.split("->")
        curr_state_dest = curr_state_dest.strip()

        #splits the current state into each character
        curr_state, transition = curr_state.split("-", 1)
        curr_state = curr_state.strip()

        #loops through section, one rule at a time
        for t in transition.split("|"):
            #gets rid of junk characters
            t = t.strip()
            if not t:
                continue
            
            #read the rules
            read, pop, push = t.split("-")
            read = read.strip()
            pop = pop.strip()
            push = push.strip()

            #handles "empty" rule
            if pop == "empty":
                pop = ""
            if push == "empty":
                push = ""

            transitions[curr_state].append((read, pop, push, curr_state_dest))

    #returns the NPDA we processed
    return {
        "states": states,           
        "start": start_state,
        "accepts": {accept_state},
        "transitions": transitions
    }

#function for handling the NFA
#summary: breaks up the string so we can gather the important data for the NFA
def parse_nfa_N(line):
    line = line.strip()
    first, *rest = line.split(",")

    #begin to process first, gathing states
    states = []
    accepts = set()
    index = 0

    #main loop for first
    while index < len(first):
        #gets rid of junk characters
        if first[index] == 'q':
            state_num = index + 1

            #gets the state number
            while state_num < len(first) and first[state_num].isdigit():
                state_num += 1
            
            #adds state to our list
            state = first[index:state_num]
            states.append(state)

            #checks to see if state accepts
            index = state_num
            if index < len(first) and first[index] == 'f':
                accepts.add(state)
                index += 1
        else:
            index += 1

    start_state = states[0]
    transitions = defaultdict(list)

    #main loop for parsing transition
    for section in rest:
        #gets rid of junk characters
        section = section.strip()
        if not section:
            continue
        
        #breaks section up by the state and transition
        curr_state, curr_transition = section.split("->")
        curr_transition = curr_transition.strip()

        curr_state, transition_char = curr_state.split("-", 1)
        curr_state = curr_state.strip()

        #breaks transtion up
        for character in transition_char.split("|"):
            #gets rid of junk characters
            character = character.strip()
            if not character:
                continue

            #adds transition to the state
            transitions[curr_state].append((character, curr_transition))

    #returns the NFA we processed
    return {
        "states": states,
        "start": start_state,
        "accepts": accepts,     
        "transitions": transitions
    }

#Note this function was partially created and debugged by AI
#Creates intersect machine using NPDA and NFA
def build_intersection_npda(M, N):
#The following the built by AI
######
    P_trans = defaultdict(list) #will store states with their transitons

    #basically will combine the machines into one
    def pair(qM, qN):
        return f"{qM}_{qN}"
    start = pair(M["start"], N["start"])
    accepts = {pair(qM, qN) for qM in M["accepts"] for qN in N["accepts"]}
    all_nfa_states = N["states"]
######

#The rest of the function was debugged by AI
#The first attempt at creating this was a different approach at unioning the transitions; similar with creating the NPDA and NFA that didn't work out
    #main loop for the NPDA
    for current_state, transition_list in M["transitions"].items():
        #loops through its transitions
        for (read, pop, push, current_state_next) in transition_list:
            #defualt check to ensure we only use "a" and "b"
            if read in ("a", "b"):
                #loop through NFA
                for current_nfa_state in all_nfa_states:
                    #loops through its transitions
                    for (character, current_nfa_state_next) in N["transitions"].get(current_nfa_state, []):
                        #AI created from debugging, basically adds both transitions to the dictionary for that current state
                        if character == read:
                            src = pair(current_state, current_nfa_state)
                            dest = pair(current_state_next, current_nfa_state_next)
                            P_trans[src].append((read, pop, push, dest))
            #empty clause for NPDA
            elif read == "empty":
                #AI created from debugging, basically adds both transitions to the dictionary for that current state
                for current_nfa_state in all_nfa_states:        
                    src = pair(current_state, current_nfa_state)
                    dest = pair(current_state_next, current_nfa_state)
                    P_trans[src].append(("empty", pop, push, dest))

#AI created from debugging, empty clause for NFA
    for current_nfa_state in all_nfa_states:
        for (character, current_nfa_state_next) in N["transitions"].get(current_nfa_state, []):
            if character == "empty":
                for current_state in M["states"]:
                    src = pair(current_state, current_nfa_state)
                    dest = pair(current_state, current_nfa_state_next)
                    P_trans[src].append(("empty", "", "", dest))

    #returns the new machine
    return {
        "start": start,
        "accepts": accepts,
        "transitions": P_trans
    }

#Function to test strings on the machine
#using the machine, we will see if it can build the langauage
#This function was debugged by AI
def run_npda(new_npda, language):
    start = new_npda["start"]
    accepts = new_npda["accepts"]
    trans = new_npda["transitions"]
    start_conf = (start, 0, ("z",)) #represents what the machine is doing
    accessible_states = deque([start_conf]) #represents accessible states
    visited = set() #prevents loops, AI's addition to this function

    #loop until no states are accessible
    while accessible_states:
        #gets available state
        state, pos, stack = accessible_states.popleft()

        #ensure we aren't looping forever
        if (state, pos, stack) in visited:
            continue
        visited.add((state, pos, stack))

        #check if state is accept state, will break out of the loop if so
        if state in accepts and pos == len(language):
            return True

        stack_element = stack[-1] if stack else None

        #processes each transition for the state
        for (read, pop, push, dest) in trans.get(state, []):
            #checks if stack matches
            if pop != "" and pop != stack_element:
                continue
            
            #checks if the read matches the language
            if read == "empty":
                new_pos = pos
            else:
                if pos >= len(language) or language[pos] != read:
                    continue
                new_pos = pos + 1

            #update stack when new state is found
            new_stack = list(stack)
            if pop != "":
                new_stack.pop()
            if push != "":
                for c in reversed(push):
                    new_stack.append(c)

            #gets more accessible state after transitioning
            accessible_states.append((dest, new_pos, tuple(new_stack)))

    return False

def print_npda(machine):
    start = machine["start"]
    accept = list(machine["accepts"])[0]

    state_str = f"{start}{accept}f"

    transitions_by_source = {}

    for src, trans_list in machine["transitions"].items():
        for (read, pop, push, dest) in trans_list:
            if src not in transitions_by_source:
                transitions_by_source[src] = {}

            if dest not in transitions_by_source[src]:
                transitions_by_source[src][dest] = []

            read_str = read if read != "empty" else "empty"
            pop_str = pop if pop != "" else "empty"
            push_str = push if push != "" else "empty"

            transitions_by_source[src][dest].append(
                f"{read_str}-{pop_str}-{push_str}"
            )

    parts = []

    for src, dests in transitions_by_source.items():
        for dest, rules in dests.items():
            rules_str = "|".join(rules)
            parts.append(f"{src}-{rules_str}->{dest}")

    return state_str + "," + ", ".join(parts)

#test script for program 2
def test(file, language1, language2):
#reads file input
    with open(file, "r") as f:
        Machine_N = f.readline() #first line is NPDA
        Machine_M = f.readline() #second line is NFA

    #parse the machines
    N = parse_nfa_N(Machine_N)
    M = parse_npda_M(Machine_M)

    #creates new machine from those grammars
    new_npda = build_intersection_npda(M, N)
    output = print_npda(new_npda) + "\n"

    #starts testing new machine with given languages
    with open("output2.txt", "a") as out:

        if run_npda(new_npda, language1):
            output += language1 + " - accept\n"
        else:
            output += language1 + " - reject\n"

        if run_npda(new_npda, language2):
            output += language2 + " - accept\n\n"
        else:
            output += language2 + " - reject\n\n"

        out.write(output)


# erase previous output
with open("output2.txt", "w") as file:
    file.flush()

# test calls for files 6-10
test("test6.txt",  "aabbaa",  "aaaa")
test("test7.txt",  "baaba",   "baaaab")
test("test8.txt",  "baaa",    "aabaaab")
test("test9.txt",  "bbaaa",   "baaabbb")
test("test10.txt", "aabbaab", "aaabbb")