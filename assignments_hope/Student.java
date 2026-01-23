public class Student {

    private String name;
    private int id;
    private double[] grades;
    private int gradeCount;
    private double gpa;

    // ===== Constructor 1: Default =====
    public Student() {
        this.name = "Unknown";
        this.id = 0;
        this.grades = new double[10];
        this.gradeCount = 0;
        this.gpa = 0.0;
    }

    // ===== Constructor 2: Name & ID =====
    public Student(String name, int id) {
        this.name = name;
        this.id = id;
        this.grades = new double[10];
        this.gradeCount = 0;
        this.gpa = 0.0;
    }

    // ===== Constructor 3: Name, ID & Grades =====
    public Student(String name, int id, double[] initialGrades) {
        this.name = name;
        this.id = id;
        this.grades = new double[10];
        this.gradeCount = 0;

        for (double g : initialGrades) {
            addGrade(g);
        }
    }

    // ===== Add Grade Method =====
    public void addGrade(double grade) {
        if (gradeCount < grades.length) {
            grades[gradeCount] = grade;
            gradeCount++;
            calculateGPA();
        } else {
            System.out.println("Grade list is full!");
        }
    }

    // ===== Calculate GPA =====
    public void calculateGPA() {
        if (gradeCount == 0) {
            gpa = 0.0;
            return;
        }

        double sum = 0;
        for (int i = 0; i < gradeCount; i++) {
            sum += grades[i];
        }

        gpa = sum / gradeCount;
    }

    // ===== Display Student Info =====
    public void displayInfo() {
        System.out.println("Name: " + name);
        System.out.println("ID: " + id);

        System.out.print("Grades: ");
        if (gradeCount == 0) {
            System.out.print("None");
        } else {
            for (int i = 0; i < gradeCount; i++) {
                System.out.print(grades[i] + " ");
            }
        }

        System.out.println("\nGPA: " + gpa);
        System.out.println("--------------------------");
    }
}
