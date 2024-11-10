import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

abstract class Animal {
    String name;
    int age;
    final String species;

    public Animal(String name, int age, String species) {
        this.name = name;
        this.age = age;
        this.species = species;
    }

    abstract void makeSound();
    abstract void eat();

    final void displayInfo() {
        System.out.println("Name: " + name + ", Age: " + age + ", Species: " + species);
    }
}

class Dino extends Animal {
    Dino(String name, int age) {
        super(name, age, "Dino");
    }
    @Override
    void makeSound() {
        System.out.println("Dino named " + name + " roars");
    }
    @Override
    void eat() {
        System.out.println("Dino named " + name + " eats meat");
    }
}

class Elephant extends Animal {
    Elephant(String name, int age) {
        super(name, age, "Elephant");
    }
    @Override
    void makeSound() {
        System.out.println("Elephant named " + name + " trumpets");
    }
    @Override
    void eat() {
        System.out.println("Elephant named " + name + " eats plants");
    }
}

public class ZooManagementSystem {
    public static void main(String[] args) {
        createCSV();

        ArrayList<Animal> zoo = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader("animal.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                String species = data[0];
                String name = data[1];
                int age = Integer.parseInt(data[2]);

                Animal animal;
                if (species.equals("Dino")) {
                    animal = new Dino(name, age);
                } else if (species.equals("Elephant")) {
                    animal = new Elephant(name, age);
                } else {
                    continue; // Skip unknown species
                }
                zoo.add(animal);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        makeAllAnimalsSound(zoo);

        for (Animal animal : zoo) {
            animal.displayInfo();
        }
    }

    public static void makeAllAnimalsSound(ArrayList<Animal> zoo) {
        for (Animal animal : zoo) {
            animal.makeSound();
        }
    }

    public static void createCSV() {
        String csvFile = "animal.csv";
        try (FileWriter writer = new FileWriter(csvFile)) {
            writer.append("Dino,Agumon,27\n");
            writer.append("Dino,Yoshi,24\n");
            writer.append("Elephant,Horton,8\n");
            writer.append("Elephant,Manny,15\n");
            System.out.println("CSV file created successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}