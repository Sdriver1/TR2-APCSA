import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import outputs.console;

public class Kennel {
	private ArrayList<Pet> petList;

	public Kennel() {
		petList = new ArrayList<>();
		loadDatabaseFile();
	}

	public void addPet(Pet pet) {
		petList.add(pet);
		console.log("Added: " + pet.getName());
		updateDatabaseFile();
	}

	public boolean removePet(String name, String type) {
		for (int i = 0; i < petList.size(); i++) {
			Pet pet = petList.get(i);
			if (pet.getName().equalsIgnoreCase(name) && pet.getClass().getSimpleName().equalsIgnoreCase(type)) {
				petList.remove(i);
				console.log("Removed: " + name + " (" + type + ")");
				updateDatabaseFile();
				return true;
			}
		}
		console.log("Couldn't remove " + name
				+ ". Type/Name didn't match database entry. Make sure to have the right information.");
		return false;
	}

	public void removeAllPets() {
		petList.clear();
		console.log("All pets removed from the kennel.");
		updateDatabaseFile();
	}

	public void sortByPetType() {
		petList.sort((p1, p2) -> {
			int order1 = getPetTypeOrder(p1);
			int order2 = getPetTypeOrder(p2);
			if (order1 == order2) {
				return p1.getName().compareToIgnoreCase(p2.getName());
			}
			return order1 - order2;
		});
		console.log("Sorted pets by type.");
		updateDatabaseFile();
	}

	// Helper method to assign an order value to each pet type
	private int getPetTypeOrder(Pet pet) {
		String type = pet.getClass().getSimpleName().toLowerCase();
		switch (type) {
		case "cat":
			return 0;
		case "dog":
			return 1;
		case "louddog":
			return 2;
		case "baddog":
			return 3;
		default:
			return 4;
		}
	}

	public ArrayList<Pet> getPets() {
		return petList;
	}

	public int size() {
		return petList.size();
	}

	public String searchByName(String name) {
		for (Pet p : petList) {
			if (p.getName().equalsIgnoreCase(name)) {
				return p.getName() + " is a " + p.getClass().getSimpleName() + " and says \"" + p.speak() + "\"";
			}
		}
		return "Pet not found.";
	}

	private void updateDatabaseFile() {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter("petsdb.txt", false))) {
			writer.write("Kennel:");
			writer.newLine();
			for (Pet pet : petList) {
				writer.write(pet.getName() + "," + pet.getClass().getSimpleName());
				writer.newLine();
			}
		} catch (IOException e) {
			console.log("Error updating database file: " + e.getMessage());
		}
	}

	private void loadDatabaseFile() {
		File file = new File("petsdb.txt");
		if (!file.exists()) {
			console.log("petsdb.txt file not found, skipping load.");
			return;
		}
		try (Scanner scanner = new Scanner(file)) {
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine().trim();
				if (line.isEmpty() || line.startsWith("Kennel:")) {
					continue;
				}
				String[] parts = line.split(",");
				if (parts.length >= 2) {
					String name = parts[0].trim();
					String type = parts[1].trim();
					Pet pet = null;
					if (type.equalsIgnoreCase("Cat")) {
						pet = new Cat(name);
					} else if (type.equalsIgnoreCase("Dog")) {
						pet = new Dog(name);
					} else if (type.equalsIgnoreCase("LoudDog")) {
						pet = new LoudDog(name);
					} else if (type.equalsIgnoreCase("BadDog")) {
						pet = new BadDog(name);
					}
					if (pet != null) {
						petList.add(pet);
					}
				}
			}
		} catch (Exception e) {
			console.log("Error loading database file: " + e.getMessage());
		}
	}
}
