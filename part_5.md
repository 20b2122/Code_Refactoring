Customer2.java

```Java 

// Replace constructors with factories

public abstract class Customer2 {
	
	private String custRating;
	static final int PREMIER = 2;
	static final int VALUED = 1;
	static final int DEADBEAT = 0;
	
	public String getCustRating(){ return custRating; }
	
	public void setCustRating(String custRating) { this.custRating = custRating; }
	
	public static void main(String[] args){
		
		// This factory will generate specific subclasses of Customer3
		CustomerFactory customerFactory  = new CustomerFactory();
		
		// This assigns the methods and fields for the class Premier
		
		// Customer3 goodCustomer = customerFactory.getCustomer(Customer3.PREMIER);
		
		Customer2 goodCustomer = customerFactory.getCustomer("Premier");
		
		System.out.println("This Customers Rating: " + goodCustomer.getCustRating());
		
		
		// This assigns the methods and fields for the class Deadbeat
		// This is replaced when the Switch is deleted (in CustomerFactory)
		// Customer3 badCustomer = customerFactory.getCustomer(Customer3.DEADBEAT);
		Customer2 badCustomer = customerFactory.getCustomer("Deadbeat");
		System.out.println("This Customers Rating: " + badCustomer.getCustRating());
		
	}

}

// haivng constructor to show that everything works.
class Premier extends Customer2{
	
	Premier(){
		
		setCustRating("Premier Customer");
		
	}
	
}

class Deadbeat extends Customer2{
	
	Deadbeat(){
		
		setCustRating("Deadbeat Customer");
		
	}
	
}

class CustomerFactory{
	
	// This is a poor design because this switch needs updated every time a new subclass is created
	/*
	public Customer2 getCustomer(int custType){
		
		switch (custType){
		case 2:
			return new Premier();
		case 0:
			return new Deadbeat();
		default:
			throw new IllegalArgumentException("Invalid Customer Type"); // didnt enter a valid number (0,1, or 2)
		
		}
		
	}
	*/
	
public Customer2 getCustomer(String custName){
		
		try {
			
			// forName returns a class object with the String that is
			// passed to it. newInstance() creates an instance of the class
			
			return (Customer2) Class.forName(custName).newInstance();
			
		}
		
		catch(Exception e){
			
			throw new IllegalArgumentException("Invalid Customer Type");
		
		}
		
	}
	
}

```


Athlete.java

```Java 

// Create a Factory that creates singletons

import java.lang.reflect.Method;

public class Athlete {
	
	private String athleteName = "";
	
	public String getAthleteName() {
		return athleteName;
	}

	public void setAthleteName(String athleteName) {
		this.athleteName = athleteName; 
	}
	
	//Singleton Restrics a Class to 1 Object
	public static Athlete getInstance(){
		return null;
	}

	
}

class GoldWinner extends Athlete{
	
	// Set to null to signify that an instance of type GoldWinner doesn't exist
	private static GoldWinner goldAthlete = null;
	
	// Constructor is set to private to keep other classes from creating an instance of GoldWinner
	private GoldWinner(String athleteName){ 
		setAthleteName(athleteName);
	}
	
	// Creates 1 instance of GoldWinner (Simple Singleton)
	public static GoldWinner getInstance(String athleteName){
		
		// If an instance of GoldWinner doesn't exist, create one
		if(goldAthlete == null){
			goldAthlete = new GoldWinner(athleteName);
		} 
		
		return goldAthlete;
		
	}
	
}

// same as the GoldWinner but cahnge the namings to Silver winner, same goes with the BronzeWinner
class SilverWinner extends Athlete{
	
	private static SilverWinner silverAthlete = null;
	
	private SilverWinner(String athleteName){ 
		setAthleteName(athleteName);
	}
	
	public static SilverWinner getInstance(String athleteName){

		if(silverAthlete == null){
			silverAthlete = new SilverWinner(athleteName);
		} 
		
		return silverAthlete;
		
	}
	
}

class BronzeWinner extends Athlete{
	
	private static BronzeWinner bronzeAthlete = null;
	
	private BronzeWinner(String athleteName){ 
		setAthleteName(athleteName);
	}
	
	public static BronzeWinner getInstance(String athleteName){
		
		if(bronzeAthlete == null){
			bronzeAthlete = new BronzeWinner(athleteName);
		} 
		
		return bronzeAthlete;
		
	}
	
}

// spit out the right subclass based on the type of subclass that it is asked for
class MedalFactory{
	
	public Athlete getMedal(String medalType, String athleteName){
		
		try {
			
			// Define the type of the parameter that will be passed to the method create below
			Class[] athleteNameParameter = new Class[]{String.class};
			
			// forName returns a class object with the String that is passed to it. getMethod 
			// returns the method provided the second parameter defines the type of parameter 
			// passed to the method
			Method getInstanceMethod =  Class.forName(medalType).getMethod("getInstance", athleteNameParameter);
			
			// Create an object array with the parameter values that will be passed to the method getInstance
			Object[] params = new Object[]{new String(athleteName)};
			
			// Pass the parameters to method getInstance and return a subclass of type Athlete
			return (Athlete) getInstanceMethod.invoke(null, params);
			
		}
		
		catch(Exception e){
			throw new IllegalArgumentException("Invalid Medal Type");
		}
		
	}
	
}

class TestMedalWinner{
	
	public static void main(String[] args){
		
		MedalFactory medalFactory = new MedalFactory();
		
		Athlete goldWinner = medalFactory.getMedal("GoldWinner", "Dave Thomas");
		Athlete silverWinner = medalFactory.getMedal("SilverWinner", "Mac McDonald");
		Athlete bronzeWinner = medalFactory.getMedal("BronzeWinner", "David Edgerton");
		
		// creating a new gold winner - didnt come out because the gold winner is already set above
		Athlete goldWinner2 = medalFactory.getMedal("GoldWinner", "Ray Kroc");
		
		System.out.println("Gold Medal Winner: " + goldWinner.getAthleteName());
		System.out.println("Silver Medal Winner: " + silverWinner.getAthleteName());
		System.out.println("Bronze Medal Winner: " + bronzeWinner.getAthleteName());
		
		// Even though I tried to create a new Object of type GoldWinner
		// it was rejected and the original object remained
		
		System.out.println("Gold Medal Winner: " + goldWinner2.getAthleteName());
		
	}
	
}


```