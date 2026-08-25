//By: Ben Goering

/*
Grade Book program designed to help teachers and students keep track of their grade for a class
functionality:
Teachers: Have full access to editing the grade book: edit, remove, add, print results.
Students: have limited access to the grade book: Students can only test and see results from the gradebook from their own grade
*/


/*
To-DO: 
* Finish Teacher functionality
    * get avg of grade book
* all of Student functionality:
    * should make copy of gradebook with only student ID
    * use that copy to print or test a grade (by edit only)
    * get avg grade with  overall letter grade
* add print by name
* clean up output
* remove,edit should return boolean to check if it successfully done it
* add should return print statement of grade added to show it successfully added
*/


use std::io::{self,Write};

#[derive(Clone)]
struct Grade
{
    grade_id:i32,
    name:String,
    name_id:i32,
    grade:f32,
}

//global variables
static mut GRADE_COUNTER:i32 = 1; //counts each grade inserted; used as the ID 
static mut STRUDENT_COUNTER:i32 = 1; //counts how many students, used as student's ID. *Note that different students cant have the same exact name, utilize numbers to differientiate

fn main()
{
    let mut grade_book: Vec<Grade> = Vec::new();
    let mut input = String::new();
    let mut stop = false;

    println!("Welcome to your grade book");

    //main loop
    while !stop
    {
        input.clear();

        //prompt for user input
        println!("1. I am a teacher\n2. I am a student\n3. Test functionality\n4. Terminate Program");
        print!("Please select a choice: ");
        io::stdout().flush().unwrap(); //waits for input
        io::stdin().read_line(&mut input).unwrap(); //stores input

        //check input
        match input.trim()
        {
            "1" => teacher_menu(&mut grade_book),
            "2" => student_menu(&mut grade_book),
            "3" => test(&mut grade_book),
            "4" => stop = true,
            _ => println!("Error"), //input doesn't match
        }
    }
}

//control for teachers
fn teacher_menu(data_base: &mut Vec<Grade>)
{
    let mut teacher_input = String::new();
    loop //loops until user breaks out
    {   
        //gets user input
        teacher_input.clear();
        println!("1. Add Grade\n2. Remove Grade\n3. Edit Grade\n4. Print Grade Options\n5. Log Out");
        println!("Please Select an Option: ");
        io::stdout().flush().unwrap();
        io::stdin().read_line(&mut teacher_input).unwrap();

        //validates user input
        match teacher_input.trim()
        {   
            "1" => Teacher::add(data_base),    //adds grade
            "2" => Teacher::delete(data_base), //deletes grade
            "3" => Teacher::edit(data_base),   //edits a current grade
            "4" => Teacher::print(&mut data_base.clone()),  //prints gradebook in either accedning, decedning, or in-order 
            "5" => break,   //terminate program
            _ => println!("Invalid input, Please try again: "),
        }
    }
}

//control for students
fn student_menu(data_base: &mut Vec<Grade>)
{
    print!("1. Test Grade\n2. Print Grade Options\n3. Exit");
}

//printing and editing methods for both teachers and students
trait Person
{
    fn print_best_to_worst(data_base: &mut Vec<Grade>)
    {
        //sorts the data in decending order
        data_base.sort_by(|a,b| b.grade.total_cmp(&a.grade));
        Self::to_string(&data_base);
    }

    fn print_worst_to_best(data_base: &mut Vec<Grade>)
    {
        //sorts the data in accending order
        data_base.sort_by(|a,b| a.grade.total_cmp(&b.grade));
        Self::to_string(&data_base);
    }

    fn to_string(data_base: &Vec<Grade>)
    {
        //prints each element of the vector
        for x in data_base
        {
            println!("Grade ID: {}\nName ID: {}\nName: {}\nGrade: {}\n",x.grade_id,x.name_id,x.name,x.grade);
        }
    }
    fn edit_grade(grade_id:i32, new_grade:f32, data_base: &mut Vec<Grade>)
    {
        //finds the grade_id and changes it
        if let Some(x) = data_base.iter_mut().find(|x| x.grade_id == grade_id)
        {
            x.grade = new_grade;
        }
    }

    //menu for printing
    fn print(data_base: &mut Vec<Grade>)
    {
        //gets user input
        let mut print_input = String::new();
        println!("1. Descending Order\n2. Inclining Order\n3. Regular Order");
        print!("Please select a choice: ");
        io::stdout().flush().unwrap();
        io::stdin().read_line(&mut print_input).unwrap();
        println!();

        //validate user input
        match print_input.trim()
        {
            "1" => Self::print_best_to_worst(&mut data_base.clone()),
            "2" => Self::print_worst_to_best(&mut data_base.clone()),
            "3" => Self::to_string(& data_base),
            _ => println!("Invalid Choice"),
        }
        println!();
    }
}

//Varibles specific to student
struct Student
{
    name_id:i32,
    name:String,
    grade_book: Vec<Grade>,
}
struct Teacher; //teachers don't need anything just methods

//gives Student and Teachers access to methods in trait
//acts like inherritence
impl Person for Student{}
impl Person for Teacher{}

//methods only for teachers
impl Teacher
{
    fn add_grade(grade_id:i32, name:String, name_id:i32,grade:f32,data_base: &mut Vec<Grade>)
    {
        //creates a structure Grade and adds to main data base
        let grade1 = Grade{grade_id,name,name_id,grade};
        data_base.push(grade1);
    }

