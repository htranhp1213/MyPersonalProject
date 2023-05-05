/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package task.manager;

/**
 *
 * @author huongtran
 */


import login_or_signup.User;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;
import java.util.*;
import java.text.*;
import java.util.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Timer;
import java.util.TimerTask;


public class TaskManagerDriver {
    private static ArrayList<User> users = new ArrayList<>();
    private static ArrayList<AcademicTask> academicTasks;
    private static ArrayList<nonAcademicTask> nonAcademicTasks;
    private static ArrayList<Task> allTasks;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws ParseException {
        // Login or Signup
        User user = null;
        while (user == null) {
            System.out.print("Do you want to login or signup? (login/signup): ");
            String loginOrSignup = scanner.nextLine();
            if (loginOrSignup.equalsIgnoreCase("login")) {
                user = login(users, scanner);
            } else if (loginOrSignup.equalsIgnoreCase("signup")) {
                user = signup(users, scanner);
            } else {
                System.out.println("Invalid input, please try again.");
            }
        }
        while (true) {
            showMenu();
            int choice = scanner.nextInt();
            scanner.nextLine(); // prepare to switch from scanning int to string
            switch (choice) {
                case 1:
                    createTask(allTasks, academicTasks, nonAcademicTasks, scanner);
                    break;
                case 2:
                    updateTask(allTasks, scanner);
                    break;
                case 3:
                    trackTasks(allTasks, scanner);
                    break;
                case 4:
                    categorizeTasks(academicTasks, nonAcademicTasks, allTasks, scanner);
                    break;
                case 5:
                    setReminders(allTasks, scanner);
                    break;
                case 6: 
                    prioritizeTask(allTasks,scanner);
                case 7:
                    System.out.println("Exiting Task Manager...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice, please try again.");
                    break;
            }
        }
    }

    private static void showMenu() {
        System.out.println("\n===============================");
        System.out.println("Welcome to Task Manager!");
        System.out.println("1. Create Task");
        System.out.println("2. Update Task");
        System.out.println("3. View Tasks");
        System.out.println("4. Categorize tasks");
        System.out.println("5. Set reminders");
        System.out.println("6. Prioritize Tasks");
        System.out.println("7. Logout");
        System.out.println("===============================");
        System.out.print("Please enter your choice: ");
    }

