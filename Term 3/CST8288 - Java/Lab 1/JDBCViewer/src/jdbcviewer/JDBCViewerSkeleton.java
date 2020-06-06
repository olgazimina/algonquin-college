package jdbcviewer;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Olga Zimina
 * @version 1.0
 * @since June 5, 2020
 */
public class JDBCViewerSkeleton extends Application {

	/**
	 * width of the scene
	 */
	private static final double   WIDTH                   = 600;
	/**
	 * height of the scene
	 */
	private static final double   HEIGHT                  = 400;
	/**
	 * title of the application
	 */
	private static final String   TITLE                   = "JDBC Viewer";
	/**
	 * URL path to database
	 */
	private static final String   DB_URL                  = "jdbc:mysql://localhost:3306/redditreader";
	/**
	 * SQL search script for getting all
	 */
	private static final String   SQL_SCRIPT_SELECT_ALL   = "SELECT * FROM redditreader.account";
	/**
	 * SQL search script for getting all with condition
	 */
	private static final String   SQL_SCRIPT_SELECT_WHERE = "SELECT * FROM redditreader.account where nickname like ? or username like ?";
	/**
	 * names of the columns
	 */
	private static final String[] COLUMN_NAMES            = {"ID", "Nickname", "Username", " Password"};
	/**
	 * username used in the DB
	 */
	private static final String   USERNAME                = "cst8288";
	/**
	 * password used in the DB
	 */
	private static final String   PASSWORD                = "8288";

	/**
	 * {@link BorderPane} is a layout manager that manages all nodes in 5 areas as below:
	 *
	 * <pre>
	 * -----------------------
	 * |        top          |
	 * -----------------------
	 * |    |          |     |
	 * |left|  center  |right|
	 * |    |          |     |
	 * -----------------------
	 * |       bottom        |
	 * -----------------------
	 * </pre>
	 * <p>
	 * this object is passed to {@link Scene} object in {@link JDBCViewerSkeleton#start(Stage)} method.
	 */
	private BorderPane root;

	private Connection connection;
	private Label      connectionStatus;
	private WebView    webView;

	/**
	 * this method is called at the very beginning of the JavaFX application and can be used to initialize
	 * all components in the application. however, {@link Scene} and {@link Stage} must not be created in
	 * this method. this method does not run JavaFX thread, it runs on JavaFX-Launcher thread.
	 */
	@Override
	public void init() throws Exception {
		root = new BorderPane();
		root.setTop(createOptionsBar());
		root.setBottom(createStatusBar());
	}

	/**
	 * <p>
	 * this method is called when JavaFX application is started and it is running on JavaFX thread.
	 * this method must at least create {@link Scene} and finish customizing {@link Stage}. these two
	 * objects must be on JavaFX thread when created.
	 * </p>
	 * <p>
	 * {@link Stage} represents the frame of your application, such as minimize, maximize and close buttons.<br>
	 * {@link Scene} represents the holder of all your JavaFX {@link Node}s.<br>
	 * {@link Node} is the super class of every javaFX class.
	 * </p>
	 *
	 * @param primaryStage - primary stage of your application that will be rendered
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		// scene holds all JavaFX components that need to be displayed in Stage
		Scene scene = new Scene(root, WIDTH, HEIGHT);
		primaryStage.setScene(scene);
		primaryStage.setTitle(TITLE);
		primaryStage.setResizable(true);
		// when escape key is pressed close the application
		primaryStage.addEventHandler(KeyEvent.KEY_RELEASED, (KeyEvent event) -> {
			if (KeyCode.ESCAPE == event.getCode()) {
				primaryStage.hide();
			}
		});
		//WebView must be created in start method because it must be initialized on JavaFX thread
		webView = new WebView();
		root.setCenter(webView);
		// display the JavaFX application
		primaryStage.show();
	}

	/**
	 * this method is called at the very end when the application is about to exit.
	 * this method is used to stop or release any resources used during the application.
	 */
	@Override
	public void stop() throws Exception {
	}

	/**
	 * create a {@link ToolBar} that will represent the status bar of the application.
	 *
	 * @return customized {@link ToolBar} as its super class {@link Region}.
	 */
	private Region createStatusBar() {
		this.connectionStatus = new Label("Not Connected");
		return new ToolBar(connectionStatus);
	}

	private Button createButton(String name, EventHandler<ActionEvent> onClick) {
		Button button = new Button(name);
		//we do this so we can stretch the button size otherwise we cannot.
		button.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		button.setOnAction(onClick);
		return button;
	}

