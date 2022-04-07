TurnConditionalIntoMethods.java

```Java 

public class TurnConditionalIntoMethods {
	
	static int bagOver70lbs(){
		return 200;
	}
	
	static int bagUnder50lbs(int bagNumber){
		return (bagNumber < 1)?25:35;
	}
	
	static int bag50To70lbs(int bagNumber){
		return (bagNumber < 2)?100:150;
	}
	
	public static void main(String[] args){
		
		int[]bagWeight = new int[]{25,55,75};
		int numberOfBags = bagWeight.length;
		int bagFees = 0;
		
		for(int i = 0; i < numberOfBags; i++){
	
			if(i < 1){
				
				if(bagWeight[i] < 50){
					
					if(i == 0) {bagFees+= 25;} else {bagFees+= 35;}
					
				} else if(bagWeight[i] < 70){
					
					if(i == 0) {bagFees+= 100;} else if(i == 1){bagFees+= 150;} else {bagFees+= 200;}
					
				} else { bagFees+= 200; }
				
			} else if(i >= 0 && bagWeight[i] < 70){
				
				if(i == 1) {bagFees+= 100;} else {bagFees+= 150;}
			
			} else {
				bagFees+= 200;
			}
		
		}
		
		System.out.println("Bag Fees: $" + bagFees);
		
		////////////////////////////
		
		bagFees = 0;
		
		for(int theBag = 0; theBag < numberOfBags; theBag++){
			
			if(bagWeight[theBag] < 50){
				
				bagFees += bagUnder50lbs(theBag);
				
			} else
				
				if(bagWeight[theBag] < 70){
					
					bagFees += bag50To70lbs(theBag);
					
				} else {
					
					bagFees += 200;
					
				}
			
		}
		
		System.out.println("Bag Fees: $" + bagFees);
		
		//////////////////////////
		
        //code is much more readable and it still works the same as above

		bagFees = 0;
		
		for(int theBag = 0; theBag < numberOfBags; theBag++){
			
            //not using any else statement
			if(bagWeight[theBag] < 50){ bagFees += bagUnder50lbs(theBag); }
			
			if(bagWeight[theBag] < 70 && bagWeight[theBag] >= 50) {
				bagFees += bag50To70lbs(theBag);
			}
			
			if(bagWeight[theBag] >= 70) { bagFees += 200; }
			
		}
		
		System.out.println("Bag Fees: $" + bagFees);
		
	}

}

```


ReplaceConditionalWithPoly.java

```Java 
// replace conditionals with polymorphism
// If a conditional chooses different behavior
// depending on the type of object use polymorphism

public class ReplaceConditionalWithPoly {

	public static void main(String[] args){
		
		String doggy = "Dog";
		String kitty = "Cat";
		
		makeSound(doggy);
		
		// Using subclasses eliminates the conditional and makes the program easy to extend
		//polymorphism
		Animal rex = new Dog("Woof");
		Animal sophie = new Cat("Meow");
		
		System.out.println(sophie.getSound());
		
	}
	
	static void makeSound(String animal){
		
		switch(animal){
		
		case "Dog":
			System.out.println("Woof");
			break;
			
		case "Cat":
			System.out.println("Meow");
			break;
			
		default:
			throw new RuntimeException("I Don't Know that Animal");
		
		}
		
	}
	
}

// make things more dynamic and to eliminate the switch statement, hence class is created
class Animal {
	
	private String sound = "";

	public String getSound() {
		return sound;
	}

	public void setSound(String sound) {
		this.sound = sound;
	}

	public Animal(String sound) {
		super();
		this.sound = sound;
	}
	
}

class Dog extends Animal{

	public Dog(String sound) {
		super(sound);
	}
	
}

class Cat extends Animal{

	public Cat(String sound) {
		super(sound);
	}
	
}

```