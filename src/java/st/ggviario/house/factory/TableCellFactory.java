package st.ggviario.house.factory;

import javafx.scene.control.TableCell;

public class TableCellFactory< Item, Type > {

    private OnUpdate<Item, Type> run;

    public TableCellFactory<Item, Type> setOnUpdate(OnUpdate<Item, Type> run) {
        this.run = run;
        return this;
    }

    public TableCellItem<Item, Type> build(){
        return new TableCellItem<>( this.run );
    }


    private static class TableCellItem<Item, Type > extends TableCell< Item, Type > {

        private final OnUpdate <Item, Type > run;

        public TableCellItem(OnUpdate<Item, Type> run) {
            this.run = run;
        }

        @Override
        protected void updateItem(Type item, boolean empty) {
            super.updateItem(item, empty);
            this.run.onUpdate( this, item );
        }
    }

    public interface OnUpdate<Item, Type> {
        public void onUpdate( TableCell<Item, Type> cell, Type item );
    }
}
