package com.example.android.justjava;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

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
    }

    /**
     * This method is called when the quantity is incremented.
     */
    public void increment(View view) {
        // set maximum mobile order limit to 20
        if (quantity == 20) {
            Toast toast = Toast.makeText(this, getText(R.string.toast_for_large), Toast.LENGTH_SHORT);
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
            Toast toast = Toast.makeText(this, getText(R.string.toast_for_negative), Toast.LENGTH_SHORT);
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
        // Find the user's name
        EditText userNameEditTextView = (EditText) findViewById(R.id.get_name);
        String userName = userNameEditTextView.getText().toString();

        // Check if user wants Whipped Cream
        CheckBox checkForCream = (CheckBox) findViewById(R.id.cream_checkbox);
        boolean hasWhippedCream = checkForCream.isChecked();

        // Check if user wants Chocolate
        CheckBox checkForChocolate = (CheckBox) findViewById(R.id.chocolate_checkbox);
        boolean hasChocolate = checkForChocolate.isChecked();

        // Compute price and Order Summary
        int total = calculatePrice(hasWhippedCream, hasChocolate);
        String orderSummary = createOrderSummary(total, hasWhippedCream, hasChocolate, userName);

        String subject = getText(R.string.subject) + " " + userName;
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, orderSummary);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText(String.valueOf(number));
    }

    /**
     * @param addCream     a boolean for whether or not the user wants whipped cream
     * @param addChocolate a boolean for whether or not the user wants chocolate
     * @return an int that indicates the total price of the coffee
     */

    private int calculatePrice(boolean addCream, boolean addChocolate) {
        int price = quantity * COFFEE_PRICE;
        if (addCream) price += quantity * CREAM_PRICE;
        if (addChocolate) price += quantity * CHOCOLATE_PRICE;
        return price;
    }

    /**
     * @param orderTotal   an int that indicates the total price of the coffee
     * @param addCream     a boolean for whether or not the user wants whipped cream
     * @param addChocolate a boolean for whether or not the user wants chocolate
     * @return a String that lists the order summary one item per line
     */
    private String createOrderSummary(int orderTotal, boolean addCream, boolean addChocolate, String name) {
        String cream = addCream ? getString(R.string.yes) : getString(R.string.no);
        String chocolate = addChocolate ? getString(R.string.yes) : getString(R.string.no);
        return getString(R.string.order_summary_name, name) +
                "\n" + getString(R.string.order_summary_quantity, quantity) +
                "\n" + getString(R.string.order_summary_cream, cream) +
                "\n" + getString(R.string.order_summary_chocolate, chocolate) +
                "\n" + getString(R.string.order_summary_total, NumberFormat.getCurrencyInstance().format(orderTotal)) +
                "\n" + getText(R.string.order_summary_ty);
    }

}
