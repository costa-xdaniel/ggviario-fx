package st.ggviario.house.controller.page;

import javafx.event.EventHandler;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import st.jigahd.support.sql.lib.SQLResource;
import st.jigahd.support.sql.lib.SQLText;

import java.text.NumberFormat;
import java.util.Locale;

public class MoneyTextField {

    private TextField textFieldObservable;
    private NumberFormat numberFormat;
    private EventHandler<? super KeyEvent> keyRelesed;

    private Character maskDivisor = ' ';
    private Character maskSeparatorScale = ',';
    private String maskCurrencyName = "";

    private CurrencyPosition positionMoneyCurrency = CurrencyPosition.RIGHT;
    private OnValidNumberInput onValidNumberInput;
    private OnInvalidNumberInput onInvalidNumberInput;
    private ResultTransformation lastResult = new ResultTransformation();
    private OnRejectInput onRejectInput;


    public void observeIn(TextField textFieldController ){
        if( textFieldController == null ) throw new RuntimeException( "Text field não definido!" );
        this.textFieldObservable = textFieldController;
        if( this.numberFormat == null ) this.initNumberFormat();
        if( this.maskCurrencyName == null )  this.initCurrency( null );


        this.lastResult = this.moneyTransformation( "0.0" );
        this.lastResult.caretPosition = lastResult.moneyTextRepresentation.length() - this.maskCurrencyName.length();
        this.applyResultInView( this.moneyTransformation( this.textFieldObservable.getText() ) );
        this.setUpEvent();
    }

    public MoneyTextField setOnValidNumberInput(OnValidNumberInput onValidNumberInput) {
        this.onValidNumberInput = onValidNumberInput;
        return this;
    }

    public MoneyTextField setOnInvalidNumberInpute(OnInvalidNumberInput onInvalidNumberInput) {
        this.onInvalidNumberInput = onInvalidNumberInput;
        return this;
    }

    public MoneyTextField setOnInvalidNumberInpute(OnRejectInput onRejectInput ) {
        this.onRejectInput = onRejectInput;
        return this;
    }

    public MoneyTextField setMaskCurrencyName(String maskCurrencyName) {
        this.initCurrency(maskCurrencyName);
        return this;
    }

    private void initNumberFormat(){
        this.numberFormat = NumberFormat.getNumberInstance( Locale.FRANCE );
        this.numberFormat.setMinimumFractionDigits( 0 );
        this.numberFormat.setMaximumFractionDigits( 5 );
        this.numberFormat.setMinimumIntegerDigits( 1 );
        this.numberFormat.setMaximumIntegerDigits( 10 );
    }

    private void initCurrency( String currency ){
        this.maskCurrencyName = SQLText.normalize( currency );
        if( this.maskCurrencyName == null ) this.maskCurrencyName = "";
        else this.maskCurrencyName = " "+this.maskCurrencyName;
    }

