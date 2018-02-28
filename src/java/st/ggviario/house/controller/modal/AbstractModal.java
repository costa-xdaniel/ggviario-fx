package st.ggviario.house.controller.modal;

import com.jfoenix.controls.JFXDialog;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;

public abstract class AbstractModal< R > implements Modal<R> {

    private JFXDialog dialogModal;
    private OnOpenModal onOpenModal;
    protected OnCloseModal onCloseModal;
    protected OnModalResult<R> onModalResult;
    private StackPane stackPane;

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
}
