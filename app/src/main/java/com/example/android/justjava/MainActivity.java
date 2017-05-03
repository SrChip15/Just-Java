package com.example.android.justjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
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
    private static final int CREAM_PRICE = 2;
    private static final int CHOCOLATE_PRICE = 3;

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
        CheckBox checkForCream = (CheckBox) findViewById(R.id.cream_checkbox);
        boolean hasWhippedCream = checkForCream.isChecked();
        CheckBox checkForChocolate = (CheckBox) findViewById(R.id.chocolate_checkbox);
        boolean hasChocolate = checkForChocolate.isChecked();
        int total = calculatePrice(hasWhippedCream, hasChocolate);
        displayMessage(createOrderSummary(total, hasWhippedCream, hasChocolate));
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

    /**
     *
     * @param hasCream a boolean for whether or not the user wants whipped cream
     * @param hasChocolate a boolean for whether or not the user wants chocolate
     * @return an int that indicates the total price of the coffee
     *
     */
    private int calculatePrice(boolean hasCream, boolean hasChocolate) {
        int price = quantity * COFFEE_PRICE;
        if (hasCream) price += quantity * CREAM_PRICE;
        if (hasChocolate) price += quantity * CHOCOLATE_PRICE;
        return price;
    }

    /**
     *
     * @param orderTotal an int that indicates the total price of the coffee
     * @param hasCream a boolean for whether or not the user wants whipped cream
     * @param hasChocolate a boolean for whether or not the user wants chocolate
     * @return a String that lists the order summary one item per line
     */
    private String createOrderSummary(int orderTotal, boolean hasCream, boolean hasChocolate) {
        String cream = hasCream? "Yes" : "No";
        String chocolate = hasChocolate ? "Yes" : "No";
        return "Name: Kaptain Kunal\nQuantity: " + quantity + "\nCream: " + cream + "\nChocolate: " + chocolate + "\nTotal: $" + orderTotal +
                "\nThank you!";
    }

}
