// release-0.1
package it.seahawk.justjava

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.view.View
import android.widget.CheckBox
import java.text.NumberFormat
import java.util.*


class MainActivity : AppCompatActivity() {

    private val pricePerCoffee : Int = 5
    private var quantity : Int = 0
    private var price : Int = 0
    private var hasWhippedCream : Boolean = false
    private var hasChocolate : Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    /**
     * This method is called when the order button is clicked.
     */
    fun submitOrder(view: View) {
        getCheckBoxState()
        displayQuantity(quantity)
        displayOrderSummary(createOrderSummary())
      }

    /**
     * This method is called when the + button is clicked.
     */
    fun increment(view: View) {
        quantity += 1
        price = calculatePrice(quantity, pricePerCoffee)
        submitOrder(view)
    }

    /**
     * This method is called when the - button is clicked.
     */
    fun decrement(view: View) {
        if (quantity > 0) quantity -= 1
        price = calculatePrice(quantity,pricePerCoffee)
        submitOrder(view)
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private fun getCheckBoxState() {
        var checkBox = findViewById<CheckBox>(R.id.whipped_cream_checkbox) as CheckBox
        hasWhippedCream = checkBox.isChecked
        checkBox = findViewById<CheckBox>(R.id.chocolate_checkbox) as CheckBox
        hasChocolate = checkBox.isChecked
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private fun displayQuantity(quantity: Int) {
        val quantityTextView = findViewById<TextView>(R.id.quantity_text_view) as TextView
        quantityTextView.text = "$quantity"
    }

    /**
     * This method displays the given price on the screen.
     */
    private fun displayOrderSummary(orderSummary: String) {
        val orderSummaryTextView = findViewById<TextView>(R.id.orderSummary_text_view) as TextView
        orderSummaryTextView.text = orderSummary
    }

    /**
     * This method calculates the price.
     */
    private fun calculatePrice(quantity: Int, price: Int) : Int = quantity * price

    /**
     * This method creates the order summary.
     */
    private fun createOrderSummary(): String {

        var orderSummary: String
        orderSummary = "Name: Kaptain Kunal"
        orderSummary += "\nQuantity: $quantity"
        orderSummary += "\nAdd whipped cream? $hasWhippedCream"
        orderSummary += "\nAdd chocolate? $hasChocolate"
        orderSummary += "\n${NumberFormat.getCurrencyInstance(Locale.ITALY).format(price)}"
        orderSummary += "\nThank you!"
        Log.v("MainActivity","Calculated price after submit order: " + price)

        return orderSummary

    }
}
