package com.example.android.justjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    /**
     * The number of cups of coffee and coffee price as Class instance variables
     */
    private int quantity = 0;
    private static final int COFFEE_PRICE = 5;
    // private static final int CUP_PRICE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the quantity is incremented.
     */
    public void increment(View view) {
        // maximum mobile order limit set to 20
        if (quantity < 20) quantity++;
        display(quantity);
    }

    /**
     * This method is called when the quantity is decremented.
     */
    public void decrement(View view) {
        // prohibit negative quantity
        if (quantity > 0) quantity--;
        display(quantity);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        int total = calculatePrice();
        //String priceMessage = "Total: $" + total + "\nThank you!";
        displayMessage(createOrderSummary(total));
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }

    private int calculatePrice() {
        return quantity * COFFEE_PRICE;
    }

    private String createOrderSummary(int orderTotal) {
        return "Name: Kaptain Kunal\nQuantity: " + quantity + "\nTotal: $" + orderTotal +
                "\nThank you!";
    }
}
