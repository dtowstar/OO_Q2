
interface Visitable {

	public double accept(Visitor visitor);
	
}

class Liquor implements Visitable {
	
	private double price;

	Liquor(double item) {
		price = item;
	}

	public double accept(Visitor visitor) {
		return visitor.visit(this);
	}

	public double getPrice() {
		return price;
	}
	
}


class Necessity implements Visitable {
	
	private double price;

	Necessity(double item) {
		price = item;
	}

	public double accept(Visitor visitor) {
		return visitor.visit(this);
	}

	public double getPrice() {
		return price;
	}
	
}

class Tobacco implements Visitable {
	
	private double price;

	Tobacco(double item) {
		price = item;
	}

	public double accept(Visitor visitor) {
		return visitor.visit(this);
	}

	public double getPrice() {
		return price;
	}
	
}


interface Visitor {
	
	public double visit(Liquor liquorItem);
	
	public double visit(Tobacco tobaccoItem);
	
	public double visit(Necessity necessityItem);
	
}

import java.text.DecimalFormat;

class TaxVisitor implements Visitor {
	
	
	DecimalFormat df = new DecimalFormat("#.##");

	public TaxVisitor() {
	}
	
	public double visit(Liquor liquorItem) {
		System.out.println("Liquor Item: Price with Tax");
		return Double.parseDouble(df.format((liquorItem.getPrice() * .18) + liquorItem.getPrice()));
	}
	
	public double visit(Tobacco tobaccoItem) {
		System.out.println("Tobacco Item: Price with Tax");
		return Double.parseDouble(df.format((tobaccoItem.getPrice() * .32) + tobaccoItem.getPrice()));
	}
	
	public double visit(Necessity necessityItem) {
		System.out.println("Necessity Item: Price with Tax");
		return Double.parseDouble(df.format(necessityItem.getPrice()));
	}

}

import java.text.DecimalFormat;

class TaxHolidayVisitor implements Visitor {
	
		DecimalFormat df = new DecimalFormat("#.##");
	public TaxHolidayVisitor() {
	}

	public double visit(Liquor liquorItem) {
		System.out.println("Liquor Item: Price with Tax");
		return Double.parseDouble(df.format((liquorItem.getPrice() * .10) + liquorItem.getPrice()));
	}
	
	public double visit(Tobacco tobaccoItem) {
		System.out.println("Tobacco Item: Price with Tax");
		return Double.parseDouble(df.format((tobaccoItem.getPrice() * .30) + tobaccoItem.getPrice()));
	}
	

	public double visit(Necessity necessityItem) {
		System.out.println("Necessity Item: Price with Tax");
		return Double.parseDouble(df.format(necessityItem.getPrice()));
	}

}

public class VisitorTest {
	public static void main(String[] args) {
		
		TaxVisitor taxCalc = new TaxVisitor();
		TaxHolidayVisitor taxHolidayCalc = new TaxHolidayVisitor();
		
		Necessity milk = new Necessity(3.47);
		Liquor vodka = new Liquor(11.99);
		Tobacco cigars = new Tobacco(19.99);
		
		System.out.println(milk.accept(taxCalc) + "\n");
		System.out.println(vodka.accept(taxCalc) + "\n");
		System.out.println(cigars.accept(taxCalc) + "\n");
		
		System.out.println("TAX HOLIDAY PRICES\n");

		System.out.println(milk.accept(taxHolidayCalc) + "\n");
		System.out.println(vodka.accept(taxHolidayCalc) + "\n");
		System.out.println(cigars.accept(taxHolidayCalc) + "\n");

	}
}

Result:
Necessity Item: Price with Tax
3.47

Liquor Item: Price with Tax
14.15

Tobacco Item: Price with Tax
26.39

TAX HOLIDAY PRICES

Necessity Item: Price with Tax
3.47

Liquor Item: Price with Tax
13.19

Tobacco Item: Price with Tax
25.99


