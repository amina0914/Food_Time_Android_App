package ca.dawson511.wheretoeat


import android.R
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import ca.dawson511.wheretoeat.databinding.ActivityMainBinding
import kotlin.random.Random


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        binding.tryAgain.setOnClickListener(getRandomFood())
    }

        //Function that chooses random food from list and updates the text and image.
//        fun getRandomFood(){
//            val foods = this.resources.getStringArray(R.array.foods)
//            val randomIndex = Random.nextInt(foods.size)
//            val randomFood = foods[randomIndex]
//      }


}