package application.beadando3.view;

import java.time.LocalDateTime;

import application.beadando3.model.RouterModel;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;




public class RouterEditDialogView {

    @FXML
	private TextField  routerName;
    @FXML
	private TextField  routerPlatform;
    @FXML
	private TextField  routerSerial;
    @FXML
	private TextField  configuredBy;  
    @FXML
	private TextField  configured;
    @FXML
	private TextField  confReg;
    @FXML
	private TextField  IOS;
    @FXML
	private TextField  man_IP;
	@FXML 
	private TextField bootstrap;


    private Stage dialogStage;
    private RouterModel router;
    private boolean okClicked = false;

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
    }

    /**
     * Sets the stage of this dialog.
     * 
     * @param dialogStage
     */
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    /**
     * Sets the person to be edited in the dialog.
     * 
     * @param person
     */
    public void setRouter(RouterModel router) {
        this.router = router;

        routerPlatform.setText(router.getPlatform());
    	routerSerial.setText(router.getSerial_number());
    	configuredBy.setText(router.getWho_Configured());
    	configured.setText(router.getWhen_configured().toString());
    	confReg.setText(router.getConfig_register());
    	IOS.setText(router.getIOS());
    	man_IP.setText(router.getMan_IP());
    	routerName.setText(router.getName());
    	bootstrap.setText(router.getBootstrap_version());
    }
    public void setNewRouter(RouterModel router) {
        this.router = router;

        routerPlatform.setText("");
    	routerSerial.setText("");
    	configuredBy.setText("");
    	configured.setText("");
    	confReg.setText("");
    	IOS.setText("");
    	man_IP.setText("");
    	routerName.setText("");
    	bootstrap.setText("");
    }

    /**
     * Returns true if the user clicked OK, false otherwise.
     * 
     * @return
     */
    public boolean isOkClicked() {
        return okClicked;
    }

    /**
     * Called when the user clicks ok.
     */
    @FXML
    private void handleOk() {
        if (isInputValid()) {
            router.setName(routerName.getText());
            router.setConfigured(configured.getText());
            router.setPlatform(routerPlatform.getText());
            router.setSerial_number(routerSerial.getText());
            router.setConfReg(confReg.getText());
            router.setConfigured(configuredBy.getText());
            router.setIOS(IOS.getText());
            router.setMan_IP(man_IP.getText());
            router.setBootstrap(bootstrap.getText());
            router.setWhen_configured(LocalDateTime.now());
            okClicked = true;
            dialogStage.close();
        }
    }

    /**
     * Called when the user clicks cancel.
     */
    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

    /**
     * Validates the user input in the text fields.
     * 
     * @return true if the input is valid
     */
    private boolean isInputValid() {
        String errorMessage = "";

        if (routerName.getText() == null || routerName.getText().length() == 0) {
            errorMessage += "No valid router name!\n"; 
        }
        if (routerPlatform.getText() == null || routerPlatform.getText().length() == 0) {
            errorMessage += "No valid router platform!\n"; 
        }
        if (routerSerial.getText() == null || routerSerial.getText().length() == 0) {
            errorMessage += "No valid SN!\n"; 
        }

        if (confReg.getText() == null || confReg.getText().length() == 0) {
            errorMessage += "No valid confReg!\n"; 
        } 

        if (man_IP.getText() == null || man_IP.getText().length() == 0) {
            errorMessage += "No valid Managament IP!\n"; 
        }

        if (IOS.getText() == null || IOS.getText().length() == 0) {
            errorMessage += "No valid birthday!\n";
        } 

        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message.
            Alert alert = new Alert(AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }
    }
}