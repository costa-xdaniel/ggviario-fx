package st.ggviario.house.controller.modal;

import com.jfoenix.controls.JFXDialog;
import javafx.scene.layout.StackPane;
import st.ggviario.house.controller.SnackbarBuilder;

import java.util.Map;

public interface Modal< R > {

    void openModal();

    void closeModal();

    void createDialogModal(StackPane stackPane );

    StackPane getStakePane();

    JFXDialog getDialogModal();

    default void setTitle( String title ){ };

    default void setOnOpenModal( OnOpenModal onOpenModal ){ }

    default void setOnCloseModal( OnCloseModal onCloseModal ){ }

    default void setOnModalResult( OnModalResult<R> onModalResult ){ }

    default void clear() {}



    interface OnOpenModal{
        void onOpenModal();
    }

    interface OnCloseModal {
        void onCloseModal();

    }

    interface OnModalResult<T> {
        void onModalResult(ModalResult<T> modalResult);
    }

    interface ModalResult< T > {

        boolean isSucceed();

        String getMessage();

        boolean isTerminated();

        T getResultValue();

        SnackbarBuilder.MessageLevel getLevel();

        Map<String, Object > mapResults();
    }

}
