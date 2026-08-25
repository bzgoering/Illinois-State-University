def prune_list(list, number=2):
    count = 1
    result = []

    for data in list:
        if count == number:
            result.append(data)
            count = 1 
        else:
            count += 1
    
    return result

def prune_list2(list, number=2):
    result = [list[count] for count in range (len(list)) if (count + 1) % number == 0]
    return result

def check_for_item(list, value):
    return value in list

def set_up_dictionary(list):
    string = "{"

    for count in range(len(list)):
        string += "'" + (list[count]) + "'" + ":" + str(count)
        if count < len(list)-1:
            string += ","

    string += "}"
    return string

import re

#determines whether a string is a valid phone number in the form xxx-xxxx or (xxx) xxx-xxxx
def check_phone_number(str):
    match = re.search(r"^(\(\d{3}\) )?(\d{3}-\d{4})$",str)

    if (match):
        return True
    else:
        return False

#reads the input file and writes to the output file only the lines from the input file that contain exactly a phone number
def save_phone_numbers(input_file, output_file):
    #enables it so i can read and write from respected files
    with open(input_file, 'r') as read_file, open(output_file, 'w') as write_file:
        #goes line by line in file
        for line in read_file:
            #gets pure content without removing spaces, only new line
            content = line.rstrip('\n')
            #checks if its a phone number either '(xxx) xxx-xxx' OR 'xxx-xxxx'
            if (check_phone_number(content)): 
                #writes to file
                write_file.write(content + "\n")
