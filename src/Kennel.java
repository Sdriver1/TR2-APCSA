import java.util.ArrayList;

public class Kennel {
	private ArrayList<Pet> petList;

	public Kennel() {
		petList = new ArrayList<>();
	}

	public void addPet(Pet pet) {
		petList.add(pet);
		console.log("Added: " + pet.getName());
	}

	public boolean removePet(String name) {
		for (int i = 0; i < petList.size(); i++) {
			if (petList.get(i).getName().equalsIgnoreCase(name)) {
				petList.remove(i);
				console.log("Removed: " + name);
				return true;
			}
		}
		return false;
	}

	public int size() {
		return petList.size();
	}

	public void sortByPetType() {
		String temp = "";

		for (Pet p : petList) {

		}

	}

	public String searchByName(String name) {
		for (Pet p : petList) {
			if (p.getName().equalsIgnoreCase(name)) {
				return p.getName() + " is a " + p.getClass().getSimpleName() + " and says \"" + p.speak() + "\"";
			}
		}
		return "Pet not found.";
	}
}