	private TextField createTextField(String value, String prompt) {
		TextField textField = new TextField();
		textField.setText(value);
		textField.setPromptText(prompt);
		//we do this to allow the textField to be maximizable horizontally so we can stretch the application.
		GridPane.setHgrow(textField, Priority.ALWAYS);
		return textField;
	}

	private PasswordField createPasswordField(String value, String prompt) {
		PasswordField passwordField = new PasswordField();
		passwordField.setText(value);
		passwordField.setPromptText(prompt);
		GridPane.setHgrow(passwordField, Priority.ALWAYS);
		return passwordField;
	}

	/**
	 * create a {@link MenuBar} that represent the menu bar at the top of the application.
	 *
	 * @return customized {@link MenuBar} as its super class {@link Region}.
	 */
	private Region createOptionsBar() {

		//for the following instructions use the three methods above
		TextField urlText = this.createTextField(DB_URL, "DB URL");
		TextField userText = this.createTextField(USERNAME, "Username");
		TextField searchText = this.createTextField("", "Search Text");
		TextField passText = this.createPasswordField(PASSWORD, "Password");

		//a lambda can look like below:
		//(String a, String b)->{System.out.println(a+b);}
		//lambdas can also be simplified using the rules below:
		//rule 1: if you only have one line of code in your lambda there is no need for {}
		//(String a, String b)->System.out.println(a+b)
		//rule 2: the type of arguments can be inferred by complier, so it can be removed.
		//(a, b)->System.out.println(a+b)
		//rule 3: if you only have one argument you can skip () and also use rule 2.
		//a->System.out.println(a)
		//rule 4: if you only have one line of code, the return statement is implied.
		//(a,b)->a+b;
		//rule 5: all lambdas are objects, they can be stored in any interface with only one method 
		//which have the same number of arguments and return type.
		//EventHandler< ActionEvent> onClick = e -> System.out.println("button clicked");
		//a list of already built-in one function interfaces called functional interface can be found in:
		//https://docs.oracle.com/javase/8/docs/api/java/util/function/package-summary.html
		//use example of rule 5. in this lambda we want to change the vale of conectionStatus and call connectTo.
		//before calling connectTo, setText on conectionStatus pass to it "connecting".
		//call connectTo and pass the values in the 3 TextFields you created. use getText on TextFeield to get the text out of it.
		//after calling connectTo, setText on conectionStatus pass to it "connecting".
		//if exception is thrown, setText on conectionStatus pass to it "failed: " + ex.getMessage().
		//don't forget connectTo throws an exception, so use try and catch.
		Button connectButton = createButton("Connect", event -> {
			this.connectionStatus.setText("Connecting...");
			try {
				this.connectTo(urlText.getText(), userText.getText(), passText.getText());
				this.connectionStatus.setText("DB connected");
			} catch (SQLException e) {
				this.connectionStatus.setText("Connection failed: " + e.getMessage());
			}
		});
		//use example of rule 5. in this lambda we want to change the vale of conectionStatus and call search and populateTextArea.
		//before calling search, setText on conectionStatus pass to it "searching".
		//call search and pass searchText.getText().trim(). store the result in a variable called list.
		//after calling connectTo, if list is null set text of conectionStatus to "must connect first" otherwise call populateTextArea( list)
		//after the if condition, ssetText on conectionStatus and pass to it "finished".
		//if exception is thrown, setText on conectionStatus pass to it "failed: " + ex.getMessage().
		//don't forget search throws an exception, so use try and catch.
		Button searchButton = createButton("Search", event -> {
			this.connectionStatus.setText("searching...");
			try {
				if (this.connection == null) {
					this.connectionStatus.setText("Must connect first.");
				} else {
					ObservableList<List<String>> list = this.search(searchText.getText().trim());
					this.populateTextArea(list);
					this.connectionStatus.setText("Found " + list.size() + " record(-s).");
				}
			} catch (SQLException e) {
				this.connectionStatus.setText("connection failed: " + e.getMessage());
			}
		});
		searchText.setOnAction(event -> searchButton.fire());
		GridPane grid = new GridPane();
		grid.setHgap(3);
		grid.setVgap(3);
		grid.setPadding(new Insets(5, 5, 5, 5));
		//to add all the Nodes created in this method to grid object.
		//use the image in the lab 1 to find the row, col, rowspan, colspan.
		//for example grid.add( connectButton, 2, 0, 1, 2); 
		//means connectButton is at column 2, row 0, it expands 1 column, and expands 2 rows. 
		grid.add(urlText, 0, 0, 1, 1);
		grid.add(userText, 0, 1, 1, 1);
		grid.add(passText, 0, 2, 1, 1);
		grid.add(searchText, 0, 3, 1, 1);
		grid.add(connectButton, 2, 0, 1, 3);
		grid.add(searchButton, 2, 3, 1, 1);
		return grid;
	}

