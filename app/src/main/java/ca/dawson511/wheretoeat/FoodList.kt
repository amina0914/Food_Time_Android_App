package ca.dawson511.wheretoeat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ca.dawson511.wheretoeat.databinding.ActivityFoodListBinding


class FoodList : AppCompatActivity() {

    private lateinit var binding: ActivityFoodListBinding
    private lateinit var list: MutableList<String>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityFoodListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        list = (this.resources.getStringArray(R.array.foods).toMutableList())
        binding.foodList.text = list.toString()

        //Adds the food specified in the text box to the list, then update textview
        binding.addButton.setOnClickListener {
            list.add(binding.newFoodText.text.toString())
            binding.foodList.text = list.toString()
        }

        //Removes the food specified in the text box from the list (if possible), the update textview
        binding.removeButton.setOnClickListener {
            list.remove(binding.newFoodText.text.toString())
            binding.foodList.text = list.toString()
        }


    }

}