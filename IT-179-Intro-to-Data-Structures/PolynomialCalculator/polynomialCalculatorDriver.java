package PolynomialCalculator;

import java.util.*;

public class polynomialCalculatorDriver 
{
	
	public static void main(String[] args) 
	{
		System.out.println("___Testing Polynimoal Calculator___\n");
		
		//test polynomials given from assignment
		testAll("3x^4+2x^2+3x+7", "2x^3-5x+5");
		testAll("3x^4+2x^2", "5");
		testAll("2x^2+3x+7", "2x^9+3");
		testAll("2x^3+x^2","2x^3+x^2+x+1");
		testAll("3x^4+7", "5x^5+2x^2+3x");
		testAll("9x^90", "x+1");
		testAll("-2x^-3", "-2x^-2");
		testAll("3x^4+2x^3-x-8x^-1+5x^-3", "2x^5-3x^4+6+8x^-1-4x^-3");
		testAll("5", "-5");
		testAll("x^2+7x^-2", "x^2+7x^-2");
		testAll("2x^3+2x^2-x+3", "3x^2-2x+2");
		testAll("x+1", "x+1");
		testAll("x+1", "x-1");
		testAll("3x^4+2x^3-x-8x^-1+5x^-3", "2x^5-3x^4+6+8x^-1-4x^-3");
		testAll("5", "-5");
	}	
	
	
	//test all methods
	public static void testAll(String polyA, String polyB)
	{
		//variables
		SingleLinkedList<Term> poly1 = new SingleLinkedList<>();
		SingleLinkedList<Term> poly2 = new SingleLinkedList<>();

		//makes 1st polynomial objects and simplify
		poly1 = handlePoly(polyA);
		poly1 = simplify(poly1);
		
		//makes 2nd polynomial objects and simplify
		poly2 = handlePoly(polyB);
		poly2 = simplify(poly2);
		
		//outputs polynomials
		System.out.print("First Polynomial: "); print(poly1);
		System.out.print("Second Polynomial: "); print(poly2);

		//Adds the two polynomials
		SingleLinkedList<Term> addedPoly = add(poly1, poly2);
		System.out.print("Sum: "); print(addedPoly);
				
		//Multiply polynomial
		SingleLinkedList<Term> multipliedPoly = multiply(poly1, poly2);
		System.out.print("Multiplication: "); print(multipliedPoly);
				
		//simplified		
		SingleLinkedList<Term> SimplifiedPoly = simplify(multipliedPoly);
		System.out.print("Simplified: "); print(SimplifiedPoly);
		System.out.println("\n");
	}
	
	//prints out polynomial using iterator
	private static void print(SingleLinkedList<Term> poly)
	{
		//variables
		Iterator<Term> myIterator = poly.iterator();
		int count = 0;
		
		//loops through list
		while(myIterator.hasNext())
		{
			Term current = myIterator.next();
			
			//adds any addition signs after the first term if needed
			if (count > 0)
			{
				if (current.getCoe() > -1)
				{
					System.out.print("+" + current.toString());
				}
				else
				{
					System.out.print(current.toString());
				}
			}
			//just adds term to string
			else
			{
				System.out.print(current.toString());
			}
			count ++;
		}
		
		//adds a line space
		System.out.println();
	}
	
	//adds two polynomials together
	private static SingleLinkedList<Term> add(SingleLinkedList<Term> poly1, SingleLinkedList<Term> poly2)
	{
		//variables
		SingleLinkedList<Term> result = new SingleLinkedList<>();
		boolean[] match = new boolean[poly2.size()];
		Iterator<Term> iterator1 = poly1.iterator();

		//traverse through first polynomial
		while (iterator1.hasNext())
		{
			//variable reset
			Term current1 = iterator1.next();
			Iterator<Term> iterator2 = poly2.iterator();
			boolean equal = false;
			int index2 = 0;
			
			//traverse through second polynomial
			while (iterator2.hasNext())
			{
				//variable reset
				Term current2 = iterator2.next();
				
				//adds the two terms if exponents are equal
				if (current1.getExp() == current2.getExp())
				{
					//variable reset
					int coe = current1.getCoe() + current2.getCoe();
					int expo = current1.getExp();
					
					//adds term, but if coe is 0, it'll only add if term is constant
					if (coe != 0 || expo == 0)
					{
						result.add(new Term(coe, expo));
					}
					
					//keeps track which terms have matched
					equal = true;
					match[index2] = true;
					break;
				}
				index2 ++;
			}
			
			//adds terms that wont be added
			if (!equal)
			{
				result.add(current1);
			}
		}
		
		//add any unmatched terms from poly2 here
		Iterator<Term> iterator2 = poly2.iterator();
		int index = 0;
		
		//loops through poly2 and array for the unmatched terms from poly2
		while (iterator2.hasNext())
		{
			Term current2 = iterator2.next();
			if (!match[index])
			{
				result.add(current2);
			}

			index++;
		}
		return result;
	}
	
