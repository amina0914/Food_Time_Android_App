/**
 * Author: Amina Turdalieva
 * Date: 14-10-2022
 * This is the food list activity that is responsible for updating the array of foods.
 * The user can add foods to the list or remove them.
 * */

package ca.dawson511.wheretoeat

//import android.R
import android.content.Intent
import android.os.Bundle
import android.widget.Adapter
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ca.dawson511.wheretoeat.databinding.ActivityFoodListBinding


class FoodList : AppCompatActivity() {

    private lateinit var binding: ActivityFoodListBinding
    private lateinit var list: MutableList<String>

    var returnList = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityFoodListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        list = getIntent().getExtras()?.getStringArray("foodsList")?.toMutableList()!!


        //RecyclerView
        val recyclerView = findViewById(R.id.rv_foods) as RecyclerView
        val linearLayoutManager = LinearLayoutManager(applicationContext)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = Adapter(this, list.toTypedArray())
        recyclerView.adapter = adapter


        //Adds the food specified in the text box to the list, then update textview
        binding.addButton.setOnClickListener {
            val newFood = binding.newFoodText.text.toString()
            list.add(newFood)

            adapter.addItem(newFood)
        }


        binding.saveButton.setOnClickListener { returnData() }


    }

    //Function used for the passing of data with the main activity activity
    fun returnData() {
        val i = Intent()
        i.putExtra("newList", list.toTypedArray())
        setResult(RESULT_OK, i)
        returnList = true
        finish()
    }

    //Function that defines what to return in case of errors
    override fun finish() {
        if (!returnList) {
            val i = Intent()
            i.putExtra("newList", "Data dropped, back button was hit!")
            setResult(RESULT_CANCELED, i)
        }
        super.finish()
    }

    //Function that removes an item from the list of foods
    fun removeFromList(foodToRemove : String) {
        list.remove(foodToRemove)
    }


}