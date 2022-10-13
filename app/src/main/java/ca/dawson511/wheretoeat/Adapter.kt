package ca.dawson511.wheretoeat

//import android.R
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView


class Adapter (var context: Context, var foodsList: Array<String>) :
    RecyclerView.Adapter<Adapter.ViewHolder>() {

    private var foodslist: MutableList<String> = foodsList.toMutableList()
    private var thisContext : Context = context


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

//        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)

        val layout = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.each_food_view, parent, false)

        return ViewHolder(layout)
//        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

//        this.foodslist = foodsList.toMutableList()
        val food = foodslist[position]
        val context = holder.view.context
        holder.button.text = food

        holder.button.setOnClickListener {
                val food = holder.button.text.toString()
                val foodId = foodslist.indexOf(food)
                if (this.foodslist.size == 1) {
                    val message = "Last item of the list can not be removed"
                    Toast.makeText(thisContext, message, Toast.LENGTH_LONG).show()
                } else {
                    foodslist.remove(food)
                    (context as FoodList).removeFromList(food)
                    notifyItemRemoved(foodId)
                }
            }
        }

    fun addItem(newFood : String) {
        foodslist.add(newFood)
        val foodId = foodslist.indexOf(newFood)
        notifyItemInserted(foodId)
    }


    override fun getItemCount(): Int = foodslist.size


    // Initializing the Views
    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val button = view.findViewById<Button>(R.id.food_button)
    }
}