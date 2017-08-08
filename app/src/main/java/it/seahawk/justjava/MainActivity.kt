package it.seahawk.justjava

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.view.View
import java.text.NumberFormat
import java.util.Locale


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    /**
     * This method is called when the order button is clicked.
     */
    fun submitOrder(view: View) {
        val quantityTextView = findViewById(R.id.quantity_text_view) as TextView
        display(quantityTextView.text.toString().toInt() + 1)
        displayPrice(quantityTextView.text.toString().toInt() * 5)
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private fun display(number: Int) {
        val quantityTextView = findViewById(R.id.quantity_text_view) as TextView
        quantityTextView.text = "$number"
    }

    /**
     * This method displays the given price on the screen.
     */
    private fun displayPrice(number: Int) {
        val priceTextView = findViewById(R.id.price_text_view) as TextView
        priceTextView.setText(NumberFormat.getCurrencyInstance(Locale.ITALY).format(number))
    }
}
