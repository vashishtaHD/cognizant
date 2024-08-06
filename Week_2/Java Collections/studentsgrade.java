import java.util.LinkedHashMap;

class Student {
    int id;
    String name;
    char grade;
}

class StudentGrades {
    LinkedHashMap<Integer, Student> students = new LinkedHashMap<>();

    void addStudent(Student student) {
        students.put(student.id, student);
    }

    void removeStudent(int studentId) {
        students.remove(studentId);
    }

    void updateStudentGrade(int studentId, char newGrade) {
        Student student = students.get(studentId);
        if (student != null) {
            student.grade = newGrade;
        }
    }

    void displayStudents() {
        students.values().forEach(student -> System.out.println(student.id + " " + student.name + " " + student.grade));
    }
}

class StudentGradesTest {
    public static void main(String[] args) {
        StudentGrades grades = new StudentGrades();
        Student student1 = new Student();
        student1.id = 1;
        student1.name = "Alice";
        student1.grade = 'A';
        grades.addStudent(student1);
        Student student2 = new Student();
        student2.id = 2;
        student2.name = "Bob";
        student2.grade = 'B';
        grades.addStudent(student2);
        grades.displayStudents();
        grades.removeStudent(1);
        grades.displayStudents();
        grades.addStudent(student1);
        grades.updateStudentGrade(1, 'C');
        grades.displayStudents();
    }
}
