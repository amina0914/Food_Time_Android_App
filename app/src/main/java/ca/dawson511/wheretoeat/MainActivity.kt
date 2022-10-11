package ca.dawson511.wheretoeat


//import android.R
//import android.R
import android.content.ContentValues.TAG
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import ca.dawson511.wheretoeat.databinding.ActivityMainBinding
import kotlin.random.Random
import android.Manifest


class MainActivity : AppCompatActivity() {

    private val infoObj = object {
        val LETTER = "letter"
        val SEARCH_PREFIX = "https://www.google.com/search?q="
    }

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val tryAgainButton = binding.tryAgain
        tryAgainButton.setOnClickListener { getRandomFood() }

        val randomFood = getRandomFood()

//        binding.foodImage.setOnClickListener { getMap() }


        binding.searchButton.setOnClickListener {
            if (randomFood != null) {
                getSearch(randomFood)
            }
        }
    }

       // Function that chooses random food from list and updates the text and image.
        fun getRandomFood(): String? {
            val foods = this.resources.getStringArray(R.array.foods)
            val randomIndex = Random.nextInt(foods.size)
            val randomFood = foods[randomIndex]
            val txtFood = binding.foodText
            txtFood.text= randomFood

           val imageId = resources.getIdentifier(randomFood.lowercase(), "drawable", packageName)
           binding.foodImage.setImageResource(imageId)

           return randomFood

       }

        fun getMap(geoLocation: Uri) {
            val intent = Intent(Intent.ACTION_VIEW).apply {
                data = geoLocation
            }
            if (intent.resolveActivity(packageManager) != null) {
                startActivity(intent)
            }
        }

        fun getSearch(randomFood : String) {
            val queryUrl: Uri = Uri.parse("${infoObj.SEARCH_PREFIX}${randomFood}")
            val intent = Intent(Intent.ACTION_VIEW, queryUrl)
            startActivity(intent)
        }


}