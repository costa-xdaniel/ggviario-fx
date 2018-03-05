package st.ggviario.house.controller.modal;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import st.ggviario.house.controller.SnackbarBuilder;
import st.jigahd.support.sql.lib.SQLText;

import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

public class ModalDestroy extends AbstractModal<ModalDestroy.Destroy> implements Initializable{

    @FXML private AnchorPane root;
    @FXML private AnchorPane anchorHeader;
    @FXML private Label modalTitle;
    @FXML private AnchorPane iconAreaCloseModal;
    @FXML private Label labelMessage;
    @FXML private JFXTextField textFieldIdentifier;
    @FXML private JFXTextArea textAreaMotivo;
    @FXML private JFXButton buttonDestroy;
    private Destroy destroy;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @Override
    void structure() {
        super.structure();
    }

    @Override
    void defineEvents() {
        super.defineEvents();
        this.buttonDestroy.setOnAction( event -> onDestroy() );
    }

    @Override
    public void clear() {
        this.textAreaMotivo.setText( null );
        this.textFieldIdentifier.setText( null );
    }

    @Override
    @Deprecated
    public void openModal() {}

    private void onDestroy(){
        ModalDestroyResult destroyResult = new ModalDestroyResult();

    }

    public void opemModal( Destroy destroy ){
        this.destroy = destroy;
        this.clear();
        this.labelMessage.setText( this.destroy.getMessage() );
        this.setTitle(SQLText.normalize( this.destroy.title) == null? "Destruir "+this.destroy.identifier : this.destroy.title );
        super.openModal();
    }


    @Override
    Region getContentRoot() {
        return null;
    }

    @Override
    Label getModalTitleView() {
        return null;
    }

    @Override
    AnchorPane getIconAreaCloseModal() {
        return null;
    }

    @Override
    AnchorPane getAnchorHeader() {
        return null;
    }

    public class Destroy {
        private String message;
        private String checkCode;
        private boolean requireText = true;
        private boolean requireIdentifier = true;
        private String identifier;
        private String text;
        private String title;

        public Destroy(String message, String checkCode) {
            this.message = message;
            this.checkCode = checkCode;
        }

        public Destroy setMessage(String message) {
            this.message = message;
            return this;
        }

        public Destroy setCheckCode(String checkCode) {
            this.checkCode = checkCode;
            return this;
        }

        public Destroy setRequireText(boolean requireText) {
            this.requireText = requireText;
            return this;
        }

        public Destroy setRequireIdentifier(boolean requireIdentifier) {
            this.requireIdentifier = requireIdentifier;
            return this;
        }

        public Destroy setTitle(String title) {
            this.title = title;
            return this;
        }

        public String getMessage() {
            return message;
        }

        public String getCheckCode() {
            return checkCode;
        }

        public boolean isRequireText() {
            return requireText;
        }

        public boolean isRequireIdentifier() {
            return requireIdentifier;
        }

        public String getIdentifier() {
            return identifier;
        }

        public String getText() {
            return text;
        }

        public String getTitle() {
            return title;
        }
    }

    class ModalDestroyResult implements ModalResult<Destroy> {
        private boolean success;
        private boolean terminated;
        private String message;
        private Destroy resultValue;
        private SnackbarBuilder.MessageLevel level;

        @Override
        public boolean isSuccess() {
            return success;
        }

        @Override
        public boolean isTerminated() {
            return terminated;
        }

        @Override
        public String getMessage() {
            return message;
        }

        @Override
        public Destroy getResultValue() {
            return resultValue;
        }

        @Override
        public SnackbarBuilder.MessageLevel getLevel() {
            return level;
        }

        @Override
        public Map<String, Object> getData() {
            throw new UnsupportedOperationException( "Operação não suportada!" );
        }
    }
}
