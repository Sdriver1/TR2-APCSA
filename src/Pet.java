public abstract class Pet {

	private String myName;

	public Pet(String name) {
		myName = name;
	}

	public String getName() {
		return myName;
	}

	public abstract String speak();

}

class Cat extends Pet {
	public Cat(String name) {
		super(name);
	}

	public String speak() {
		return "meow";
	}
}

class Dog extends Pet {
	public Dog(String name) {
		super(name);
	}

	public String speak() {
		return "bark";
	}
}

class LoudDog extends Dog {
	public LoudDog(String name) {
		super(name);
	}

	public String speak() {
		return super.speak() + " " + super.speak();
	}
}

class BadDog extends LoudDog {
	public BadDog(String name) {
		super(name);
	}

	public String jump() {
		return "jumps";
	}
}
