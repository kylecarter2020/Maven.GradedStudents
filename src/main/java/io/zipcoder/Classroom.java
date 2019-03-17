package io.zipcoder;

import java.util.Arrays;
import java.util.TreeMap;

public class Classroom {
    private Student[] students;

    public Classroom(int maxNumberOfStudents) {
        this.students = new Student[maxNumberOfStudents];
    }

    public Classroom(Student[] students) {
        this.students = students;
    }

    public Classroom() {
        this.students = new Student[30];
    }

    public TreeMap<Student, Character> getGradeBook() {
        Student[] gradeStudents = this.getStudentsByScore();

        TreeMap<Student, Character> gradeBook = new TreeMap<>();

        Character[] letterGrades = this.getLetterGrades();

        for (int i = 0; i < gradeStudents.length; i++) {
            gradeBook.put(gradeStudents[i], letterGrades[(gradeStudents.length-1)-i]);
        }

        return gradeBook;
    }

    private Character[] getLetterGrades() {
        Character[] grades = new Character[students.length];

        for (int i = 0; i < grades.length; i++) {
            grades[i] = getGrade(i+1);
        }
        return grades;
    }

    private Character getGrade(Integer index) {
        Integer percentile = (100 / students.length) * index;

        if(percentile >= 90){
            return 'A';
        }
        if(percentile >= 100-29){
            return 'B';
        }
        if(percentile >= 50){
            return 'C';
        }
        if(percentile >= 100-89){
            return 'D';
        }
        return 'F';
    }

    public Student[] getStudents() {
        return this.students;
    }

    public Double getAverageExamScore() {
        Double sum = 0.0;

        for (Student s : students) {
            sum += s.getAverageExamScore();
        }

        return sum / students.length;
    }

    public void addStudent(Student student) {
        for (int i = 0; i < students.length; i++) {
            if (students[i] == null) {
                students[i] = student;
                break;
            }
        }
    }

    @Override
    public String toString() {

        if (isEmpty()) {
            return "[]";
        } else {
            return getString();
        }
    }

    private String getString() {
        StringBuilder result = new StringBuilder("[");
        for (Student s : students) {
            if (s != null) {
                result.append(s.toString());
            } else {
                result.append("=====\n");
            }
        }
        result.append("]");
        return result.toString();
    }

    private boolean isEmpty() {
        for (int i = 0; i < students.length; i++) {
            if (students[i] != null) {
                return false;
            }
        }
        return true;
    }

    public void removeStudent(String firstName, String lastName) {
        for (int i = 0; i < students.length; i++) {
            if (students[i].getFirstName().equals(firstName) && students[i].getLastName().equals(lastName)) {
                students[i] = null;
                sortArray();
                break;
            }
        }
    }

    private void sortArray() {
        //This Lambda tho!
        Arrays.sort(students, (o1, o2) -> {
            if (o1 == null && o2 == null) {
                return 0;
            }
            if (o1 == null) {
                return 1;
            }
            if (o2 == null) {
                return -1;
            }
            return o1.compareTo(o2);
        });
    }

    public Student[] getStudentsByScore() {
        this.sortArrayByScores();
        return this.students;
    }

    private void sortArrayByScores() {
        Arrays.sort(students, (o1, o2) -> {
            if (o1.getAverageExamScore() == o2.getAverageExamScore())
                return o1.compareTo(o2);
            if (o1.getAverageExamScore() > o2.getAverageExamScore())
                return -1;
            return 1;
        });
    }

}
