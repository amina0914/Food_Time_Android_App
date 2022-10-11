package ca.dawson511.wheretoeat


//import android.R
//import android.R
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ca.dawson511.wheretoeat.databinding.ActivityMainBinding
import kotlin.random.Random


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

        binding.foodImage.setOnClickListener {
            if (randomFood != null) {
                getMap(randomFood)
            }
        }


        binding.searchButton.setOnClickListener {
            if (randomFood != null) {
                getSearch(randomFood)
            }
        }

        binding.editListButton.setOnClickListener { callListActivity() }
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

        // * For now latitude and longitude hardcoded
        //Function that shows the closest restaurants on the map based on the chosen food
        fun getMap(randomFood : String) {
            val locationUri = Uri.parse("geo:0,0?q=${randomFood}")

            val mapIntent = Intent(Intent.ACTION_VIEW, locationUri)
            mapIntent.setPackage("com.google.android.apps.maps")
            startActivity(mapIntent)

        }

        //Function that searches for more info for the specified food
        fun getSearch(randomFood : String) {
            val queryUrl: Uri = Uri.parse("${infoObj.SEARCH_PREFIX}${randomFood}")
            val intent = Intent(Intent.ACTION_VIEW, queryUrl)
            startActivity(intent)
        }

        fun callListActivity() {
            val intent = Intent(this, FoodList::class.java)
            startActivity(intent)
        }



}