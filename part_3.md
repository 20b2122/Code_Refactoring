```Java 
public class Product {
    private String name = "";
	private double price = 0.0;
	private double shippingCost = 0.0;
	private int quantity = 0;
	
	public String getName(){ return name; }
	public double getPrice(){ return price; }
	public double getShippingCost(){ return shippingCost; }
	public int getQuantity(){ return quantity; }
	
	Product(String name, double price, double shippingCost, int quantity){
		
		this.name = name;
		this.price = price;
		this.shippingCost = shippingCost;
		this.quantity = quantity;
		
	}
	
	public double getTotalCost(){
		
		double quantityDiscount = 0.0;
		
		// save them using explaining variable - when having expressions that are complicated
		// final - so that it will only have one value per itertion
		final boolean over50Products = (quantity > 50) || ((quantity * price) > 500); 
		final boolean over25Products = (quantity > 25) || ((quantity * price) > 100);
		final boolean over10Products = (quantity >= 10) || ((quantity * price) > 50);
		
		if(over50Products) {
			quantityDiscount = .10;
			
		} else if(over25Products) {
			quantityDiscount = .07;
			
		} else if(over10Products) {
			quantityDiscount = .05;
			
		}
		
		/* // BAD CODE 1 - a lot is going on and hard to see what is happening
		if((quantity > 50) || ((quantity * price) > 500)) {
			quantityDiscount = .10;
			
		} else if((quantity > 25) || ((quantity * price) > 100)) {
			quantityDiscount = .07;
			
		} else if((quantity >= 10) || ((quantity * price) > 50)) {
			quantityDiscount = .05;
			
		}
		*/
		
		double discount = ((quantity - 1) * quantityDiscount) * price;
		return (quantity * price) + (quantity * shippingCost) - discount;
		
	}
}

```


```Java 
import java.util.ArrayList;

public class Store{
	
	public ArrayList<Product> theProducts = new ArrayList<Product>();
	
	public void addAProduct(Product newProduct){
		
		theProducts.add(newProduct);
		
	}
	
	public void getCostOfProducts(){
		
		for(Product product : theProducts){
			final int numOfProducts = product.getQuantity();  
			final String prodName = product.getName();
			final double cost = product.getTotalCost();
			
			// use an explaining variable for complicated calculations
			final double costWithDiscount = product.getTotalCost() / product.getQuantity();
			final double costWithoutDiscount = product.getPrice() + product.getShippingCost();
			
			System.out.println("Total cost for " + numOfProducts + " " + prodName + "s is $" + cost);
			System.out.println("Cost per product " + costWithDiscount);
			System.out.println("Savings per product " + (costWithoutDiscount - costWithDiscount) + "\n");
			
			
			/* // BAD CODE 2 - the code is very long and hard to read
			System.out.println("Total cost for " + product.getQuantity() + " " + product.getName() + "s is $" + product.getTotalCost());
			
			System.out.println("Cost per product " + product.getTotalCost() / product.getQuantity());
			
			System.out.println("Savings per product " + ((product.getPrice() + product.getShippingCost()) - (product.getTotalCost() / product.getQuantity())) + "\n");
			*/
			
		}
		
	}
	
	public static void main(String[] args){
		
		Store cornerStore = new Store();
		
		cornerStore.addAProduct(new Product("Pizza", 10.00, 1.00, 52));
		
		cornerStore.addAProduct(new Product("Pizza", 10.00, 1.00, 26));
		
		cornerStore.addAProduct(new Product("Pizza", 10.00, 1.00, 10));
		
		cornerStore.getCostOfProducts();
		
	}

	/* // BAD CODE 3 - Why Is it Bad to Assign Many Values to a Temp?
	// because the name is not descriptive and possible to forget what it means
	double temp = totalCost / numberOfProducts;
	
	temp = temp + shipping;
	
	// temp may be the product price + shipping - discount
	// but will this make sense a year from now?
	
	temp = temp - discount;
	*/
	
	/* // BAD CODE 4 - Don't assign values to parameters 	
	// because the name is not descriptive and possible to forget what it means
	public double getTotPrice(double quantity, double price, double shippingCost, double discount) {
		
		price = price + shippingCost;
		price = price * quantity;
		return price - discount;
		
	}
	*/

}

```