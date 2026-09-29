scholors = []

def build_experts():
    with open("Experts.txt", mode = 'r') as file:
        next(file)

        #reads every line except 1st
        for line in file:
            row = line.strip().split('"')

            #gets data from line
            id = row[1]
            name = f"{row[5]} {row[3]}"
            affiliation = row[11].split(',')             
            institute = affiliation[0].strip()
            department = affiliation[-1].strip()  

            #multiple degrees are allowed, checks only for Computer Science and Mathematics
            fields = []
            if "Computer Science" in department:
                fields.append("Computer Science")
            if "Mathematics" in department:
                fields.append("Mathematics")

            #adds if scholor has a degree we are looking for, otherwise we dont need it
            if fields:
                scholors.append({
                    "id": id,
                    "name": name,
                    "institute": institute,
                    "field": fields,
                    "concept": []
                })

#searches for scholor index by id
def search_id(id):
    index = 0
    for scholor in scholors:
        if scholor['id'] == id:
            return index
        index += 1
    return None

#adds to the concept key
def build_profiles():
    build_experts() #experts must already be in file.

    id = '0'
    index = None
    with open("Profiles.txt", mode = 'r') as file:
        next(file)

        for line in file:
            row = line.strip().split('"')

            #checks if id is the same as previous since text is in order to an extent
            if row[1] != id:
                id = row[1]
                index = search_id(id)

            #once index is found, adds concept (area of study)
            if index is not None:
                concept_name = row[5]
                scholors[index]["concept"].append(concept_name)

#lists all institutes thats in scholors, only once
def list_institutes():
    institutes = []
    for scholor in scholors:
        institute = scholor["institute"]
        if institute not in institutes:
            institutes.append(institute)
    print(*institutes, sep = ",")    

#lists scholors in specifc institute by department
def print_names_in_institute_by_department(institute):
    computer_science = []
    math = []

    #cycles through scholors checking if they are comp sci or math
    for scholor in scholors:
        if scholor['institute'] == institute:
            if 'Computer Science' in scholor['field']:
                computer_science.append(scholor['name'])
            if 'Mathematics' in scholor['field']:
                math.append(scholor['name'])

    #prints
    if len(computer_science) != 0:
        print(f'\n{institute} has the following scholars in computer science:')
        for name in computer_science:
            print(name)
    if len(math) != 0:
        print(f'\n{institute} has the following scholars in math:')
        for name in math:
            print(name)

#prints scholors in area of field by department
def print_names_in_field(institute, field):
    exact_match_cs = []
    exact_match_math = []
    partial_match_cs = []
    partial_match_math = []

    #cycles through all scholors
    for scholor in scholors:
        #checks if scholor is in specific institute
        if scholor['institute'] != institute:
            continue

        name = scholor['name']

        #checks if scholor is in area of study
        if field in scholor['concept']:
            if 'Computer Science' in scholor['field']:
                exact_match_cs.append(name)
            if 'Mathematics' in scholor['field']:
                exact_match_math.append(name)
        else:
            match = next((item for item in scholor['concept'] if field in item),None)
            if match:
                if 'Computer Science' in scholor['field']:
                    partial_match_cs.append(f"{name} ({match})")
                if 'Mathematics' in scholor['field']:
                    partial_match_math.append(f"{name} ({match})")

    #count check
    any_exact = len(exact_match_cs) != 0 or len(exact_match_math) != 0
    any_partial = len(partial_match_cs) != 0 or len(partial_match_math) != 0

    #prints scholors either exact or partial by department or a none at all found
    if not any_exact and not any_partial:
        print(f'\nThere is no one that studies {field}')
        return
    if len(exact_match_cs) != 0:
        print(f'\n{field} has the following CS scholors:')
        print(*exact_match_cs, sep=', ')
    elif len(partial_match_cs):
        print('\nThere is no exact match, but we found some scholars in CS that study similar areas:')
        print(*partial_match_cs, sep='; ')
    if len(exact_match_math) != 0:
        print(f'\n{field} has the following math scholors:')
        print(*exact_match_math, sep=', ')
    elif len(partial_match_math) != 0:
        print('\nThere is no exact match, but we found some scholars in math that study similar areas:')
        print(*partial_match_math, sep='; ')

build_profiles() #gets data

#start of application driver
print('\t     Welcome to the NC Scholar System')
user_input = 'c'
while user_input == 'c':
    print('We currently include scholars from the following institutes:')
    list_institutes()

    institute = input("\nSelect an institute: ").upper()
    print_names_in_institute_by_department(institute)

    study = input("\n\nInput an area of study: ")
    print_names_in_field(institute, study)

    user_input = input("\n\nPress c to continue or press anything else to quit: ")

print("Thanks for using the system!")
