package ca.dawson511.wheretoeat

import android.R
import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ca.dawson511.wheretoeat.databinding.ActivityFoodListBinding



class Adapter (var context: Context, var foodsList: Array<String>) :
    RecyclerView.Adapter<Adapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

//        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)

        val layout = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.each_food_view, parent, false)

        return ViewHolder(layout)
//        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        // TypeCast Object to int type
//        val res = courseImg[position] as Int
//        holder.images.setImageResource(res)
//        holder.text.text = courseName[position] as String

        val food = foodsList[position]
        val context = holder.view.context
        holder.button.text = food

//        holder.button.setOnClickListener {
//            ...
        }

    override fun getItemCount(): Int = foodsList.size




    // Initializing the Views
    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val button = view.findViewById<Button>(R.id.food_button)
    }
}