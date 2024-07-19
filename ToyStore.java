import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ToyStore {
    private List<Toy> toys;

    public void updateToyWeight(int id, double weight) {
        for (Toy toy : toys) {
            if (toy.getId() == id) {
                toy.setWeight(weight);
                System.out.println("Weight updated for toy with id: " + id);
                return;
            }
        }
        System.out.println("Toy with id " + id + " not found!");
    }

    public static void main(String[] args) {
        ToyStore toyStore = new ToyStore();


        // Пример использования: добавление игрушек, обновление веса и сохранение в файл
        toyStore.addToy(new Toy(1, "Teddy Bear", 10, 0.5));
        toyStore.addToy(new Toy(2, "LEGO Set", 5, 2.0));

        toyStore.updateToyWeight(1, 0.7);

        toyStore.saveToFile("toys.txt");
    }

    public ToyStore() {
        this.toys = new ArrayList<>();
    }

    public void addToy(Toy toy) {
        toys.add(toy);
    }

    public void changeWeight(int toyId, double weight) {
        for (Toy toy : toys) {
            if (toy.getId() == toyId) {
                toy.setWeight(weight);
                break;
            }
        }
    }

    public Toy play() {

        double totalWeight = toys.stream().mapToDouble(Toy::getWeight).sum();
        double random = Math.random() * totalWeight;
        double current = 0;

        for (Toy toy : toys) {
            current += toy.getWeight();
            if (current >= random) {
                toy.setQuantity(toy.getQuantity() - 1);
                // Write prize toy to a text file
                writeToFile(toy);
                return toy;
            }
        }
        
        return null;
    }

    private void writeToFile(Toy toy) {
        try {
            FileWriter writer = new FileWriter("prizes.txt", true);
            writer.write("Prize Toy: " + toy.getName() + System.lineSeparator());
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void saveToFile(String fileName) {
        try (FileWriter writer = new FileWriter(fileName)) {
            for (Toy toy : toys) {
                String line = toy.getId() + "," + toy.getName() + "," + toy.getQuantity() + "," + toy.getWeight() + "\n";
                writer.write(line);
            }
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    // Другие методы, например, удаление игрушки, поиск игрушки и т.д.
}


