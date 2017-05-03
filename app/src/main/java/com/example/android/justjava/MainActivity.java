package com.example.android.justjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    /**
     * The number of cups of coffee and coffee price as Class instance variables
     */
    private int quantity = 1;
    private static final int COFFEE_PRICE = 5;
    private static final int CREAM_PRICE = 1;
    private static final int CHOCOLATE_PRICE = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView orderQuantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        orderQuantityTextView.setText(String.valueOf(quantity));
    }

    /**
     * This method is called when the quantity is incremented.
     */
    public void increment(View view) {
        // set maximum mobile order limit to 20
        if (quantity == 20) {
            Toast toast = Toast.makeText(this, "You cannot make more than 20 mobile coffee orders", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, -90);
            toast.show();
            return;
        }
        quantity++;
        display(quantity);
    }

    /**
     * This method is called when the quantity is decremented.
     */
    public void decrement(View view) {
        // prohibit negative quantity
        if (quantity == 1) {
            Toast toast = Toast.makeText(this, "You cannot have less than 1 coffee", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
            return;
        }
        quantity--;
        display(quantity);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        EditText userNameEditTextView = (EditText) findViewById(R.id.get_name);
        String userName = userNameEditTextView.getText().toString();
        CheckBox checkForCream = (CheckBox) findViewById(R.id.cream_checkbox);
        boolean hasWhippedCream = checkForCream.isChecked();
        CheckBox checkForChocolate = (CheckBox) findViewById(R.id.chocolate_checkbox);
        boolean hasChocolate = checkForChocolate.isChecked();
        int total = calculatePrice(hasWhippedCream, hasChocolate);
        displayMessage(createOrderSummary(total, hasWhippedCream, hasChocolate, userName));
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
     * @param addCream a boolean for whether or not the user wants whipped cream
     * @param addChocolate a boolean for whether or not the user wants chocolate
     * @return an int that indicates the total price of the coffee
     *
     */
    private int calculatePrice(boolean addCream, boolean addChocolate) {
        int price = quantity * COFFEE_PRICE;
        if (addCream) price += quantity * CREAM_PRICE;
        if (addChocolate) price += quantity * CHOCOLATE_PRICE;
        return price;
    }

    /**
     *
     * @param orderTotal an int that indicates the total price of the coffee
     * @param addCream a boolean for whether or not the user wants whipped cream
     * @param addChocolate a boolean for whether or not the user wants chocolate
     * @return a String that lists the order summary one item per line
     */
    private String createOrderSummary(int orderTotal, boolean addCream, boolean addChocolate, String name) {
        String cream = addCream? "Yes" : "No";
        String chocolate = addChocolate ? "Yes" : "No";
        return "Name: " + name + "\nQuantity: " + quantity + "\nCream: " + cream + "\nChocolate: " + chocolate + "\nTotal: $" + orderTotal +
                "\nThank you!";
    }

}
