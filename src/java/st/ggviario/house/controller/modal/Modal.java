package st.ggviario.house.controller.modal;

import com.jfoenix.controls.JFXDialog;
import javafx.scene.layout.StackPane;

import java.util.Map;

public interface Modal<R> {

    void openModal();

    void closeModal();

    void createDialogModal(StackPane stackPane );

    StackPane getStakePane();

    JFXDialog getDialogModal();

    default void setTitle( String title ){ };

    default void setOnOpenModal( OnOpenModal onOpenModal ){ }

    default void setOnCloseModal( OnCloseModal onCloseModal ){ }

    default void setOnOperationResult( OnOperationResult<R> onOperationResult){ }



    interface OnOpenModal{
        void onOpenModal();
    }

    interface OnCloseModal {
        void onCloseModal();

    }

    interface OnOperationResult<T> {
        void onOperationSucessed( OperationResult<T> operationResult );
    }

    interface OperationResult<T> {

        boolean isSucceed();

        String getMessage();

        boolean isTerminated();

        T getResltValue();

        Map<String, Object > mapResults();
    }

}
