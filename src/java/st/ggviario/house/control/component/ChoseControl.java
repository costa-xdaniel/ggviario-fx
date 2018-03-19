package st.ggviario.house.control.component;

import com.jfoenix.controls.JFXRippler;
import com.jfoenix.effects.JFXDepthManager;
import de.jensd.fx.glyphs.GlyphIcon;
import javafx.css.PseudoClass;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

import java.util.LinkedList;
import java.util.List;

public class ChoseControl {

    private static final String PSEUD_CLASS_CHOSEN = "chosen";
    private static final String PSEUD_CLASS_FIRST_ITEN = "first-item";
    private static final String PSEUD_CLASS_LAST_ITEM = "last-item";


    private List< BaseItem > itemList = new LinkedList<>();
    private Item chosenItem;
    private HBox root = new HBox();
    private OnChoseItem onChoseItem;


    public ChoseControl() {
        init();
    }

    private void init(){
        this.getRoot().getStyleClass().add( "chose-control" );
        JFXDepthManager.pop( this.getRoot() );
    }

    public Item newItem(){
        return new Item();
    }

    public ChoseControl setIcon( GlyphIcon icon ){
        return this.setIcon( icon, null );
    }

    public ChoseControl setIcon( GlyphIcon icon, OnClickIcon onClickIcon ){

        BaseItem item = new BaseItem();
        item.icon = icon;
        Label label = new Label();

        StackPane stackPane;
        if( onClickIcon != null ){
            stackPane = new JFXRippler( label );
            stackPane.setOnMouseClicked(mouseEvent -> onClickIcon.onClickIcon());
        } else {
            stackPane = new StackPane( label );
        }
        stackPane.getStyleClass().add( "chose-item" );
        stackPane.getStyleClass().add( "icon" );
        label.getStyleClass().add( "chose-item-label" );

        item.icon.getStyleClass().add( "chose-item-icon" );
        label.setGraphic( item.icon );
        this.getRoot().getChildren().add( stackPane );
        item.label = label;
        item.itemView = stackPane;
        this.itemList.add( item );
        reState();
        return this;
    }

    public Item getChosenItem() {
        return chosenItem;
    }



    private void append( Item item ) {
        Label label = new Label( item.text);
        JFXRippler rippler = new JFXRippler( label );

        rippler.getStyleClass().add( "chose-item" );
        label.getStyleClass().add( "chose-item-label" );

        if( item.icon != null ) {
            item.icon.getStyleClass().add( "chose-item-icon" );
            label.setGraphic( item.icon );
        }

        this.getRoot().getChildren().add( rippler );
        rippler.setOnMouseClicked(mouseEvent -> onChose( rippler, label, item));
        item.itemView = rippler;
        item.label = label;
        this.itemList.add( item );
        reState();

        if( this.itemList.size() == 1 || ( itemList.size() == 2 && !( this.itemList.get( 0 ) instanceof Item ) ) ) {
            System.out.println("ChoseControl.append item: " +item.text);
            this.onChose( rippler, label, item );
        }
    }

    private void reState() {
        for( int i=0; i< this.itemList.size(); i++ ){
            BaseItem next = itemList.get(i);
            next.itemView.pseudoClassStateChanged( PseudoClass.getPseudoClass( PSEUD_CLASS_FIRST_ITEN ), i == 0 );
            next.itemView.pseudoClassStateChanged( PseudoClass.getPseudoClass( PSEUD_CLASS_LAST_ITEM ), i+1 == this.itemList.size() );
        }
    }


    private void onChose(JFXRippler rippler, Label label, Item item){
        for( BaseItem next: this.itemList){
            if( !( next instanceof Item ) ) continue;
            next.itemView.pseudoClassStateChanged(PseudoClass.getPseudoClass( PSEUD_CLASS_CHOSEN ), next.itemView.equals(rippler));
        }
        this.chosenItem = item;
        if( item.onChose != null )  item.onChose.onChoseMe( item );
        if( this.onChoseItem != null ) this.onChoseItem.onChoseItem( this.chosenItem, item );
    }

    public Pane getRoot() {
        return root;
    }

    public class BaseItem{
        protected GlyphIcon icon;
        Label label;
        StackPane itemView;
    }

    public class  Item extends BaseItem {
        private String text;
        private OnChose onChose;
        private boolean append;
        private String key;

        public Item setIcon(GlyphIcon icon) {
            this.icon = icon;
            return this;
        }

        public Item setText(String text) {
            this.text = text;
            return this;
        }

        public Item setKey(String key) {
            this.key = key;
            return this;
        }

        public String getKey() {
            return key;
        }

        public Item setOnChose(OnChose onChose) {
            this.onChose = onChose;
            return this;
        }

        public OnChose getOnChose() {
            return onChose;
        }



        public ChoseControl append() {
            if( this.append ) return ChoseControl.this;
            ChoseControl.this.append( this );
            this.append = true;
            return ChoseControl.this;
        }
    }

    interface OnChoseItem {
        void onChoseItem( Item oldItem, Item newItem );
    }

    public interface OnChose {
        void onChoseMe(Item item );
    }

    public interface  OnClickIcon {
        void onClickIcon( );
    }
}

