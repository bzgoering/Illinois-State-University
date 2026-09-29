//this file is co-authored by Temka Zorigt and Joseph Kedas
use cursive::Cursive;
use cursive::views::{Button, Dialog, DummyView, EditView, LinearLayout, ListView, TextView};
use cursive::traits::*;
use rand::Rng;
use crate::binary_tree;


//displays the difficulty selection menu for the game
pub fn game_menu(s: &mut Cursive) {

    //creates the buttons of the difficulty selection
    let buttons = Dialog::around(ListView::new()
        .child("1)", Button::new("Easy", |s|{play_game(s, 1)}))
        .child("2)", Button::new("Medium", |s|{play_game(s, 2)}))
        .child("3)", Button::new("Hard", |s|{play_game(s, 3)}))

        .child("", DummyView)
        .child("4)", Button::new("Quit", |curs: &mut Cursive| {curs.pop_layer();})))
        .title("Game");

    //adds the difficulty selection to the window
    s.add_layer(buttons.fixed_width(30));

}


//initializes the game menu after selecting a difficulty
fn play_game(s: &mut Cursive, level: u8) {
    //retrieves the list of words based on the difficulty the user selected
    let word_list = get_words(level);
    let choice = rand::rng().random_range(0..=9);
    let word = word_list[choice];
    
    //compoments of dialog for the game
    let top = Dialog::around(TextView::new(word).with_name("word"));
    let input = Dialog::around(EditView::new().with_name("input")).fixed_width(word.len()*5 -1 );
    
    //adds the game dialog to the window
    s.add_layer(
        Dialog::around(
            LinearLayout::vertical().child(top).child(input))
            .title("Enter Your Guess")
            .button("Check", check)
            .button("Quit", |s|{s.pop_layer();})
            .fixed_width(30)
        );
}


//gets words for the selected difficulty
fn get_words(difficulty: u8) -> &'static [&'static str] {
    match difficulty {
        1 => &["cat", "dog", "run", "gold", "blue", "leg", "arm", "two", "one", "log"],
        2 => &["house", "color", "bread", "online", "fortnite", "overwatch", "monitor", "yellow", "charger", "illinois"],
        3 => &["arbitrary", "encyclopedia", "objectively", "rudimentary", "albatross", "equalizing", "derivavative", "quintessential", "maximization", "misinterpretation"],
        _ => &[] 
    }
}


//wrapper function for check_answer
fn check(s: &mut Cursive) {
    
    //gets the displayed word and the user input
    let word = s.call_on_name("word", |w: &mut TextView|{w.get_content()}).unwrap();
    let guess = s.call_on_name("input", |i: &mut EditView|{i.get_content()}).unwrap();
    
    //checks the user input against the word
    let check = check_answer(word.source(), guess.as_str());

    
    
    if check {
        //displays correct if user is correct
        s.add_layer(Dialog::around(TextView::new("Correct!"))
            .dismiss_button("Enter to close"));
    }
    else {
        //displays incorrect if user is incorrect
        s.add_layer(Dialog::around(TextView::new("Incorrect"))
            .dismiss_button("Enter to close"));
    }
}



//function to check the user input against the specific word
fn check_answer(word: &str, guess: &str/*, tree: &MorseTree*/) -> bool {
    //creates binary tree for translation
    let mut morse_tree = binary_tree::init_tree();


    //creates temp variables that will be used throughout this loop
    //the loop is for every character that is in the user's guess, and will terminate/return false if there
    //is any other character besides ' ', '-', or '.'
    let mut guess_decoded = String::new();
    let mut morse_string = String::new();
    for letter in guess.chars() {
        match letter {
            //here we will be "building" our morse code string
            '.' | '-' => {
            	//adds morse code letter to our morse code string
                morse_string.push(letter);
                //automatically returns false if morse code string exceeds 4 characters.
                if morse_string.len() > 4 {
                    return false;
                }
            }
            //if there is a space we know that is the end of our morse string for that character
            ' ' => {
                if !morse_string.is_empty() {

                    if morse_string == "----" {
                        return false
                    }
                    let translated = morse_tree.find(&morse_string).unwrap();
                    guess_decoded.push(translated);
                    morse_string.clear();
                }
            }
            _ => /*if there is any unusual character */return false,
        }
        
    }
    
    if !morse_string.is_empty() {

        if morse_string == "----" {
            return false;
        }
        let translated = morse_tree.find(&morse_string).unwrap();
        guess_decoded.push(translated);
        morse_string.clear();
    }


    let upper_word = word.trim().to_uppercase();
    return upper_word == guess_decoded;
}