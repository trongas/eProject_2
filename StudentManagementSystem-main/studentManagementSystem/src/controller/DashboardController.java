package controller;

import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import entity.CourseData;
import database.Database;
import entity.GetData;
import entity.SroData;
import entity.StudentData;
import entity.SubjectData;
import java.io.IOException;
import javafx.beans.Observable;
import javafx.util.StringConverter;

public class DashboardController implements Initializable {

    @FXML
    private AnchorPane main_form;

    @FXML
    private Button close;

    @FXML
    private Button minimize;

    @FXML
    private Label username;

    @FXML
    private Button home_btn;

    @FXML
    private Button addStudents_btn;

    @FXML
    private Button availableCourse_btn;

    @FXML
    private Button studentGrade_btn;

    @FXML
    private Button logout;

    @FXML
    private AnchorPane home_form;

    @FXML
    private Label home_totalEnrolled;

    @FXML
    private Label home_totalFemale;

    @FXML
    private Label home_totalMale;

    @FXML
    private BarChart<?, ?> home_totalEnrolledChart;

    @FXML
    private AreaChart<?, ?> home_totalFemaleChart;

    @FXML
    private LineChart<?, ?> home_totalMaleChart;

    @FXML
    private AnchorPane addStudents_form;

    @FXML
    private TextField addStudents_search;

    @FXML
    private TableView<StudentData> addStudents_tableView;

    @FXML
    private TableColumn<StudentData, String> addStudents_col_studentNum;

    @FXML
    private TableColumn<StudentData, String> addStudents_col_year;

    @FXML
    private TableColumn<StudentData, String> addStudents_col_course;

    @FXML
    private TableColumn<StudentData, String> addStudents_col_firstName;

    @FXML
    private TableColumn<StudentData, String> addStudents_col_lastName;

    @FXML
    private TableColumn<StudentData, String> addStudents_col_gender;

    @FXML
    private TableColumn<StudentData, String> addStudents_col_birth;

    @FXML
    private TableColumn<StudentData, String> addStudents_col_status;

    @FXML
    private TextField addStudents_studentNum;

    @FXML
    private ComboBox<?> addStudents_year;

    @FXML
    private ComboBox<?> addStudents_course;

    @FXML
    private TextField addStudents_firstName;

    @FXML
    private TextField addStudents_lastName;

    @FXML
    private DatePicker addStudents_birth;

    @FXML
    private ComboBox<?> addStudents_status;

    @FXML
    private ComboBox<?> addStudents_gender;

    @FXML
    private ImageView addStudents_imageView;

    @FXML
    private Button addStudents_insertBtn;

    @FXML
    private Button addStudents_addBtn;

    @FXML
    private Button addStudents_updateBtn;

    @FXML
    private Button addStudents_deleteBtn;

    @FXML
    private Button addStudents_clearBtn;

    @FXML
    private AnchorPane availableCourse_form;

    @FXML
    private TextField availableCourse_course;

    @FXML
    private TextField availableCourse_description;

    @FXML
    private TextField availableCourse_degree;

    @FXML
    private Button availableCourse_addBtn;

    @FXML
    private Button availableCourse_updateBtn;

    @FXML
    private Button availableCourse_clearBtn;

    @FXML
    private Button availableCourse_deleteBtn;

    @FXML
    private TableView<CourseData> availableCourse_tableView;

    @FXML
    private TableColumn<CourseData, String> availableCourse_col_course;

    @FXML
    private TableColumn<CourseData, String> availableCourse_col_description;

    @FXML
    private TableColumn<CourseData, String> availableCourse_col_degree;

    @FXML
    private AnchorPane studentGrade_form;

    @FXML
    private TextField studentGrade_studentNum;

    @FXML
    private Label studentGrade_year;

    @FXML
    private Label studentGrade_course;

    @FXML
    private TextField studentGrade_firstSem;

    @FXML
    private TextField studentGrade_secondSem;

    @FXML
    private Button studentGrade_updateBtn;

    @FXML
    private Button studentGrade_clearBtn;

    @FXML
    private TableView<StudentData> studentGrade_tableView;

    @FXML
    private TableColumn<StudentData, String> studentGrade_col_studentNum;

    @FXML
    private TableColumn<StudentData, String> studentGrade_col_year;

    @FXML
    private TableColumn<StudentData, String> studentGrade_col_course;

    @FXML
    private TableColumn<StudentData, String> studentGrade_col_firstSem;

    @FXML
    private TableColumn<StudentData, String> studentGrade_col_secondSem;

    @FXML
    private TableColumn<StudentData, String> studentGrade_col_final;

    @FXML
    private TextField studentGrade_search;

    @FXML
    private Button teacher_btn;

    @FXML
    private AnchorPane teacher_form;

    @FXML
    private Button Class_btn;

    @FXML
    private AnchorPane Class_form;

    @FXML
    private Button Sro_btn;

    @FXML
    private AnchorPane Sro_form;

    @FXML
    private Button Subject_btn;

    @FXML
    private AnchorPane Subject_form;

    @FXML
    private TextField txtSubjectId;
    @FXML
    private TextField txtSubjectName;
    @FXML
    private ComboBox<CourseData> comboCourse;

    @FXML
    private TableView<SubjectData> tableSubjects;
    @FXML
    private TableColumn<SubjectData, Integer> colSubjectId;
    @FXML
    private TableColumn<SubjectData, String> colSubjectName;
    @FXML
    private TableColumn<SubjectData, Integer> colCourseName;
    @FXML
    private Button Subject_addBtn;

    @FXML
    private Button Subject_clearBtn;

    @FXML
    private Button Subject_deleteBtn;

    @FXML
    private Button Subject_updateBtn;

    @FXML
    private Button Sro_AddBtn;

    @FXML
    private Button Sro_ClearBtn;

    @FXML
    private Button Sro_DeleteBtn;

    @FXML
    private TextField Sro_Search;

    @FXML
    private Button Sro_UpdateBtn;
    
    @FXML
    private TextField txtSroAge;

    @FXML
    private TextField txtSroEmail;

    @FXML
    private TextField txtSroName;

    @FXML
    private TextField txtSroPhone;
    @FXML
    private ComboBox<?> listSroGender;
    @FXML
    private TableView<SroData> Sro_tableView;
      @FXML
    private TableColumn<SroData, Integer> colSroID;
    @FXML
    private TableColumn<SroData, Integer> colSroAge;
    @FXML
    private TableColumn<SroData, String> colSroEmail;
    @FXML
    private TableColumn<SroData, String> colSroName;
    @FXML
    private TableColumn<SroData, String> colSroGender;
    @FXML
    private TableColumn<SroData, Integer> colSroPhone;

    private Connection connect;
    private PreparedStatement prepare;
    private Statement statement;
    private ResultSet result;

    private Image image;

