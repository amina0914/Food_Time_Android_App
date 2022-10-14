/**
 * Author: Amina Turdalieva
 * Date: 14-10-2022
 * This is the adapter class
 * */
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

        val layout = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.each_food_view, parent, false)

        return ViewHolder(layout)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val food = foodslist[position]
        val context = holder.view.context
        holder.button.text = food

        holder.button.setOnClickListener {
                val food = holder.button.text.toString()
                val foodId = foodslist.indexOf(food)
                // If its the last item from the list, it can not be removed
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

    // Function that adds the food to the list of foods
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