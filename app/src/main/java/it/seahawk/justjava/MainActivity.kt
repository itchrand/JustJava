// release-0.1
package it.seahawk.justjava

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.view.View


class MainActivity : AppCompatActivity() {

    private var quantity : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    /**
     * This method is called when the order button is clicked.
     */
    fun submitOrder(view: View) {
        displayQuantity(quantity)
        displayPrice(quantity * 5)
      }

    /**
     * This method is called when the + button is clicked.
     */
    fun increment(view: View) {
        quantity = quantity + 1
        submitOrder(view)
    }

    /**
     * This method is called when the - button is clicked.
     */
    fun decrement(view: View) {
        if (quantity > 0) quantity = quantity - 1
        submitOrder(view)
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private fun displayQuantity(number: Int) {
        val quantityTextView = findViewById(R.id.quantity_text_view) as TextView
        quantityTextView.text = "$number"
    }

    /**
     * This method displays the given price on the screen.
     */
    private fun displayPrice(number: Int) {
        val priceTextView = findViewById(R.id.price_text_view) as TextView
        priceTextView.setText("$(NumberFormat.getCurrencyInstance(Locale.ITALY).format(number))")
    }
}
