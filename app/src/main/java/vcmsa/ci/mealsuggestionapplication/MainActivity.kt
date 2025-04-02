package vcmsa.ci.mealsuggestionapplication

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.system.exitProcess

class MainActivity : AppCompatActivity() {

    private var timeInput: EditText? = null
    private var resultText: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val suggestButton = findViewById<Button>(R.id.suggestButton)
        val btnExit = findViewById<Button>(R.id.btnExit)
        val btnClear = findViewById<Button>(R.id.btnClear)

        // Initialize UI components
        timeInput = findViewById(R.id.timeInput)
        resultText = findViewById(R.id.resultText)


        btnExit.setOnClickListener {

            finishAffinity()
            exitProcess(0)
        }

        btnClear.setOnClickListener {
            timeInput?.text?.clear()
            resultText?.text = ""
        }

        suggestButton.setOnClickListener{
            suggestionMeal()
        }

    }

    private fun isNotEmpty(): Boolean {
        var b = true
        val timeText = timeInput?.text.toString().trim()
        if (timeText.isEmpty()) {
            timeInput?.error = "Input required!"
            b = false

        } else {
            val time = timeText.toIntOrNull()
            if (time == null) {
                timeInput?.error = "Invalid input. Please enter a valid number."
                b = false
            }
        }
        return b
    }


    private fun suggestionMeal() {
        if (isNotEmpty()) {
            val timeText = timeInput?.text.toString().trim()
            val time = timeText.toIntOrNull()
            if (time != null) {
                when (time) {


                    in 6..11 -> resultText?.text =
                        "Late night/Early morning suggestion: Herbal tea or water" +
                                "Oatmeal with fruits and nuts"

                    in 12..14 -> resultText?.text =
                        "lunch suggestion:chicken salad with avocado"

                    in 15..17 -> resultText?.text =
                        "Mid-afternoon snack suggestion: Greek yogurt with honey"

                    in 17..21 -> resultText?.text =
                        "dinner suggestion:Grilled salmon with roasted vegetables  "

                    in 22..23 -> resultText?.text =
                        "Late night snack suggestion: Mixed fruit bowl"


                    else -> {
                        resultText?.text = "No meals served at this time of day!"
                    }
                }
            }
        }
    }
}