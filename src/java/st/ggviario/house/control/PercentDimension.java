package st.ggviario.house.control;

import javafx.scene.layout.Region;

public class PercentDimension {

    public static void widthChangePercent(Region parent, Region children, double percent  ){
        parent.widthProperty().addListener((observable, oldValue, newValue) -> {
            double calc = newValue.doubleValue() * percent / 100;
            children.setPrefHeight( calc );
        });
    }

    public static void widthChangePercent(Region parent, double percent, OnNewPercent onNewPercent ){
        parent.widthProperty().addListener((observable, oldValue, newValue) -> {
            double calc = newValue.doubleValue() * percent / 100;
            onNewPercent.onNewPrecentValue( percent, calc );
        });
    }

    public static void prefHeight(Region parent, Region children, double percent  ){
        parent.prefHeightProperty().addListener((observable, oldValue, newValue) -> {
            double calc = newValue.doubleValue() * percent / 100;
            children.setPrefHeight( calc );
        });
    }

    public interface OnNewPercent {
        void onNewPrecentValue( double percent, double percentValue );
    }

}
