import java.util.ArrayList;
import java.util.Scanner;

class Course {
    private String courseCode;
    private String title;
    private String description;
    private int capacity;
    private String schedule;
    private int availableSlots;

    public Course(String courseCode, String title, String description, int capacity, String schedule) {
        this.courseCode = courseCode;
        this.title = title;
        this.description = description;
        this.capacity = capacity;
        this.schedule = schedule;
        this.availableSlots = capacity;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getCapacity() {
        return capacity;
    }

    public String getSchedule() {
        return schedule;
    }

    public int getAvailableSlots() {
        return availableSlots;
    }

    public void registerStudent() {
        if (availableSlots > 0) {
            availableSlots--;
        } else {
            System.out.println("Sorry, the course is full. Cannot register.");
        }
    }

    public void removeStudent() {
        if (availableSlots < capacity) {
            availableSlots++;
        } else {
            System.out.println("No students registered in this course.");
        }
    }

    @Override
    public String toString() {
        return "Course Code: " + courseCode +
               "\nTitle: " + title +
               "\nDescription: " + description +
               "\nCapacity: " + capacity +
               "\nSchedule: " + schedule +
               "\nAvailable Slots: " + availableSlots;
    }
}

class Student {
    private int studentID;
    private String name;
    private ArrayList<Course> registeredCourses;

    public Student(int studentID, String name) {
        this.studentID = studentID;
        this.name = name;
        this.registeredCourses = new ArrayList<>();
    }

    public int getStudentID() {
        return studentID;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Course> getRegisteredCourses() {
        return registeredCourses;
    }

    public void registerCourse(Course course) {
        registeredCourses.add(course);
        course.registerStudent();
        System.out.println("Registration successful for course: " + course.getCourseCode());
    }

    public void removeCourse(Course course) {
        registeredCourses.remove(course);
        course.removeStudent();
        System.out.println("Course removed: " + course.getCourseCode());
    }

    public void displayRegisteredCourses() {
        if (registeredCourses.isEmpty()) {
            System.out.println("No courses registered.");
        } else {
            System.out.println("Registered Courses:");
            for (Course course : registeredCourses) {
                System.out.println(course.getCourseCode() + " - " + course.getTitle());
            }
        }
    }
}

class CourseRegistrationSystem {
    private ArrayList<Course> courseDatabase;
    private ArrayList<Student> studentDatabase;
    private Scanner scanner;

    public CourseRegistrationSystem() {
        this.courseDatabase = new ArrayList<>();
        this.studentDatabase = new ArrayList<>();
        this.scanner = new Scanner(System.in);
    }

    public void addCourse(String courseCode, String title, String description, int capacity, String schedule) {
        Course course = new Course(courseCode, title, description, capacity, schedule);
        courseDatabase.add(course);
        System.out.println("Course added: " + courseCode);
    }

    public void displayCourseListing() {
        if (courseDatabase.isEmpty()) {
            System.out.println("No courses available.");
        } else {
            System.out.println("Available Courses:");
            for (Course course : courseDatabase) {
                System.out.println(course.getCourseCode() + " - " + course.getTitle() +
                                   " (Available Slots: " + course.getAvailableSlots() + ")");
            }
        }
    }

    public void addStudent(int studentID, String name) {
        Student student = new Student(studentID, name);
        studentDatabase.add(student);
        System.out.println("Student added: " + studentID);
    }

    public void displayStudentList() {
        if (studentDatabase.isEmpty()) {
            System.out.println("No students registered.");
        } else {
            System.out.println("Student List:");
            for (Student student : studentDatabase) {
                System.out.println(student.getStudentID() + " - " + student.getName());
            }
        }
    }

    public void run() {
        while (true) {
            System.out.println("\nCourse Registration System Menu:");
            System.out.println("1. Add Course");
            System.out.println("2. Display Course Listing");
            System.out.println("3. Add Student");
            System.out.println("4. Display Student List");
            System.out.println("5. Register Course for Student");
            System.out.println("6. Remove Course for Student");
            System.out.println("7. Display Registered Courses for Student");
            System.out.println("8. Exit");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    System.out.print("Enter Course Code: ");
                    String courseCode = scanner.nextLine();
                    System.out.print("Enter Title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter Description: ");
                    String description = scanner.nextLine();
                    System.out.print("Enter Capacity: ");
                    int capacity = scanner.nextInt();
                    scanner.nextLine(); 
                    System.out.print("Enter Schedule: ");
                    String schedule = scanner.nextLine();
                    addCourse(courseCode, title, description, capacity, schedule);
                    break;
                case 2:
                    displayCourseListing();
                    break;
                case 3:
                    System.out.print("Enter Student ID: ");
                    int studentID = scanner.nextInt();
                    scanner.nextLine(); 
                    System.out.print("Enter Student Name: ");
                    String studentName = scanner.nextLine();
                    addStudent(studentID, studentName);
                    break;
                case 4:
                    displayStudentList();
                    break;
                case 5:
                    registerCourseForStudent();
                    break;
                case 6:
                    removeCourseForStudent();
                    break;
                case 7:
                    displayRegisteredCoursesForStudent();
                    break;
                case 8:
                    System.out.println("Exiting the Course Registration System. Goodbye!");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 8.");
            }
        }
    }

    private void registerCourseForStudent() {
        displayStudentList();
        System.out.print("Enter Student ID to register a course for: ");
        int studentID = scanner.nextInt();
        scanner.nextLine();

        Student student = findStudent(studentID);
        if (student != null) {
            displayCourseListing();
            System.out.print("Enter Course Code to register: ");
            String courseCode = scanner.nextLine();

            Course course = findCourse(courseCode);
            if (course != null) {
                student.registerCourse(course);
            } else {
                System.out.println("Course with code '" + courseCode + "' not found.");
            }
        } else {
            System.out.println("Student with ID '" + studentID + "' not found.");
        }
    }

    private void removeCourseForStudent() {
        displayStudentList();
        System.out.print("Enter Student ID to remove a course for: ");
        int studentID = scanner.nextInt();
        scanner.nextLine(); 

        Student student = findStudent(studentID);
        if (student != null) {
            student.displayRegisteredCourses();

            System.out.print("Enter Course Code to remove: ");
            String courseCode = scanner.nextLine();

            Course course = findCourse(courseCode);
            if (course != null) {
                student.removeCourse(course);
            } else {
                System.out.println("Course with code '" + courseCode + "' not found.");
            }
        } else {
            System.out.println("Student with ID '" + studentID + "' not found.");
        }
    }

    private void displayRegisteredCoursesForStudent() {
        displayStudentList();
        System.out.print("Enter Student ID to display registered courses: ");
        int studentID = scanner.nextInt();
        scanner.nextLine(); 

        Student student = findStudent(studentID);
        if (student != null) {
            student.displayRegisteredCourses();
        } else {
            System.out.println("Student with ID '" + studentID + "' not found.");
        }
    }

    private Student findStudent(int studentID) {
        for (Student student : studentDatabase) {
            if (student.getStudentID() == studentID) {
                return student;
            }
        }
        return null;
    }

    private Course findCourse(String courseCode) {
        for (Course course : courseDatabase) {
            if (course.getCourseCode().equalsIgnoreCase(courseCode)) {
                return course;
            }
        }
        return null;
    }
}

public class StudentCourseRegistrationSystem {

    public static void main(String[] args) {
        CourseRegistrationSystem system = new CourseRegistrationSystem();
        system.run();
    }
}