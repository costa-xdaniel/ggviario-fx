package st.ggviario.house.controller.page;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.*;
import st.ggviario.house.model.Fornecedor;

import java.util.Date;

public abstract class FornecedorController extends TableController<FornecedorController.FornecedorViewModel> implements Page  {

    class FornecedorViewModel extends  RecursiveTreeObject<FornecedorViewModel> {
        private StringProperty fornecedorNome;
        private StringProperty fornecedorNif;
        private StringProperty fornecedorTelefone;
        private StringProperty fornecedorTelemovel;
        private StringProperty fornecedorMail;
        private StringProperty fornecedorLocal;
        private ObjectProperty<Date> fornecedorDataRegisto;
        private ObjectProperty<Number> fornecedorCompras;
        private ObjectProperty<Number> fornecedorComprasPagos;
        private ObjectProperty<Number> fornecedorComprasPenentes;
        private Fornecedor fornecedor;

        public FornecedorViewModel( Fornecedor fornecedor ) {
            this.fornecedor = fornecedor;
            this.fornecedorNome = new SimpleStringProperty( this.fornecedor.getFornecedorNome() );
            this.fornecedorNif = new SimpleStringProperty( this.fornecedor.getFornecedorNif() );
            this.fornecedorTelefone = new SimpleStringProperty( this.fornecedor.getFornecedorTelefone() );
            this.fornecedorTelemovel = new SimpleStringProperty( this.fornecedor.getFornecedorTelemovel() );
            this.fornecedorMail = new SimpleStringProperty( this.fornecedor.getFornecedorMail() );
            this.fornecedorLocal = new SimpleStringProperty( this.fornecedor.getFornecedorLocal() );
            this.fornecedorDataRegisto = new SimpleObjectProperty<>(this.fornecedor.getFornecedorDataregisto());
            this.fornecedorCompras = new SimpleObjectProperty<>( this.fornecedor.getFornecedorMontanteCompras() );
            this.fornecedorComprasPenentes = new SimpleObjectProperty<>( this.fornecedor.getFornecedorMonntantePendentes() );
            this.fornecedorComprasPagos = new SimpleObjectProperty<>( this.fornecedor.getFornecedorMontantePagos() );
        }
    }
}