    public void homeDisplayTotalEnrolledStudents() throws SQLException {

        String sql = "SELECT COUNT(student_id) FROM student";

        connect = Database.connectDb();

        int countEnrolled = 0;

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            if (result.next()) {
                countEnrolled = result.getInt("COUNT(student_id)");
            }

            home_totalEnrolled.setText(String.valueOf(countEnrolled));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void homeDisplayFemaleEnrolled() throws SQLException {

        String sql = "SELECT COUNT(student_id) FROM student WHERE gender = 'Female' and status = 'Enrolled'";

        connect = Database.connectDb();

        try {
            int countFemale = 0;

            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            if (result.next()) {
                countFemale = result.getInt("COUNT(student_id)");
            }

            home_totalFemale.setText(String.valueOf(countFemale));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void homeDisplayMaleEnrolled() throws SQLException {

        String sql = "SELECT COUNT(student_id) FROM student WHERE gender = 'Male' and status = 'Enrolled'";

        connect = Database.connectDb();

        try {
            int countMale = 0;

            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            if (result.next()) {
                countMale = result.getInt("COUNT(student_id)");
            }
            home_totalMale.setText(String.valueOf(countMale));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void homeDisplayTotalEnrolledChart() throws SQLException {

        home_totalEnrolledChart.getData().clear();

        String sql = "SELECT create_date, COUNT(student_id) FROM student WHERE status = 'Enrolled' GROUP BY create_date ORDER BY TIMESTAMP(create_date) ASC LIMIT 5";

        connect = Database.connectDb();

        try {
            XYChart.Series chart = new XYChart.Series();

            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            while (result.next()) {
                chart.getData().add(new XYChart.Data(result.getString(1), result.getInt(2)));
            }

            home_totalEnrolledChart.getData().add(chart);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void homeDisplayFemaleEnrolledChart() throws SQLException {

        home_totalFemaleChart.getData().clear();

        String sql = "SELECT create_date, COUNT(student_id) FROM student WHERE status = 'Enrolled' and gender = 'Female' GROUP BY create_date ORDER BY TIMESTAMP(create_date) ASC LIMIT 5";

        connect = Database.connectDb();

        try {
            XYChart.Series chart = new XYChart.Series();

            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            while (result.next()) {
                chart.getData().add(new XYChart.Data(result.getString(1), result.getInt(2)));
            }

            home_totalFemaleChart.getData().add(chart);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void homeDisplayEnrolledMaleChart() throws SQLException {

        home_totalMaleChart.getData().clear();

        String sql = "SELECT create_date, COUNT(student_id) FROM student WHERE status = 'Enrolled' and gender = 'Male' GROUP BY create_date ORDER BY TIMESTAMP(create_date) ASC LIMIT 5";

        connect = Database.connectDb();

        try {
            XYChart.Series chart = new XYChart.Series();

            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            while (result.next()) {
                chart.getData().add(new XYChart.Data(result.getString(1), result.getInt(2)));
            }

            home_totalMaleChart.getData().add(chart);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    public void addStudentsAdd() throws SQLException {
        String insertStudentData = "INSERT INTO student "
                + "(studentNum, year, course_id, firstName, lastName, gender, birth, status, image, create_date) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

//        String insertStudentGrade = "INSERT INTO student_grades (student_id, subject_id, score, status) "
//                + "VALUES (?, ?, ?, ?)";
        connect = Database.connectDb();

        try {
            if (addStudents_studentNum.getText().isEmpty()
                    || addStudents_year.getSelectionModel().getSelectedItem() == null
                    || addStudents_course.getSelectionModel().getSelectedItem() == null
                    || addStudents_firstName.getText().isEmpty()
                    || addStudents_lastName.getText().isEmpty()
                    || addStudents_gender.getSelectionModel().getSelectedItem() == null
                    || addStudents_birth.getValue() == null
                    || addStudents_status.getSelectionModel().getSelectedItem() == null
                    || GetData.path == null || GetData.path == "") {
                showAlert(AlertType.ERROR, "Error Message", "Please fill all blank fields");
                return;
            }

            // Check if student number already exists
            if (isStudentNumberExists(addStudents_studentNum.getText())) {
                showAlert(AlertType.ERROR, "Error Message", "Student #" + addStudents_studentNum.getText() + " already exists!");
                return;
            }

            // Get course ID
            int courseId = getCourseId((String) addStudents_course.getSelectionModel().getSelectedItem());
            if (courseId == -1) {
                showAlert(AlertType.ERROR, "Error Message", "Selected course does not exist!");
                return;
            }

            // Prepare student data insertion
            prepare = connect.prepareStatement(insertStudentData, Statement.RETURN_GENERATED_KEYS);
            prepare.setString(1, addStudents_studentNum.getText());
            prepare.setString(2, addStudents_year.getValue().toString());
            prepare.setInt(3, courseId);
            prepare.setString(4, addStudents_firstName.getText());
            prepare.setString(5, addStudents_lastName.getText());
            prepare.setString(6, addStudents_gender.getValue().toString());
            prepare.setString(7, addStudents_birth.getValue().toString());
            prepare.setString(8, addStudents_status.getValue().toString());
            prepare.setString(9, GetData.path);
            prepare.setDate(10, new java.sql.Date(new Date().getTime()));
            prepare.executeUpdate();

            // Get auto-generated student ID
            ResultSet generatedKeys = prepare.getGeneratedKeys();
            if (generatedKeys.next()) {
                generatedKeys.getInt(1);
            } else {
                showAlert(AlertType.ERROR, "Error Message", "Failed to retrieve student ID!");
                return;
            }

            // Prepare student grade insertion
//            PreparedStatement insertGradeStatement = connect.prepareStatement(insertStudentGrade);
//            insertGradeStatement.setInt(1, studentId);
//            insertGradeStatement.setInt(2, subjectId); // Assuming you have a variable for subjectId
//            insertGradeStatement.setDouble(3, 0.0); // Default score value
//            insertGradeStatement.setString(4, ""); // Default status value
//            insertGradeStatement.executeUpdate();
//            insertGradeStatement.close();
            showAlert(AlertType.INFORMATION, "Information Message", "Successfully Added!");

            // Update the table view
            addStudentsShowListData();
            // Clear the fields
            addStudentsClear();
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(AlertType.ERROR, "Error Message", "An error occurred while adding student!");
        } finally {
            closeResources();
        }
    }

    private boolean isStudentNumberExists(String studentNum) throws SQLException {
        String checkData = "SELECT studentNum FROM student WHERE studentNum = ?";
        prepare = connect.prepareStatement(checkData);
        prepare.setString(1, studentNum);
        ResultSet resultSet = prepare.executeQuery();
        return resultSet.next();
    }

    private int getCourseId(String courseName) throws SQLException {
        String query = "SELECT id FROM course WHERE course_name = ?";
        prepare = connect.prepareStatement(query);
        prepare.setString(1, courseName);
        ResultSet resultSet = prepare.executeQuery();
        return resultSet.next() ? resultSet.getInt("id") : -1;
    }

    private void showAlert(AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void closeResources() {
        try {
            if (prepare != null) {
                prepare.close();
            }
            if (connect != null) {
                connect.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void addStudentsUpdate() throws SQLException {
        String uri = GetData.path;
        uri = uri.replace("\\", "\\\\");

        // Assuming that addStudents_course stores the course name, we need to get the course_id.
        String selectedCourseName = (String) addStudents_course.getSelectionModel().getSelectedItem();
        int courseId = getCourseIdByName(selectedCourseName);

        String updateData = "UPDATE student SET "
                + "year = ?, course_id = ?, firstName = ?, lastName = ?, gender = ?, birth = ?, status = ?, image = ? "
                + "WHERE studentNum = ?";

        connect = Database.connectDb();

        try {
            Alert alert;
            if (addStudents_studentNum.getText().isEmpty()
                    || addStudents_year.getSelectionModel().getSelectedItem() == null
                    || addStudents_course.getSelectionModel().getSelectedItem() == null
                    || addStudents_firstName.getText().isEmpty()
                    || addStudents_lastName.getText().isEmpty()
                    || addStudents_gender.getSelectionModel().getSelectedItem() == null
                    || addStudents_birth.getValue() == null
                    || addStudents_status.getSelectionModel().getSelectedItem() == null
                    || GetData.path == null || "".equals(GetData.path)) {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
            } else {
                alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to UPDATE Student #" + addStudents_studentNum.getText() + "?");
                Optional<ButtonType> option = alert.showAndWait();

                if (option.isPresent() && option.get() == ButtonType.OK) {
                    PreparedStatement statement = connect.prepareStatement(updateData);
                    statement.setString(1, (String) addStudents_year.getSelectionModel().getSelectedItem());
                    statement.setInt(2, courseId);
                    statement.setString(3, addStudents_firstName.getText());
                    statement.setString(4, addStudents_lastName.getText());
                    statement.setString(5, (String) addStudents_gender.getSelectionModel().getSelectedItem());
                    statement.setDate(6, java.sql.Date.valueOf(addStudents_birth.getValue()));
                    statement.setString(7, (String) addStudents_status.getSelectionModel().getSelectedItem());
                    statement.setString(8, uri);
                    statement.setString(9, addStudents_studentNum.getText());

                    statement.executeUpdate();

                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Updated!");
                    alert.showAndWait();

                    // TO UPDATE THE TABLEVIEW
                    addStudentsShowListData();
                    // TO CLEAR THE FIELDS
                    addStudentsClear();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private int getCourseIdByName(String courseName) throws SQLException {
        String query = "SELECT id FROM course WHERE course_name = ?";
        connect = Database.connectDb();
        PreparedStatement statement = connect.prepareStatement(query);
        statement.setString(1, courseName);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            return resultSet.getInt("id");
        } else {
            throw new SQLException("Course not found: " + courseName);
        }
    }

    @FXML
    public void addStudentsDelete() throws SQLException {
        String studentNum = addStudents_studentNum.getText();

        // Check if the student number is provided
        if (studentNum.isEmpty()) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Please provide the student number.");
            alert.showAndWait();
            return;
        }

        // Confirmation dialog
        Alert confirmationAlert = new Alert(AlertType.CONFIRMATION);
        confirmationAlert.setTitle("Confirmation Message");
        confirmationAlert.setHeaderText(null);
        confirmationAlert.setContentText("Are you sure you want to DELETE Student #" + studentNum + "?");

        Optional<ButtonType> option = confirmationAlert.showAndWait();

        // Check user's choice
        if (option.isPresent() && option.get() == ButtonType.OK) {
            try {
                connect = Database.connectDb();

                // Start a transaction
                connect.setAutoCommit(false);

                // Delete corresponding records in student_grades table first
                String deleteGrade = "DELETE FROM student_grades WHERE student_id = (SELECT student_id FROM student WHERE studentNum = ?)";
                PreparedStatement deleteGradeStatement = connect.prepareStatement(deleteGrade);
                deleteGradeStatement.setString(1, studentNum);
                deleteGradeStatement.executeUpdate();

                // Delete corresponding records in class_student table
                String deleteClassStudent = "DELETE FROM class_student WHERE student_id = (SELECT student_id FROM student WHERE studentNum = ?)";
                PreparedStatement deleteClassStudentStatement = connect.prepareStatement(deleteClassStudent);
                deleteClassStudentStatement.setString(1, studentNum);
                deleteClassStudentStatement.executeUpdate();

                // Then delete the student record
                String deleteData = "DELETE FROM student WHERE studentNum = ?";
                PreparedStatement deleteStatement = connect.prepareStatement(deleteData);
                deleteStatement.setString(1, studentNum);
                deleteStatement.executeUpdate();

                // Commit the transaction
                connect.commit();

                // Success message
                Alert successAlert = new Alert(AlertType.INFORMATION);
                successAlert.setTitle("Information Message");
                successAlert.setHeaderText(null);
                successAlert.setContentText("Successfully Deleted!");
                successAlert.showAndWait();

                // Update the table view
                addStudentsShowListData();

                // Clear the fields
                addStudentsClear();

            } catch (SQLException e) {
                // Rollback the transaction in case of error
                if (connect != null) {
                    try {
                        connect.rollback();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
                e.printStackTrace();
            } finally {
                if (connect != null) {
                    try {
                        connect.setAutoCommit(true);
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        }
    }

    @FXML
    public void addStudentsClear() {
        addStudents_studentNum.setText("");
        addStudents_year.getSelectionModel().clearSelection();
        addStudents_course.getSelectionModel().clearSelection();
        addStudents_firstName.setText("");
        addStudents_lastName.setText("");
        addStudents_gender.getSelectionModel().clearSelection();
        addStudents_birth.setValue(null);
        addStudents_status.getSelectionModel().clearSelection();
        addStudents_imageView.setImage(null);

        GetData.path = "";
    }

    @FXML
    public void addStudentsInsertImage() {

        FileChooser open = new FileChooser();
        open.setTitle("Open Image File");
        open.getExtensionFilters().add(new ExtensionFilter("Image File", "*jpg", "*png"));

        File file = open.showOpenDialog(main_form.getScene().getWindow());

        if (file != null) {

            image = new Image(file.toURI().toString(), 120, 149, false, true);
            addStudents_imageView.setImage(image);

            GetData.path = file.getAbsolutePath();

        }
    } //WHILE WE INSERT THE DATA ON STUDENT, WE SHOULD INSERT ALSO THE DATA TO STUDENT_GRADE

    @FXML
    public void addStudentsSearch() {

        FilteredList<StudentData> filter = new FilteredList<>(addStudentsListD, e -> true);

        addStudents_search.textProperty().addListener((Observable, oldValue, newValue) -> {

            filter.setPredicate(predicateStudentData -> {

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String searchKey = newValue.toLowerCase();

                if (predicateStudentData.getStudentNum().toString().contains(searchKey)) {
                    return true;
                } else if (predicateStudentData.getYear().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (predicateStudentData.getCourseName().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (predicateStudentData.getFirstName().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (predicateStudentData.getLastName().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (predicateStudentData.getGender().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (predicateStudentData.getBirth().toString().contains(searchKey)) {
                    return true;
                } else if (predicateStudentData.getStatus().toLowerCase().contains(searchKey)) {
                    return true;
                } else {
                    return false;
                }
            });
        });

        SortedList<StudentData> sortList = new SortedList<>(filter);

        sortList.comparatorProperty().bind(addStudents_tableView.comparatorProperty());
        addStudents_tableView.setItems(sortList);

    }

    private String[] yearList = {"First Year", "Second Year", "Third Year", "Fourth Year"};

    @FXML
    public void addStudentsYearList() {

        List<String> yearL = new ArrayList<>();

        for (String data : yearList) {
            yearL.add(data);
        }

        ObservableList ObList = FXCollections.observableArrayList(yearL);
        addStudents_year.setItems(ObList);

    }

    @FXML
    public void addStudentsCourseList() throws SQLException {

        String listCourse = "SELECT * FROM course";

        connect = Database.connectDb();

        try {

            ObservableList listC = FXCollections.observableArrayList();

            prepare = connect.prepareStatement(listCourse);
            result = prepare.executeQuery();

            while (result.next()) {
                listC.add(result.getString("course_name"));
            }
            addStudents_course.setItems(listC);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private String[] genderList = {"Male", "Female", "Others"};

    @FXML
    public void addStudentsGenderList() {
        List<String> genderL = new ArrayList<>();

        for (String data : genderList) {
            genderL.add(data);
        }

        ObservableList ObList = FXCollections.observableArrayList(genderL);
        addStudents_gender.setItems(ObList);
    }

    private String[] statusList = {"Enrolled", "Not Enrolled", "Inactive"};

    @FXML
    public void addStudentsStatusList() {
        List<String> statusL = new ArrayList<>();

        for (String data : statusList) {
            statusL.add(data);
        }

        ObservableList ObList = FXCollections.observableArrayList(statusL);
        addStudents_status.setItems(ObList);
    }

//    NOW WE NEED THE COURSE, SO LETS WORK NOW THE AVAILABLE COURSE FORM : ) 
//    LETS WORK FIRST THE ADD STUDENTS FORM : ) 
    public ObservableList<StudentData> addStudentsListData() {
        ObservableList<StudentData> listStudents = FXCollections.observableArrayList();

        try (Connection connection = Database.connectDb(); PreparedStatement preparedStatement = connection.prepareStatement("SELECT s.studentNum, s.year, c.course_name, s.firstName, s.lastName, s.gender, s.birth, s.status, s.image FROM student s JOIN course c ON s.course_id = c.id"); ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                StudentData studentD = new StudentData(
                        resultSet.getInt("studentNum"),
                        resultSet.getString("year"),
                        resultSet.getString("course_name"),
                        resultSet.getString("firstName"),
                        resultSet.getString("lastName"),
                        resultSet.getString("gender"),
                        resultSet.getDate("birth"),
                        resultSet.getString("status"),
                        resultSet.getString("image")
                );

                listStudents.add(studentD);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listStudents;
    }

    private ObservableList<StudentData> addStudentsListD;

    public void addStudentsShowListData() {
        ObservableList<StudentData> addStudentsListD = addStudentsListData();

        addStudents_col_studentNum.setCellValueFactory(new PropertyValueFactory<>("studentNum"));
        addStudents_col_year.setCellValueFactory(new PropertyValueFactory<>("year"));
        addStudents_col_course.setCellValueFactory(new PropertyValueFactory<>("courseName"));
        addStudents_col_firstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        addStudents_col_lastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        addStudents_col_gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        addStudents_col_birth.setCellValueFactory(new PropertyValueFactory<>("birth"));
        addStudents_col_status.setCellValueFactory(new PropertyValueFactory<>("status"));

        addStudents_tableView.setItems(addStudentsListD);

    }

    @FXML
    public void addStudentsSelect() {

        StudentData studentD = addStudents_tableView.getSelectionModel().getSelectedItem();
        int num = addStudents_tableView.getSelectionModel().getSelectedIndex();

        if ((num - 1) < -1) {
            return;
        }

        addStudents_studentNum.setText(String.valueOf(studentD.getStudentNum()));
        addStudents_firstName.setText(studentD.getFirstName());
        addStudents_lastName.setText(studentD.getLastName());
        addStudents_birth.setValue(LocalDate.parse(String.valueOf(studentD.getBirth())));

        String uri = "file:" + studentD.getImage();

        image = new Image(uri, 120, 149, false, true);
        addStudents_imageView.setImage(image);

        GetData.path = studentD.getImage();

    }

    @FXML
    public void availableCourseAdd() throws SQLException {

        String insertData = "INSERT INTO course (course_name,description,degree) VALUES(?,?,?)";

        connect = Database.connectDb();

        try {
            Alert alert;

            if (availableCourse_course.getText().isEmpty()
                    || availableCourse_description.getText().isEmpty()
                    || availableCourse_degree.getText().isEmpty()) {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
            } else {
//            CHECK IF THE COURSE YOU WANT TO INSERT IS ALREADY EXIST!
                String checkData = "SELECT course_name FROM course WHERE course_name  = '"
                        + availableCourse_course.getText() + "'";

                statement = connect.createStatement();
                result = statement.executeQuery(checkData);

                if (result.next()) {
                    alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Course: " + availableCourse_course.getText() + " was already exist!");
                    alert.showAndWait();
                } else {
                    prepare = connect.prepareStatement(insertData);
                    prepare.setString(1, availableCourse_course.getText());
                    prepare.setString(2, availableCourse_description.getText());
                    prepare.setString(3, availableCourse_degree.getText());

                    prepare.executeUpdate();

                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Added!");
                    alert.showAndWait();

                    // TO BECOME UPDATED OUR TABLEVIEW ONCE THE DATA WE GAVE SUCCESSFUL
                    availableCourseShowListData();
                    // TO CLEAR THE TEXT FIELDS
                    availableCourseClear();

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close resources
            if (result != null) {
                result.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connect != null) {
                connect.close();
            }
        }
    }

    @FXML
    public void availableCourseUpdate() throws SQLException {
        connect = Database.connectDb();

        try {
            Alert alert;

            if (availableCourse_course.getText().isEmpty()
                    || availableCourse_description.getText().isEmpty()
                    || availableCourse_degree.getText().isEmpty()) {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
            } else {
                alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to UPDATE Course: " + availableCourse_course.getText() + "?");
                Optional<ButtonType> option = alert.showAndWait();

                if (option.isPresent() && option.get() == ButtonType.OK) {
                    String updateData = "UPDATE course SET description = ?, degree = ? WHERE course_name = ?";
                    prepare = connect.prepareStatement(updateData);
                    prepare.setString(1, availableCourse_description.getText());
                    prepare.setString(2, availableCourse_degree.getText());
                    prepare.setString(3, availableCourse_course.getText());

                    prepare.executeUpdate();

                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Updated!");
                    alert.showAndWait();

                    // Refresh the table view after successful update
                    availableCourseShowListData();
                    // Clear the text fields
                    availableCourseClear();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close resources
            if (prepare != null) {
                prepare.close();
            }
            if (connect != null) {
                connect.close();
            }
        }
    }

    @FXML
    public void availableCourseDelete() throws SQLException {
        String deleteData = "DELETE FROM course WHERE course_name = ?";

        connect = Database.connectDb();

        try {
            Alert alert;

            if (availableCourse_course.getText().isEmpty()
                    || availableCourse_description.getText().isEmpty()
                    || availableCourse_degree.getText().isEmpty()) {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
            } else {
                alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to DELETE Course: " + availableCourse_course.getText() + "?");
                Optional<ButtonType> option = alert.showAndWait();

                if (option.isPresent() && option.get() == ButtonType.OK) {
                    prepare = connect.prepareStatement(deleteData);
                    prepare.setString(1, availableCourse_course.getText());
                    prepare.executeUpdate();

                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Deleted!");
                    alert.showAndWait();

                    // Refresh the table view after successful deletion
                    availableCourseShowListData();
                    // Clear the text fields
                    availableCourseClear();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close resources
            if (prepare != null) {
                prepare.close();
            }
            if (connect != null) {
                connect.close();
            }
        }
    }

    @FXML
    public void availableCourseClear() {
        availableCourse_course.setText("");
        availableCourse_description.setText("");
        availableCourse_degree.setText("");
    }

    public ObservableList<CourseData> availableCourseListData() throws SQLException {
        ObservableList<CourseData> listData = FXCollections.observableArrayList();

        String sql = "SELECT * FROM course";

        connect = Database.connectDb();

        try {
            CourseData courseD;
            prepare = connect.prepareStatement(sql);
            ResultSet result = prepare.executeQuery();

            while (result.next()) {
                courseD = new CourseData(
                        result.getString("course_name"),
                        result.getString("description"),
                        result.getString("degree")
                );

                listData.add(courseD);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (prepare != null) {
                prepare.close();
            }
            if (connect != null) {
                connect.close();
            }
        }
        return listData;
    }

    private ObservableList<CourseData> availableCourseList;

    public void availableCourseShowListData() throws SQLException {
        availableCourseList = availableCourseListData();

        availableCourse_col_course.setCellValueFactory(new PropertyValueFactory<>("course"));
        availableCourse_col_description.setCellValueFactory(new PropertyValueFactory<>("description"));
        availableCourse_col_degree.setCellValueFactory(new PropertyValueFactory<>("degree"));

        availableCourse_tableView.setItems(availableCourseList);

    }

    @FXML
    public void availableCourseSelect() {
        CourseData courseD = availableCourse_tableView.getSelectionModel().getSelectedItem();
        if (courseD != null) {
            availableCourse_course.setText(courseD.getCourse());
            availableCourse_description.setText(courseD.getDescription());
            availableCourse_degree.setText(courseD.getDegree());

        }
    }

    @FXML
    public void studentGradesUpdate() throws SQLException {
        double finalCheck1 = 0;
        double finalCheck2 = 0;

        String checkData = "SELECT * FROM student_grade WHERE studentNum = '"
                + studentGrade_studentNum.getText() + "'";

        connect = Database.connectDb();

        double finalResult = 0;

        try {

            prepare = connect.prepareStatement(checkData);
            result = prepare.executeQuery();

            if (result.next()) {
                finalCheck1 = result.getDouble("first_sem");
                finalCheck2 = result.getDouble("second_sem");
            }

            if (finalCheck1 == 0 || finalCheck2 == 0) {
                finalResult = 0;
            } else { //LIKE (X+Y)/2 AVE WE NEED TO FIND FOR FINALS
                finalResult = (Double.parseDouble(studentGrade_firstSem.getText())
                        + Double.parseDouble(studentGrade_secondSem.getText()) / 2);
            }

            String updateData = "UPDATE student_grade SET "
                    + " year = '" + studentGrade_year.getText()
                    + "', course = '" + studentGrade_course.getText()
                    + "', first_sem = '" + studentGrade_firstSem.getText()
                    + "', second_sem = '" + studentGrade_secondSem.getText()
                    + "', final = '" + finalResult + "' WHERE studentNum = '"
                    + studentGrade_studentNum.getText() + "'";

            Alert alert;

            if (studentGrade_studentNum.getText().isEmpty()
                    || studentGrade_year.getText().isEmpty()
                    || studentGrade_course.getText().isEmpty()) {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();

            } else {

                alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to UPDATE Student #" + studentGrade_studentNum.getText() + "?");
                Optional<ButtonType> option = alert.showAndWait();

                if (option.get().equals(ButtonType.OK)) {
                    statement = connect.createStatement();
                    statement.executeUpdate(updateData);

                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Updated!");
                    alert.showAndWait();

                    // TO UPDATE THE TABLEVIEW
                    studentGradesShowListData();
                } else {
                    return;
                }

            }// NOT WE ARE CLOSER TO THE ENDING PART  :) LETS PROCEED TO DASHBOARD FORM 
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void studentGradesClear() {
        studentGrade_studentNum.setText("");
        studentGrade_year.setText("");
        studentGrade_course.setText("");
        studentGrade_firstSem.setText("");
        studentGrade_secondSem.setText("");
    }

    public ObservableList<StudentData> studentGradesListData() throws SQLException {

        ObservableList<StudentData> listData = FXCollections.observableArrayList();

        String sql = "SELECT * FROM student_grade";

        connect = Database.connectDb();

        try {
            StudentData studentD;

            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            while (result.next()) {
                studentD = new StudentData(result.getInt("studentNum"),
                        result.getString("year"),
                        result.getString("course"),
                        result.getDouble("first_sem"),
                        result.getDouble("second_sem"),
                        result.getDouble("final"));

                listData.add(studentD);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listData;
    }

    private ObservableList<StudentData> studentGradesList;

    public void studentGradesShowListData() throws SQLException {
        studentGradesList = studentGradesListData();

        studentGrade_col_studentNum.setCellValueFactory(new PropertyValueFactory<>("studentNum"));
        studentGrade_col_year.setCellValueFactory(new PropertyValueFactory<>("year"));
        studentGrade_col_course.setCellValueFactory(new PropertyValueFactory<>("course"));
        studentGrade_col_firstSem.setCellValueFactory(new PropertyValueFactory<>("firstSem"));
        studentGrade_col_secondSem.setCellValueFactory(new PropertyValueFactory<>("secondSem"));
        studentGrade_col_final.setCellValueFactory(new PropertyValueFactory<>("finals"));
//        WE NEED TO FIX THE DELETE ON ADD STUDENT FORM 
        studentGrade_tableView.setItems(studentGradesList);

    }

    @FXML
    public void studentGradesSelect() {

        StudentData studentD = studentGrade_tableView.getSelectionModel().getSelectedItem();
        int num = studentGrade_tableView.getSelectionModel().getSelectedIndex();

        if ((num - 1) < -1) {
            return;
        }

        studentGrade_studentNum.setText(String.valueOf(studentD.getStudentNum()));
        studentGrade_year.setText(studentD.getYear());
        studentGrade_course.setText(studentD.getCourseName());
        studentGrade_firstSem.setText(String.valueOf(studentD.getFirstSem()));
        studentGrade_secondSem.setText(String.valueOf(studentD.getSecondSem()));
    }

    @FXML
    public void studentGradesSearch() {

        FilteredList<StudentData> filter = new FilteredList<>(studentGradesList, e -> true);

        studentGrade_search.textProperty().addListener((Observable, oldValue, newValue) -> {

            filter.setPredicate(predicateStudentData -> {

                if (newValue.isEmpty() || newValue == null) {
                    return true;
                }
                String searchKey = newValue.toLowerCase();

                if (predicateStudentData.getStudentNum().toString().contains(searchKey)) {
                    return true;
                } else if (predicateStudentData.getYear().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (predicateStudentData.getCourseName().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (predicateStudentData.getFirstSem().toString().contains(searchKey)) {
                    return true;
                } else if (predicateStudentData.getSecondSem().toString().contains(searchKey)) {
                    return true;
                } else if (predicateStudentData.getFinals().toString().contains(searchKey)) {
                    return true;
                } else {
                    return false;
                }
            });
        });

        SortedList<StudentData> sortList = new SortedList<>(filter);

        sortList.comparatorProperty().bind(studentGrade_tableView.comparatorProperty());
        studentGrade_tableView.setItems(sortList);

    }
//SUBJECT
    private ObservableList<SubjectData> subjectListData;
    private ObservableList<CourseData> courseListData;

    private void loadCourseData() {
        courseListData = FXCollections.observableArrayList();
        String query = "SELECT id, course_name FROM course";
        try {
            connect = Database.connectDb();
            prepare = connect.prepareStatement(query);
            result = prepare.executeQuery();

            while (result.next()) {
                CourseData course = new CourseData(result.getInt("id"),
                        result.getString("course_name"));
                courseListData.add(course);
            }
            comboCourse.setItems(courseListData);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeDatabaseResources();
        }
    }

    private void setupCourseComboBox() {
        comboCourse.setConverter(new StringConverter<CourseData>() {
            @Override
            public String toString(CourseData courseData) {
                return courseData == null ? null : courseData.getCourse();
            }

            @Override
            public CourseData fromString(String s) {
                return null; // Not needed for ComboBox display
            }
        });
    }

    @FXML
    public void addSubject() {
        String subjectName = txtSubjectName.getText();
        CourseData selectedCourse = comboCourse.getValue();

        if (subjectName.isEmpty() || selectedCourse == null) {
            showAlert(Alert.AlertType.ERROR, "Error", "Please fill all fields.");
            return;
        }

        String insertSubjectQuery = "INSERT INTO subject (subject_name) VALUES (?)";
        String linkCourseSubjectQuery = "INSERT INTO course_subject (course_id, subject_id) VALUES (?, ?)";

        try {
            connect = Database.connectDb();
            // Insert the subject into the subject table
            prepare = connect.prepareStatement(insertSubjectQuery, Statement.RETURN_GENERATED_KEYS);
            prepare.setString(1, subjectName);
            prepare.executeUpdate();

            // Retrieve the generated subject ID
            ResultSet generatedKeys = prepare.getGeneratedKeys();
            int subjectId = 0;
            if (generatedKeys.next()) {
                subjectId = generatedKeys.getInt(1);
            }

            // Link the subject to the selected course in the course_subject table
            prepare = connect.prepareStatement(linkCourseSubjectQuery);
            prepare.setInt(1, selectedCourse.getId());
            prepare.setInt(2, subjectId);
            prepare.executeUpdate();

            // Show success message and update UI
            showAlert(Alert.AlertType.INFORMATION, "Success", "Subject added to course successfully!");
            subjectShowListData();
            clearSubject();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeDatabaseResources();
        }
    }

    @FXML
    public void updateSubject() {
        String subjectId = txtSubjectId.getText();
        String subjectName = txtSubjectName.getText();
        CourseData selectedCourse = comboCourse.getValue();

        if (subjectId.isEmpty() || subjectName.isEmpty() || selectedCourse == null) {
            showAlert(Alert.AlertType.ERROR, "Error", "Please fill all fields.");
            return;
        }

        String updateSubjectQuery = "UPDATE subject SET subject_name = ? WHERE subject_id = ?";
        String updateCourseSubjectQuery = "UPDATE course_subject SET course_id = ? WHERE subject_id = ?";

        try {
            connect = Database.connectDb();
            prepare = connect.prepareStatement(updateSubjectQuery);
            prepare.setString(1, subjectName);
            prepare.setInt(2, Integer.parseInt(subjectId));
            prepare.executeUpdate();

            prepare = connect.prepareStatement(updateCourseSubjectQuery);
            prepare.setInt(1, selectedCourse.getId());
            prepare.setInt(2, Integer.parseInt(subjectId));
            prepare.executeUpdate();

            showAlert(Alert.AlertType.INFORMATION, "Success", "Subject and course updated successfully!");
            subjectShowListData();
            clearSubject();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeDatabaseResources();
        }
    }

    @FXML
    public void deleteSubject() {
        String subjectId = txtSubjectId.getText();

        if (subjectId.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Error", "Please enter the subject ID.");
            return;
        }

        Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationAlert.setTitle("Confirmation");
        confirmationAlert.setHeaderText(null);
        confirmationAlert.setContentText("Are you sure you want to delete this subject?");
        Optional<ButtonType> option = confirmationAlert.showAndWait();

        if (option.isPresent() && option.get() == ButtonType.OK) {
            String deleteCourseSubjectQuery = "DELETE FROM course_subject WHERE subject_id = ?";
            String deleteSubjectQuery = "DELETE FROM subject WHERE subject_id = ?";

            try {
                connect = Database.connectDb();
                prepare = connect.prepareStatement(deleteCourseSubjectQuery);
                prepare.setInt(1, Integer.parseInt(subjectId));
                prepare.executeUpdate();

                prepare = connect.prepareStatement(deleteSubjectQuery);
                prepare.setInt(1, Integer.parseInt(subjectId));
                prepare.executeUpdate();

                showAlert(Alert.AlertType.INFORMATION, "Success", "Subject deleted successfully!");
                subjectShowListData();
                clearSubject();

            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                closeDatabaseResources();
            }
        }
    }

    @FXML
    public void handleRowSelect() {
        SubjectData selectedSubject = tableSubjects.getSelectionModel().getSelectedItem();
        if (selectedSubject != null) {
            txtSubjectId.setText(String.valueOf(selectedSubject.getSubjectId()));
            txtSubjectName.setText(selectedSubject.getSubjectName());

            // Set the value of comboCourse based on the selected subject's course name
            for (CourseData course : courseListData) {
                if (course.getCourse().equals(selectedSubject.getCourseName())) {
                    comboCourse.setValue(course);
                    break;
                }
            }
        }
    }

    @FXML
    private void clearSubject() {
        txtSubjectId.clear();
        txtSubjectName.clear();
        comboCourse.getSelectionModel().clearSelection();
    }

    private ObservableList<SubjectData> loadSubjectData() throws SQLException {
        subjectListData = FXCollections.observableArrayList();
        String query = "SELECT s.subject_id, s.subject_name, c.course_name "
                + "FROM subject s "
                + "JOIN course_subject cs ON s.subject_id = cs.subject_id "
                + "JOIN course c ON cs.course_id = c.id";
        try (Connection connection = Database.connectDb(); PreparedStatement preparedStatement = connection.prepareStatement(query); ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                SubjectData subject = new SubjectData(
                        resultSet.getInt("subject_id"),
                        resultSet.getString("subject_name"),
                        resultSet.getString("course_name")
                );
                subjectListData.add(subject);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        return subjectListData;
    }

    public void subjectShowListData() throws SQLException {
        ObservableList<SubjectData> subjectData = loadSubjectData();
        colSubjectId.setCellValueFactory(new PropertyValueFactory<>("subjectId"));
        colSubjectName.setCellValueFactory(new PropertyValueFactory<>("subjectName"));
        colCourseName.setCellValueFactory(new PropertyValueFactory<>("courseName")); // Adjust column mapping
        tableSubjects.setItems(subjectData);
    }

    @FXML
    public void SroAdd() throws SQLException {
        String insertSroData = "INSERT INTO sro " + "(sro_name, age, gender, phone_number, email)" + "VALUES(?, ?, ?, ?, ?)";
        connect = Database.connectDb();

        try {
            if (txtSroName.getText().isEmpty()
                    || txtSroEmail.getText().isEmpty()
                    || txtSroAge.getText().isEmpty()
                    || listSroGender.getSelectionModel().getSelectedItem() == null
                    || txtSroPhone.getText().isEmpty()) {
                showAlert(AlertType.ERROR, "Error Message", "Please fill all blank fields");
                return;
            }
            
            String sroAge = txtSroAge.getText();
            int age = Integer.parseInt(sroAge);
            
            String sroPhone = txtSroPhone.getText();
            int phone = Integer.parseInt(sroPhone); 
            
            prepare = connect.prepareStatement(insertSroData, Statement.RETURN_GENERATED_KEYS);
            prepare.setString(1, txtSroName.getText());
            prepare.setInt(2, age);
            prepare.setString(3, listSroGender.getValue().toString());
            prepare.setInt(4, phone);
            prepare.setString(5, txtSroEmail.getText());
            prepare.executeUpdate();
            showAlert(AlertType.INFORMATION, "Information Message", "Successfully Added!");
            SroShowListData();
            SroClear();
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(AlertType.ERROR, "Error Message", "An error occurred while adding SRO!");
        } finally {
            closeResources();
        }

    }
    
    public ObservableList<SroData> addSroListData() {
        ObservableList<SroData> listSro = FXCollections.observableArrayList();
        
        try(Connection connection = Database.connectDb();
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM sro");
                ResultSet rs = preparedStatement.executeQuery()
            ) {
            while(rs.next()) {
                SroData sroD = new SroData(
                    rs.getString("sro_name"),
                    rs.getInt("sro_id"),
                    rs.getInt("age"),
                    rs.getString("gender"),
                    rs.getInt("phone_number"),
                    rs.getString("email")
                );
                
                listSro.add(sroD);
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        return listSro;
    }
    
    private ObservableList<SroData> SroListD;
    
    public void SroShowListData() {
        SroListD = addSroListData();
        
        colSroID.setCellValueFactory(new PropertyValueFactory<>("sro_id"));
        colSroName.setCellValueFactory(new PropertyValueFactory<>("sro_name"));
        colSroAge.setCellValueFactory(new PropertyValueFactory<>("age"));
        colSroGender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        colSroPhone.setCellValueFactory(new PropertyValueFactory<>("phone_number"));
        colSroEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        
        Sro_tableView.setItems(SroListD);
    }
    
    @FXML
    public void SroGenderList() {
        List<String> genderL = new ArrayList<>();

        for (String data : genderList) {
            genderL.add(data);
        }

        ObservableList ObList = FXCollections.observableArrayList(genderL);
        listSroGender.setItems(ObList);
    }
    
    @FXML
    public void SroClear() {
        txtSroName.setText("");
        txtSroAge.setText("");
        txtSroEmail.setText("");
        txtSroPhone.setText("");
        listSroGender.getSelectionModel().clearSelection();
    }
    
    @FXML
    public void SroUpdate() throws SQLException {
          String updateSroData = "UPDATE sro SET "
                    + " sro_name = '" + txtSroName.getText()
                    + "', age = '" + txtSroAge.getText()
                    + "', email = '" + txtSroEmail.getText()
                    + "', phone_number = '" + txtSroPhone.getText()
                    + "', gender = '" + listSroGender.getSelectionModel().getSelectedIndex() + "' WHERE sro_id = ?'" + "'";
          
          
         
        connect =  Database.connectDb();
        try {
            Alert alert;
            alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure want to UPDATE SRO " + txtSroName.getText() + "?");
                Optional<ButtonType> option = alert.showAndWait();
                
                if(option.get().equals(ButtonType.OK)) {
                    statement = connect.createStatement();
                    statement.executeUpdate(updateSroData);
                alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Information Message");
                alert.setHeaderText(null);
                alert.setContentText("Successfully Updated!");
                alert.showAndWait();
                
                SroShowListData();
                SroClear();
                }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    public void SroDelete() throws SQLException {
        String deleteSroData = "DELETE FROM sro WHERE sro_id = ?";
        connect = Database.connectDb();
        
        try {
            Alert alert;
             if (txtSroName.getText().isEmpty()
                    || txtSroEmail.getText().isEmpty()
                    || txtSroAge.getText().isEmpty()
                    || listSroGender.getSelectionModel().getSelectedItem() == null
                    || txtSroPhone.getText().isEmpty()) {
                showAlert(AlertType.ERROR, "Error Message", "Please fill all blank fields");
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    public void SroSelect() {
        SroData sroD = Sro_tableView.getSelectionModel().getSelectedItem();
//        int num = Sro_tableView.getSelectionModel().getSelectedIndex();
        
        txtSroName.setText(String.valueOf(sroD.getSro_name()));
        txtSroEmail.setText(String.valueOf(sroD.getEmail()));
        txtSroAge.setText(String.valueOf(sroD.getAge()));
        txtSroPhone.setText(String.valueOf(sroD.getPhone_number()));
    }
    
    @FXML
    public void handleRowSro() {
        SroData sroSubject = Sro_tableView.getSelectionModel().getSelectedItem();
        if (sroSubject != null) {
            txtSroName.setText(String.valueOf(sroSubject.getSro_name()));
            txtSroEmail.setText(String.valueOf(sroSubject.getEmail()));
            txtSroAge.setText(String.valueOf(sroSubject.getAge()));
            txtSroPhone.setText(String.valueOf(sroSubject.getPhone_number()));
        }
    }
    
    @FXML
    public void SroSearch() {
        FilteredList<SroData> filter = new FilteredList<>(SroListD, e -> true);
        Sro_Search.textProperty().addListener((Observable, oldValue, newValue) -> {
            filter.setPredicate(predicateSroData -> {
                if(newValue  == null || newValue.isEmpty()) {
                    return true;
                }
                
                String searchKey = newValue.toLowerCase();
                if(predicateSroData.getSro_name().contains(searchKey)) {
                    return true;
                } else if(predicateSroData.getEmail().contains(searchKey)) {
                    return true;
                } else if(predicateSroData.getGender().contains(searchKey)) {
                    return true;
                } else if(predicateSroData.getGender().contains(searchKey)) {
                    return true;
                } else {
                    return false;
                }
            });
        });
        
        SortedList<SroData> sortList = new SortedList<>(filter);
        
        sortList.comparatorProperty().bind(Sro_tableView.comparatorProperty());
        Sro_tableView.setItems(sortList);
    }

    private void closeDatabaseResources() {
        try {
            if (result != null) {
                result.close();
            }
            if (prepare != null) {
                prepare.close();
            }
            if (connect != null) {
                connect.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Logout
    private double x = 0;
    private double y = 0;

    @FXML
    public void logout() {

        try {

            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Message");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to logout?");

            Optional<ButtonType> option = alert.showAndWait();

            if (option.get().equals(ButtonType.OK)) {

                //HIDE YOUR DASHBOARD FORM
                logout.getScene().getWindow().hide();

                //LINK YOUR LOGIN FORM 
                Parent root = FXMLLoader.load(getClass().getResource("/view/Login.fxml"));
                Stage stage = new Stage();
                Scene scene = new Scene(root);

                root.setOnMousePressed((MouseEvent event) -> {
                    x = event.getSceneX();
                    y = event.getSceneY();
                });

                root.setOnMouseDragged((MouseEvent event) -> {
                    stage.setX(event.getScreenX() - x);
                    stage.setY(event.getScreenY() - y);

                    stage.setOpacity(.8);
                });

                root.setOnMouseReleased((MouseEvent event) -> {
                    stage.setOpacity(1);
                });

                stage.initStyle(StageStyle.TRANSPARENT);

                stage.setScene(scene);
                stage.show();

            } else {
                return;
            }

        } catch (IOException e) {
        }

    }

    public void displayUsername() {
        username.setText(GetData.username);
    }

    public void defaultNav() {
        home_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #3f82ae, #26bf7d);");
    }

    @FXML
    public void switchForm(ActionEvent event) throws SQLException {
        if (event.getSource() == home_btn) {
            home_form.setVisible(true);
            addStudents_form.setVisible(false);
            availableCourse_form.setVisible(false);
            studentGrade_form.setVisible(false);
            teacher_form.setVisible(false);
            Class_form.setVisible(false);
            Sro_form.setVisible(false);
            Subject_form.setVisible(false);

            home_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #3f82ae, #26bf7d);");
            addStudents_btn.setStyle("-fx-background-color:transparent");
            availableCourse_btn.setStyle("-fx-background-color:transparent");
            studentGrade_btn.setStyle("-fx-background-color:transparent");
            teacher_btn.setStyle("-fx-background-color:transparent");
            Sro_btn.setStyle("-fx-background-color:transparent");
            Class_btn.setStyle("-fx-background-color:transparent");
            Subject_btn.setStyle("-fx-background-color:transparent");

            homeDisplayTotalEnrolledStudents();
            homeDisplayMaleEnrolled();
            homeDisplayFemaleEnrolled();
            homeDisplayEnrolledMaleChart();
            homeDisplayFemaleEnrolledChart();
            homeDisplayTotalEnrolledChart();

        } else if (event.getSource() == addStudents_btn) {
            home_form.setVisible(false);
            addStudents_form.setVisible(true);
            availableCourse_form.setVisible(false);
            studentGrade_form.setVisible(false);
            teacher_form.setVisible(false);
            Class_form.setVisible(false);
            Sro_form.setVisible(false);
            Subject_form.setVisible(false);

            addStudents_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #3f82ae, #26bf7d);");
            home_btn.setStyle("-fx-background-color:transparent");
            availableCourse_btn.setStyle("-fx-background-color:transparent");
            studentGrade_btn.setStyle("-fx-background-color:transparent");
            teacher_btn.setStyle("-fx-background-color:transparent");
            Sro_btn.setStyle("-fx-background-color:transparent");
            Class_btn.setStyle("-fx-background-color:transparent");
            Subject_btn.setStyle("-fx-background-color:transparent");

//            TO BECOME UPDATED ONCE YOU CLICK THE ADD STUDENTS BUTTON ON NAV
            addStudentsShowListData();
            addStudentsYearList();
            addStudentsGenderList();
            addStudentsStatusList();
            addStudentsCourseList();
            addStudentsSearch();

        } else if (event.getSource() == availableCourse_btn) {
            home_form.setVisible(false);
            addStudents_form.setVisible(false);
            availableCourse_form.setVisible(true);
            studentGrade_form.setVisible(false);
            teacher_form.setVisible(false);
            Class_form.setVisible(false);
            Sro_form.setVisible(false);
            Subject_form.setVisible(false);

            availableCourse_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #3f82ae, #26bf7d);");
            addStudents_btn.setStyle("-fx-background-color:transparent");
            home_btn.setStyle("-fx-background-color:transparent");
            studentGrade_btn.setStyle("-fx-background-color:transparent");
            teacher_btn.setStyle("-fx-background-color:transparent");
            Sro_btn.setStyle("-fx-background-color:transparent");
            Class_btn.setStyle("-fx-background-color:transparent");
            Subject_btn.setStyle("-fx-background-color:transparent");

            availableCourseShowListData();

        } else if (event.getSource() == studentGrade_btn) {
            home_form.setVisible(false);
            addStudents_form.setVisible(false);
            availableCourse_form.setVisible(false);
            studentGrade_form.setVisible(true);
            teacher_form.setVisible(false);
            Class_form.setVisible(false);
            Sro_form.setVisible(false);
            Subject_form.setVisible(false);

            studentGrade_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #3f82ae, #26bf7d);");
            addStudents_btn.setStyle("-fx-background-color:transparent");
            availableCourse_btn.setStyle("-fx-background-color:transparent");
            home_btn.setStyle("-fx-background-color:transparent");
            teacher_btn.setStyle("-fx-background-color:transparent");
            Sro_btn.setStyle("-fx-background-color:transparent");
            Class_btn.setStyle("-fx-background-color:transparent");
            Subject_btn.setStyle("-fx-background-color:transparent");

            studentGradesShowListData();
            studentGradesSearch();

        } else if (event.getSource() == teacher_btn) {
            home_form.setVisible(false);
            addStudents_form.setVisible(false);
            availableCourse_form.setVisible(false);
            studentGrade_form.setVisible(false);
            teacher_form.setVisible(true);
            Class_form.setVisible(false);
            Sro_form.setVisible(false);
            Subject_form.setVisible(false);

            studentGrade_btn.setStyle("-fx-background-color:transparent);");
            addStudents_btn.setStyle("-fx-background-color:transparent");
            availableCourse_btn.setStyle("-fx-background-color:transparent");
            home_btn.setStyle("-fx-background-color:transparent");
            teacher_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #3f82ae, #26bf7d);");
            Sro_btn.setStyle("-fx-background-color:transparent");
            Class_btn.setStyle("-fx-background-color:transparent");
            Subject_btn.setStyle("-fx-background-color:transparent");

//            studentGradesShowListData();
//            studentGradesSearch();
        } else if (event.getSource() == Class_btn) {
            home_form.setVisible(false);
            addStudents_form.setVisible(false);
            availableCourse_form.setVisible(false);
            studentGrade_form.setVisible(false);
            teacher_form.setVisible(false);
            Class_form.setVisible(true);
            Sro_form.setVisible(false);
            Subject_form.setVisible(false);

            studentGrade_btn.setStyle("-fx-background-color:transparent);");
            addStudents_btn.setStyle("-fx-background-color:transparent");
            availableCourse_btn.setStyle("-fx-background-color:transparent");
            home_btn.setStyle("-fx-background-color:transparent");
            teacher_btn.setStyle("-fx-background-color:transparent");
            Sro_btn.setStyle("-fx-background-color:transparent");
            Class_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #3f82ae, #26bf7d);");
            Subject_btn.setStyle("-fx-background-color:transparent");

//            studentGradesShowListData();
//            studentGradesSearch();
        } else if (event.getSource() == Sro_btn) {
            home_form.setVisible(false);
            addStudents_form.setVisible(false);
            availableCourse_form.setVisible(false);
            studentGrade_form.setVisible(false);
            teacher_form.setVisible(false);
            Class_form.setVisible(false);
            Sro_form.setVisible(true);
            Subject_form.setVisible(false);

            studentGrade_btn.setStyle("-fx-background-color:transparent);");
            addStudents_btn.setStyle("-fx-background-color:transparent");
            availableCourse_btn.setStyle("-fx-background-color:transparent");
            home_btn.setStyle("-fx-background-color:transparent");
            teacher_btn.setStyle("-fx-background-color:transparent");
            Sro_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #3f82ae, #26bf7d);");
            Class_btn.setStyle("-fx-background-color:transparent");
            Subject_btn.setStyle("-fx-background-color:transparent");
            SroGenderList();
            SroShowListData();
            SroSearch();
        } else if (event.getSource() == Subject_btn) {
            home_form.setVisible(false);
            addStudents_form.setVisible(false);
            availableCourse_form.setVisible(false);
            studentGrade_form.setVisible(false);
            teacher_form.setVisible(false);
            Class_form.setVisible(false);
            Sro_form.setVisible(false);
            Subject_form.setVisible(true);

            studentGrade_btn.setStyle("-fx-background-color:transparent);");
            addStudents_btn.setStyle("-fx-background-color:transparent");
            availableCourse_btn.setStyle("-fx-background-color:transparent");
            home_btn.setStyle("-fx-background-color:transparent");
            teacher_btn.setStyle("-fx-background-color:transparent");
            Sro_btn.setStyle("-fx-background-color:transparent");
            Class_btn.setStyle("-fx-background-color:transparent");
            Subject_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #3f82ae, #26bf7d);");

            setupCourseComboBox();
            subjectShowListData();
            loadCourseData();
        }
    }

    @FXML
    public void close() {
        System.exit(0);
    }

    @FXML
    public void minimize() {
        Stage stage = (Stage) main_form.getScene().getWindow();
        stage.setIconified(true);
    }

    // SORRY ABOUT THAT, I JUST NAMED THE DIFFERENT COMPONENTS WITH THE SAME NAME 
    // MAKE SURE THAT THE NAME YOU GAVE TO THEM ARE DIFFERENT TO THE OTHER OKAY?
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            displayUsername();
            defaultNav();

            homeDisplayTotalEnrolledStudents();
            homeDisplayMaleEnrolled();
            homeDisplayFemaleEnrolled();
            homeDisplayEnrolledMaleChart();
            homeDisplayFemaleEnrolledChart();
            homeDisplayTotalEnrolledChart();

            // TO SHOW IMMIDIATELY WHEN WE PROCEED TO DASHBOARD APPLICATION FORM
            addStudentsShowListData();
            addStudentsYearList();
            addStudentsGenderList();
            addStudentsStatusList();
            addStudentsCourseList();

            availableCourseShowListData();

            studentGradesShowListData();
            
            SroGenderList();
            SroShowListData();
        } catch (SQLException ex) {
            Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
