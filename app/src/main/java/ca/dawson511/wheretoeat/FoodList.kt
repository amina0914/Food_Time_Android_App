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
//        list = (this.resources.getStringArray(R.array.foods).toMutableList())
        list = getIntent().getExtras()?.getStringArray("foodsList")?.toMutableList()!!


        //RecyclerView
        val recyclerView = findViewById(R.id.rv_foods) as RecyclerView
        val linearLayoutManager = LinearLayoutManager(applicationContext)
        recyclerView.layoutManager = LinearLayoutManager(this)
//        recyclerView.adapter = Adapter(this, list.toTypedArray())
        val adapter = Adapter(this, list.toTypedArray())
        recyclerView.adapter = adapter



        //Adds the food specified in the text box to the list, then update textview
        binding.addButton.setOnClickListener {
            val newFood = binding.newFoodText.text.toString()
            list.add(newFood)
//            binding.foodList.text = list.toString()
//            adapter.notifyItemInserted(list.size-1 )
            adapter.addItem(newFood)

        }

//        //Removes the food specified in the text box from the list (if possible), the update textview
//        binding.removeButton.setOnClickListener {
//            list.remove(binding.newFoodText.text.toString())
//            binding.foodList.text = list.toString()
//        }



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

    fun removeFromList(foodToRemove : String) {
        list.remove(foodToRemove)
    }


}