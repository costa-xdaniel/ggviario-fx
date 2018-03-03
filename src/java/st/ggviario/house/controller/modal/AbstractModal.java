package st.ggviario.house.controller.modal;

import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXRippler;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;

import java.net.URL;
import java.util.ResourceBundle;

public abstract class AbstractModal< R > implements Modal<R>, Initializable {

    private JFXDialog dialogModal;
    private OnOpenModal onOpenModal;
    protected OnCloseModal onCloseModal;
    protected OnModalResult<R> onModalResult;
    private StackPane stackPane;
    private JFXRippler ripplerCloseModal;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.structureCloseButton();
        this.defineEventClickCloseButton();
    }

    void structureCloseButton(){
        this.ripplerCloseModal = new JFXRippler( this.getIconAreaCloseModal() );
        this.getAnchorHeader().getChildren().add( this.ripplerCloseModal );
        AnchorPane.setTopAnchor( this.ripplerCloseModal, 0.0 );
        AnchorPane.setRightAnchor( this.ripplerCloseModal, 0.0 );
        this.ripplerCloseModal.setStyle( "-jfx-rippler-fill: md-red-500" );
    }

    void defineEventClickCloseButton(){
        this.ripplerCloseModal.setOnMouseClicked(event -> {
            this.clear();
            this.closeModal();
        });
    }


    @Override
    public void openModal() {
        this.dialogModal.show();
        if( this.onOpenModal != null ) this.onOpenModal.onOpenModal();
    }

    @Override
    public void closeModal() {
        this.dialogModal.close();
        if( this.onCloseModal != null ) this.onCloseModal.onCloseModal();
    }

    @Override
    public void createDialogModal(StackPane stackPane) {
        this.stackPane = stackPane;
        this.dialogModal = new JFXDialog( stackPane, this.getContentRoot(), getDialogTransition() );
    }

    @Override
    public StackPane getStakePane() {
        return stackPane;
    }

    void executeOnOperationResult( ModalResult<R> modalResult){
        if( this.onModalResult != null )
            this.onModalResult.onModalResult(modalResult);
    }

    @Override
    public void setTitle(String title) {
        this.getModalTitleView().setText( title );
    }

    @Override
    public JFXDialog getDialogModal() {
        return this.dialogModal;
    }

    @Override
    public void setOnOpenModal(OnOpenModal onOpenModal) {
        this.onOpenModal = onOpenModal;
    }

    @Override
    public void setOnCloseModal(OnCloseModal onCloseModal) {
        this.onCloseModal = onCloseModal;
    }

    @Override
    public void setOnModalResult(OnModalResult<R> onModalResult) {
        this.onModalResult = onModalResult;
    }

    JFXDialog.DialogTransition getDialogTransition(){
        return JFXDialog.DialogTransition.CENTER;
    }

    abstract Region getContentRoot();

    abstract Label getModalTitleView();

    abstract AnchorPane getIconAreaCloseModal();

    abstract AnchorPane getAnchorHeader();
}
