//This file in its entirety was authored by Ben Goering

struct Node
{
    data: char,
    right: Option<Box<Node>>,
    left: Option<Box<Node>>
}

impl Node
{
    fn new(data: char) -> Self
    {
        Node
        {
            data,
            left: None,
            right: None,
        }
    }
}

pub struct BinaryTree
{
    root: Option<Box<Node>>
}

impl BinaryTree
{
    pub fn new() -> Self
    { 
        BinaryTree
        {
            root: None
        }
    }
    
    pub fn add(&mut self, data: char, code: &str)
    {
        let new_data = Node::new(data);

        match self.root
        {
            None => self.root = Some(Box::new(new_data)), //root is null
            Some(ref mut root_node) => 
            {
                Self::insert(root_node, new_data, code)
            }
        }
    }

    fn insert(current: &mut Node, data_node: Node, code: &str)
    {
        if code.is_empty()
        {
            return;
        }

        let (first_code, rest_code) = code.split_at(1);

        match first_code
        {
            "." =>
                match current.left
                {
                    None => current.left = Some(Box::new(data_node)),
                    Some(ref mut left_node) => 
                    {
                        Self::insert(left_node, data_node, rest_code)
                    }
                }
            "-" => 
                match current.right
                {
                    None => current.right = Some(Box::new(data_node)),
                    Some(ref mut right_node) => 
                    {
                        Self::insert(right_node, data_node, rest_code)
                    }
                }
            &_ => println!("Invalid code: {first_code}"),
        }
    }

    pub fn find(&mut self, code:&str) -> Option<char>
    {
        let fix_code;
        if code.chars().all(|c| c == '.')
        {
            fix_code = code.replacen(".","",1);
        }
        else
        {
            fix_code = code.to_string();
        }

        match self.root
        {
            None => {
                        "ERROR - EXPECTED DATA NOT FOUND";
                        return None
                    }
            Some(ref mut root_node) => 
            {
                return Self::get_data(root_node, &fix_code)
            }
        }
    }

    fn get_data(current: &mut Node, code: &str) -> Option<char>
    {
        if code.is_empty()
        {
            return Some(current.data)
        }

        let (first_code, rest_code) = code.split_at(1);

        match first_code
        {
            "." =>
                match current.left
                {
                    None => {
                                "ERROR - EXPECTED DATA NOT FOUND";
                                return None
                            }
                    Some(ref mut left_node) => 
                    {
                        Self::get_data(left_node, rest_code)
                    }
                }
            "-" => 
                match current.right
                {
                    None => {
                                "ERROR - EXPECTED DATA NOT FOUND";
                                return None
                            }
                    Some(ref mut right_node) => 
                    {
                        Self::get_data(right_node, rest_code)
                    }
                }
            &_ =>   {
                    println!("Invalid code: {first_code}");
                    return None
                    }
        }
    }

    pub fn to_string(&self)
    {
        if let Some(ref root) = self.root
        {
            Self::traverse(root);
        }
        println!();
    }

    fn traverse(current: &Node)
    {
        let space:&str;

        if let Some(ref left) = current.left
        {
            Self::traverse(left);
        }

        println!("{}", current.data);

        if let Some(ref right) = current.right
        {
            Self::traverse(right);
        }
    } 
}


pub fn init_tree() -> BinaryTree {
    let mut my_tree = BinaryTree::new();

    my_tree.add('E', ".");
    my_tree.add('T', "-");
    my_tree.add('I', "..");
    my_tree.add('A', ".-");
    my_tree.add('N', "-.");
    my_tree.add('M', "--");
    my_tree.add('S', "...");
    my_tree.add('U', "..-");
    my_tree.add('R', ".-.");
    my_tree.add('W', ".--");
    my_tree.add('D', "-..");
    my_tree.add('K', "-.-");
    my_tree.add('G', "--.");
    my_tree.add('O', "---");
    my_tree.add('H', "....");
    my_tree.add('V', "...-");
    my_tree.add('F', "..-.");
    my_tree.add('L', ".-..");
    my_tree.add('P', ".--.");
    my_tree.add('J', ".---");
    my_tree.add('B', "-...");
    my_tree.add('X', "-..-");
    my_tree.add('C', "-.-.");
    my_tree.add('Y', "-.--");
    my_tree.add('Z', "--..");
    my_tree.add('Q', "--.-");

    return my_tree;
}