    private void setUpEvent(){
        if( this.keyRelesed == null ) this.keyRelesed = textFieldObservable.getOnKeyReleased();
        this.textFieldObservable.setOnKeyReleased(keyEvent -> {
            int newPositionCaret = this.textFieldObservable.getCaretPosition();
            String text = this.textFieldObservable.getText( );
            System.out.println("point 1");
            if( text.length() == lastResult.moneyTextRepresentation.length() && newPositionCaret != lastResult.caretPosition ){
                System.out.println("point 2");
                this.textFieldObservable.setText( text );
            } else if( keyEvent.getCode().isDigitKey() || SQLResource.existIn( keyEvent.getCode(), KeyCode.COMMA, KeyCode.PERIOD, KeyCode.BACK_SPACE, KeyCode.DELETE ) ) {
                System.out.println("point 5");
                ResultTransformation res = this.moneyTransformation( text );
                applyResultInView( res );
                if( res.sucess ){
                    System.out.println("point 6");
                    if( this.onValidNumberInput != null ) onValidNumberInput.onValidNumberInput( keyEvent, lastResult.moneyNumberRepresentation, res.moneyNumberRepresentation, res.moneyTextRepresentation);
                    this.lastResult = res;
                } else {
                    System.out.println("point 7");
                    if( this.onInvalidNumberInput != null ) this.onInvalidNumberInput.onInvalidNumberInout( keyEvent, lastResult.moneyNumberRepresentation, text );
                }
            } else if( text.length() != this.lastResult.moneyTextRepresentation.length() ){
                System.out.println("point 8");
                this.textFieldObservable.setText( this.lastResult.moneyTextRepresentation );
                this.lastResult.caretPosition = caretPointFor( this.lastResult.moneyTextRepresentation );
            }
            System.out.println("point 9");
            if( this.keyRelesed != null )
                this.keyRelesed.handle( keyEvent );
        });

        this.textFieldObservable.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if( newValue ){
                ResultTransformation res = this.moneyTransformation(this.textFieldObservable.getText());
                this.applyResultInView( res );
                if( res.sucess ) this.lastResult = res;
            }
        });
    }

    private int caretPointFor( String text ){
        return text.length() - this.maskCurrencyName.length();
    }

    private void applyResultInView( ResultTransformation res ) {
        System.out.println("point 10");
        if( res.sucess ){
            System.out.println("point 11");

            this.textFieldObservable.setText( res.moneyTextRepresentation);
            res.caretPosition = this.caretPointFor( res.moneyTextRepresentation );
        } else {
            System.out.println("point 12");
            this.textFieldObservable.setText( this.lastResult.moneyTextRepresentation );
            this.lastResult.caretPosition = this.caretPointFor( this.lastResult.moneyTextRepresentation );
        }
    }

    private ResultTransformation moneyTransformation( String text ){
        System.out.println("point 13");
        ResultTransformation res = new ResultTransformation();
        res.sucess = false;

        res.moneyTextRepresentation = text.replace( String.valueOf( this.maskDivisor), "" );
        res.moneyTextRepresentation = res.moneyTextRepresentation.replace( String.valueOf( " " ), "" );
        res.moneyTextRepresentation = res.moneyTextRepresentation.replace( ".", String.valueOf(maskSeparatorScale) );
        res.moneyTextRepresentation = res.moneyTextRepresentation.replace( this.maskCurrencyName.trim().replace(" ", ""), "" );

        String campos[ ] = res.moneyTextRepresentation.split( "["+ maskSeparatorScale +"]" );
        if( campos.length>2 ) {
            System.out.println("point 14");
            return res;
        }

        if( campos[0].length() > this.numberFormat.getMaximumIntegerDigits() ) {
            System.out.println("point 15");
            return res;
        }

        if( campos.length> 1 && campos[ 1 ].length()> numberFormat.getMaximumFractionDigits() ){
            System.out.println("point 16");
            return res;
        }

        res.moneyTextRepresentation = res.moneyTextRepresentation.replace( ",", "." );
        if( res.moneyTextRepresentation.length() > 0 ){
            System.out.println("point 17");
            res.moneyNumberRepresentation = Double.valueOf( res.moneyTextRepresentation );
        } else {
            System.out.println("point 18");
            res.moneyNumberRepresentation = 0.0;
        }
        
        System.out.println("point 19");
        res.moneyTextRepresentation = format( res.moneyNumberRepresentation );
        res.sucess = true;
        return res;
    }


    public String format(Double value) {
        System.out.println("point 20");
        String newTextMoney = this.numberFormat.format( value );
        if( this.positionMoneyCurrency == CurrencyPosition.LEFT ){
            System.out.println("point 21");
            newTextMoney = this.maskCurrencyName + newTextMoney;
        } else{
            System.out.println("point 22");
            newTextMoney = newTextMoney + maskCurrencyName;
        }
        return newTextMoney;
    }



    public Double getCurrentMoneyValue() {
        return lastResult.moneyNumberRepresentation;
    }


    public enum CurrencyPosition {
        LEFT,
        RIGHT
    }

    private class ResultTransformation {
        private boolean sucess = true;
        public Double moneyNumberRepresentation = .0;
        public int caretPosition;
        public String moneyTextRepresentation;
    }

    public interface OnValidNumberInput {
        void onValidNumberInput(KeyEvent keyEvent, Double oldValue, Double newValue, String newTextFormat );
    }

    public interface OnInvalidNumberInput {
        void onInvalidNumberInout(KeyEvent keyEvent, Double oldValue, String failedTextFormat );

    }
    public interface OnRejectInput {
        void onRejectInput( KeyEvent keyEvent, Double oldValue, String failedTextFormat );

    }
}
