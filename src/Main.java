import java.io.*;
import java.util.*;
import java.text.*;

class Event {
    int id;
    String title;
    String date;
    String location;
    List<String> guests;

    public Event(int id, String title, String date, String location, List<String> guests) {
        this.id = id;
        this.title = title;
        this.date = date;
        this.location = location;
        this.guests = guests;
    }

    public String toString() {
        return "ID: " + id + "\nTitle: " + title + "\nDate: " + date + "\nLocation: " + location + "\nGuests: " + String.join(", ", guests);
    }
}

public class Main {
    static List<Event> eventList = new ArrayList<>();
    static final String FILE_NAME = "events.dat";

    public static void main(String[] args) {
        loadFromFile();

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n=== Event Planner ===");
            System.out.println("1. Create Event");
            System.out.println("2. View Events");
            System.out.println("3. Update Event");
            System.out.println("4. Delete Event");
            System.out.println("5. Import Events from CSV");
            System.out.println("6. Export Events to CSV");
            System.out.println("7. Generate Report");
            System.out.println("8. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine();  // consume the newline

            switch (choice) {
                case 1:
                    createEvent(scanner);
                    break;
                case 2:
                    viewEvents();
                    break;
                case 3:
                    updateEvent(scanner);
                    break;
                case 4:
                    deleteEvent(scanner);
                    break;
                case 5:
                    importFromCSV(scanner);
                    break;
                case 6:
                    exportToCSV();
                    break;
                case 7:
                    generateReport();
                    break;
                case 8:
                    saveToFile();
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    // Create Event
    public static void createEvent(Scanner scanner) {
        System.out.println("Enter event title:");
        String title = scanner.nextLine();
        System.out.println("Enter event date (yyyy-mm-dd):");
        String date = scanner.nextLine();
        System.out.println("Enter event location:");
        String location = scanner.nextLine();
        System.out.println("Enter guest names (comma separated):");
        String guestsInput = scanner.nextLine();
        List<String> guests = Arrays.asList(guestsInput.split(","));

        int id = eventList.size() + 1;
        Event newEvent = new Event(id, title, date, location, guests);
        eventList.add(newEvent);
        System.out.println("Event created successfully!");
    }

    // View Events
    public static void viewEvents() {
        if (eventList.isEmpty()) {
            System.out.println("No events available.");
        } else {
            for (Event event : eventList) {
                System.out.println("\n" + event);
                System.out.println("----------------------------");
            }
        }
    }

    // Update Event
    public static void updateEvent(Scanner scanner) {
        System.out.println("Enter event ID to update:");
        int id = scanner.nextInt();
        scanner.nextLine();  // consume newline
        Event event = getEventById(id);

        if (event != null) {
            System.out.println("Enter new event title (current: " + event.title + "):");
            event.title = scanner.nextLine();
            System.out.println("Enter new event date (current: " + event.date + "):");
            event.date = scanner.nextLine();
            System.out.println("Enter new event location (current: " + event.location + "):");
            event.location = scanner.nextLine();
            System.out.println("Enter new guest names (current: " + String.join(", ", event.guests) + "):");
            String guestsInput = scanner.nextLine();
            event.guests = Arrays.asList(guestsInput.split(","));
            System.out.println("Event updated successfully!");
        } else {
            System.out.println("Event not found.");
        }
    }

    // Delete Event
    public static void deleteEvent(Scanner scanner) {
        System.out.println("Enter event ID to delete:");
        int id = scanner.nextInt();
        Event event = getEventById(id);

        if (event != null) {
            eventList.remove(event);
            System.out.println("Event deleted successfully!");
        } else {
            System.out.println("Event not found.");
        }
    }

    // Import Events from CSV
    public static void importFromCSV(Scanner scanner) {
        System.out.println("Enter CSV file path to import:");
        String filePath = scanner.nextLine();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",");
                int id = Integer.parseInt(fields[0]);
                String title = fields[1];
                String date = fields[2];
                String location = fields[3];
                List<String> guests = Arrays.asList(fields[4].split(";"));
                eventList.add(new Event(id, title, date, location, guests));
            }
            System.out.println("Events imported successfully!");
        } catch (IOException e) {
            System.out.println("Error reading CSV file: " + e.getMessage());
        }
    }

    // Export Events to CSV
    public static void exportToCSV() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("events.csv"))) {
            for (Event event : eventList) {
                String guests = String.join(";", event.guests);
                bw.write(event.id + "," + event.title + "," + event.date + "," + event.location + "," + guests);
                bw.newLine();
            }
            System.out.println("Events exported to CSV successfully!");
        } catch (IOException e) {
            System.out.println("Error writing CSV file: " + e.getMessage());
        }
    }

    // Generate Report
    public static void generateReport() {
        Map<String, Integer> locationCount = new HashMap<>();
        int totalGuests = 0;

        for (Event event : eventList) {
            locationCount.put(event.location, locationCount.getOrDefault(event.location, 0) + 1);
            totalGuests += event.guests.size();
        }

        String mostPopularLocation = locationCount.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse("N/A");

        System.out.println("\n--- Report ---");
        System.out.println("Total Events: " + eventList.size());
        System.out.println("Total Guests: " + totalGuests);
        System.out.println("Most Popular Location: " + mostPopularLocation);
    }

    // Save Events to File
    public static void saveToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(eventList);
            System.out.println("Events saved to file.");
        } catch (IOException e) {
            System.out.println("Error saving to file: " + e.getMessage());
        }
    }

    // Load Events from File
    public static void loadFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            eventList = (List<Event>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading from file: " + e.getMessage());
        }
    }

    // Get Event by ID
    public static Event getEventById(int id) {
        for (Event event : eventList) {
            if (event.id == id) {
                return event;
            }
        }
        return null;
    }
}
