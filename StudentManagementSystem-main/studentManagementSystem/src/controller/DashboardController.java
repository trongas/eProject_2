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
import entity.ClassData;
import entity.GetData;
import entity.SroData;
import entity.StudentData;
import entity.StudentGrade;
import entity.SubjectData;
import entity.TeacherData;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.Pagination;
import javafx.scene.control.cell.CheckBoxListCell;
import javafx.util.Callback;
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
    private TableColumn<StudentData, Integer> addStudents_col_studentNum;
    @FXML
    private TableColumn<StudentData, String> addStudents_col_class;
    @FXML
    private TableColumn<StudentData, String> addStudents_col_course;
    @FXML
    private TableColumn<StudentData, String> addStudents_col_firstName;
    @FXML
    private TableColumn<StudentData, String> addStudents_col_lastName;
    @FXML
    private TableColumn<StudentData, String> addStudents_col_gender;
    @FXML
    private TableColumn<StudentData, Date> addStudents_col_birth;
    @FXML
    private TableColumn<StudentData, String> addStudents_col_status;
    @FXML
    private TableColumn<StudentData, String> addStudents_col_address;
    @FXML
    private TableColumn<StudentData, String> addStudents_col_phone;
    @FXML
    private TableColumn<StudentData, String> addStudents_col_email;
    @FXML
    private TableColumn<StudentData, String> addStudents_col_cccd;
    @FXML
    private TextField addStudents_studentNum;

    @FXML
    private TextField addStudents_cccd;

    @FXML
    private TextField addStudents_email;

    @FXML
    private TextField addStudents_phone_number;

    @FXML
    private TextField addStudents_address;

    @FXML
    private ComboBox<String> addStudents_course;

    @FXML
    private TextField addStudents_firstName;

    @FXML
    private TextField addStudents_lastName;

    @FXML
    private DatePicker addStudents_birth;

    @FXML
    private ComboBox<String> addStudents_status;

    @FXML
    private ComboBox<String> addStudents_class;
    ;
        
    @FXML
    private ComboBox<String> addStudents_gender;

    @FXML
    private ImageView addStudents_imageView;

    @FXML
    private AnchorPane availableCourse_form;

    @FXML
    private TextField availableCourse_course;

    @FXML
    private TextField availableCourse_description;

    @FXML
    private TextField availableCourse_duration;

    @FXML
    private TextField availableCourse_degree;

    @FXML
    private TextField availableCourse_id;

    @FXML
    private TableView<CourseData> availableCourse_tableView;

    @FXML
    private TableColumn<CourseData, String> availableCourse_col_course;

    @FXML
    private TableColumn<CourseData, String> availableCourse_col_id;
    @FXML
    private TableColumn<CourseData, String> availableCourse_col_description;

    @FXML
    private TableColumn<CourseData, String> availableCourse_col_degree;

    @FXML
    private TableColumn<CourseData, String> availableCourse_col_duration;

    //Student_grade
    @FXML
    private AnchorPane studentGrade_form;

    @FXML
    private TextField scoreField;
    @FXML
    private ComboBox<String> subjectComboBox;
    @FXML
    private TextField studentNumField;
    @FXML
    private TableView<StudentGrade> studentGradesTable;
    @FXML
    private TableColumn<StudentGrade, Integer> gradeIdColumn;
    @FXML
    private TableColumn<StudentGrade, Integer> studentNameColumn;
    @FXML
    private TableColumn<StudentGrade, String> subjectColumn;
    @FXML
    private TableColumn<StudentGrade, Double> scoreColumn;
    @FXML
    private TableColumn<StudentGrade, String> statusColumn;
    @FXML
    private TextField studentGrade_search;
    @FXML
    private Button updateGradeButton;
    @FXML
    private Button loadGradesButton;
    @FXML
    private Button exportToExcelButton;

    //Teacher
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
    private TextField txtSroID;
    @FXML
    private TextField txtSroAge;

    @FXML
    private TextField txtSroEmail;

    @FXML
    private TextField txtSroName;

    @FXML
    private TextField txtSroPhone;
    @FXML
    private TextField txtSroPeopleID;

    @FXML
    private ComboBox<String> listSroGender;
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
    @FXML
    private TableColumn<SroData, String> colSroPeopleID;

    @FXML
    private TextField txtTeacherName;
    @FXML
    private TextField txtTeacherAge;
    @FXML
    private ComboBox<String> comboTeacherGender;
    @FXML
    private TextField txtTeacherPhoneNumber;
    @FXML
    private TextField txtTeacherEmail;
    @FXML
    private TextField txtTeacherCccd;
    @FXML
    private ListView<SubjectData> listViewSubjects;
    @FXML
    private TableView<TeacherData> tableViewTeachers;
    @FXML
    private TableColumn<TeacherData, Integer> colTeacherId;
    @FXML
    private TableColumn<TeacherData, String> colTeacherName;
    @FXML
    private TableColumn<TeacherData, Integer> colTeacherAge;
    @FXML
    private TableColumn<TeacherData, String> colTeacherGender;
    @FXML
    private TableColumn<TeacherData, String> colTeacherPhoneNumber;
    @FXML
    private TableColumn<TeacherData, String> colTeacherSubjects;
    @FXML
    private TableColumn<TeacherData, String> colTeacherEmail;
    @FXML
    private TableColumn<TeacherData, String> colTeacherCccd;
    @FXML
    private TextField searchTeacherField;

    //Class
    @FXML
    private TableView<ClassData> tableViewClasses;
    @FXML
    private TableColumn<ClassData, Integer> colClassId;
    @FXML
    private TableColumn<ClassData, String> colClassName;
    @FXML
    private TableColumn<ClassData, String> colCourseNameClass;
    @FXML
    private TableColumn<ClassData, String> colTeacherNameClass;
    @FXML
    private TableColumn<ClassData, String> colSroNameClass;

    @FXML
    private TextField txtClassName;
    @FXML
    private ComboBox<String> comboCourseName;
    @FXML
    private ComboBox<String> comboTeacherName;
    @FXML
    private ComboBox<String> comboSroName;
    @FXML
    private TextField searchClassField;

    private ObservableList<ClassData> classListData;

    private Connection connect;
    private PreparedStatement prepare;
    private Statement statement;
    private ResultSet result;

    private Image image;

    // Home Page
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

        String sql = "SELECT COUNT(student_id) FROM student WHERE gender = 'Female' and status = 'Active'";

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

        String sql = "SELECT COUNT(student_id) FROM student WHERE gender = 'Male' and status = 'Active'";

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

        String sql = "SELECT create_date, COUNT(student_id) FROM student WHERE status = 'active' GROUP BY create_date ORDER BY TIMESTAMP(create_date) ASC LIMIT 5";

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

        String sql = "SELECT create_date, COUNT(student_id) FROM student WHERE status = 'ctive' and gender = 'Female' GROUP BY create_date ORDER BY TIMESTAMP(create_date) ASC LIMIT 5";

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

        String sql = "SELECT create_date, COUNT(student_id) FROM student WHERE status = 'Active' and gender = 'Male' GROUP BY create_date ORDER BY TIMESTAMP(create_date) ASC LIMIT 5";

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

    //STUDENT
    private void populateComboBoxes() {
        ObservableList<String> classList = FXCollections.observableArrayList(fetchClassNames());
        ObservableList<String> courseList = FXCollections.observableArrayList(fetchCourseNames());
        addStudents_class.setItems(classList);
        addStudents_course.setItems(courseList);
    }

    private List<String> fetchClassNames() {
        List<String> classNames = new ArrayList<>();
        String query = "SELECT class_name FROM class";
        try (PreparedStatement stmt = connect.prepareStatement(query); ResultSet result = stmt.executeQuery()) {
            while (result.next()) {
                classNames.add(result.getString("class_name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return classNames;
    }

    private List<String> fetchCourseNames() {
        List<String> courseNames = new ArrayList<>();
        String query = "SELECT course_name FROM course";
        try (PreparedStatement stmt = connect.prepareStatement(query); ResultSet result = stmt.executeQuery()) {
            while (result.next()) {
                courseNames.add(result.getString("course_name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return courseNames;
    }

    private boolean validateStudentFields() {
        String emailPattern = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        String phonePattern = "^[0-9]{10}$";  // Assuming phone number is 10 digits
        String cccdPattern = "^[0-9]{12}$";  // Assuming CCCD is 12 digits

        if (addStudents_studentNum.getText().isEmpty()
                || addStudents_class.getSelectionModel().getSelectedItem() == null
                || addStudents_course.getSelectionModel().getSelectedItem() == null
                || addStudents_firstName.getText().isEmpty()
                || addStudents_lastName.getText().isEmpty()
                || addStudents_gender.getSelectionModel().getSelectedItem() == null
                || addStudents_birth.getValue() == null
                || addStudents_status.getSelectionModel().getSelectedItem() == null
                || GetData.path == null || GetData.path.isEmpty()
                || addStudents_address.getText().isEmpty()
                || addStudents_phone_number.getText().isEmpty()
                || !addStudents_phone_number.getText().matches(phonePattern)
                || addStudents_email.getText().isEmpty()
                || !addStudents_email.getText().matches(emailPattern)) {

            String errorMessage = "Please fill all fields correctly:\n";

            if (addStudents_studentNum.getText().isEmpty()) {
                errorMessage += " - Student Number is empty\n";
            }
            if (addStudents_class.getSelectionModel().getSelectedItem() == null) {
                errorMessage += " - Class is not selected\n";
            }
            if (addStudents_course.getSelectionModel().getSelectedItem() == null) {
                errorMessage += " - Course is not selected\n";
            }
            if (addStudents_firstName.getText().isEmpty()) {
                errorMessage += " - First Name is empty\n";
            }
            if (addStudents_lastName.getText().isEmpty()) {
                errorMessage += " - Last Name is empty\n";
            }
            if (addStudents_gender.getSelectionModel().getSelectedItem() == null) {
                errorMessage += " - Gender is not selected\n";
            }
            if (addStudents_birth.getValue() == null) {
                errorMessage += " - Birth Date is not selected\n";
            }
            if (addStudents_status.getSelectionModel().getSelectedItem() == null) {
                errorMessage += " - Status is not selected\n";
            }
            if (GetData.path == null || GetData.path.isEmpty()) {
                errorMessage += " - Image is not selected\n";
            }
            if (addStudents_address.getText().isEmpty()) {
                errorMessage += " - Address is empty\n";
            }
            if (addStudents_phone_number.getText().isEmpty()) {
                errorMessage += " - Phone Number is empty\n";
            } else if (!addStudents_phone_number.getText().matches(phonePattern)) {
                errorMessage += " - Phone Number is not valid\n";
            }
            if (addStudents_email.getText().isEmpty()) {
                errorMessage += " - Email is empty\n";
            } else if (!addStudents_email.getText().matches(emailPattern)) {
                errorMessage += " - Email is not valid\n";
            }

            showAlert(Alert.AlertType.ERROR, "Error Message", errorMessage);
            return false;
        }
        return true;
    }

    @FXML
    public void addStudentsAdd() throws SQLException {
        String insertStudentData = "INSERT INTO student "
                + "(studentNum, class_id, course_id, firstName, lastName, gender, birth, status, image, create_date, address, phone_number, email, cccd) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        connect = Database.connectDb();

        try {
            if (!validateStudentFields()) {
                showAlert(Alert.AlertType.ERROR, "Error Message", "Please fill all blank fields");
                return;
            }

            // Check if student number already exists
            if (isStudentNumberExists(addStudents_studentNum.getText())) {
                showAlert(Alert.AlertType.ERROR, "Error Message", "Student #" + addStudents_studentNum.getText() + " already exists!");
                return;
            }

            if (isStudentCccdExists(addStudents_cccd.getText())) {
                showAlert(Alert.AlertType.ERROR, "Error Message", "Student #" + addStudents_cccd.getText() + " already exists!");
                return;
            }

            // Get class ID
            int classId = getClassId((String) addStudents_class.getSelectionModel().getSelectedItem());
            if (classId == -1) {
                showAlert(Alert.AlertType.ERROR, "Error Message", "Selected class does not exist!");
                return;
            }

            // Get course ID
            int courseId = getCourseId((String) addStudents_course.getSelectionModel().getSelectedItem());
            if (courseId == -1) {
                showAlert(Alert.AlertType.ERROR, "Error Message", "Selected course does not exist!");
                return;
            }

            // Prepare student data insertion
            prepare = connect.prepareStatement(insertStudentData, Statement.RETURN_GENERATED_KEYS);
            prepare.setString(1, addStudents_studentNum.getText());
            prepare.setInt(2, classId);
            prepare.setInt(3, courseId);
            prepare.setString(4, addStudents_firstName.getText());
            prepare.setString(5, addStudents_lastName.getText());
            prepare.setString(6, addStudents_gender.getValue());
            prepare.setString(7, addStudents_birth.getValue().toString());
            prepare.setString(8, addStudents_status.getValue());
            prepare.setString(9, GetData.path);
            prepare.setDate(10, new java.sql.Date(new Date().getTime()));
            prepare.setString(11, addStudents_address.getText());
            prepare.setString(12, addStudents_phone_number.getText());
            prepare.setString(13, addStudents_email.getText());
            prepare.setString(14, addStudents_cccd.getText());
            prepare.executeUpdate();

            // Get auto-generated student ID
            ResultSet generatedKeys = prepare.getGeneratedKeys();
            int studentId;
            if (generatedKeys.next()) {
                studentId = generatedKeys.getInt(1);
            } else {
                showAlert(Alert.AlertType.ERROR, "Error Message", "Failed to retrieve student ID!");
                return;
            }

            // Add initial grades for the student
            addInitialStudentGrades(studentId, courseId);

            showAlert(Alert.AlertType.INFORMATION, "Information Message", "Successfully Added!");

            // Update the table view
            addStudentsShowListData();
            // Clear the fields
            addStudentsClear();
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Error Message", "An error occurred while adding student!");
        } finally {
            closeDatabaseResources();
        }
    }

    private boolean isStudentNumberExists(String studentNum) throws SQLException {
        String query = "SELECT COUNT(*) FROM student WHERE studentNum = ?";
        try (PreparedStatement ps = connect.prepareStatement(query)) {
            ps.setString(1, studentNum);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        }
        return false;
    }

    private boolean isStudentCccdExists(String cccd) throws SQLException {
        String query = "SELECT COUNT(*) FROM student WHERE cccd = ?";
        try (PreparedStatement ps = connect.prepareStatement(query)) {
            ps.setString(1, cccd);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        }
        return false;
    }

    private int getCourseId(String courseName) throws SQLException {
        String query = "SELECT course_id FROM course WHERE course_name = ?";
        prepare = connect.prepareStatement(query);
        prepare.setString(1, courseName);
        ResultSet resultSet = prepare.executeQuery();
        return resultSet.next() ? resultSet.getInt("course_id") : -1;
    }

    private int getClassId(String className) throws SQLException {
        String query = "SELECT class_id FROM class WHERE class_name = ?";
        prepare = connect.prepareStatement(query);
        prepare.setString(1, className);
        ResultSet resultSet = prepare.executeQuery();
        return resultSet.next() ? resultSet.getInt("class_id") : -1;
    }

    @FXML
    public void addStudentsUpdate() throws SQLException {
        String studentNum = addStudents_studentNum.getText();

        if (studentNum.isEmpty()) {
            showAlert(AlertType.ERROR, "Error Message", "Please provide the student number.");
            return;
        }

        // Confirmation dialog
        Alert confirmationAlert = new Alert(AlertType.CONFIRMATION);
        confirmationAlert.setTitle("Confirmation Message");
        confirmationAlert.setHeaderText(null);
        confirmationAlert.setContentText("Are you sure you want to UPDATE Student #" + studentNum + "?");

        Optional<ButtonType> option = confirmationAlert.showAndWait();

        // Check user's choice
        if (option.isPresent() && option.get() == ButtonType.OK) {
            try {
                connect = Database.connectDb();

                // Get class ID
                int classId = getClassId(addStudents_class.getSelectionModel().getSelectedItem());
                if (classId == -1) {
                    showAlert(Alert.AlertType.ERROR, "Error Message", "Selected class does not exist!");
                    return;
                }

                // Get course ID
                int courseId = getCourseId(addStudents_course.getSelectionModel().getSelectedItem());
                if (courseId == -1) {
                    showAlert(Alert.AlertType.ERROR, "Error Message", "Selected course does not exist!");
                    return;
                }

                String updateData = "UPDATE student SET class_id = ?, course_id = ?, firstName = ?, lastName = ?, gender = ?, birth = ?, status = ?, image = ?, address = ?, phone_number = ?, email = ?, cccd = ? WHERE studentNum = ?";
                PreparedStatement updateStatement = connect.prepareStatement(updateData);
                updateStatement.setInt(1, classId);
                updateStatement.setInt(2, courseId);
                updateStatement.setString(3, addStudents_firstName.getText());
                updateStatement.setString(4, addStudents_lastName.getText());
                updateStatement.setString(5, addStudents_gender.getValue());
                updateStatement.setString(6, addStudents_birth.getValue().toString());
                updateStatement.setString(7, addStudents_status.getValue());
                updateStatement.setString(8, GetData.path);
                updateStatement.setString(9, addStudents_address.getText());
                updateStatement.setString(10, addStudents_phone_number.getText());
                updateStatement.setString(11, addStudents_email.getText());
                updateStatement.setString(12, addStudents_cccd.getText());
                updateStatement.setString(13, studentNum);
                updateStatement.executeUpdate();

                showAlert(AlertType.INFORMATION, "Information Message", "Successfully Updated!");

                // Update the table view
                addStudentsShowListData();
                // Clear the fields
                addStudentsClear();
            } catch (SQLException e) {
                e.printStackTrace();
                showAlert(AlertType.ERROR, "Error Message", "An error occurred while updating student!");
            } finally {
                closeDatabaseResources();
            }
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

                // Get student ID
                String getStudentIdQuery = "SELECT student_id FROM student WHERE studentNum = ?";
                PreparedStatement getStudentIdStatement = connect.prepareStatement(getStudentIdQuery);
                getStudentIdStatement.setString(1, studentNum);
                ResultSet rs = getStudentIdStatement.executeQuery();
                int studentId = -1;
                if (rs.next()) {
                    studentId = rs.getInt("student_id");
                }

                if (studentId == -1) {
                    showAlert(Alert.AlertType.ERROR, "Error Message", "Student #" + studentNum + " not found!");
                    return;
                }

                // Delete corresponding records in student_grades table
                String deleteGradesQuery = "DELETE FROM student_grades WHERE student_id = ?";
                PreparedStatement deleteGradesStatement = connect.prepareStatement(deleteGradesQuery);
                deleteGradesStatement.setInt(1, studentId);
                deleteGradesStatement.executeUpdate();

                // Delete the student record
                String deleteData = "DELETE FROM student WHERE studentNum = ?";
                PreparedStatement deleteStatement = connect.prepareStatement(deleteData);
                deleteStatement.setString(1, studentNum);
                deleteStatement.executeUpdate();

                // Commit the transaction
                connect.commit();

                showAlert(AlertType.INFORMATION, "Information Message", "Successfully Deleted!");

                // Update the table view
                addStudentsShowListData();
                // Clear the fields
                addStudentsClear();
            } catch (SQLException e) {
                if (connect != null) {
                    try {
                        connect.rollback();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
                e.printStackTrace();
                showAlert(AlertType.ERROR, "Error Message", "An error occurred while deleting student!");
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
        addStudents_class.getSelectionModel().clearSelection();
        addStudents_course.getSelectionModel().clearSelection();
        addStudents_firstName.setText("");
        addStudents_lastName.setText("");
        addStudents_gender.getSelectionModel().clearSelection();
        addStudents_birth.setValue(null);
        addStudents_status.getSelectionModel().clearSelection();
        GetData.path = "";
        addStudents_imageView.setImage(null);
        addStudents_address.setText("");
        addStudents_phone_number.setText("");
        addStudents_email.setText("");
        addStudents_cccd.setText("");

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
    }

    //Load  Status Student
    ObservableList<String> statusOpions = FXCollections.observableArrayList("ACTIVE", "INACTIVE", "GRADUATED", "SUSPENDED");

    @FXML
    private void loadStatus() {
        addStudents_status.setItems(statusOpions);
    }

    @FXML
    public void addStudentsSearch() {
        FilteredList<StudentData> filteredList = new FilteredList<>(addStudentsListD, b -> true);
        addStudents_search.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredList.setPredicate(student -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if (student.getFirstName().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (student.getLastName().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (String.valueOf(student.getStudentNum()).contains(lowerCaseFilter)) {
                    return true;
                } else if (String.valueOf(student.getCccd()).contains(lowerCaseFilter)) {
                    return true;
                } else if (String.valueOf(student.getAddress()).contains(lowerCaseFilter)) {
                    return true;
                } else if (String.valueOf(student.getBirth()).contains(lowerCaseFilter)) {
                    return true;
                } else if (String.valueOf(student.getClassName()).contains(lowerCaseFilter)) {
                    return true;
                } else if (String.valueOf(student.getEmail()).contains(lowerCaseFilter)) {
                    return true;
                } else if (String.valueOf(student.getGender()).contains(lowerCaseFilter)) {
                    return true;
                } else if (String.valueOf(student.getCourseName()).contains(lowerCaseFilter)) {
                    return true;
                } else if (String.valueOf(student.getPhoneNumber()).contains(lowerCaseFilter)) {
                    return true;
                } else if (String.valueOf(student.getStatus()).contains(lowerCaseFilter)) {
                    return true;
                }
                return false;
            });
        });
        addStudents_tableView.setItems(filteredList);
    }

    public ObservableList<StudentData> addStudentsListData() {
        ObservableList<StudentData> listStudents = FXCollections.observableArrayList();

        try (Connection connection = Database.connectDb(); PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT s.studentNum, c.course_name, cl.class_name, s.firstName, s.lastName, s.gender, s.birth, s.status, s.image, s.address, s.phone_number, s.email, s.cccd "
                + "FROM student s "
                + "JOIN course c ON s.course_id = c.course_id "
                + "JOIN class cl ON s.class_id = cl.class_id"); ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                StudentData studentD = new StudentData(
                        resultSet.getString("studentNum"),
                        resultSet.getString("class_name"),
                        resultSet.getString("course_name"),
                        resultSet.getString("firstName"),
                        resultSet.getString("lastName"),
                        resultSet.getString("gender"),
                        resultSet.getDate("birth"),
                        resultSet.getString("status"),
                        resultSet.getString("image"),
                        resultSet.getString("address"),
                        resultSet.getString("phone_number"),
                        resultSet.getString("email"),
                        resultSet.getString("cccd")
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
        addStudentsListD = addStudentsListData();

        addStudents_col_studentNum.setCellValueFactory(new PropertyValueFactory<>("studentNum"));
        addStudents_col_class.setCellValueFactory(new PropertyValueFactory<>("className"));
        addStudents_col_course.setCellValueFactory(new PropertyValueFactory<>("courseName"));
        addStudents_col_firstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        addStudents_col_lastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        addStudents_col_gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        addStudents_col_birth.setCellValueFactory(new PropertyValueFactory<>("birth"));
        addStudents_col_status.setCellValueFactory(new PropertyValueFactory<>("status"));
        addStudents_col_address.setCellValueFactory(new PropertyValueFactory<>("address"));
        addStudents_col_phone.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        addStudents_col_email.setCellValueFactory(new PropertyValueFactory<>("email"));
        addStudents_col_cccd.setCellValueFactory(new PropertyValueFactory<>("cccd"));

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

        addStudents_class.setValue(studentD.getClassName());
        addStudents_course.setValue(studentD.getCourseName());
        addStudents_gender.setValue(studentD.getGender());
        addStudents_status.setValue(studentD.getStatus());
        addStudents_address.setText(studentD.getAddress());
        addStudents_phone_number.setText(studentD.getPhoneNumber());
        addStudents_email.setText(studentD.getEmail());
        addStudents_cccd.setText(studentD.getCccd());
    }

    //COURSE
    @FXML
    public void availableCourseAdd() throws SQLException {

        String insertData = "INSERT INTO course (course_name,description,degree,duration) VALUES(?,?,?,?)";

        connect = Database.connectDb();

        try {
            Alert alert;

            if (availableCourse_course.getText().isEmpty()
                    || availableCourse_description.getText().isEmpty()
                    || availableCourse_degree.getText().isEmpty()
                    || availableCourse_duration.getText().isEmpty()) {
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
                    prepare.setString(4, availableCourse_duration.getText());

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
            closeDatabaseResources();
        }
    }

    @FXML
    public void availableCourseUpdate() {
        try {
            if (availableCourse_course.getText().isEmpty()
                    || availableCourse_description.getText().isEmpty()
                    || availableCourse_degree.getText().isEmpty()
                    || availableCourse_duration.getText().isEmpty()) {
                showAlert(Alert.AlertType.ERROR, "Error Message", "Please fill all blank fields");
                return;
            }

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Message");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to UPDATE Course: " + availableCourse_course.getText() + "?");
            Optional<ButtonType> option = alert.showAndWait();

            if (option.isPresent() && option.get() == ButtonType.OK) {
                connect = Database.connectDb();
                String updateData = "UPDATE course SET description = ?, degree = ?, duration = ? WHERE course_name = ?";
                prepare = connect.prepareStatement(updateData);
                prepare.setString(1, availableCourse_description.getText());
                prepare.setString(2, availableCourse_degree.getText());
                prepare.setString(3, availableCourse_duration.getText());
                prepare.setString(4, availableCourse_course.getText());

                prepare.executeUpdate();

                showAlert(Alert.AlertType.INFORMATION, "Information Message", "Successfully Updated!");

                // Refresh the table view after successful update
                availableCourseShowListData();
                // Clear the text fields
                availableCourseClear();
            }
        } catch (SQLException e) {
            showAlert(Alert.AlertType.ERROR, "Error", "Database error: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // Close resources
            closeDatabaseResources();
        }
    }

    @FXML
    public void availableCourseDelete() {
        try {
            int courseId = Integer.parseInt(availableCourse_id.getText());

            if (courseId <= 0) {
                showAlert(Alert.AlertType.ERROR, "Error", "Please select a course to delete.");
                return;
            }

            Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
            confirmationAlert.setTitle("Confirmation");
            confirmationAlert.setHeaderText(null);
            confirmationAlert.setContentText("Are you sure you want to delete the selected course?");

            Optional<ButtonType> result = confirmationAlert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                connect = Database.connectDb();
                // Delete course-subject relationships first
                String deleteCourseSubjectQuery = "DELETE FROM course_subject WHERE course_id = ?";
                prepare = connect.prepareStatement(deleteCourseSubjectQuery);
                prepare.setInt(1, courseId);
                prepare.executeUpdate();

                // Now delete the course
                String deleteCourseQuery = "DELETE FROM course WHERE id = ?";
                prepare = connect.prepareStatement(deleteCourseQuery);
                prepare.setInt(1, courseId);
                prepare.executeUpdate();

                showAlert(Alert.AlertType.INFORMATION, "Success", "Course deleted successfully!");

                // Refresh the table view after successful deletion
                availableCourseShowListData();
                // Clear the text fields
                availableCourseClear();
            }
        } catch (SQLException e) {
            showAlert(Alert.AlertType.ERROR, "Error", "Database error: " + e.getMessage());
            e.printStackTrace();
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Error", "Invalid course ID.");
            e.printStackTrace();
        } finally {
            closeDatabaseResources();
        }
    }

    @FXML
    public void availableCourseClear() {
        availableCourse_course.setText("");
        availableCourse_description.setText("");
        availableCourse_degree.setText("");
        availableCourse_duration.setText("");

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
                        result.getInt("course_id"),
                        result.getString("course_name"),
                        result.getString("description"),
                        result.getString("degree"),
                        result.getString("duration")
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

        availableCourse_col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        availableCourse_col_course.setCellValueFactory(new PropertyValueFactory<>("course"));
        availableCourse_col_description.setCellValueFactory(new PropertyValueFactory<>("description"));
        availableCourse_col_degree.setCellValueFactory(new PropertyValueFactory<>("degree"));
        availableCourse_col_duration.setCellValueFactory(new PropertyValueFactory<>("duration"));

        availableCourse_tableView.setItems(availableCourseList);

    }

    @FXML
    public void availableCourseSelect() {
        CourseData courseD = availableCourse_tableView.getSelectionModel().getSelectedItem();
        if (courseD != null) {
            availableCourse_id.setText(String.valueOf(courseD.getId()));
            availableCourse_course.setText(courseD.getCourse());
            availableCourse_description.setText(courseD.getDescription());
            availableCourse_degree.setText(courseD.getDegree());
            availableCourse_duration.setText(courseD.getDuration());

        }
    }

//SUBJECT
    private ObservableList<SubjectData> subjectListData;
    private ObservableList<CourseData> courseListData;

    private void loadCourseData() {
        courseListData = FXCollections.observableArrayList();
        String query = "SELECT course_id, course_name FROM course";
        try {
            connect = Database.connectDb();
            prepare = connect.prepareStatement(query);
            result = prepare.executeQuery();

            while (result.next()) {
                CourseData course = new CourseData(
                        result.getInt("course_id"),
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
                return null;
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

        Connection connect = null;
        PreparedStatement prepare = null;
        ResultSet generatedKeys = null;

        try {
            connect = Database.connectDb();

            prepare = connect.prepareStatement(insertSubjectQuery, Statement.RETURN_GENERATED_KEYS);
            prepare.setString(1, subjectName);
            prepare.executeUpdate();

            generatedKeys = prepare.getGeneratedKeys();
            int subjectId = 0;
            if (generatedKeys.next()) {
                subjectId = generatedKeys.getInt(1);
            } else {
                showAlert(Alert.AlertType.ERROR, "Error", "Failed to retrieve subject ID.");
                return;
            }

            prepare = connect.prepareStatement(linkCourseSubjectQuery);
            prepare.setInt(1, selectedCourse.getId());
            prepare.setInt(2, subjectId);
            prepare.executeUpdate();

            showAlert(Alert.AlertType.INFORMATION, "Success", "Subject added to course successfully!");
            subjectShowListData();
            clearSubject();

        } catch (SQLException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Error", "Database error: " + e.getMessage());
        } finally {
            try {
                if (generatedKeys != null) {
                    generatedKeys.close();
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
            String deleteStudentGradesQuery = "DELETE FROM student_grades WHERE course_subject_id IN (SELECT course_subject_id FROM course_subject WHERE subject_id = ?)";
            String deleteCourseSubjectQuery = "DELETE FROM course_subject WHERE subject_id = ?";
            String deleteSubjectQuery = "DELETE FROM subject WHERE subject_id = ?";

            try {
                connect = Database.connectDb();

                // Delete from student_grades table first
                prepare = connect.prepareStatement(deleteStudentGradesQuery);
                prepare.setInt(1, Integer.parseInt(subjectId));
                prepare.executeUpdate();

                // Delete from course_subject table
                prepare = connect.prepareStatement(deleteCourseSubjectQuery);
                prepare.setInt(1, Integer.parseInt(subjectId));
                prepare.executeUpdate();

                // Delete from subject table
                prepare = connect.prepareStatement(deleteSubjectQuery);
                prepare.setInt(1, Integer.parseInt(subjectId));
                prepare.executeUpdate();

                showAlert(Alert.AlertType.INFORMATION, "Success", "Subject deleted successfully!");
                subjectShowListData();
                clearSubject();

            } catch (SQLException e) {
                e.printStackTrace();
                showAlert(Alert.AlertType.ERROR, "Error", "An error occurred while deleting the subject. Please try again.");
            } finally {
                closeDatabaseResources();
            }
        }
    }

    @FXML
    public void handleRowSelectSubject() {
        SubjectData selectedSubject = tableSubjects.getSelectionModel().getSelectedItem();
        if (selectedSubject != null) {
            txtSubjectId.setText(String.valueOf(selectedSubject.getSubjectId()));
            txtSubjectName.setText(selectedSubject.getSubjectName());

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
        subjectListDataWithTeacher = FXCollections.observableArrayList();
        String query = "SELECT s.subject_id, s.subject_name, c.course_name "
                + "FROM subject s "
                + "JOIN course_subject cs ON s.subject_id = cs.subject_id "
                + "JOIN course c ON cs.course_id = c.course_id";
        try (Connection connection = Database.connectDb(); PreparedStatement preparedStatement = connection.prepareStatement(query); ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                SubjectData subject = new SubjectData(
                        resultSet.getInt("subject_id"),
                        resultSet.getString("subject_name"),
                        resultSet.getString("course_name")
                );
                subjectListDataWithTeacher.add(subject);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        return subjectListDataWithTeacher;
    }

    public void subjectShowListData() throws SQLException {
        ObservableList<SubjectData> subjectData = loadSubjectData();
        colSubjectId.setCellValueFactory(new PropertyValueFactory<>("subjectId"));
        colSubjectName.setCellValueFactory(new PropertyValueFactory<>("subjectName"));
        colCourseName.setCellValueFactory(new PropertyValueFactory<>("courseName"));
        tableSubjects.setItems(subjectData);
    }

    //Teacher
    private ObservableList<TeacherData> teacherListData;
    private ObservableList<SubjectData> subjectListDataWithTeacher;

    private void loadSubjectDataWithTeacher() {
        subjectListDataWithTeacher = FXCollections.observableArrayList();
        String query = "SELECT subject_id, subject_name FROM subject";
        try {
            connect = Database.connectDb();
            prepare = connect.prepareStatement(query);
            result = prepare.executeQuery();

            while (result.next()) {
                SubjectData subject = new SubjectData(result.getInt("subject_id"), result.getString("subject_name"));
                subjectListDataWithTeacher.add(subject);
            }
            listViewSubjects.setItems(subjectListDataWithTeacher);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeDatabaseResources();
        }
    }

    private void loadTeachers() {
        teacherListData = FXCollections.observableArrayList();
        String query = "SELECT t.teacher_id, t.teacher_name, t.age, t.gender, t.phone_number, t.email, t.cccd, GROUP_CONCAT(ts.subject_id) as subject_ids "
                + "FROM teacher t "
                + "LEFT JOIN teacher_subject ts ON t.teacher_id = ts.teacher_id "
                + "LEFT JOIN subject s ON ts.subject_id = s.subject_id "
                + "GROUP BY t.teacher_id, t.teacher_name, t.age, t.gender, t.phone_number, t.email, t.cccd";

        try {
            connect = Database.connectDb();
            prepare = connect.prepareStatement(query);
            result = prepare.executeQuery();

            // Create a map for quick lookup of subject names by ID
            Map<Integer, String> subjectMap = new HashMap<>();
            for (SubjectData subject : subjectListDataWithTeacher) {
                subjectMap.put(subject.getSubjectId(), subject.getSubjectName());
            }

            while (result.next()) {
                String subjectIds = result.getString("subject_ids");
                List<String> subjectsList = new ArrayList<>();
                if (subjectIds != null && !subjectIds.isEmpty()) {
                    for (String subjectId : subjectIds.split(",")) {
                        subjectsList.add(subjectMap.get(Integer.parseInt(subjectId)));
                    }
                }

                TeacherData teacher = new TeacherData(result.getInt("teacher_id"),
                        result.getString("teacher_name"),
                        result.getInt("age"),
                        result.getString("gender"),
                        result.getString("phone_number"),
                        result.getString("email"),
                        result.getString("cccd"),
                        subjectsList
                );
                teacherListData.add(teacher);
            }

            colTeacherId.setCellValueFactory(new PropertyValueFactory<>("teacherId"));
            colTeacherName.setCellValueFactory(new PropertyValueFactory<>("teacherName"));
            colTeacherAge.setCellValueFactory(new PropertyValueFactory<>("age"));
            colTeacherGender.setCellValueFactory(new PropertyValueFactory<>("gender"));
            colTeacherPhoneNumber.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
            colTeacherEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
            colTeacherCccd.setCellValueFactory(new PropertyValueFactory<>("cccd"));
            colTeacherSubjects.setCellValueFactory(new PropertyValueFactory<>("subjects"));

            tableViewTeachers.setItems(teacherListData);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeDatabaseResources();
        }
    }

    private List<String> getSubjectNamesByIds(List<Integer> subjectIds) {
        List<String> subjectNames = new ArrayList<>();
        for (Integer subjectId : subjectIds) {
            SubjectData subject = getSubjectById(subjectId);
            if (subject != null) {
                subjectNames.add(subject.getSubjectName());
            }
        }
        return subjectNames;
    }

    private SubjectData getSubjectById(int subjectId) {
        for (SubjectData subject : subjectListDataWithTeacher) {
            if (subject.getSubjectId() == subjectId) {
                return subject;
            }
        }
        return null;
    }

    @FXML
    private void addTeacherButton() throws SQLException {
        String teacherName = txtTeacherName.getText();
        int age = Integer.parseInt(txtTeacherAge.getText());
        String gender = comboTeacherGender.getValue();
        String phoneNumber = txtTeacherPhoneNumber.getText();
        String email = txtTeacherEmail.getText();
        String cccd = txtTeacherCccd.getText();
        List<SubjectData> selectedSubjects = listViewSubjects.getSelectionModel().getSelectedItems();

        if (teacherName.isEmpty() || txtTeacherAge.getText().isEmpty() || gender == null || phoneNumber.isEmpty() || selectedSubjects.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Error", "Please fill all fields and select at least one subject.");
            return;
        }

//        if (isTeacherCccdExists(txtTeacherCccd.getText())) {
//            showAlert(AlertType.ERROR, "Error Message", "Student #" + txtTeacherCccd.getText() + " already exists!");
//            return;
//        }
        String insertTeacherQuery = "INSERT INTO teacher (teacher_name, age, gender, phone_number, email, cccd) VALUES (?, ?, ?, ?, ?, ?)";
        String linkTeacherSubjectQuery = "INSERT INTO teacher_subject (teacher_id, subject_id) VALUES (?, ?)";

        try {
            connect = Database.connectDb();

            prepare = connect.prepareStatement(insertTeacherQuery, Statement.RETURN_GENERATED_KEYS);
            prepare.setString(1, teacherName);
            prepare.setInt(2, age);
            prepare.setString(3, gender);
            prepare.setString(4, phoneNumber);
            prepare.setString(5, email);
            prepare.setString(6, cccd);

            prepare.executeUpdate();

            ResultSet generatedKeys = prepare.getGeneratedKeys();
            int teacherId = 0;
            if (generatedKeys.next()) {
                teacherId = generatedKeys.getInt(1);
            }

            prepare = connect.prepareStatement(linkTeacherSubjectQuery);
            for (SubjectData subject : selectedSubjects) {
                prepare.setInt(1, teacherId);
                prepare.setInt(2, subject.getSubjectId());
                prepare.executeUpdate();
            }

            showAlert(Alert.AlertType.INFORMATION, "Success", "Teacher added successfully!");
            loadTeachers();
            clearFields();

        } catch (SQLException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Error", "Database error: " + e.getMessage());
        } finally {
            closeDatabaseResources();
        }
    }

    private boolean isTeacherCccdExists(String cccd) throws SQLException {
        String query = "SELECT COUNT(*) FROM teacher WHERE cccd = ?";
        try (PreparedStatement ps = connect.prepareStatement(query)) {
            ps.setString(1, cccd);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        }
        return false;
    }

    @FXML
    private void updateTeacherButton() {
        TeacherData selectedTeacher = tableViewTeachers.getSelectionModel().getSelectedItem();
        if (selectedTeacher == null) {
            showAlert(Alert.AlertType.ERROR, "Error", "Please select a teacher to update.");
            return;
        }

        int teacherId = selectedTeacher.getTeacherId();
        String teacherName = txtTeacherName.getText();
        int age = Integer.parseInt(txtTeacherAge.getText());
        String gender = comboTeacherGender.getValue();
        String phoneNumber = txtTeacherPhoneNumber.getText();
        String email = txtTeacherEmail.getText();
        String cccd = txtTeacherCccd.getText();
        List<SubjectData> selectedSubjects = listViewSubjects.getSelectionModel().getSelectedItems();

        if (teacherName.isEmpty() || txtTeacherAge.getText().isEmpty() || gender == null || phoneNumber.isEmpty() || selectedSubjects.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Error", "Please fill all fields and select at least one subject.");
            return;
        }

        String updateTeacherQuery = "UPDATE teacher SET teacher_name = ?, age = ?, gender = ?, phone_number = ?, email = ?, cccd = ? WHERE teacher_id = ?";
        String deleteTeacherSubjectQuery = "DELETE FROM teacher_subject WHERE teacher_id = ?";
        String linkTeacherSubjectQuery = "INSERT INTO teacher_subject (teacher_id, subject_id) VALUES (?, ?)";

        try {
            connect = Database.connectDb();
            prepare = connect.prepareStatement(updateTeacherQuery);
            prepare.setString(1, teacherName);
            prepare.setInt(2, age);
            prepare.setString(3, gender);
            prepare.setString(4, phoneNumber);
            prepare.setString(5, email);
            prepare.setString(6, cccd);
            prepare.setInt(7, teacherId);
            prepare.executeUpdate();

            prepare = connect.prepareStatement(deleteTeacherSubjectQuery);
            prepare.setInt(1, teacherId);
            prepare.executeUpdate();

            prepare = connect.prepareStatement(linkTeacherSubjectQuery);
            for (SubjectData subject : selectedSubjects) {
                prepare.setInt(1, teacherId);
                prepare.setInt(2, subject.getSubjectId());
                prepare.executeUpdate();
            }

            showAlert(Alert.AlertType.INFORMATION, "Success", "Teacher updated successfully!");
            loadTeachers();
            clearFields();

        } catch (SQLException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Error", "Database error: " + e.getMessage());
        } finally {
            closeDatabaseResources();
        }
    }

    @FXML
    private void deleteTeacherButton() {
        TeacherData selectedTeacher = tableViewTeachers.getSelectionModel().getSelectedItem();
        if (selectedTeacher == null) {
            showAlert(Alert.AlertType.ERROR, "Error", "Please select a teacher to delete.");
            return;
        }

        int teacherId = selectedTeacher.getTeacherId();

        Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationAlert.setTitle("Confirmation");
        confirmationAlert.setHeaderText(null);
        confirmationAlert.setContentText("Are you sure you want to delete this teacher?");
        Optional<ButtonType> option = confirmationAlert.showAndWait();

        if (option.isPresent() && option.get() == ButtonType.OK) {
            String deleteTeacherSubjectQuery = "DELETE FROM teacher_subject WHERE teacher_id = ?";
            String deleteTeacherQuery = "DELETE FROM teacher WHERE teacher_id = ?";

            try {
                connect = Database.connectDb();
                prepare = connect.prepareStatement(deleteTeacherSubjectQuery);
                prepare.setInt(1, teacherId);
                prepare.executeUpdate();

                prepare = connect.prepareStatement(deleteTeacherQuery);
                prepare.setInt(1, teacherId);
                prepare.executeUpdate();

                showAlert(Alert.AlertType.INFORMATION, "Success", "Teacher deleted successfully!");
                loadTeachers();
                clearFields();

            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                closeDatabaseResources();
            }
        }
    }

    @FXML
    private void teacherSearch() {
        FilteredList<TeacherData> filteredData = new FilteredList<>(teacherListData, e -> true);
        tableViewTeachers.setItems(filteredData);

        searchTeacherField.textProperty().addListener((observable, oldValue, newValue) -> {
            String searchText = newValue.toLowerCase();
            filteredData.setPredicate(teacherData -> {
                if (searchText == null || searchText.isEmpty()) {
                    return true;
                }
                // Check if any field contains the search text
                return teacherData.getTeacherName().toLowerCase().contains(searchText)
                        || String.valueOf(teacherData.getAge()).contains(searchText)
                        || teacherData.getGender().toLowerCase().contains(searchText)
                        || teacherData.getPhoneNumber().contains(searchText)
                        || teacherData.getEmail().toLowerCase().contains(searchText)
                        || teacherData.getCccd().contains(searchText)
                        || teacherData.getSubjects().toString().toLowerCase().contains(searchText);
            });
        });

        SortedList<TeacherData> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tableViewTeachers.comparatorProperty());
        tableViewTeachers.setItems(sortedData);
    }

    private void clearFields() {
        txtTeacherName.clear();
        txtTeacherAge.clear();
        comboTeacherGender.getSelectionModel().clearSelection();
        txtTeacherPhoneNumber.clear();
        txtTeacherEmail.clear();
        txtTeacherCccd.clear();
        listViewSubjects.getSelectionModel().clearSelection();
    }

    @FXML
    private void handleRowSelectTeacher(MouseEvent event) {
        if (event.getClickCount() == 1) { // Check if it's a single click
            TeacherData selectedTeacher = tableViewTeachers.getSelectionModel().getSelectedItem();
            if (selectedTeacher != null) {
                txtTeacherName.setText(selectedTeacher.getTeacherName());
                txtTeacherAge.setText(String.valueOf(selectedTeacher.getAge()));
                comboTeacherGender.setValue(selectedTeacher.getGender());
                txtTeacherPhoneNumber.setText(selectedTeacher.getPhoneNumber());
                txtTeacherEmail.setText(selectedTeacher.getEmail());
                txtTeacherCccd.setText(selectedTeacher.getCccd());

                // Clear the selected subjects in the subject list view
                listViewSubjects.getSelectionModel().clearSelection();

                // Select the subjects corresponding to the teacher
                for (String subjectName : selectedTeacher.getSubjects()) {
                    for (SubjectData subject : subjectListDataWithTeacher) {
                        if (subject.getSubjectName().equals(subjectName)) {
                            listViewSubjects.getSelectionModel().select(subject);
                            break;
                        }
                    }
                }
            }
        }
    }

    //SRO
    @FXML
    public void SroAdd() throws SQLException {
        String insertSroData = "INSERT INTO sro " + "(sro_name, age, gender, phone_number, email, cccd)" + "VALUES(?, ?, ?, ?, ?, ?)";
        connect = Database.connectDb();

        try {

            if (txtSroName.getText().isEmpty()
                    || txtSroEmail.getText().isEmpty()
                    || txtSroAge.getText().isEmpty()
                    || listSroGender.getSelectionModel().getSelectedItem() == null
                    || txtSroPhone.getText().isEmpty()
                    || txtSroPeopleID.getText().isEmpty()) {
                showAlert(AlertType.ERROR, "Error Message", "Please fill all blank fields");
            } else {

                String checkData = "SELECT * From sro WHERE cccd = '" + txtSroPeopleID.getText() + "'";

                statement = connect.createStatement();
                result = statement.executeQuery(checkData);

                if (result.next()) {
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("SRO was already exist!!!");
                    alert.showAndWait();
                } else {
                    prepare = connect.prepareStatement(insertSroData, Statement.RETURN_GENERATED_KEYS);
                    String sroAge = txtSroAge.getText();
                    int age = Integer.parseInt(sroAge);
                    prepare.setString(1, txtSroName.getText());
                    prepare.setInt(2, age);
                    prepare.setString(3, listSroGender.getValue());
                    prepare.setString(4, txtSroPhone.getText());
                    prepare.setString(5, txtSroEmail.getText());
                    prepare.setString(6, txtSroPeopleID.getText());
                    prepare.executeUpdate();

                    showAlert(AlertType.INFORMATION, "Information Message", "Successfully Added!");
                    // TO UPDATE THE TABLEVIEW
                    SroShowListData();
                    // TO CLEAR THE TEXT FIELDS
                    SroClear();
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
            showAlert(AlertType.ERROR, "Error Message", "An error occurred while adding SRO!");
        } finally {
            closeDatabaseResources();
        }

    }

    private ObservableList<SroData> SroListD;

    public ObservableList<SroData> SroListData() {
        SroListD = FXCollections.observableArrayList();

        try (Connection connection = Database.connectDb(); PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM sro"); ResultSet rs = preparedStatement.executeQuery()) {
            while (rs.next()) {
                SroData sroD = new SroData(
                        rs.getInt("sro_id"),
                        rs.getString("sro_name"),
                        rs.getInt("age"),
                        rs.getString("gender"),
                        rs.getString("phone_number"),
                        rs.getString("email"),
                        rs.getString("cccd")
                );

                SroListD.add(sroD);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return SroListD;
    }

    public void SroShowListData() {
        SroListD = SroListData();

        colSroID.setCellValueFactory(new PropertyValueFactory<>("sro_id"));
        colSroName.setCellValueFactory(new PropertyValueFactory<>("sro_name"));
        colSroAge.setCellValueFactory(new PropertyValueFactory<>("age"));
        colSroGender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        colSroPhone.setCellValueFactory(new PropertyValueFactory<>("phone_number"));
        colSroEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colSroPeopleID.setCellValueFactory(new PropertyValueFactory<>("cccd"));

        Sro_tableView.setItems(SroListD);
    }

    @FXML
    public void SroClear() {
        txtSroName.setText("");
        txtSroAge.setText("");
        txtSroEmail.setText("");
        txtSroPhone.setText("");
        listSroGender.getSelectionModel().clearSelection();
        txtSroPeopleID.setText("");
    }

    @FXML
    public void SroUpdate() throws SQLException {

        connect = Database.connectDb();
        try {
            Alert alert;
            if (txtSroName.getText().isEmpty()
                    || txtSroAge.getText().isEmpty()
                    || txtSroEmail.getText().isEmpty()
                    || txtSroPhone.getText().isEmpty()
                    || listSroGender.getSelectionModel().isEmpty()
                    || txtSroPeopleID.getText().isEmpty()) {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
            } else {
                alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure want to update?");
                Optional<ButtonType> option = alert.showAndWait();

                if (option.get().equals(ButtonType.OK)) {
                    String updateSroData = "UPDATE sro SET sro_name = ?, age = ?, gender = ?, phone_number = ?, email = ?, cccd = ? WHERE sro_id = '" + txtSroID.getText() + "'";
                    String sroAge = txtSroAge.getText();
                    int age = Integer.parseInt(sroAge);

                    prepare = connect.prepareStatement(updateSroData);
                    prepare.setString(1, txtSroName.getText());
                    prepare.setInt(2, age);
                    prepare.setString(3, (String) listSroGender.getSelectionModel().getSelectedItem());
                    prepare.setString(4, txtSroPhone.getText());
                    prepare.setString(5, txtSroEmail.getText());
                    prepare.setString(6, txtSroPeopleID.getText());

                    prepare.executeUpdate();
                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Updated!");
                    alert.showAndWait();

                    SroShowListData();
                    SroClear();

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (result != null) {
                result.close();
            }
            if (prepare != null) {
                prepare.close();
            }
            if (connect != null) {
                connect.close();
            }
        }
    }

    @FXML
    public void SroDelete() throws SQLException {
        String deleteSroData = "DELETE FROM sro WHERE sro_id = '" + txtSroID.getText() + "'";
        connect = Database.connectDb();

        try {
            Alert alert;
            if (txtSroName.getText().isEmpty()
                    || txtSroEmail.getText().isEmpty()
                    || txtSroAge.getText().isEmpty()
                    || listSroGender.getSelectionModel().getSelectedItem() == null
                    || txtSroPhone.getText().isEmpty()
                    || txtSroPeopleID.getText().isEmpty()) {
                showAlert(AlertType.ERROR, "Error Message", "Please fill all blank fields");
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
            } else {
                alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure want to delete ?");
                Optional<ButtonType> option = alert.showAndWait();

                if (option.get().equals(ButtonType.OK)) {
                    statement = connect.createStatement();
                    statement.executeUpdate(deleteSroData);
                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Deleted!");
                    alert.showAndWait();

                    // TO UPDATE THE TABLEVIEW
                    SroShowListData();
                    // TO CLEAR THE FIELDS
                    SroClear();
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void SroSelect() {
        SroData sroD = Sro_tableView.getSelectionModel().getSelectedItem();
        if (sroD != null) {
            txtSroID.setText(String.valueOf(sroD.getSro_id()));
            txtSroName.setText(String.valueOf(sroD.getSro_name()));
            txtSroEmail.setText(String.valueOf(sroD.getEmail()));
            txtSroAge.setText(String.valueOf(sroD.getAge()));
            txtSroPhone.setText(String.valueOf(sroD.getPhone_number()));
            txtSroPeopleID.setText(String.valueOf(sroD.getCccd()));
        }
    }

    @FXML
    public void handleRowSro() {
        SroData sroSubject = Sro_tableView.getSelectionModel().getSelectedItem();
        if (sroSubject != null) {
            txtSroName.setText(String.valueOf(sroSubject.getSro_name()));
            txtSroEmail.setText(String.valueOf(sroSubject.getEmail()));
            txtSroAge.setText(String.valueOf(sroSubject.getAge()));
            txtSroPhone.setText(String.valueOf(sroSubject.getPhone_number()));
            txtSroPeopleID.setText(String.valueOf(sroSubject.getCccd()));
        }
    }

    @FXML
    public void SroSearch() {
        FilteredList<SroData> filter = new FilteredList<>(SroListD, e -> true);
        Sro_Search.textProperty().addListener((Observable, oldValue, newValue) -> {
            filter.setPredicate(predicateSroData -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String searchKey = newValue.toLowerCase();
                if (predicateSroData.getSro_name().contains(searchKey)) {
                    return true;
                } else if (predicateSroData.getEmail().contains(searchKey)) {
                    return true;
                } else if (predicateSroData.getGender().contains(searchKey)) {
                    return true;
                } else if (predicateSroData.getGender().contains(searchKey)) {
                    return true;
                } else if (predicateSroData.getPhone_number().contains(searchKey)) {
                    return true;
                } else {
                    return predicateSroData.getCccd().contains(searchKey);
                }
            });
        });

        SortedList<SroData> sortList = new SortedList<>(filter);

        sortList.comparatorProperty().bind(Sro_tableView.comparatorProperty());
        Sro_tableView.setItems(sortList);
    }
    //Class
    //Load classes form database

    private void loadClasses() {
        classListData = FXCollections.observableArrayList();
        String query = "SELECT c.class_id, c.class_name, cr.course_name, t.teacher_name, s.sro_name "
                + "FROM class c "
                + "JOIN course cr ON c.course_id = cr.course_id "
                + "LEFT JOIN teacher t ON c.teacher_id = t.teacher_id "
                + "LEFT JOIN sro s ON c.sro_id = s.sro_id";

        try {
            connect = Database.connectDb();
            prepare = connect.prepareStatement(query);
            result = prepare.executeQuery();

            while (result.next()) {
                ClassData classData = new ClassData(
                        result.getInt("class_id"),
                        result.getString("class_name"),
                        result.getString("course_name"),
                        result.getString("teacher_name"),
                        result.getString("sro_name")
                );
                classListData.add(classData);
            }

            colClassId.setCellValueFactory(new PropertyValueFactory<>("classId"));
            colClassName.setCellValueFactory(new PropertyValueFactory<>("className"));
            colCourseNameClass.setCellValueFactory(new PropertyValueFactory<>("courseName"));
            colTeacherNameClass.setCellValueFactory(new PropertyValueFactory<>("teacherName"));
            colSroNameClass.setCellValueFactory(new PropertyValueFactory<>("sroName"));

            tableViewClasses.setItems(classListData);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeDatabaseResources();
        }
    }

    @FXML
    private void addClassButton() {
        String className = txtClassName.getText();
        String courseName = comboCourseName.getValue();
        String teacherName = comboTeacherName.getValue();
        String sroName = comboSroName.getValue();

        if (className.isEmpty() || courseName == null || teacherName == null || sroName == null) {
            showAlert(Alert.AlertType.ERROR, "Error", "Please fill all fields.");
            return;
        }

        int courseId = getIdFromName("course", "course_name", "course_id", courseName);
        int teacherId = getIdFromName("teacher", "teacher_name", "teacher_id", teacherName);
        int sroId = getIdFromName("sro", "sro_name", "sro_id", sroName);

        if (courseId == -1 || teacherId == -1 || sroId == -1) {
            showAlert(Alert.AlertType.ERROR, "Error", "Invalid selection.");
            return;
        }

        String insertClassQuery = "INSERT INTO class (class_name, course_id, teacher_id, sro_id) VALUES (?, ?, ?, ?)";

        try {
            connect = Database.connectDb();
            prepare = connect.prepareStatement(insertClassQuery);
            prepare.setString(1, className);
            prepare.setInt(2, courseId);
            prepare.setInt(3, teacherId);
            prepare.setInt(4, sroId);

            prepare.executeUpdate();

            showAlert(Alert.AlertType.INFORMATION, "Success", "Class added successfully!");
            loadClasses();
            clearFields();
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Error", "Database error: " + e.getMessage());
        } finally {
            closeDatabaseResources();
        }
    }

    @FXML
    private void updateClassButton() {
        ClassData selectedClass = tableViewClasses.getSelectionModel().getSelectedItem();
        if (selectedClass == null) {
            showAlert(Alert.AlertType.ERROR, "Error", "Please select a class to update.");
            return;
        }

        int classId = selectedClass.getClassId();
        String className = txtClassName.getText();
        String courseName = comboCourseName.getValue();
        String teacherName = comboTeacherName.getValue();
        String sroName = comboSroName.getValue();

        if (className.isEmpty() || courseName == null || teacherName == null || sroName == null) {
            showAlert(Alert.AlertType.ERROR, "Error", "Please fill all fields.");
            return;
        }

        int courseId = getIdFromName("course", "course_name", "course_id", courseName);
        int teacherId = getIdFromName("teacher", "teacher_name", "teacher_id", teacherName);
        int sroId = getIdFromName("sro", "sro_name", "sro_id", sroName);

        if (courseId == -1 || teacherId == -1 || sroId == -1) {
            showAlert(Alert.AlertType.ERROR, "Error", "Invalid selection.");
            return;
        }

        String updateClassQuery = "UPDATE class SET class_name = ?, course_id = ?, teacher_id = ?, sro_id = ? WHERE class_id = ?";

        try {
            connect = Database.connectDb();
            prepare = connect.prepareStatement(updateClassQuery);
            prepare.setString(1, className);
            prepare.setInt(2, courseId);
            prepare.setInt(3, teacherId);
            prepare.setInt(4, sroId);
            prepare.setInt(5, classId);

            prepare.executeUpdate();

            showAlert(Alert.AlertType.INFORMATION, "Success", "Class updated successfully!");
            loadClasses();
            clearFields();
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Error", "Database error: " + e.getMessage());
        } finally {
            closeDatabaseResources();
        }
    }

    @FXML
    private void deleteClassButton() {
        ClassData selectedClass = tableViewClasses.getSelectionModel().getSelectedItem();
        if (selectedClass == null) {
            showAlert(Alert.AlertType.ERROR, "Error", "Please select a class to delete.");
            return;
        }

        int classId = selectedClass.getClassId();

        Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationAlert.setTitle("Confirmation");
        confirmationAlert.setHeaderText(null);
        confirmationAlert.setContentText("Are you sure you want to delete this class?");
        Optional<ButtonType> option = confirmationAlert.showAndWait();

        if (option.isPresent() && option.get() == ButtonType.OK) {
            String deleteClassQuery = "DELETE FROM class WHERE class_id = ?";

            try {
                connect = Database.connectDb();
                prepare = connect.prepareStatement(deleteClassQuery);
                prepare.setInt(1, classId);
                prepare.executeUpdate();

                showAlert(Alert.AlertType.INFORMATION, "Success", "Class deleted successfully!");
                loadClasses();
                clearFields();
            } catch (SQLException e) {
                e.printStackTrace();
                showAlert(Alert.AlertType.ERROR, "Error", "Database error: " + e.getMessage());
            } finally {
                closeDatabaseResources();
            }
        }
    }

    //Load data into ComboBox
    private void loadComboBoxes() {
        ObservableList<String> courseOptions = FXCollections.observableArrayList();
        ObservableList<String> teacherOptions = FXCollections.observableArrayList();
        ObservableList<String> sroOptions = FXCollections.observableArrayList();

        // Load course names
        String courseQuery = "SELECT course_name FROM course";
        try {
            connect = Database.connectDb();
            prepare = connect.prepareStatement(courseQuery);
            result = prepare.executeQuery();

            while (result.next()) {
                courseOptions.add(result.getString("course_name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Load teacher names
        String teacherQuery = "SELECT teacher_name FROM teacher";
        try {
            prepare = connect.prepareStatement(teacherQuery);
            result = prepare.executeQuery();

            while (result.next()) {
                teacherOptions.add(result.getString("teacher_name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Load SRO names
        String sroQuery = "SELECT sro_name FROM sro";
        try {
            prepare = connect.prepareStatement(sroQuery);
            result = prepare.executeQuery();

            while (result.next()) {
                sroOptions.add(result.getString("sro_name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        comboCourseName.setItems(courseOptions);
        comboTeacherName.setItems(teacherOptions);
        comboSroName.setItems(sroOptions);
    }

    private int getIdFromName(String tableName, String columnName, String idColumnName, String name) {
        String query = "SELECT " + idColumnName + " FROM " + tableName + " WHERE " + columnName + " = ?";
        try {
            connect = Database.connectDb();
            prepare = connect.prepareStatement(query);
            prepare.setString(1, name);
            result = prepare.executeQuery();

            if (result.next()) {
                return result.getInt(idColumnName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeDatabaseResources();
        }
        return -1; // Return -1 if the ID is not found
    }

    @FXML
    private void handleClearActionClass() {
        txtClassName.clear();
        comboCourseName.getSelectionModel().clearSelection();
        comboTeacherName.getSelectionModel().clearSelection();
        comboSroName.getSelectionModel().clearSelection();
    }

    @FXML
    private void handleRowSelecterClass() {
        tableViewClasses.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<ClassData>() {
            @Override
            public void changed(ObservableValue<? extends ClassData> observable, ClassData oldValue, ClassData newValue) {
                if (newValue != null) {
                    txtClassName.setText(newValue.getClassName());
                    comboCourseName.setValue(newValue.getCourseName());
                    comboTeacherName.setValue(newValue.getTeacherName());
                    comboSroName.setValue(newValue.getSroName());
                }
            }
        });
    }

    @FXML
    private void searchClass() {
        FilteredList<ClassData> filteredData = new FilteredList<>(classListData, e -> true);
        tableViewClasses.setItems(filteredData);

        searchClassField.textProperty().addListener((observable, oldValue, newValue) -> {
            String searchText = newValue.toLowerCase();
            filteredData.setPredicate(classData -> {
                if (searchText == null || searchText.isEmpty()) {
                    return true;
                }
                return classData.getClassName().toLowerCase().contains(searchText)
                        || classData.getCourseName().toLowerCase().contains(searchText)
                        || classData.getTeacherName().toLowerCase().contains(searchText)
                        || classData.getSroName().toLowerCase().contains(searchText);
            });
        });
    }

    //Student_grade
    private ObservableList<StudentGrade> studentGrades;
    private final ObservableList<StudentData> studentList = FXCollections.observableArrayList();
    private final ObservableList<String> subjects = FXCollections.observableArrayList();

    @FXML
    private void setTableGradeColumn() {
        studentGrades = FXCollections.observableArrayList();

        gradeIdColumn.setCellValueFactory(new PropertyValueFactory<>("gradeId"));
        studentNameColumn.setCellValueFactory(new PropertyValueFactory<>("studentName"));
        subjectColumn.setCellValueFactory(new PropertyValueFactory<>("subject"));
        scoreColumn.setCellValueFactory(new PropertyValueFactory<>("score"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));

        studentGradesTable.setItems(studentGrades);
    }
    
     @FXML
    public void handleRowSelectStudentGrade(StudentGrade selectedGrade) {
        if (selectedGrade != null) {
            studentNumField.setText(selectedGrade.getStudentName());
            subjectComboBox.setValue(selectedGrade.getSubject());
            scoreField.setText(String.valueOf(selectedGrade.getScore()));
        }
    }
    
    
    @FXML
    private void loadGrades() {
        String studentNum = studentNumField.getText();
        String selectedSubject = subjectComboBox.getSelectionModel().getSelectedItem();

        if (studentNum == null || selectedSubject == null) {
            showAlert(Alert.AlertType.WARNING, "Warning", "Please enter student number and select subject!");
            return;
        }

        studentGrades.clear();

        String query = "SELECT sg.grade_id, CONCAT(s.firstName, ' ', s.lastName) AS student_name, su.subject_name, sg.score, sg.status "
                + "FROM student_grades sg "
                + "JOIN student s ON sg.student_id = s.student_id "
                + "JOIN course_subject cs ON sg.course_subject_id = cs.course_subject_id "
                + "JOIN subject su ON cs.subject_id = su.subject_id "
                + "WHERE s.studentNum = ? AND su.subject_name = ?";

        try (Connection connection = Database.connectDb(); PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, studentNum);
            preparedStatement.setString(2, selectedSubject);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int gradeId = resultSet.getInt("grade_id");
                String studentName = resultSet.getString("student_name");
                String subjectName = resultSet.getString("subject_name");
                double score = resultSet.getDouble("score");
                String status = resultSet.getString("status");

                studentGrades.add(new StudentGrade(gradeId, studentName, subjectName, score, status));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeDatabaseResources();
        }
    }

    private void loadStudentGrades() {
        studentGrades.clear();
        try (Connection conn = Database.connectDb(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(
                "SELECT sg.grade_id, CONCAT(st.firstName, ' ', st.lastName) AS student_name, s.subject_name, sg.score, sg.status "
                + "FROM student_grades sg "
                + "JOIN course_subject cs ON sg.course_subject_id = cs.course_subject_id "
                + "JOIN subject s ON cs.subject_id = s.subject_id "
                + "JOIN student st ON sg.student_id = st.student_id")) {

            while (rs.next()) {
                int gradeId = rs.getInt("grade_id");
                String studentName = rs.getString("student_name");
                String subject = rs.getString("subject_name");
                double score = rs.getDouble("score");
                String status = rs.getString("status");

                studentGrades.add(new StudentGrade(gradeId, studentName, subject, score, status));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeDatabaseResources();
        }
    }

    private void loadSubjects() {

        try (Connection connection = Database.connectDb(); Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery("SELECT subject_name FROM subject")) {

            while (resultSet.next()) {
                subjects.add(resultSet.getString("subject_name"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        subjectComboBox.setItems(subjects);
    }
    
    
     
    @FXML
    private void updateGrade() {
        StudentGrade selectedGrade = studentGradesTable.getSelectionModel().getSelectedItem();
        if (selectedGrade == null) {
            showAlert(Alert.AlertType.WARNING, "Warning", "Please select a grade to update!");
            return;
        }

        String newScoreStr = scoreField.getText();
        if (newScoreStr == null || newScoreStr.isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Warning", "Please enter a new score!");
            return;
        }

        try {
            double newScore = Double.parseDouble(newScoreStr);
            String newStatus = calculateStatus(newScore);

            try (Connection connection = Database.connectDb(); PreparedStatement preparedStatement = connection.prepareStatement(
                    "UPDATE student_grades SET score = ?, status = ? WHERE grade_id = ?")) {

                preparedStatement.setDouble(1, newScore);
                preparedStatement.setString(2, newStatus);
                preparedStatement.setInt(3, selectedGrade.getGradeId());
                int rowsUpdated = preparedStatement.executeUpdate();

                if (rowsUpdated > 0) {
                    selectedGrade.setScore(newScore);
                    selectedGrade.setStatus(newStatus);
                    studentGradesTable.refresh();
                    showAlert(Alert.AlertType.INFORMATION, "Success", "Grade updated successfully!");
                }
            } catch (SQLException e) {
                e.printStackTrace();
                showAlert(Alert.AlertType.ERROR, "Error", "Failed to update grade. Please try again.");
            }
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.WARNING, "Warning", "Invalid score format. Please enter a valid number.");
        } finally {
            closeDatabaseResources();
        }
    }

    private void addInitialStudentGrades(int studentId, int courseId) throws SQLException {
        // Retrieve subjects related to the given courseId
        List<String> subjects = getSubjectsByCourseId(courseId);

        // Prepare initial score and status
        double initialScore = 0.0;
        String status = calculateStatus(initialScore);

        // Insert initial grades for each subject
        for (String subject : subjects) {
            int courseSubjectId = getCourseSubjectIdBySubjectNameAndCourseId(subject, courseId);
            if (courseSubjectId != -1) {
                insertStudentGrade(studentId, courseSubjectId, initialScore, status);
            } else {
                System.out.println("No course subject found for subject: " + subject);
            }
        }
    }

    /// Helper method to retrieve subjects related to a courseId
    private List<String> getSubjectsByCourseId(int courseId) throws SQLException {
        List<String> subjects = new ArrayList<>();
        String query = "SELECT s.subject_name FROM subject s JOIN course_subject cs ON s.subject_id = cs.subject_id WHERE cs.course_id = ?";
        try (PreparedStatement ps = connect.prepareStatement(query)) {
            ps.setInt(1, courseId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                subjects.add(rs.getString("subject_name"));
            }
        }
        return subjects;
    }
    // Helper method to retrieve the course_subject_id for a given subject name and courseId

    private int getCourseSubjectIdBySubjectNameAndCourseId(String subjectName, int courseId) throws SQLException {
        String query = "SELECT course_subject_id FROM course_subject WHERE subject_id = (SELECT subject_id FROM subject WHERE subject_name = ?) AND course_id = ?";
        try (PreparedStatement ps = connect.prepareStatement(query)) {
            ps.setString(1, subjectName);
            ps.setInt(2, courseId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("course_subject_id");
            }
        }
        return -1;
    }

    // Helper method to insert a student grade into the database
    private void insertStudentGrade(int studentId, int courseSubjectId, double score, String status) throws SQLException {
        String sql = "INSERT INTO student_grades (student_id, course_subject_id, score, status) VALUES (?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connect.prepareStatement(sql)) {
            preparedStatement.setInt(1, studentId);
            preparedStatement.setInt(2, courseSubjectId);
            preparedStatement.setDouble(3, score);
            preparedStatement.setString(4, status);
            preparedStatement.executeUpdate();
        }
    }

// Helper method to calculate status based on score
    private String calculateStatus(double score) {
        if (score >= 85) {
            return "DISTINCTION";
        } else if (score >= 75) {
            return "CREDIT";
        } else if (score >= 50) {
            return "PASS";
        } else {
            return "RE-EXAM";
        }
    }

    @FXML
    public void exportToExcel(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save as Excel");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Excel Files", "*.xlsx"));
        File file = fileChooser.showSaveDialog(studentGradesTable.getScene().getWindow());

        if (file != null) {
            CSVExporter.exportToCSV(studentGrades, file);
        }
    }

//Load  Gender
    ObservableList<String> genderOptions = FXCollections.observableArrayList("MALE", "FEMALE", "OTHER");

    @FXML
    private void loadGenders() {
        comboTeacherGender.setItems(genderOptions);
        addStudents_gender.setItems(genderOptions);
        listSroGender.setItems(genderOptions);
    }

    //Show Alert
    private void showAlert(AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    //closeDatabase
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

//          TO BECOME UPDATED ONCE YOU CLICK THE ADD STUDENTS BUTTON ON NAV
            addStudentsShowListData();
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

            setTableGradeColumn();

            loadStudentGrades();

        } else if (event.getSource() == teacher_btn) {
            home_form.setVisible(false);
            addStudents_form.setVisible(false);
            availableCourse_form.setVisible(false);
            studentGrade_form.setVisible(false);
            teacher_form.setVisible(true);
            Class_form.setVisible(false);
            Sro_form.setVisible(false);
            Subject_form.setVisible(false);

            studentGrade_btn.setStyle("-fx-background-color:transparent");
            addStudents_btn.setStyle("-fx-background-color:transparent");
            availableCourse_btn.setStyle("-fx-background-color:transparent");
            home_btn.setStyle("-fx-background-color:transparent");
            teacher_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #3f82ae, #26bf7d);");
            Sro_btn.setStyle("-fx-background-color:transparent");
            Class_btn.setStyle("-fx-background-color:transparent");
            Subject_btn.setStyle("-fx-background-color:transparent");

//            setupSubjectComboBox();
            loadGenders();
            loadSubjectDataWithTeacher();
//            teacherSearch();
            loadTeachers();

        } else if (event.getSource() == Class_btn) {
            home_form.setVisible(false);
            addStudents_form.setVisible(false);
            availableCourse_form.setVisible(false);
            studentGrade_form.setVisible(false);
            teacher_form.setVisible(false);
            Class_form.setVisible(true);
            Sro_form.setVisible(false);
            Subject_form.setVisible(false);

            studentGrade_btn.setStyle("-fx-background-color:transparent");
            addStudents_btn.setStyle("-fx-background-color:transparent");
            availableCourse_btn.setStyle("-fx-background-color:transparent");
            home_btn.setStyle("-fx-background-color:transparent");
            teacher_btn.setStyle("-fx-background-color:transparent");
            Sro_btn.setStyle("-fx-background-color:transparent");
            Class_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #3f82ae, #26bf7d);");
            Subject_btn.setStyle("-fx-background-color:transparent");

            loadClasses();
            loadComboBoxes();
            searchClass();

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
            //student
            addStudentsShowListData();
            populateComboBoxes();
            loadStatus();
            //teacher
            loadSubjectDataWithTeacher();
            loadGenders();
            loadTeachers();
            teacherSearch();
            //SRO
            SroShowListData();
            SroSearch();
            //class
            loadClasses();
            loadComboBoxes();
            searchClass();

            //Student_grade
            setTableGradeColumn();
            loadSubjects();
            loadStudentGrades();

        } catch (SQLException ex) {
            Logger.getLogger(DashboardController.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

    }

}
