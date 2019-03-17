package io.zipcoder;

import java.util.ArrayList;
import java.util.Arrays;

public class Student implements Comparable {
    private String firstName;
    private String lastName;
    private ArrayList<Double> examScores;

    public Student(String firstName, String lastName, Double[] examScores) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.examScores = new ArrayList<>();
        this.examScores.addAll(Arrays.asList(examScores));
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getExamScores() {
        StringBuilder results = new StringBuilder("Exam Scores:\n");

        for (Double score : this.examScores) {
            String examNumber = "Exam " + (examScores.indexOf(score) + 1);
            results.append(String.format("%10s -> %.0f\n", examNumber, score));
        }
        return results.toString();
    }

    public void addExamScore(Double score) {
        this.examScores.add(score);
    }

    public void setExamScore(Integer index, Double score) {
        this.examScores.remove(index - 1);
        this.examScores.add((index - 1), score);
    }

    public double getAverageExamScore() {
        Double sum = 0.0;
        for (Double score : examScores) {
            sum += score;
        }
        return sum / examScores.size();
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();

        result.append(String.format("Student Name: %s %s\n" +
                "> Average Score: %.0f\n" +
                "> %s", firstName, lastName, getAverageExamScore(), getExamScores()));

        return result.toString();
    }

    @Override
    public int compareTo(Object o) {
        Student s = (Student) o;
        if (this.firstName.equals(s.firstName) && this.lastName.equals(s.lastName)) {
            return 0;
        }
        if (this.firstName.equals(s.firstName)) {
            return this.lastName.compareTo(s.lastName);
        }
        return this.firstName.compareTo(s.firstName);
    }
}
