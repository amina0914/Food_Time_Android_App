package ca.dawson511.wheretoeat

//import android.R
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView


class Adapter (var context: Context, var foodsList: Array<String>) :
    RecyclerView.Adapter<Adapter.ViewHolder>() {

    private var foodslist: MutableList<String> = foodsList.toMutableList()


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
                foodslist.remove(food)
                val foodId = foodsList.indexOf(food)
                notifyItemRemoved(foodId)
            }
        }

    fun addItem(newFood : String) {
        val food = newFood
        foodslist.add(food)
        val foodId = foodsList.indexOf(food)
        notifyItemInserted(foodId)
    }


    override fun getItemCount(): Int = foodslist.size


    // Initializing the Views
    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val button = view.findViewById<Button>(R.id.food_button)
    }
}