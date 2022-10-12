package ca.dawson511.wheretoeat

//import android.R
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import ca.dawson511.wheretoeat.databinding.ActivityFoodListBinding


class FoodList : AppCompatActivity() {

    private lateinit var binding: ActivityFoodListBinding
    private lateinit var list: MutableList<String>

    var returnList = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityFoodListBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        list = (this.resources.getStringArray(R.array.foods).toMutableList())
        list = getIntent().getExtras()?.getStringArray("foodsList")?.toMutableList()!!

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

        binding.saveButton.setOnClickListener { returnData() }


    }


    fun returnData() {
        val i = Intent()
        i.putExtra("newList", list.toTypedArray())
        setResult(RESULT_OK, i)
        returnList = true
        finish()
    }

    override fun finish() {
        if (!returnList) {
            val i = Intent()
            i.putExtra("newList", "Data dropped, back button was hit!")
            setResult(RESULT_CANCELED, i)
        }
        super.finish()
    }


}