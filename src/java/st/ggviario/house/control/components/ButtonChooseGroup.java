package st.ggviario.house.control.components;

import com.jfoenix.controls.JFXRippler;
import com.jfoenix.effects.JFXDepthManager;
import javafx.css.PseudoClass;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

import java.util.LinkedList;
import java.util.List;

public class ButtonChooseGroup  {
    private static final String PSEUDO_CLASS_CHOSEN = "chosen";
    private final List<JFXRippler> buttonList = new LinkedList<>();
    private HBox containerController;
    private JFXRippler selectedControll;
    private OnSelect onSelect;


    public ButtonChooseGroup(HBox choseArea, Label... buttonList) {
        this.applyOn( choseArea );
        this.setAll(buttonList);

    }
    private void applyOn( HBox choseArea ) {
        choseArea.getChildren().clear();
        choseArea.getStyleClass().add( "chose-content-area" );
        JFXDepthManager.pop( choseArea );
        this.containerController = choseArea;
    }

    public void setAll(Label ... buttons) {
        this.buttonList.clear();
        for( Label next : buttons ) this.append( next );
    }

    public JFXRippler append(Label label ){

        JFXRippler rippler = new JFXRippler(label);
        rippler.getStyleClass().add("chose-item");
        label.getStyleClass().add( "chose-item-control" );

        rippler.setOnMouseClicked(event -> onChose( rippler ));
        label.setOnMouseClicked(event -> onChose( rippler ));
        this.containerController.getChildren().add( rippler );
        this.buttonList.add( rippler );
        if( this.buttonList.size() == 1 ) this.onChose( rippler );
        return rippler;
    }


    private void onChose(JFXRippler rippler ) {
        this.selectedControll = rippler;
        for( JFXRippler next: this.buttonList )
            next.pseudoClassStateChanged(PseudoClass.getPseudoClass(PSEUDO_CLASS_CHOSEN), next.equals(rippler));
        if( this.onSelect != null ) this.onSelect.onSelect( ( Label ) this.selectedControll.getControl());
    }

    public JFXRippler getSelectedControll() {
        return selectedControll;
    }

    public ButtonChooseGroup setOnSelect(OnSelect onSelect) {
        this.onSelect = onSelect;
        return this;
    }



    interface OnSelect {
        void onSelect( Label button );
    }
}
