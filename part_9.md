### Replace Implied Primitive Trees with the Composite Pattern

What do you do when you want to model a tree structure? 
- Either list the info as primitives and then search through them for what you want 
- Or create objects that represent each level of the tree with the composite pattern


ProductComponent.java

```Java

// This acts as an interface for every Product and ProductGroup that is created
// using sbstract class to not force subclasses to create all the methods inside of this class
public abstract class ProductComponent {

	void add(ProductComponent newProductComponent) { }
	
	void remove(ProductComponent newProductComponent) { }
	
	ProductComponent getProductComponent(int componentIndex) { return null; }
	
	String getProductGroupName() { return null; }
	
	abstract void displayProductInfo();
	
}

```


ProductGroup.java

```Java

import java.util.ArrayList;
import java.util.Iterator;


// handle all the product groups
public class ProductGroup extends ProductComponent{
	
	// Each group and all the products contained in that group are stored in this ArrayList
	ArrayList<ProductComponent> productComponents = new ArrayList<ProductComponent>();
	
	private String productGroupName;

	public ProductGroup(String productGroupName) {
		super();
		this.productGroupName = productGroupName;
	}

	public void add(ProductComponent newProductComponent) {
		productComponents.add(newProductComponent);
	}

	public void remove(ProductComponent newProductComponent) {
		productComponents.remove(newProductComponent);
	}

	public ProductComponent getProductComponent(int componentIndex) {
		return (ProductComponent) productComponents.get(componentIndex);
	}

	public String getProductGroupName() { return productGroupName; }

	public void displayProductInfo() {
		System.out.println(getProductGroupName());
		
		// Cycle through and print every product in this Product Group
        // using iterator
		Iterator<ProductComponent> productIterator = productComponents.iterator();
		
		while(productIterator.hasNext()){

			ProductComponent productInfo = (ProductComponent) productIterator.next();
			productInfo.displayProductInfo();
			
		}
		
		System.out.println();
		
	}

}

```

Product.java

```Java

// Each product represents a leaf in the ProductGroup
public class Product extends ProductComponent{

	private String productName;
	private double productPrice;
	
    //constructor
	public Product(String productName, double productPrice) {
		super();
		this.productName = productName;
		this.productPrice = productPrice;
	}

    // setting the getter and setter for name and price
	public String getProductName() { return productName;}
	public void setProductName(String productName) {
		this.productName = productName;
	}

	public double getProductPrice() { return productPrice; }
	public void setProductPrice(double productPrice) {
		this.productPrice = productPrice;
	}

	void displayProductInfo() {
		System.out.println(getProductName() + " $" + getProductPrice());
	}

}

```


ProductSystem.java

```Java

//to test all the codes
public class ProductSystem {

	public static void main(String[] args){

		//making the tree

		// Add product groups
		ProductComponent produce = new ProductGroup("Produce");
		ProductComponent cereal = new ProductGroup("Cereal");
		
		// Top level component that contains all products
		ProductComponent everyProduct = new ProductGroup("All Products\n");
		
		// Add produce and cereal groups to everyProduct list
		everyProduct.add(produce);
		everyProduct.add(cereal);
		
		// Add individual products to the groups
        // you can make trees as big as you want. the composite pattern make any type of tree structure
		produce.add(new Product("Tomato", 1.99));
		produce.add(new Product("Orange", .99));
		produce.add(new Product("Potato", .35));
		
		cereal.add(new Product("Special K", 4.79));
		cereal.add(new Product("Cheerios", 3.68));
		cereal.add(new Product("Raisin Bran", 3.68));
		
		// Display all products sorted into groups
		everyProduct.displayProductInfo();
		
	}
	
}

```