	private void populateTextArea(ObservableList<List<String>> data) {
		StringBuilder builder = new StringBuilder();

		//use the method StringBuilder::append to append string to builder.
		//use the method StringBuilder::toString to get the final string out of builder.

		//using the string builder and the argument fill in the sample html table
		builder.append("<table style=\"margin: auto;width: 90%;\" border=\"1\">");
		builder.append("<caption>Account Table</caption>");
		builder.append("<tr style=\"height: 2rem;\">");
		for (String columnName : COLUMN_NAMES) {
			builder.append("<th>");
			builder.append(columnName);
			builder.append("</th>");
		}
		builder.append("</tr>");
		builder.append("<tr>");
		for (List<String> row : data) {
			builder.append("<tr>");
			for (String value : row) {
				builder.append("<td>");
				builder.append(value);
				builder.append("</td>");
			}
			builder.append("</tr>");
		}
		builder.append("</tr>");
		builder.append("</table>");
		//extract the string out of builder and load it in the webview engine.
		webView.getEngine().loadContent(builder.toString());
	}

	private void connectTo(String dbURL, String user, String pass) throws SQLException {
		if (!dbURL.contains("useUnicode")) {
			dbURL += "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		}
		if (this.connection == null) {
			this.connection = DriverManager.getConnection(dbURL, user, pass);
		}
	}

	/**
	 * Search method. This method is called after the connection to the DB is established.
	 *
	 * @param searchTerm - a nullable string which represent what text to search on DB, empty or null mean get everything.
	 *
	 * @return return a two dimensional list data retrieved from DB. First dimension are the rows.
	 * The type of this list is "?" as we don't know each type.
	 *
	 * @throws SQLException this exception is if there is an issue with DB access,
	 *                      connection is closed or any exceptions forwarded from {@link Connection#prepareStatement(String)}
	 *                      or {@link PreparedStatement#executeQuery()}.
	 */
	private ObservableList<List<String>> search(String searchTerm) throws SQLException {
		ObservableList<List<String>> data = FXCollections.observableArrayList();
		//the query must pass to it depends on searchTrem.
		//if searchTerm is null or empty use SQL_SCRIPT_SELECT_ALL otherwise use SQL_SCRIPT_SELECT_WHERE.
		String script = searchTerm == null || searchTerm.isEmpty() ? SQL_SCRIPT_SELECT_ALL : SQL_SCRIPT_SELECT_WHERE;
		try (PreparedStatement preparedStatement = this.connection.prepareStatement(script)) {
			//searchTerm = "%" + searchTerm + "%";
			//ps.setString( 1, searchTerm);
			//ps.setString( 2, searchTerm);
			//the rest of the code will be inside of this try and resource.
			if (searchTerm != null && !searchTerm.isEmpty()) {
				searchTerm = "%" + searchTerm + "%";
				preparedStatement.setString(1, searchTerm);
				preparedStatement.setString(2, searchTerm);
			}
			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				//the rest of the code will be inside of this try and resource.
				//TODO inside of the while loop create a List<String> called row and initialize it using ArrayList<>(5);
				//this can be done by creating a for loop inside of the while loop with max length of COLUMN_NAMES.length.
				//inside of the for loop use row.add( rs.getString( i));
				//outside of for loop but inside of while loop add row to list.
				while (resultSet.next()) {
					List<String> row = new ArrayList<>(5);
					for (int i = 1; i < COLUMN_NAMES.length + 1; i++) {
						row.add(resultSet.getString(i));
					}
					data.add(row);
				}
			} catch (SQLException res) {
				res.printStackTrace();
			}
		} catch (SQLException prs) {
			prs.printStackTrace();
		}
		return data;
	}

	/**
	 * main starting point of the application
	 *
	 * @param args - arguments provided through command line, if any
	 */
	public static void main(String[] args) {
		launch(args);
	}
}
