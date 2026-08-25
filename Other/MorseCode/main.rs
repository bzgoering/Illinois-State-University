//This file is authored by Temka Zorigt, Ben Goering, Kacy Fisher, and Joseph Kedas
use cursive::Cursive;
use cursive::views::{Button, Dialog, EditView, LinearLayout, ListView, TextView};
use cursive::traits::*;
use game::{game_menu};

mod game;
mod binary_tree;

/*
simple text representation of binary tree
We were going to use the to_string method of the binary tree but this reduces overhead
*/
const TREE_TEXT: &str = "
English         Morse Code
E               .
T               -
I               ..
A               .-
N               -.
M               --
S               ...
U               ..-
R               .-.
W               .--
D               -..
K               -.-
G               --.
O               ---
H               ....
V               ...-
F               ..-.
L               .-..
P               .--.
J               .---
B               -...
X               -..-
C               -.-.
Y               -.--
Z               --..
Q               --.-
";

//main function handles initialization of cursive window
fn main() {

    //initializes cursive view
    let mut siv = cursive::default();

    //loads the theme
    siv.load_toml(include_str!("../theme.toml")).unwrap();

    //construction of the start menu
    siv.add_layer(Dialog::new().title("Morse Code Tree"));

    let options = ListView::new()
        .child("1) ",Button::new( "Translate", translator))
        .child("2) ",Button::new( "Dictionary", show_dict))
        .child("3) ",Button::new( "Play the Game", game_menu))
        .child("4) ",Button::new( "Quit", |s| {s.quit()}));

    siv.add_layer(Dialog::around(options).title("Morse Code Tree").fixed_width(30));


    siv.run();
}

//displays the tree dictionary
fn show_dict(s: &mut Cursive) {
    s.add_layer(Dialog::around(TextView::new(TREE_TEXT)).title("Dictionary").button("Done", |s| {s.pop_layer();}).fixed_width(30));
}

//translator function
fn translator(s: &mut Cursive) {

    let help = Dialog::around(TextView::new("Enter a combination of  '.', '-', and spaces. Click 'Translate' to see the english\n\n<Help>\nMake sure that you only enter up to four dots or dashes in a row\nRemember to space separate all sequences"));
    let input_box = Dialog::around(EditView::new().with_name("input"));
    let translate_box = Dialog::around(TextView::new("Stuff").with_name("english"));
    s.add_layer(Dialog::around(LinearLayout::vertical().child(help).child(input_box).child(translate_box)).title("Translator").button("Translate", translate).button("Quit", |s|{s.pop_layer();}));

    fn translate(s: &mut Cursive) {
        let morse = s.call_on_name("input", |i:&mut EditView| { i.get_content() }).unwrap();
        
        let translated_morse = morse_to_english(morse.as_str());

        if morse.chars().all(|c| "-. ".contains(c)) {
            s.call_on_name("english", |view: &mut TextView| {view.set_content( translated_morse.as_str() )} );
        }
        else {
            s.call_on_name("english", |view: &mut TextView| {view.set_content( "Invalid Input" )} );
        }
    }
}

//translates morse code input to english
fn morse_to_english(word: &str) -> String {
    //creates binary tree for translation
    let mut morse_tree = binary_tree::init_tree();


    //creates temp variables that will be used throughout this loop
    //the loop is for every character that is in the user's guess, and will terminate/return false if there
    //is any other character besides ' ', '-', or '.'
    let mut english = String::new();
    let mut morse_string = String::new();
    for letter in word.chars() {
        match letter {
            //here we will be "building" our morse code string
            '.' | '-' => {
            	//adds morse code letter to our morse code string
                morse_string.push(letter);
                //returns if input format is incorrect
                if morse_string.len() > 4 {
                    return String::from("Incorrect Input");
                }
            }
            //if there is a space we know that is the end of our morse string for that character
            ' ' => {
                if !morse_string.is_empty() {
                    if morse_string == "----" {
                        return String::from("Incorrect Input");
                    }
                    let translated = morse_tree.find(&morse_string).unwrap();
                    english.push(translated);
                    morse_string.clear();
                }
            }
            _ => /*if there is any unusual character */return String::from("Incorrect Input"),
        }
    }
    
    //catches the exception of "----" which is 'valid' morse, but doesn't have a translation
    if morse_string == "----" {
        return String::from("Incorrect Input");
    }
    
    //catches a last character if the user doesn't pad a space at the end
    if !morse_string.is_empty() {
        let translated = morse_tree.find(&morse_string).unwrap();
        english.push(translated);
        morse_string.clear();
    }


    return english;
}