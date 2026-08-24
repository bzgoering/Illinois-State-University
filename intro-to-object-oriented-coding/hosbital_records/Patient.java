/**
 * parent class for Covid 19 patients and regular patients
 */
public abstract class Patient 
{
	//variables
	private int id;
	private String fName;
	private String lName;
	private int age;
	private boolean pcr;
	
	//main constructor with four param
	//param: int, string, string, int
	public Patient(int i, String f, String l, int a)
	{
		id = i;
		fName = f;
		lName = l;
		age = a;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @return the fName
	 */
	public String getfName() {
		return fName;
	}

	/**
	 * @return the lName
	 */
	public String getlName() {
		return lName;
	}

	/**
	 * @return the age
	 */
	public int getAge() {
		return age;
	}

	/**
	 * @return the pcr
	 */
	public boolean isPcr() {
		return pcr;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @param fName the fName to set
	 */
	public void setfName(String fName) {
		this.fName = fName;
	}

	/**
	 * @param lName the lName to set
	 */
	public void setlName(String lName) {
		this.lName = lName;
	}

	/**
	 * @param age the age to set
	 */
	public void setAge(int age) {
		this.age = age;
	}

	/**
	 * @param pcr the pcr to set
	 */
	public void setPcr(boolean pcr) {
		this.pcr = pcr;
	}
	
	//abstract method for treat
	//returns string
	public abstract String treat();
	
	public String toString()
	{
		return null;
	}
}
