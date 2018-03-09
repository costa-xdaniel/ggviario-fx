package st.ggviario.house.controller.modals;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import st.ggviario.house.controller.ControllerLoader;
import st.ggviario.house.controller.SnackbarBuilder;
import st.jigahd.support.sql.lib.SQLText;

import java.util.Map;

public class ModalDestroy< T > extends AbstractModal<ModalDestroy.Destroy< T >> implements Initializable{

    public static <T>  ModalDestroy< T > newInstance(StackPane stackPane ){
        ControllerLoader< AnchorPane, ModalDestroy<T> > loader = new ControllerLoader<>("/fxml/modal/modal_destroy.fxml");
        loader.getController().createDialogModal( stackPane );
        loader.getController().structure();
        loader.getController().defineEvents();
        return loader.getController();
    }

    @FXML private AnchorPane root;
    @FXML private AnchorPane anchorHeader;
    @FXML private Label modalTitle;
    @FXML private AnchorPane iconAreaCloseModal;
    @FXML private Label labelMessage;
    @FXML private JFXTextField textFieldIdentifier;
    @FXML private JFXTextArea textAreaText;
    @FXML private JFXButton buttonDestroy;
    private Destroy< T > destroy;
    private String messageMissingIdentifier;
    private String messageInvalidIdentifier;
    private String messageMissingText;


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
        this.textAreaText.setText( null );
        this.textFieldIdentifier.setText( null );
    }

    @Override
    @Deprecated
    public void openModal() {}

    private void onDestroy() {
        ModalDestroyResult destroyResult = new ModalDestroyResult();
        this.destroy.capturedText = SQLText.normalize( this.textAreaText.getText() );
        this.destroy.capturedIdentifier = SQLText.normalize( this.textFieldIdentifier.getText() );
        boolean equals = false;
        boolean equalsIgnore = false;

        if( this.destroy.originalIdentifier  != null ){
            equals =  this.destroy.originalIdentifier.equals( this.destroy.getCapturedIdentifier() );
            equalsIgnore = this.destroy.ignoreCase &&  this.destroy.originalIdentifier.equalsIgnoreCase( this.destroy.getCapturedIdentifier() );
        }

        if( destroy.isRequireIdentifier() && this.destroy.getCapturedIdentifier() == null ){
            destroyResult.message = this.messageMissingIdentifier;
        } else if ( this.destroy.isRequireIdentifier() && !( equals || equalsIgnore )
        ){
            destroyResult.message = this.messageInvalidIdentifier;
        } else if( this.destroy.isRequireText() && this.destroy.getCapturedText() == null ){
            destroyResult.message = this.messageMissingText;
        } else {
            destroyResult.success = true;
            destroyResult.terminated = true;
        }
        destroyResult.resultValue = this.destroy;
        this.executeOnOperationResult( destroyResult );
    }

    public void opemModal( Destroy<T> destroy ){
        this.destroy = destroy;
        this.clear();
        this.labelMessage.setText( this.destroy.getMessage() );
        super.openModal();
    }

    public String getMessageMissingIdentifier() {
        return messageMissingIdentifier;
    }

    public ModalDestroy<T> setMessageMissingIdentifier(String messageMissingIdentifier) {
        this.messageMissingIdentifier = messageMissingIdentifier;
        return this;
    }

    public String getMessageInvalidIdentifier() {
        return messageInvalidIdentifier;
    }

    public ModalDestroy<T> setMessageInvalidIdentifier(String messageInvalidIdentifier) {
        this.messageInvalidIdentifier = messageInvalidIdentifier;
        return this;
    }

    public String getMessageMissingText() {
        return messageMissingText;
    }

    public ModalDestroy<T> setMessageMissingText(String messageMissingText) {
        this.messageMissingText = messageMissingText;
        return this;
    }

    @Override
    Region getContentRoot() {
        return this.root;
    }

    @Override
    Label getModalTitleView() {
        return this.modalTitle;
    }

    @Override
    AnchorPane getIconAreaCloseModal() {
        return this.iconAreaCloseModal;
    }

    @Override
    AnchorPane getAnchorHeader() {
        return this.anchorHeader;
    }

    public static class Destroy < T > {
        private String message;
        private String originalIdentifier;
        private boolean requireText = true;
        private boolean requireIdentifier = true;
        private String capturedIdentifier;
        private String capturedText;
        private T object;
        private boolean ignoreCase;

        public Destroy( T object, String message, String originalIdentifier) {
            this.message = message;
            this.originalIdentifier = originalIdentifier;
            this.object = object;
        }

        public Destroy setMessage(String message) {
            this.message = message;
            return this;
        }

        public Destroy setOriginalIdentifier(String originalIdentifier) {
            this.originalIdentifier = originalIdentifier;
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

        public T getObject() {
            return object;
        }

        public String getMessage() {
            return message;
        }

        public String getOriginalIdentifier() {
            return originalIdentifier;
        }

        public boolean isRequireText() {
            return requireText;
        }

        public boolean isRequireIdentifier() {
            return requireIdentifier;
        }

        public String getCapturedIdentifier() {
            return capturedIdentifier;
        }

        public String getCapturedText() {
            return capturedText;
        }

        public void setIgnoreCase(boolean ignoreCase) {
            this.ignoreCase = ignoreCase;
        }
    }

    class ModalDestroyResult implements ModalResult<Destroy < T > > {
        private boolean success;
        private boolean terminated;
        private String message;
        private Destroy<T> resultValue;
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
        public Destroy<T> getValue() {
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
