# Project Title: Event Planner
## Student Name: [Atshaal Fathoni Al Aufa]
### Link [https://docs.google.com/presentation/d/1EZHzVwfZba4fyoapy0VRxxIrOMfbMbsg/edit?usp=sharing&ouid=109273617365523007904&rtpof=true&sd=true]


### Description:
 Event Planner is a command-line based application that allows users to plan and manage events. The application supports basic CRUD operations such as creating, viewing, updating, and deleting events. Users can invite guests, schedule dates, and coordinate event details. Additionally, it includes features like report generation and import/export via CSV for better usability and data management.

### Objectives:
1. To implement CRUD functionalities in Java using CLI.

2. To apply file handling for data persistence.

3. To create a modular and user-friendly design.

4. To demonstrate the ability to manage event-related data effectively.

5. To validate inputs to avoid incorrect or incomplete data.

6. To generate useful reports based on user activity.

7. To implement CSV import and export for easier data sharing.

8. To gain hands-on experience with Java Collections and File I/O.

9. To ensure error handling is robust and user-friendly.

10. To build a project that is both practical and extendable in the future.

### Project Requirements List
1. CRUD Operations: Supports Create, Read, Update, and Delete for events.

2. Command Line Interface: Interactive menu for user-friendly CLI-based operations.

3. Input Validation: Prevents empty fields and ensures correct formats for date and guest inputs.

4. Data Persistence: Saves and loads event data using file handling (serialization and CSV).

5. Modular Design: Uses separate classes (Event, EventManager) for clean and reusable code.

6. Report Generation: Provides summary reports like total events and invited guests.

7. Documentation: Code is documented with explanations in README and structured naming conventions.

8. Test Cases & Outputs: Includes example inputs/outputs for all major features (see below).

9. Error Handling: Detects and handles invalid choices, file errors, and format issues.

10. Import/Export CSV: Allows event data to be exported/imported from .csv files.

### Documentation

This section provides an overview of the design, logic, and implementation details of the *Event Planner* project.

---

#### Project Structure

The project is composed of two main Java classes:

1. **`Event` class**  
   Represents a single event with the following attributes:
    - `id` (int): Unique identifier.
    - `title` (String): Name of the event.
    - `date` (String): Scheduled date of the event.
    - `location` (String): Location of the event.
    - `guests` (ArrayList<String>): List of invited guest names.

2. **`Main` class**  
   Handles the logic for managing events:
    - Stores events using an `ArrayList<Event>`.
    - Provides methods to create, read, update, delete, save, load, and generate reports.

---

#### Logic and Algorithms

- **ID Auto-Incrementation**  
  Each event gets a unique auto-incremented ID to simplify tracking and updates.

- **Input Validation**  
  Methods check for:
    - Non-empty title, location, and date.
    - Valid guest entries (non-blank).
    - Proper format in CSV import.

- **File Handling**
    - **Save/Load**: Uses `ObjectOutputStream` and `ObjectInputStream` for binary data serialization.
    - **CSV Import/Export**: Uses `FileReader`, `FileWriter`, and `BufferedReader` for plain-text CSV format.

- **Report Generation**  
  Scans the list of events and aggregates:
    - Total number of events.
    - Total number of invited guests.
    - Most used locations (via a `Map<String, Integer>` counter).

---

#### Data Structures Used

- **ArrayList<Event>**  
  Used to store and iterate through events efficiently.

- **ArrayList<String>**  
  Used inside `Event` to hold multiple guest names.

- **Map<String, Integer>**  
  Used for counting locations or generating summary reports.

---

#### Functions/Modules

| Method Name               | Purpose                                 |
|---------------------------|-----------------------------------------|
| `createEvent()`           | Creates and adds a new event.           |
| `viewEvents()`            | Displays all stored events.             |
| `updateEvent()`           | Edits a specific event by ID.           |
| `deleteEvent()`           | Deletes an event by ID.                 |
| `saveToFile()`            | Saves events to a file (binary).        |
| `loadFromFile()`          | Loads events from a file (binary).      |
| `exportToCSV()`           | Saves events to a `.csv` file.          |
| `importFromCSV()`         | Loads events from a `.csv` file.        |
| `generateReport()`        | Creates and displays event summaries.   |

---

#### Challenges Faced

- Ensuring data persistence across sessions while keeping the code beginner-friendly.
- Handling different formats (binary vs CSV) without external libraries.
- Maintaining modularity to keep the logic clean and testable.

