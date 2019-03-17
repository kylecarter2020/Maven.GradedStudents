package io.zipcoder;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

public class ClassroomTest {
    @Test
    public void getAverageExamScore() {
        // : Given
        Double[] s1Scores = { 100.0, 150.0 };
        Double[] s2Scores = { 225.0, 25.0 };

        Student s1 = new Student("student", "one", s1Scores);
        Student s2 = new Student("student", "two", s2Scores);

        Student[] students = {s1,s2};
        Classroom classroom = new Classroom(students);

        // When
        double output = classroom.getAverageExamScore();

        // Then
        System.out.println(output);

    }

    @Test
    public void addStudent() {
        // : Given
        int maxNumberOfStudents = 1;
        Classroom classroom = new Classroom(maxNumberOfStudents);
        Double[] examScores = { 100.0, 150.0, 250.0, 0.0 };
        Student student = new Student("Leon", "Hunter", examScores);

        // When
        //Student[] preEnrollment = classroom.getStudents();
        String preEnrollmentAsString = classroom.toString();//Arrays.toString(preEnrollment);
        classroom.addStudent(student);
        //Student[] postEnrollment = classroom.getStudents();

        // Then
        String postEnrollmentAsString = classroom.toString(); //Arrays.toString(postEnrollment);

        System.out.println("===========================");
        System.out.println(preEnrollmentAsString);
        System.out.println("===========================");
        System.out.println(postEnrollmentAsString);
    }

    @Test
    public void removeStudent() {
        //Given
        Double[] examScores1 = { 100.0, 150.0, 250.0, 0.0 };
        Double[] examScores2 = { 10.0, 110.0, 25.0, 100.0 };
        Double[] examScores3 = { 90.0, 99.0, 109.0, 190.0 };
        Student s1 = new Student("Leon", "Hunter", examScores1);
        Student s2 = new Student("Leon", "Maxwell", examScores2);
        Student s3 = new Student("Ronald", "Weasley", examScores3);

        Student[] students = {s1, s2, s3, null};

        Classroom classroom = new Classroom(students);

        //When
        classroom.removeStudent("Leon", "Maxwell");

        //Then
        System.out.println(classroom.toString());
    }

    @Test
    public void testGetStudentByScore(){
        //Given
        Double[] examScores1 = { 100.0, 150.0, 200.0, 0.0 };
        Double[] examScores2 = { 10.0, 110.0, 25.0, 100.0 };
        Double[] examScores3 = { 90.0, 99.0, 109.0, 190.0 };
        Student s1 = new Student("Leon", "Hunter", examScores1);
        Student s2 = new Student("Jon", "Maxwell", examScores2);
        Student s3 = new Student("Ronald", "Weasley", examScores3);
        Student s4 = new Student("Leon", "Ender", examScores1);
        Student[] students = {s1, s2, s3, s4, s3};
        Student[] expected = {s3, s3, s4, s1, s2};

        Classroom classroom = new Classroom(students);

        //when
        Student[] sortedStudents = classroom.getStudentsByScore();

        //then
        Arrays.stream(sortedStudents).forEach(System.out::println);
        Assert.assertArrayEquals(expected, sortedStudents);
    }

    @Test
    public void testNulleryConstructor() {
        //given
        Student[] expected = new Student[30];

        //when
        Classroom classroom = new Classroom();

        //then
        Assert.assertArrayEquals(expected, classroom.getStudents());
    }

    @Test
    public void testGetGradeBook(){
        //given
        Double[] examScores1 = { 100.0, 150.0, 200.0, 0.0 };
        Double[] examScores2 = { 10.0, 110.0, 25.0, 100.0 };
        Double[] examScores3 = { 90.0, 99.0, 109.0, 190.0 };
        Student s1 = new Student("Leon", "Hunter", examScores1);
        Student s2 = new Student("Jon", "Maxwell", examScores2);
        Student s3 = new Student("Ronald", "Weasley", examScores3);

        Student[] students = {s1, s2, s3};

        Classroom classroom = new Classroom(students);

        Map<Student, Character> expected = new TreeMap<Student, Character>(){{
            put(s2, 'D');
            put(s1, 'C');
            put(s3, 'A');
        }};

        //when
        TreeMap<Student, Character> actual = classroom.getGradeBook();

        //then
        System.out.println(actual.toString());
        Assert.assertEquals(expected, actual);
    }
}
