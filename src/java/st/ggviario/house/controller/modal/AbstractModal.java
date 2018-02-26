package st.ggviario.house.controller.modal;

import com.jfoenix.controls.JFXDialog;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;

public abstract class AbstractModal< R > implements Modal<R> {

    private JFXDialog dialogModal;
    private OnOpenModal onOpenModal;
    protected OnCloseModal onCloseModal;
    protected OnOperationResult<R> onOperationResult;
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

    void executeOnOperationResult( OperationResult<R> operationResult ){
        if( this.onOperationResult != null )
            this.onOperationResult.onOperationSucessed( operationResult );
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
    public void setOnOperationResult( OnOperationResult<R> onOperationResult ) {
        this.onOperationResult = onOperationResult;
    }

    JFXDialog.DialogTransition getDialogTransition(){
        return JFXDialog.DialogTransition.CENTER;
    }

    abstract Region getContentRoot();


}