    private static void createTask(ArrayList<Task> allTasks, ArrayList<AcademicTask> academicTasks, ArrayList<nonAcademicTask> nonAcademicTasks, Scanner scanner) throws ParseException {
        Task task = null;
        LocalDate deadline;
        //SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy");
        System.out.println("\nCreating a new task...");
        
        // ask for name of task
        System.out.print("Enter the name of the task: ");
        String name = scanner.nextLine();
        
        // description
        System.out.print("Enter the description of the task: ");
        String description = scanner.nextLine();
        
        // deadline
        System.out.print("Enter the deadline of the task (mm/dd/yyyy): ");
        String deadlineStr = scanner.nextLine();
        // casting to the date format
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        deadline = LocalDate.parse(deadlineStr, formatter);
        
        
        // categorize
        System.out.print("Do you want to categorize for this task? (y/n): ");
        boolean wantCategorize = scanner.nextLine().equalsIgnoreCase("y"); // convert string to boolean
        if (wantCategorize) {
            System.out.print("Is this an academic task? (y/n): ");
            boolean isAcademic = scanner.nextLine().equalsIgnoreCase("y"); // convert string to boolean
            if (isAcademic){
                System.out.print("Enter course name: ");
                String courseName = scanner.nextLine();
                task = new AcademicTask(name, description, deadline, courseName);
                academicTasks.add((AcademicTask) task); // casting task to academic task and add to academic task array
            }
            else {
                System.out.print("Enter task type: ");
                String type = scanner.nextLine();
                task = new nonAcademicTask(name, description, deadline, type);
                nonAcademicTasks.add((nonAcademicTask) task); // casting task to nonacademic task and add to academic task array
            }
        }
        
        
        
        // priority
        System.out.print("Do you want to set a priority for this task? (y/n): ");
        boolean setPriority = scanner.nextLine().equalsIgnoreCase("y");
        Priority priority = null;
        if (setPriority) {
            System.out.println("Select a priority level: ");
            System.out.println("1. High");
            System.out.println("2. Medium");
            System.out.println("3. Low");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // switch from int to string
            switch (choice) {
                case 1:
                    priority = Priority.HIGH;
                    break;
                case 2:
                    priority = Priority.MEDIUM;
                    break;
                case 3:
                    priority = Priority.LOW;
                    break;
                default:
                    System.out.println("Invalid choice, priority not set.");
                    break;
            }
        }
        
        if (priority != null) {
            task.updatePriority(priority);
        }
        
        // Automatically set reminder 3 days before deadline
        LocalDate reminderDate = deadline.minusDays(3);
        // Convert the deadline to LocalDate https://www.baeldung.com/java-date-to-localdate-and-localdatetime
        Date reminder = Date.from(reminderDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        task.setReminder(reminder);
        
        
        allTasks.add(task);
        System.out.println("Task created successfully!");
    } // end of createTask()

    private static void updateTask(ArrayList<Task> allTasks, Scanner scanner) {
        scanner.nextLine();
        System.out.println("\nEnter a task name to update:");
        String name = scanner.nextLine();
        Task task = null;
        
        for (Task t: allTasks) {
            if (t.getName().equalsIgnoreCase(name)) {
                task = t;
                break;
            }
        }
        
        if (task == null) {
            System.out.println("Task not found!");
            return;
        }
        System.out.print("Update task name (or none): ");
        String newName = scanner.nextLine();
        if (!newName.equalsIgnoreCase("none")) {
            task.updateName(newName);
        }

        System.out.print("Update task description (or none): ");
        String newDescription = scanner.nextLine();
        if (!newDescription.equalsIgnoreCase("none")) {
            task.updateDescription(newDescription);
        }

        System.out.print("Update task deadline (MM-DD-YYYY, or none): ");
        String newDeadline = scanner.nextLine();
        if (!newDeadline.equalsIgnoreCase("none")) {
            task.updateDeadline(LocalDate.parse(newDeadline));
        }

        System.out.print("Update task progress (or none): ");
        String newProgress = scanner.nextLine();
        if (!newProgress.equalsIgnoreCase("none")) {
            task.updateProgress(Integer.parseInt(newProgress));
        }
        
        // Automatically set reminder 3 days before deadline
        LocalDate deadline = task.getDeadline();
        LocalDate reminderDate = deadline.minusDays(3);
        // Convert the deadline to LocalDate https://www.baeldung.com/java-date-to-localdate-and-localdatetime
        Date reminder = Date.from(reminderDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        task.setReminder(reminder);
    } // end of updateTask()
        
    
    // Track tasks: view all tasks sorted by deadline, or view by category sorted by deadlines
    public static void trackTasks(ArrayList<Task> allTasks, Scanner scanner) {
        while (true) {
            System.out.println("\n========== Track Tasks ==========");
            System.out.println("1. View tasks by deadlines");
            System.out.println("2. View tasks by categories");
            System.out.println("3. Back");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("\n========== Tasks by Deadlines ==========");
                    ArrayList<Task> sortedByDeadline = new ArrayList<>(allTasks);
                    sortedByDeadline.sort(Comparator.comparing(Task::getDeadline)); // sorted by deadline
                    for (Task task : sortedByDeadline) {
                        System.out.println(task.getName() + " (Deadline: " + task.getDeadline() + ")");
                        System.out.println("Description: " + task.getDescription());
                        System.out.println("Progress: " + task.getProgress() + "%");
                        System.out.println("Status: " + (task.isCompleted() ? "Completed" : "Incomplete"));
                        if (task.getReminder() != null) {
                            System.out.println("Reminder: " + task.getReminder());
                        }
                        System.out.println();
                    }
                    break;
                case 2:
                    System.out.println("\n========== Tasks by Categories ==========");
                    System.out.println("1. Academic tasks");
                    System.out.println("2. Non-academic tasks");
                    System.out.print("Enter your choice: ");
                    String categoryChoice = scanner.nextLine();
                    switch (categoryChoice) {
                        case "1":
                            System.out.println("\n========== Academic Tasks ==========");
                            ArrayList<AcademicTask> academicTasks = new ArrayList<>();
                            for (Task task : allTasks) {
                                if (task instanceof AcademicTask) {
                                    academicTasks.add((AcademicTask) task);
                                }
                            }

                            // Comparator.comparing static function accepts a sort key Function and returns a Comparator for the type that contains the sort key:https://www.baeldung.com/java-8-comparator-comparing
                            academicTasks.sort(Comparator.comparing(Task::getDeadline)); 
                            for (Task task : academicTasks) {
                                System.out.println(task.getName() + " (Deadline: " + task.getDeadline() + ")");
                                System.out.println("Description: " + task.getDescription());
                                System.out.println("Progress: " + task.getProgress() + "%");
                                System.out.println("Status: " + (task.isCompleted() ? "Completed" : "Incomplete"));
                                if (task.getReminder() != null) {
                                    System.out.println("Reminder: " + task.getReminder());
                                }
                                System.out.println();
                            }
                            break;
                        case "2":
                            System.out.println("\n========== Non-academic Tasks ==========");
                            ArrayList<nonAcademicTask> nonAcademicTasks = new ArrayList<>();
                            for (Task task : allTasks) {
                                if (task instanceof nonAcademicTask) {
                                    nonAcademicTasks.add((nonAcademicTask) task);
                                }
                            }
                            nonAcademicTasks.sort(Comparator.comparing(Task::getDeadline));
                            for (Task task : nonAcademicTasks) {
                                System.out.println(task.getName() + " (Deadline: " + task.getDeadline() + ")");
                                System.out.println("Description: " + task.getDescription());
                                System.out.println("Progress: " + task.getProgress() + "%");
                                System.out.println("Status: " + (task.isCompleted() ? "Completed" : "Incomplete"));
                                if (task.getReminder() != null) {
                                    System.out.println("Reminder: " + task.getReminder());
                                }
                                System.out.println();
                            }
                            break;
                        default:
                            System.out.println("Invalid choice.");
                            break;
                    } // end of categoryChoice
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Invalid choice.");
                    break;
            }// end of switch
        } // end of while(true)
    } // end of trackTask()


    public static void categorizeTasks(ArrayList<AcademicTask> academicTasks, ArrayList<nonAcademicTask> nonAcademicTasks, ArrayList<Task> allTasks, Scanner scanner) {
        while (true) {
            System.out.println("\n========== Categorize Tasks ==========");

            for (Task task : allTasks) {
            if (!(task instanceof AcademicTask) && !(task instanceof nonAcademicTask)) {
                System.out.println(task.getName() + ": " + task.getDescription());
                System.out.println("1. Set academic task");
                System.out.println("2. Set non-academic task");
                System.out.println("3. Back");
                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();
               
                switch (choice) {
                    case 1:
                        AcademicTask academicTask = new AcademicTask(task.getName(), task.getDescription(), task.getDeadline());
                        academicTasks.add(academicTask);
                        break;
                    case 2:
                        nonAcademicTask nonAcademicTask = new nonAcademicTask(task.getName(), task.getDescription(), task.getDeadline());
                        nonAcademicTasks.add(nonAcademicTask);
                        break;
                    default:
                        System.out.println("Invalid choice. Task was not categorized.");
                        break;
                    }
                }
            }
            System.out.println("Task categorization complete.");
        } // end of while
    } // end of categorizeTask()
    
    
    public static void setReminders(ArrayList<Task> allTasks, Scanner scanner) {
        System.out.println("\n========== Set Reminder ==========");
        System.out.print("Enter the name of the task to set a reminder for: ");
        String taskName = scanner.nextLine();

        Task task = null;
        for (Task t : allTasks) {
            if (t.getName().equalsIgnoreCase(taskName)) {
                task = t;
                break;
            }
        }

        if (task == null) {
            System.out.println("Task not found.");
            return;
        }

        System.out.print("Enter the date and time for the reminder (MM-DD-YYYY HH:MM): ");
        String reminderString = scanner.nextLine();
        LocalDateTime reminder = null;

        try {
            reminder = LocalDateTime.parse(reminderString, DateTimeFormatter.ofPattern("MM-DD-YYYY HH:mm"));
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format. Please enter a date and time in the format MM-DD-YYYY HH:MM.");
            return;
        }

        task.setReminder(reminder);
        System.out.println("Reminder set for task \"" + task.getName() + "\" on " + reminder);
    }// end of setReminder
    
    public static void prioritizeTask(ArrayList<Task> allTasks, Scanner scanner) {
        System.out.println("\n========== Prioritize Task ==========");
        System.out.print("Enter the name of the task you want to prioritize: ");
        String taskName = scanner.nextLine();

        // find the task with the given name
        Task taskToPrioritize = null;
        for (Task task : allTasks) {
            if (task.getName().equals(taskName)) {
                taskToPrioritize = task;
                break;
            }
        }

        if (taskToPrioritize == null) {
            System.out.println("Task not found.");
            return;
        }

        System.out.println("Select a priority level: ");
        System.out.println("1. High");
        System.out.println("2. Medium");
        System.out.println("3. Low");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // switch from int to string
        Priority priority = null;
        switch (choice) {
            case 1:
                priority = Priority.HIGH;
                break;
            case 2:
                priority = Priority.MEDIUM;
                break;
            case 3:
                priority = Priority.LOW;
                break;
            default:
                System.out.println("Invalid choice, priority not set.");
                return;
        }

        // update the priority level of the task
        taskToPrioritize.updatePriority(priority);

        System.out.println("Task priority updated successfully.");
    }

    public static User login(ArrayList<User> users, Scanner scanner) {
    System.out.print("Enter your username: ");
    String username = scanner.nextLine();

    System.out.print("Enter your password: ");
    String password = scanner.nextLine();

    for (User user : users) {
        if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
            System.out.println("Login successful. Welcome, " + user.getName() + "!");
            return user;
        }
    }

    System.out.println("Invalid username or password. Please try again.");
    return null;
}

    public static User signup(ArrayList<User> users, Scanner scanner) {
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();

        System.out.print("Enter your username: ");
        String username = scanner.nextLine();

        System.out.print("Enter your password: ");
        String password = scanner.nextLine();

        User newUser = new User(username, password);
        users.add(newUser);

        System.out.println("Signup successful. Welcome, " + newUser.getName() + "!");
        return newUser;
    }


        
                       

                

}
