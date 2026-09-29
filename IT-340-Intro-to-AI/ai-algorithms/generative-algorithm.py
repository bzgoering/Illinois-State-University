#Fitness function by: xfang13
#Reset of the code by: Ben Goering

import numpy as np
import random
import matplotlib.pyplot as plt

SEED = 100
N_QUEENS = 8
ROW_MIN = 1
ROW_MAX = 8

def fitness(Input):
    #Input should be a list
    # assert(type(Input)==list)

    #Step1: make the state out of the Input
    state = np.zeros((8,8))
    for j in range(8):
        state[Input[j]-1][j] = 1
            

    #Step2: find the fitness of the state
    attacks = 0
    k = -1
    for j in range(8):
        k += 1
        #direction 1: the east
        for l in range(k+1,8):
            attacks += state[state[:,j].argmax()][l]
    
        #direction 2: the northeast
        row = state[:,j].argmax()
        column = j
        while row > 0 and column < 7:
            row -= 1
            column += 1
            attacks += state[row][column]
            
        #direction 3: the southeast
        row = state[:,j].argmax()
        column = j
        while row < 7 and column < 7:
            row += 1
            column += 1
            attacks += state[row][column]
            
    return 28 - attacks

#one board: its queens, fitness score, and ancestry
class Game:
    def __init__(self, board, fitness_fn, generation = 0, mutated = False, parent = None):
        self.board = board
        self.fit = fitness_fn(board)
        self.generation = generation
        self.mutated = mutated
        self.parent = parent

#creates one random board, one queen per column
#col = index
#row = some random value between 1 and 8
def random_board(fitness_fn):
    board = []
    for x in range(N_QUEENS):
        board.append(random.randint(ROW_MIN, ROW_MAX))
    return Game(board, fitness_fn, generation=0) 

#picks a parent at random from the boards scoring at least half the average
def random_selection(population, fitness_fn, threshold_fraction = 0.5):
    fits = [game.fit for game in population] #fitness scores for each game
    threshold = (sum(fits) / len(fits)) * threshold_fraction #half of the average (threshold_fraction=0.5)

    pool = [game for game in population if game.fit >= threshold] #keeps boards at or above the threshold
    return random.choice(pool)

#combines 2 boards
def reproduce(x, y, fitness_fn, generation):
    n = len(x.board)
    c = random.randint(1, n)

    #first c columns from x, the rest from y
    child_board = x.board[0:c] + y.board[c:n]

    #the parent that gave more columns is recorded for the ancestry trace
    dominant_parent = x if c >= n - c else y
    return Game(child_board, fitness_fn, generation = generation,
                      mutated=False, parent = dominant_parent)

#moves 1 to max_positions queens to random new rows
def mutate(child, fitness_fn, max_positions = 2):
    #copy the board so the edit is made on a fresh list
    board = child.board[:]
    for p in random.sample(range(N_QUEENS), random.randint(1, max_positions)):
        board[p] = random.randint(ROW_MIN, ROW_MAX)

    #save the new board, rescore it, and mark it as mutated
    child.board = board
    child.fit = fitness_fn(board)
    child.mutated = True
    return child


def genetic_algorithm(pop_size, fitness_fn = fitness, mutation_prob = 0.15):
    #creates the boards then gets the best one from the population
    population = [random_board(fitness_fn) for _ in range(pop_size)]
    best = max(population, key=lambda ind: ind.fit) 
    
    #data for the plot
    history = {"max": [], "min": [], "avg": []}

    #repeat until a board scores 28
    generation = 0
    while best.fit < MAX_FITNESS:
        generation += 1

        #build the next generation: select 2 parents, reproduce, maybe mutate
        new_population = []
        for _ in range(pop_size):
            x = random_selection(population, fitness_fn)
            y = random_selection(population, fitness_fn)
            child = reproduce(x, y, fitness_fn, generation)
            if random.random() < mutation_prob:
                child = mutate(child, fitness_fn)
            new_population.append(child)
        population = new_population

        #record max, min and average fitness of this generation for the plot
        fits = [ind.fit for ind in population]
        history["max"].append(max(fits))
        history["min"].append(min(fits))
        history["avg"].append(sum(fits) / len(fits))

        #best board so far, checked by the loop condition
        best = max(population, key=lambda ind: ind.fit)

    return best, history


#prints the solution, then follows each board's parent back to generation 1
def print_ancestry(pop_size, solution):
    print(f"Population size: {pop_size} Solution: {solution.board}\n")
    node = solution
    while node.generation > 0:
        print(f"Generation: {node.generation:3d} State: {node.board} Mutated: {node.mutated}")
        node = node.parent


#plots max (blue), min (orange) and average (black) fitness per generation,
#one subplot per population size
def plot_all(histories, pop_sizes):
    fig, axes = plt.subplots(2, 2, figsize=(11, 8))
    for ax, size in zip(axes.flat, pop_sizes):
        hist = histories[size]
        gens = range(1, len(hist["max"]) + 1)
        ax.plot(gens, hist["max"], color = "tab:blue", marker = "^")
        ax.plot(gens, hist["min"], color = "tab:orange", marker = "s")
        ax.plot(gens, hist["avg"], color = "black", linestyle = "--")
        ax.set_title(f"population size = {size}")
        ax.set_xlabel("generation")
        ax.set_ylabel("fitness")
        ax.grid(True, alpha = 0.4)

    fig.tight_layout()
    plt.show()


if __name__=='__main__':
    #MAX_FITNESS = fitness([2, 4, 7, 4, 8, 5, 1, 3])
    MAX_FITNESS = fitness([5,2,4,7,3,8,6,1])
    print(MAX_FITNESS)

    random.seed(SEED)   

    #runs once per population size, tracking data for the plot
    pop_sizes = [50, 100, 200, 500]
    histories = {}
    for size in pop_sizes:
        best, histories[size] = genetic_algorithm(size)

    #prints the last run ancestry
    print_ancestry(500, best)
    plot_all(histories, pop_sizes)
