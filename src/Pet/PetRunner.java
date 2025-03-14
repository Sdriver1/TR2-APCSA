package Pet;
import outputs.console;

public class PetRunner {
	public static void main(String[] args) {
		Kennel kennel = new Kennel();

		// Testing add method
		kennel.addPet(new Cat("Mittens"));
		kennel.addPet(new Cat("Whiskers"));
		kennel.addPet(new Dog("Charlie"));
		kennel.addPet(new Dog("Buddy"));
		kennel.addPet(new LoudDog("Baxter"));
		kennel.addPet(new LoudDog("Rex"));
		kennel.addPet(new BadDog("Rocky"));
		kennel.addPet(new BadDog("Bruno"));
		console.log(kennel.size());

		// Testing remove method
		kennel.removePet("Buddy", "Cat");
		kennel.removePet("Rex", "LoudDog");
		console.log(kennel.size());

		// Test searchByName method
		console.log("\nSearch Result:");
		console.log(kennel.searchByName("Charlie"));
		console.log(kennel.searchByName("Mittens"));
		console.log(kennel.searchByName("Rocky"));
		console.log(kennel.searchByName("Unknown"));
		
		kennel.removeAllPets();
		console.log(kennel.size());
	}
}
