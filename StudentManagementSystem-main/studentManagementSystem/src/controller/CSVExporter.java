package controller;

import entity.StudentGrade;
import javafx.collections.ObservableList;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class CSVExporter {

    public static void exportToCSV(ObservableList<StudentGrade> grades, File file) {
        if (grades == null || grades.isEmpty()) {
            System.err.println("No data available to export.");
            return;
        }

        try (PrintWriter writer = new PrintWriter(new FileWriter(file))) {
            // Write the CSV file header
            writer.println("Grade ID,Student Name,Subject,Score,Status");

            // Write data
            for (StudentGrade grade : grades) {
                writer.printf("%d,%s,%s,%.2f,%s%n",
                        grade.getGradeId(),
                        grade.getStudentName(),
                        grade.getSubject(),
                        grade.getScore(),
                        grade.getStatus());
            }

            System.out.println("Export successful: " + file.getAbsolutePath());
            

        } catch (IOException e) {
            System.err.println("Error exporting to CSV: " + e.getMessage());
        }
    }
}