    fn delete_student(name_id:i32, data_base: &mut Vec<Grade>)
    {
        //keeps grades that don't match the name_id
        data_base.retain(|x| x.name_id != name_id);
    }

    fn delete_grade(grade_id:i32,data_base: &mut Vec<Grade>)
    {
        //keeps grades that don't match the grade_id
        data_base.retain(|x| x.grade_id != grade_id);
    }

    //menu for adding
    fn add(data_base: &mut Vec<Grade>)
    {
        let mut add_input = String::new();  

        //gets a unqiue grade_id
        let grade_id;
        unsafe{grade_id = GRADE_COUNTER.clone(); GRADE_COUNTER += 1;}
        
        //gets student's name
        print!("Enter student name: ");
        io::stdout().flush().unwrap();
        io::stdin().read_line(&mut add_input).unwrap();
        let name = add_input.trim().to_string(); //user input is defualt String, no need to validate
        add_input.clear();

        //gets unqiue student_id
        let name_id;
        if let Some(grade) = data_base.iter().find(|x| x.name == name)
        {
            name_id = grade.name_id;
        }
        else
        {
            unsafe{name_id = STRUDENT_COUNTER.clone(); STRUDENT_COUNTER += 1;}
        }


        //gets student's grade
        print!("Enter student's Grade': ");
        io::stdout().flush().unwrap();
        io::stdin().read_line(&mut add_input).unwrap();
        let grade: f32 = add_input.trim().parse().expect("Invalid Number, Try Again: "); //validate input

        Self::add_grade(grade_id,name,name_id,grade,data_base);
    }

    //menu for deleting
    fn delete(data_base: &mut Vec<Grade>)
    {
        //gets user input
        let mut remove_input = String::new();
        println!("1. Delete Student\n 2.Delete Grade");
        print!("Please select a choice: ");
        io::stdout().flush().unwrap();
        io::stdin().read_line(&mut remove_input).unwrap();

        //validate input
        match remove_input.trim()
        {
            "1" => 
            {
                //gets user input
                remove_input.clear();
                print!("Enter Student's ID: ");        
                io::stdout().flush().unwrap();
                io::stdin().read_line(&mut remove_input).unwrap();
                let student_id: i32 = remove_input.trim().parse().expect("Invalid Number, Try Again: "); //validate input
                
                Self::delete_student(student_id, data_base);
            },
            "2" =>
            {
                //gets user input
                remove_input.clear();
                print!("Enter Grade ID: ");        
                io::stdout().flush().unwrap();
                io::stdin().read_line(&mut remove_input).unwrap();
                let grade_id: i32 = remove_input.trim().parse().expect("Invalid Number, Try Again: "); //validate input
                
                Self::delete_grade(grade_id, data_base);
            },
            _ => println!("Invalid Choice"),
        }

    }

    //menu for adding
    fn edit(data_base: &mut Vec<Grade>)
    {  
        //gets user input
        let mut edit_input = String::new();

        print!("Enter grade ID: ");
        io::stdout().flush().unwrap();
        io::stdin().read_line(&mut edit_input).unwrap();
        let grade_id:i32 = edit_input.trim().parse().expect("Invalid Number, Try Again: "); //validate input
        edit_input.clear();
        
        print!("Enter new grade: ");
        io::stdout().flush().unwrap();
        io::stdin().read_line(&mut edit_input).unwrap();
        let new_grade: f32 = edit_input.trim().parse().expect("Invalid Number, Try Again: "); //validate input

        Teacher::edit_grade(grade_id,new_grade,data_base);
    }   
}


//functionality testing; DOES NOT test user input controls
fn test(data_base: &mut Vec<Grade>)
{
    println!("Adding Students");
    let grade = 85.0;
    let name = String::from("John Doe");
    let name_id = 80952;
    let grade_id = 782435;
    Teacher::add_grade(grade_id,name,name_id,grade,data_base);

    let grade = 90.0;
    let name = String::from("John Doe");
    let name_id = 80952;
    let grade_id = 783953;
    Teacher::add_grade(grade_id,name,name_id,grade,data_base);

    let grade = 90.0;
    let name = String::from("Alex Doe");
    let name_id = 7453;
    let grade_id = 46573;
    Teacher::add_grade(grade_id,name,name_id,grade,data_base);

    println!("Printing best to worst...");
    Teacher::print_best_to_worst(&mut data_base.clone());
    println!("Printing worst to best...");
    Teacher::print_worst_to_best(&mut data_base.clone());

    println!("editing Alex's grade and deleted one of John Doe's grade");
    Teacher::edit_grade(46573,100.0,data_base);
    Teacher::delete_grade(783953,data_base);

    println!("Printing best to worst...");
    Teacher::print_best_to_worst(&mut data_base.clone());
    println!("Printing worst to best...");
    Teacher::print_worst_to_best(&mut data_base.clone());

    println!("Deleting JohnDoe from database");
    Teacher::delete_student(80952,data_base);
    Teacher::print_best_to_worst(&mut data_base.clone());
    println!("Printing worst to best...");
    Teacher::print_worst_to_best(&mut data_base.clone());
}