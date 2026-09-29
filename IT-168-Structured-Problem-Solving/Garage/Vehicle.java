package Garage;

/*
 * this class is where all the information about a vehicle object is stored
 * Author: Ben Goering
 */
public class Vehicle {
private String plateNum;
private String maker;
private String vType;
private String year;
private String model;
private String color;
private String gotDate;
private double cost;
private double currentVal;
private boolean workNeeded;
private String notes;
private String miles;

//defult constructor
public Vehicle()
{
	plateNum = "";
	maker = "";
	vType = "";
	year = "";
	model = "";
	color = "";
	gotDate = "00/00/0000";
	cost = 0.0;
	currentVal = 0.0;
	workNeeded = false;
	notes = "";
	miles = "0";
}

//main constructor
//params: eight Strings, 2 doubles, a boolean
public Vehicle(String plate, String manuf, String type, String y, String inputModel, String inputColor, String date, double inputCost, double CVal, boolean work, String inputNotes, String mile)
{
	plateNum = plate;
	maker = manuf;
	vType = type;
	year = y;
	model = inputModel;
	color = inputColor;
	gotDate = date;
	cost = inputCost;
	currentVal = CVal;
	workNeeded = work;
	notes = inputNotes;
	miles = mile;
}

/**
 * @return the miles
 */
public String getMiles() {
	return miles;
}

/**
 * @param miles the miles to set
 */
public void setMiles(String mile) {
	miles = mile;
}

/**
 * @return the plateNum
 */
public String getPlateNum() {
	return plateNum;
}

/**
 * @param plateNum the plateNum to set
 */
public void setPlateNum(String plateNum) {
	this.plateNum = plateNum;
}

/**
 * @return the maker
 */
public String getMaker() {
	return maker;
}

/**
 * @param maker the maker to set
 */
public void setMaker(String maker) {
	this.maker = maker;
}

/**
 * @return the vtype
 */
public String getVtype() {
	return vType;
}

/**
 * @param vtype the vtype to set
 */
public void setVtype(String vtype) {
	vType = vtype;
}

/**
 * @return the year
 */
public String getYear() {
	return year;
}

/**
 * @param year the year to set
 */
public void setYear(String year) {
	this.year = year;
}

/**
 * @return the model
 */
public String getModel() {
	return model;
}

/**
 * @param model the model to set
 */
public void setModel(String model) {
	this.model = model;
}

/**
 * @return the color
 */
public String getColor() {
	return color;
}

/**
 * @param color the color to set
 */
public void setColor(String color) {
	this.color = color;
}

/**
 * @return the gotDate
 */
public String getGotDate() {
	return gotDate;
}

/**
 * @param gotDate the gotDate to set
 */
public void setGotDate(String gotDate) {
	this.gotDate = gotDate;
}

/**
 * @return the cost
 */
public double getCost() {
	return cost;
}

/**
 * @param cost the cost to set
 */
public void setCost(double cost) {
	this.cost = cost;
}

/**
 * @return the currentVal
 */
public double getCurrentVal() {
	return currentVal;
}

/**
 * @param currentVal the currentVal to set
 */
public void setCurrentVal(double currentVal) {
	this.currentVal = currentVal;
}

/**
 * @return the workNeeded
 */
public boolean isWorkNeeded() {
	return workNeeded;
}

/**
 * @param workNeeded the workNeeded to set
 */
public void setWorkNeeded(boolean workNeeded) {
	this.workNeeded = workNeeded;
}

/**
 * @return the notes
 */
public String getNotes() {
	return notes;
}

/**
 * @param notes the notes to set
 */
public void setNotes(String notes) {
	this.notes = notes;
}

//return string of contents of vehicle
public String toString()
{
	String result =  "plate Num:      " + plateNum + "\n";
		   result += "Year:           " + year + "\n";
		   result += "Miles:          " + miles + "\n";
		   result += "Manufact:       " + maker + "\n";
		   result += "Model:          " + model + "\n";
		   result += "type:           " + vType + "\n";
		   result += "Acquired:       " + gotDate + "\n";
		   result += "Needs work:     " + workNeeded + "\n";
		   result += "Nature of work: " + notes + "\n\n";
	return result;
}
}