	//multiplies two polynomials together
	private static SingleLinkedList<Term> multiply(SingleLinkedList<Term> poly1, SingleLinkedList<Term> poly2)
	{
		//variables
		SingleLinkedList<Term> result = new SingleLinkedList<>();
		Iterator<Term> iterator1 = poly1.iterator();

		//traverse through first polynomial
		while (iterator1.hasNext())
		{
			//variables reset
			Term current1 = iterator1.next();
			Iterator<Term> iterator2 = poly2.iterator();
			
			//traverse through second polynomial
			while (iterator2.hasNext())
			{
				//variables reset
				Term current2 = iterator2.next();
				
				//multiplies and adds to list
				int coe = current1.getCoe() * current2.getCoe();
				int exp = current1.getExp() + current2.getExp();
				result.add(new Term(coe,exp));
			}
		}
		
		return result;
	}
	
	//adds terms from string list
	private static SingleLinkedList<Term> addTerm(SingleLinkedList<String> terms)
	{
		//variables
		SingleLinkedList<Term> result = new SingleLinkedList<>();
		
		//loops through list
		while (terms.size() > 0)
		{
			//resets variables
			int expo;
			int coe;
			boolean negative;
			String term;
			
			//gets term
			term = terms.get(0);
			
			//check if term coefficient is negative
			negative = term.indexOf('-') == 0;
			
			//gets rid of negative sign
			if (negative)
			{
				term = term.substring(1);
			}
			
			//looks for x
			if (term.contains("x"))
			{
				//if its just x
				if (term.indexOf('x') == 0)
				{
					coe = 1;
				}
				//if there's an integer
				else
				{
					coe = Integer.parseInt(term.substring(0, term.indexOf('x')));
				}
								
				//looks for carrot
				if (term.contains("^"))
				{
					expo = Integer.parseInt(term.substring(term.indexOf('^')+1));
				}
				//if carrot isn't found, but x is
				else
				{
					expo = 1;
				}
			}
			//if there isn't an x, constant
			else
			{
				coe = Integer.parseInt(term);
				expo = 0;
			}
			
			//makes coefficient negative
			if (negative)
			{
				coe *= -1;
			}
						
			//adds term
			result.add(new Term(coe,expo));
			
			//deletes current term
			terms.remove(0);
		}
		return result;
	}	
	
	//Separates string and adds to string list
	private static SingleLinkedList<Term> handlePoly(String input) 
	{
		//variables
	    String temp = input;
	    SingleLinkedList<String> terms = new SingleLinkedList<>();
	    
	    //adjust minor fixes if any
	  	input = input.replaceAll(" ", "").toLowerCase();
	  		
	    //loops until all terms are found
	    while (temp.length() > 0) 
	    {	
	    	//variable reset
	        int minus = temp.indexOf('-', 1);
	        int plus = temp.indexOf('+', 1);

	        //skip negative exponents
	        if (minus != -1 && temp.charAt(minus - 1) == '^') 
	        {
	            minus = temp.indexOf('-', minus + 1);
	        }

	        //if plus is found first
	        if (plus != -1 && (plus < minus || minus == -1)) 
	        {
	            //add term till next proper sign
	            terms.add(temp.substring(0, plus));
	            temp = temp.substring(plus + 1);
	        } 
	        //if minus is found first
	        else if (minus != -1 && (minus < plus || plus == -1)) 
	        {
	            // adds till next proper sign
	            terms.add(temp.substring(0, minus));
	            temp = temp.substring(minus);
	        } 
	        //last term
	        else 
	        {
	            terms.add(temp);
	            temp = "";
	        }

	    }
	    
	    return addTerm(terms);
	}

	//Simplifies polynomials
	private static SingleLinkedList<Term> simplify(SingleLinkedList<Term> poly)
	{
		//variables
		Iterator iterator1 = poly.iterator();
		SingleLinkedList<Term> result = new SingleLinkedList<>();
		SingleLinkedList<Term> copyPoly = new SingleLinkedList<>();

		//copies poly
		while (iterator1.hasNext())
		{
			copyPoly.add((Term) iterator1.next());
		}
		
		//traverse through copyPoly until empty
		while (copyPoly.size() != 0)
		{
			//variable reset
			Term current = copyPoly.remove(0);
			int coe = current.getCoe();
			int exp = current.getExp();
			iterator1 = copyPoly.iterator();
			
			//traverse through copyPoly
			while (iterator1.hasNext())
			{
				//variable reset
				Term temp = (Term) iterator1.next();
				
				//terms that can be simplified are removed and coefficients are added together
				if (temp.getExp() == current.getExp())
				{
					coe += temp.getCoe();
					iterator1.remove();
				}
			}
			
			//ensure we are adding if the term isn't 0 unless constant
			if (coe != 0 || exp == 0)
			{
				result.add(new Term(coe, exp));
			}
		}
		return result;
	}
}