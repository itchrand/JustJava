// release-0.1
package it.seahawk.justjava

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.view.View
import android.widget.CheckBox
import android.widget.EditText
import java.text.NumberFormat
import java.util.*
import android.widget.Toast
import android.content.Intent
import android.net.Uri


class MainActivity : AppCompatActivity() {

    private val pricePerCoffee : Int = 5
    private val pricePerWhippedCream : Int = 1
    private val pricePerChocolate : Int = 2
    private var name : String = ""
    private var quantity : Int = 1
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
        getName()
        getCheckBoxStates()
        calculatePrice()
        displayQuantity(quantity)

        val intent = Intent(Intent.ACTION_SENDTO)
        intent.data = Uri.parse("mailto:") // only email apps should handle this
        //intent.putExtra(Intent.EXTRA_EMAIL, addresses)
        intent.putExtra(Intent.EXTRA_SUBJECT, "Java order")
        intent.putExtra(Intent.EXTRA_TEXT, createOrderSummary())
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        }
        // displayOrderSummary(createOrderSummary())
      }

    /**
     * This method is called when the + button is clicked.
     */
    fun increment(view: View) {
        if (quantity < 100){
            quantity += 1
            displayOrderSummary()
        }
        else giveAToast("You can not order more than 100 cups")
    }

    /**
     * This method is called when the - button is clicked.
     */
    fun decrement(view: View) {
        if (quantity > 1) {
            quantity -= 1
            displayOrderSummary()
        }
        else giveAToast ("You can not order less than 1 cup")
    }

    /**
     * This method gets the status of checkboxes.
     */
    private fun giveAToast(message: String) {
        val context = applicationContext
        val duration = Toast.LENGTH_SHORT

        val toast = Toast.makeText(context, message, duration)
        toast.show()
    }

    /**
     * This method gets the status of checkboxes.
     */
    private fun getName() {
        var editText = findViewById<EditText>(R.id.name_view) as EditText
        name = editText.text.toString()
    }

    /**
     * This method gets the status of checkboxes.
     */
    private fun getCheckBoxStates() {
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
    private fun displayOrderSummary() {
        getName()
        getCheckBoxStates()
        calculatePrice()
        displayQuantity(quantity)

        val orderSummaryTextView = findViewById<TextView>(R.id.orderSummary_text_view) as TextView
        orderSummaryTextView.text = createOrderSummary()
    }

    /**
     * This method calculates the price.
     */
    private fun calculatePrice() {
        var pricePerQuantity : Int = pricePerCoffee

        if (hasWhippedCream) pricePerQuantity += pricePerWhippedCream
        if (hasChocolate) pricePerQuantity += pricePerChocolate

        price = quantity * pricePerQuantity
    }

    /**
     * This method creates the order summary.
     */
    private fun createOrderSummary(): String {

        var orderSummary: String
        orderSummary = "Name: $name"
        orderSummary += "\nQuantity: $quantity"
        orderSummary += "\nAdd whipped cream? $hasWhippedCream"
        orderSummary += "\nAdd chocolate? $hasChocolate"
        orderSummary += "\n${NumberFormat.getCurrencyInstance(Locale.ITALY).format(price)}"
        orderSummary += "\nThank you!"
        Log.v("MainActivity","Calculated price after submit order: " + price)

        return orderSummary

    }
}
