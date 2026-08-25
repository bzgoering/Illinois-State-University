package airbonReservation;

import java.util.*;
/*
 * This class creates cinctructors and methods for an airbnb listing object
 * that gives a list of different airbnbs with their locations, rating, type, and name with their
 * own code. the methods will enable user to list them by location, rating, and type
 * the user will also be able to pick an airbnb, input the nightsd they want to stay and 
 * see a whole summary of what they got and the recipt
 * 
 * Author: Ben Goering
 */
public class AirbnbListing {
	//variables
	public static int totalListings = 0;
	private final double CLEANING_FEE = 35.00;
	private final double SERVICE_FEE = 13.75;
	private final double TAX_RATE = .12;
	
	private String listingCode;
	private String location;
	private String listingName;
	private String listingType;
	private String wifiName;
	private String wifiPass;
	private String confirmCode;
	private String cityName;
	private int night;
	private int key;
	private double pricePerNight;
	private double avgRating;
	char firstLettetName;
	Random rand = new Random();
	
	//default constructor
	public AirbnbListing()
	{
		wifiName = "Guest";
		wifiPass = "BeMyGuest23";
		totalListings += 1;
	}
	
	//constructor with 4 args
	public AirbnbListing(String name, String loc, String listType, int price)
	{
		this();
		listingName = name;
		pricePerNight = (double)price;
		location = loc;
		listingType = listType;
		avgRating = rand.nextDouble(5)+1;
		calcMinimumNights(listingType);
		generateListingCode();
	}
	
	//calculates the minimum nights required at each type of listing
	//parms: String listing type
	//returns: int of minimum nights for a specific type of ari bnb
	private int calcMinimumNights(String listType)
	{
		//gets minimum night from entry
		if (listType.equals("Studio"))
		{
			setNight(1);
		}
		else if (listType.equals("Apartment"))
		{
			setNight(3);
		}
		else if (listType.equals("Private room"))
		{
			setNight(1);

		}
		else if (listType.equals("Town House"))
		{
			setNight(7);

		}
		
		//output
		int result = night;
		return result;
	}
	
	//generates a code using variables: listingName, location, listingType
	//parms: void
	//return: void
	private void generateListingCode()
	{	
		//finds first letter of listing name
		char name = listingName.substring(0,1).charAt(0);
		
		//find city and first letter
		int start = location.lastIndexOf(" ") + 1;
		cityName = location.substring(start);
		
		char city = cityName.charAt(0);
		
		//finds fist letter of listing type
		char type = listingType.substring(0,1).charAt(0);
		
		//total listings if under 10
		String total = "";
		if (totalListings < 10)
		{
		total = "0" + totalListings;
		}
		
		//output
		String result = "" + name + city + type + total;
		listingCode = result;
	}
	
	//generates confirmCode using listingCode
	//return: void
	//parm: void
	private void generateConfirmationCode()
	{
		String str = listingCode.substring(0,3);
		int code = rand.nextInt(9999)+1000;
		
		//output
		String result = str + code;
		confirmCode = result;
	}

	
	//gets listing code
	//returns String
	public String getListingCode() {
		return listingCode;}
	
	//sets listingCode
	//param: String
	public void setListingCode(String listingCode) {
		this.listingCode = listingCode;}

	//gets locations
	//returns a string
	public String getLocation() {
		return location;}

	//sets the locations
	//param: String of address
	public void setLocation(String location) {
		this.location = location;}
	
	//gets listing name
	//param: a String
	public String getListingName() {
		return listingName;}
	
	//sets the listing name
	//returns a string
	public void setListingName(String listingName) {
		this.listingName = listingName;}
	
	//gets listing type
	//param: a String
	public String getListingType() {
		return listingType;}

	//sets listing type
	//returns a string
	public void setListingType(String listingType) {
		this.listingType = listingType;}

	//gets wifi name
	//param: a String
	public String getWifiName() {
		return wifiName;}

	//set wifi name
	//returns a string
	public void setWifiName(String wifiName) {
		this.wifiName = wifiName;}

	//gets wifi password
	//param: a String
	public String getWifiPass() {
		return wifiPass;}

	//sets wifi password
	//returns a string
	public void setWifiPass(String wifiPass) {
		this.wifiPass = wifiPass;}

	//gets a confirmation code
	//param: a String
	public String getConfirmCode() {
		return confirmCode;}

	//sets a confirmation code
	//returns a string
	public void setConfirmCode(String confirmCode) {
		this.confirmCode = confirmCode;}

	//gets number of nights
	//returns an integer
	public int getNight() {
		return night;}

	//sets number of nights
	//param: integer
	public void setNight(int night) {
		this.night = night;}

	//gets a key code
	//returns a 4 digit code
	public int getKey() {
		return key;}

	//sets key code
	//param: a four digit integer
	public void setKey(int key) {
		this.key = key;}

	//gets price per night
	//returns a price in form of a double
	public double getPricePerNight() {	
		return pricePerNight;}

	//sets price per night
	//param: double
	public void setPricePerNight(double pricePerNight) {
		this.pricePerNight = pricePerNight;}

	//gets avg rating
	//returns a double with one decimal place
	public Double getAvgRating() {
		return (double) Math.round(avgRating);}

	//sets avg rating
	//params a double 
	public void setAvgRating(double avgRating) {
		this.avgRating = avgRating;}

	//gets city name
	//param: a String
	public String getCityName() {
		return cityName;}
	
	//toString method that formats the listings
	//param: void
	//return: string of listings and details about them
	public String toString()
	{
		String result = String.format("%s %16s %15s %15s %8s", getListingCode(), getListingName(), getCityName(), getListingType(), getAvgRating());
		return result;
	}
	
	//calculates total price
	//returns the total price of the airbnb listing
	public double calculateTotalPrice()
	{
		double result = (pricePerNight * night) + CLEANING_FEE + SERVICE_FEE;
		double tax = result * TAX_RATE;
		result += tax;
		return result;
	}
	
	//generates a summary that has price, wifi details, address, and check in/check out times
	//returns a string with the informaation in the summary
	public String bookingListing(int x)
	{
		key = rand.nextInt(9999)+1000;
		String result;
		if (night < x)
		{
			result = "requested nights violate the minimum";
		}
		else
		{
			generateConfirmationCode();
			result = "Summary of your trip:\n";
			result += "Your wifi username: " + getWifiName() + ", password: " + getWifiPass() + "\n";
			result += "Adress: " + getLocation() + "\n";
			result += "Check-in: after 3:00pm\n";
			result += "Check-out: by 10:00am\n";
			result += "Keypad Code: " + getKey() + "\n";
			result += "total(USD):" + calculateTotalPrice();
		}
		return result;
	}
	
	//generates a receipt from price
	//returns it in the form of a string
	public String displayReceipt()
	{
		double price = (pricePerNight * night);
		String result = "Price breakdown\n";
		result += getPricePerNight() + " X " + night + " night " + price + "\n";
		result += "Cleaning fee: " + CLEANING_FEE + "\n";
		result += "Service fee: " + SERVICE_FEE + "\n";
		price += CLEANING_FEE + SERVICE_FEE;
		double tax = price * TAX_RATE;
		result += "Taxes: " + tax + "\n";
		result += "Total (USD): " + calculateTotalPrice();
		return result;
	}
}
