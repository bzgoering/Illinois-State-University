package PolynomialCalculator;

public class Term
  {
    //variables
    private int coefficient;
    private int exponent;

    //constructor
    public Term (int coe, int expo)
    {
      coefficient = coe;
      exponent = expo;
    }

    //gets coefficient
    public int getCoe()
    {
      return coefficient;
    }

    //return exponent
    public int getExp()
    {
      return exponent;
    }

    public String toString()
    {
      String result = "";

      //adds exponent and proper sign
		  if (exponent > 1 || exponent <= -1)
	  	{
		  	if (coefficient == 1)
			  {
			  	result = "x^" + exponent;
			  }
		  	else if (coefficient == -1)
		  	{
		  		result = "-x^" + exponent;
  			}
  			else
  			{
  				result = coefficient + "x^" + exponent;
  			}
  		}
  		//adds proper sign and only x
  		else if (exponent == 1)
  		{
  			if (coefficient == 1)
  			{
  				result = "x";
  			}
  			else if (coefficient == -1)
  			{
  				result = "-x";
  			}
  			else
  			{
  				result = coefficient + "x";
  			}
  		}
  		//constant
  		else if (exponent == 0)
  		{
  			result += coefficient;
  		}
  		
  		return result;
  	}
  }
