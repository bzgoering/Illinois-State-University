#Part 0: Set up problem statement and policies
action_set = {1,2}
nonterminal = {'A','B','C'}
reward_model = -10
initial_state_value_table = { #inital values are 0 except D which is 100
    'A': 0, 
    'B': 0, 
    'C': 0, 
    'D': 100
}
state_transition_model = { #follows 90% rule and given example
    'A': {
        1: {'B': 0.9, 'C': 0.1},
        2: {'C': 0.9, 'B': 0.1}
    },
    'B': {
        1: {'D': 0.9, 'A': 0.1},
        2: {'A': 0.9, 'D': 0.1}
    },
    'C': {
        1: {'A': 0.9, 'D': 0.1},
        2: {'D': 0.9, 'A': 0.1}
    }
}
policy_1 = { #always chooses action 1
    'A': {1: 1, 2: 0},
    'B': {1: 1, 2: 0},
    'C': {1: 1, 2: 0}
}
policy_2 = { #always chooses action 2
    'A': {1: 0, 2: 1},
    'B': {1: 0, 2: 1},
    'C': {1: 0, 2: 1}
}
policy_3 = { #mix policy
    'A': {1: .4, 2: .6},
    'B': {1: 1, 2: 0},
    'C': {1: 0, 2: 1}
}

#step 1: Polciy Evaluation
def policy_evaluation(policy, discount = 0.8):
    state_value_table = initial_state_value_table.copy()

    for _ in range(100):
        for s in nonterminal: #updates the state_value_table using bellman equation
            new_value = 0
            for a in action_set:
                for s_prime, probability in state_transition_model[s][a].items():
                    new_value += policy[s][a] * probability * (reward_model + discount * state_value_table[s_prime])
            state_value_table[s] = new_value

    return state_value_table

#Part 2: Policy Iteration
def calculate_action_value(s, a, state_value_table, discount = 0.8): #helper function to calculate the action value using bellman equation
    total = 0

    for s_prime, probability in state_transition_model[s][a].items():
        total += probability * (reward_model + discount * state_value_table[s_prime])
    return total

def policy_iteration(discount = 0.8):
    state_value_table = initial_state_value_table.copy()
    policy = {'A': 1, 'B':1, 'C':1} #arbitrary deterministic policy

    while True:
        for _ in range(100):
            for s in nonterminal:
                state_value_table[s] = calculate_action_value(s, policy[s], state_value_table)

        #policy improvement
        policy_stable = True

        for s in nonterminal:
            old_action = policy[s]
            q = {a: calculate_action_value(s, a, state_value_table) for a in action_set}

            best_action = old_action
            for a in action_set:
                if q[a] > q[best_action] + 1e-9:
                    best_action = a
            policy[s] = best_action

            if old_action != policy[s]:
                policy_stable = False

        if policy_stable:
            return state_value_table, policy

#Run algorithm
def test_part_1():
    policies = [policy_1,policy_2, policy_3]
    correct_values = { 
        1: {'A': 38.97, 'B': 65.12, 'C': 26.06, 'D': 100},
        2: {'A': 38.97, 'B': 26.06, 'C': 65.12, 'D': 100},
        3: {'A': 42.31, 'B': 65.38, 'C': 65.38, 'D': 100}
    }
    my_values = { 
        1: {},
        2: {},
        3: {}
    }
    index = 1
    for policy in policies:
        values = policy_evaluation(policy)
        my_values[index] = {s: round(v, 2) for s, v in values.items()} #rounds values to match the correct format
        index += 1
    return(my_values == correct_values, my_values)

def test_part_2(optimal_values):
    correct_values = {'A': 42.31, 'B': 65.38, 'C': 65.38, 'D': 100}  # policy 3 values
    my_values = {s: round(v, 2) for s, v in optimal_values.items()}
    return(my_values == correct_values, my_values)

def proof(optimal_values, optimal_policy):
    to_b = calculate_action_value('A', 1, optimal_values)
    to_c = calculate_action_value('A', 2, optimal_values)

    print('\nProving: "it is equally good for the agent to visit state B and C from A"')
    if to_b == to_c:
        print('To B and C have equal action_values -\nmeaning they are equally as good of an option for the agent to pick:')
        print(f'Action value from A to B: {to_b}')
        print(f'Action value from A to C: {to_c}')
    else:
        print('Not Equal :(')




print("Testing policy evaluation....")
results, my_values = test_part_1()
if results:
    print(f'Policy Evaulation passed:')
else:
    print(f'Policy Evaluation Failed:')
for policy, values in my_values.items():
    print(f'Policy {policy}: {values}')

print('\nTesting policy iteration....')
optimal_values, optimal_policy = policy_iteration()
results, my_values = test_part_2(optimal_values)
if results:
    print(f'Policy iteration passed:')
else:
    print(f'Policy iteration Failed:')
print('Optimal policy:', optimal_policy)
print('Optimal values:', my_values)

proof(optimal_values, optimal_policy)