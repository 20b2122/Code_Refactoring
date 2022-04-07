replace conditional statements using strategy design pattern

CalculateSalary.java

``` Java

// If you needed to figure out the pay for different
// employees, you'd have to perform many conditional 
// checks to see if they get a bonus, or not.
// It would also require code writing any time a special
// circumstance occurred. The strategy pattern eliminates that

public class CalculateSalary {
	
	public static void main(String[] args){
		
		// This seems to work as long as there are no changes
		// I could make subclasses to eliminate the need for the boolean 
		
		Employees salesman = new Employees(true, 15000.00, 0.20);
		
		System.out.println(salesman.getSalary());
		
		Employees secretary = new Employees(false, 20000.00);
		
		System.out.println(secretary.getSalary());
		
		// What happens if I need to change the bonus amount?
		// What happens if we want to create a new pay structure?
		
	}

}

class Employees{
	
	private boolean bonus = false;
	private double salary = 0.0;
	
	// If the bonus amount changes I have to add a new field
	// and a new constructor and change getSalary

	// changing class code is BAD
    // if yoou have to change the class to add features, your design is bad 
	private double bonusAmount = .15;
	
	public Employees(boolean bonus, double salary, double bonusAmount) {
		super();
		this.salary = salary;
		this.bonus = bonus;
		this.bonusAmount = bonusAmount;
	}
	
	public Employees(boolean bonus, double salary) {
		super();
		this.salary = salary;
		this.bonus = bonus;
	}

	public Employees() {}

	public double getSalary() {
		
		if(bonus){
			
			// CHANGED return salary + (salary * .15);
			
			return salary + (salary * bonusAmount);
			
		}
		
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}
	
}

// That isn't as bad as if we tried to do this with conditionals
/*
if(salesman.getsBonus()){
	
    //this gets extremely busy and hard to read
	double payAmount = salesman.getSalary() + (salesman.getSalary() * salesman.getBonusAmount());
	
	salesman.setPay(payAmount);
	
} else { ............ }

*/

```


ReplaceConditionalWithStrategy.java

``` Java

// Conditional logic is often used to decide which algorithm to use.

// The strategy design pattern is used to dynamically
// change the algorithm used by an object at run time
// which makes it great for eliminating conditionals

// You create subclasses for each algorithm and then the right algorithm is used at run time. 
// This is another example of how you can replace conditionals with Polymorphism

public class ReplaceConditionalWithStrategy {

	public static void main(String[] args){
		
		Employee salesman = new Salesman(15000.00);
		
		Employee secretary = new Secretary(25000.00);
		
		System.out.println("Salesman Pay: $" + salesman.getPay());
		System.out.println("Secretary Pay: $" + secretary.getPay());
		
		// You can add a bonus to a salesman's salary at run time
		salesman.setBonusOption(new GetsBonus());
		System.out.println("Salesman's New Pay: $" + salesman.getPay());
		
		// You could also set set Bonus type in the constructor
		Employee salesTrainee = new Salesman(15000.00, new NoBonus());
		System.out.println("Sales Trainee Pay: $" + salesTrainee.getPay());
		
        //secretary with bonus
		secretary.setBonusOption(new GetsBonus());
		System.out.println("Secretary Pay: $" + secretary.getPay());
		
	}
	
	
}

class Employee{
	
	protected double salary = 0.0;
	
	// We use an instance of the Pay interface
	// Employee doesn't care what Pay does
	// This allows the capabilities of objects to change
	// at run time
		
	public Pay payType = new NoBonus();
	
	Employee(double salary){
		this.salary = salary;
	}
	
	Employee(double salary, Pay payType) {
		this.salary = salary;
		this.payType = payType;
	}
	
    // to set the bonus option
    // easily get the pay classes without affecting the amount
	public void setBonusOption(Pay newPayType){
		payType = newPayType;
	}
	
    // to get the pay
	public double getPay(){
		return payType.getPay(this.salary);
	}
	
}

// By implementing Pay classes can easily change pay amount without effecting the program

interface Pay{
	double getPay(double salary);
}

class GetsBonus implements Pay{

	public double getPay(double salary) {
		return salary + (salary * .15);
	}
	
}

class NoBonus implements Pay{
	
	public double getPay(double salary) {
		return salary;
	}
	
}

// Adding new pay structures has little effect

class Bonus20Per implements Pay{
	
	public double getPay(double salary) {
		return salary + (salary * .20);
	}
	
}

class Salesman extends Employee{

	Salesman(double salary) {
		super(salary);
	}

	public Salesman(double salary, Pay payType) {
		
		super(salary);
		setBonusOption(payType);
		
	}

}

class Secretary extends Employee{

	Secretary(double salary) {
		super(salary);
	}
	
	Secretary(double salary, Pay payType) {
		
		super(salary);
		setBonusOption(payType);
		
	}
	
}

```


Guard Clause Code

``` Java

// The Guard Clause makes the normal path of execution clear
// by eliminating the ins and outs of using if-else statements
		
// If we thought it was just as likely that a bag would weigh
// less than 50 lbs as if it weighed over 70 lbs we wouldn't
// use the guard clause.
		
		bagFees = 0;
		
		for(int theBag = 0; theBag < numberOfBags; theBag++){
			
			if(bagWeight[theBag] < 50){ bagFees += bagUnder50lbs(theBag); }
			
			if(bagWeight[theBag] < 70 && bagWeight[theBag] >= 50) {
				
				bagFees += bag50To70lbs(theBag);
				
			}
			
			if(bagWeight[theBag] >= 70) { bagFees += 200; }
			
		}
		
		System.out.println("Bag Fees: $" + bagFees);
		
		
	}
	
	// guard clause is normally used below.
    // makes the code more readable but also to emphasize the if statements
	int getBagPrice(double[] weight, int theBag){	
			
		if(weight[theBag] < 50) return bagUnder50lbs(theBag);
			
		if(weight[theBag] < 70 && weight[theBag] >= 50) return bag50To70lbs(theBag);
			
		if(weight[theBag] >= 70) return 200;
		
		return 200;
			
	}

```