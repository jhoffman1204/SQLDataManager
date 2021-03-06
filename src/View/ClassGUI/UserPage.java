package View.ClassGUI;

import ModelData.DataObjects.Micropost;
import ModelData.DataObjects.User;
import View.GUIManager;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * Created by James Hoffman on 6/20/2017.
 */
public class UserPage {

    GUIManager guimanager;

    public UserPage(GUIManager manager){
        this.guimanager = manager;
    }
    public HBox generateUserPage(User user, boolean isCurrentUser){
        if(isCurrentUser){
            guimanager.getController().getUserStateManager().setCurrentViewingUser(guimanager.getController().getUserStateManager().getCurrentUser());
        }
        VBox leftColumn = new VBox();
        VBox userInformationPane = new VBox();
        HBox userPage = new HBox();
        VBox microPostPane = createMicroPostPane();
        VBox bioPane = this.createBioPane(isCurrentUser);
        ScrollPane microposts = createMicroPostDisplayPane();

        Label userNameLabel = new Label(user.getUsername());
        userNameLabel.setStyle("-fx-font: 35px Verdana;-fx-text-fill: black;");
        Label information = new Label("Your Information: ");
        information.setStyle("-fx-font: 23px Verdana;-fx-underline: true;");
        Label majorLabel  = new Label("Major: " + user.getMajor());
        Label yearLabel = new Label("Year: " + user.getYear());
        Label gitLabel = new Label("Git: " + user.getGit());
        Label website = new Label("Website: " + user.getWebsite());
        Label courses = new Label("Courses: " + user.getCourses());
        Label email  = new Label("Email: " + user.getEmail());

        userInformationPane.getChildren().add(userNameLabel);
        userInformationPane.getChildren().add(information);
        userInformationPane.getChildren().add(majorLabel);
        userInformationPane.getChildren().add(yearLabel);
        userInformationPane.getChildren().add(gitLabel);
        userInformationPane.getChildren().add(website);
        userInformationPane.getChildren().add(courses);
        userInformationPane.getChildren().add(email);


        leftColumn.getChildren().add(userInformationPane);
        leftColumn.getChildren().add(bioPane);
        if(isCurrentUser) {
            leftColumn.getChildren().add(microPostPane);
        }

        leftColumn.setMargin(microPostPane , new Insets(20,0,15,0));

        userPage.getChildren().add(leftColumn);
        userPage.getChildren().add(microposts);

        userPage.setMargin(microposts,new Insets(0,0,0,100));

        return userPage;
    }
    public VBox createMicroPostPane(){
        VBox box = new VBox();
        Label titleLabel = new Label("New Post");
        titleLabel.setStyle("-fx-font: 30px Verdana;-fx-text-fill: black;");
        Label subjectlabel = new Label("Title");
        TextField title = new TextField();
        title.setMaxSize(300,100);
        title.setMinSize(300,30);


        Label label = new Label("Post");
        TextArea post = new TextArea();
        post.setWrapText(true);
        post.setMaxSize(300,100);
        post.setMinSize(300,100);


        box.getChildren().add(titleLabel);
        box.getChildren().add(subjectlabel);
        box.getChildren().add(title);
        box.getChildren().add(label);
        box.getChildren().add(post);

        Button submitButton = new Button("Submit");
        box.getChildren().add(submitButton);

        submitButton.setOnAction(event -> {
            Micropost micropost = new Micropost();
            String titleData = title.getText();
            String postData = post.getText();
            String userData = guimanager.getController().getUserStateManager().getCurrentUser();

            micropost.setTitle(titleData);
            micropost.setPost(postData);
            micropost.setUser(userData);
            System.out.println(micropost.getUser());

            guimanager.getController().postMicroPost(micropost);
            guimanager.setAsBodyPane(generateUserPage(guimanager.getController().getCurrentUser(),true));
        });

        return box;
    }
    public VBox createPostPane(String message, String title){
        VBox box = new VBox();
        Label messageLabel = new Label(message);
        Label titleLabel = new Label(title);

        titleLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 25px;");

        box.getChildren().add(titleLabel);
        box.getChildren().add(messageLabel);
        box.getChildren().add(new Label("________________________________________________________________"));

        return box;
    }
    public ScrollPane createMicroPostDisplayPane(){
        VBox pane = new VBox();

        //Micropost[] a = guimanager.getController().retrievePosts(guimanager.getCurrentViewingUser());
        Micropost[] a = guimanager.getController().retrievePosts(guimanager.getController().getUserStateManager().getCurrentViewingUser());
        //fSystem.out.println(guimanager.getCurrentViewingUser());
        for(int i = 0; i < a.length; i++){
            if(a[i]!= null) {
                pane.getChildren().add(createPostPane(a[i].getPost(),a[i].getTitle()));
            }
        }

        ScrollPane postsPane = new ScrollPane();
        postsPane.setPrefSize(300,600);
        postsPane.setStyle("-fx-border-style: solid;");

        postsPane.setContent(pane);

        return postsPane;
    }
    public VBox createBioPane(boolean isCurrentUser){
        VBox box = new VBox();

        Label label = new Label("Bio");
        HBox bioPane = new HBox();
        Label bio = new Label(guimanager.getController().getCurrentUser().getBio());
        bioPane.setPrefSize(500,200);
        bioPane.setMaxWidth(300);
        bioPane.setStyle("-fx-border-style: solid;");
        Button editBioButton = new Button("Edit Bio");
        //Button editInfoButton = new Button("Edit User Info");

        bioPane.getChildren().add(bio);
        box.getChildren().add(label);
        box.getChildren().add(bioPane);
        if(isCurrentUser) {
            box.getChildren().add(editBioButton);
            //box.getChildren().add(editInfoButton);
        }
        editBioButton.setOnAction(event -> {
            String currentBio = guimanager.getController().getCurrentUser().getBio();
            bioPane.getChildren().clear();
            TextArea area = new TextArea();
            area.setPrefSize(500,200);
            area.setMaxWidth(300);
            bioPane.getChildren().add(area);
            Button submitNewBio = new Button("Submit New Bio");
            box.getChildren().remove(editBioButton);
            box.getChildren().add(submitNewBio);
            submitNewBio.setOnAction(event1 -> {
                Label newBio = new Label(area.getText());
                if(!newBio.getText().equalsIgnoreCase(currentBio)){
                    System.out.println(newBio.getText());
                    System.out.println(currentBio);
                    bioPane.getChildren().clear();
                    bioPane.getChildren().add(newBio);
                    guimanager.getController().changeBio(newBio.getText());
                    box.getChildren().remove(submitNewBio);
                    box.getChildren().add(editBioButton);
                }
                else{
                    System.out.println("No Changes detected, bio not changed");
                }
            });

        });

        return box;
    }
}
