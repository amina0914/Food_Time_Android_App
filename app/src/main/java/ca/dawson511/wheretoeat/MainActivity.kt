/**
 * Author: Amina Turdalieva
 * Date: 14-10-2022
 * This is the WhereToEat application. When started it randomly chooses a food to eat.
 * The user can reroll to change the food, or even change the list by adding or removing items.
 * When the image is clicked, the user is referred to the google maps application to find the nearest restaurant with that chosen food.
 * The user also has the option to click on more info, which will redirect to a google search about the food chosen.
 * Finally, the user can change the language of the application.
 * */

package ca.dawson511.wheretoeat

//import android.R
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.LocaleList
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import ca.dawson511.wheretoeat.databinding.ActivityMainBinding
import java.util.*
import kotlin.random.Random

//This is the main class where the interaction of the first page happens.
class MainActivity : AppCompatActivity() {

    private val infoObj = object {
        val SEARCH_PREFIX = "https://www.google.com/search?q="
    }

    private lateinit var binding: ActivityMainBinding

    private lateinit var foodsList: Array<String>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadLocale()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Gets the array of foods from resources
        foodsList = resources.getStringArray(R.array.foods)

        val randomFood = getRandomFood(foodsList)

        val tryAgainButton = binding.tryAgain
        tryAgainButton.setOnClickListener { getRandomFood(foodsList) }


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

        binding.editListButton.setOnClickListener { callListActivity(foodsList ) }

        // The following code sets the translation to three languages: english, french and russian
        val changeLangButton: Button = findViewById(R.id.buttonChangeLang)
        changeLangButton.setText(R.string.change_lang)
        changeLangButton.setOnClickListener {
            val languages = arrayOf("Русский", "Français", "English")
            val langSelectorBuilder = AlertDialog.Builder(this@MainActivity)
            langSelectorBuilder.setTitle("Choose language:")
            langSelectorBuilder.setSingleChoiceItems(languages, -1) { dialog, selection ->
                when(selection) {
                    0 -> {
                        setLocale("ru")
                    }
                    1 -> {
                        setLocale("fr")
                    }
                    2 -> {
                        setLocale("en")
                    }
                }
                recreate()
                dialog.dismiss()
            }
            langSelectorBuilder.create().show()
        }
    }

        //Function used for the translation feature
        private fun setLocale(localeToSet: String) {
            val localeListToSet = LocaleList(Locale(localeToSet))
            LocaleList.setDefault(localeListToSet)
            resources.configuration.setLocales(localeListToSet)
            resources.updateConfiguration(resources.configuration, resources.displayMetrics)
            val sharedPref = getSharedPreferences("Settings", Context.MODE_PRIVATE).edit()
            sharedPref.putString("locale_to_set", localeToSet)
            sharedPref.apply()
        }

        //Function used for the translation feature
        private fun loadLocale() {
            val sharedPref = getSharedPreferences("Settings", Context.MODE_PRIVATE)
            val localeToSet: String = sharedPref.getString("locale_to_set", "")!!
            setLocale(localeToSet)
        }

       // Function that chooses random food from list and updates the text and image.
        fun getRandomFood(foods : Array<String>): String? {
            val randomIndex = Random.nextInt(foods.size)
            val randomFood = foods[randomIndex]
            val txtFood = binding.foodText
            txtFood.text= randomFood

           val imageId = resources.getIdentifier(randomFood.lowercase(), "drawable", packageName)
           if (imageId == 0) {
               binding.foodImage.setImageResource(resources.getIdentifier("default_food", "drawable", packageName))
           } else {
               binding.foodImage.setImageResource(imageId)
           }

           return randomFood

       }


        //Function that shows the closest restaurants on the map based on the chosen food
        fun getMap(randomFood : String) {
            val locationUri = Uri.parse("geo:45,?q=${randomFood} near me")

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

        //Function used for the passing of data with the foodlist activity
        fun callListActivity(foods : Array<String>) {
            val intent = Intent(this, FoodList::class.java)
            intent.putExtra("foodsList", foods);
            startActivityForResult(intent,0)
        }

        //Function used for the results from the foodlist activity
        override fun onActivityResult(request: Int, result: Int, i: Intent?) {
            super.onActivityResult(request, result, i)
            when (result) {
                RESULT_OK -> {
                    if (i != null) {
                        foodsList = i.extras!!.getStringArray("newList") as Array<String>
                    }

                }

            }

        }

}