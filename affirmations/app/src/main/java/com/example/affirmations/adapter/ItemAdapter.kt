package com.example.affirmations.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.affirmations.R
import com.example.affirmations.model.Affirmation

/**
 * Adapter for the [RecyclerView] in [MainActivity]. Displays [Affirmation] data object.
 */
class ItemAdapter(private val context: Context,
                  private val dataset: List<Affirmation>
                  ): RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {

    // provide a reference to the views for each data item
    // Complex data items may need more than one view per item
    // and you provide access to all the view for a data item in a view
    // holder
    // Each data item is just an Affirmation object
    class ItemViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView = view.findViewById(R.id.item_title)
    }

    /**
     * create new views (invoked by layout manager
     */
    // creates new viewholders for the recycler view
    // when tere are no existing view holders
    // that can be reused
    // parent: view group that the new list item
    // will be attached to as a child. Parent is RecylerView
    // viewType important when multiple viewtypes in the same
    // recycler view. You can only recycle views with the
    // same item view type
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        //create a new view from parent context
        // fill with list_item as part of parent group
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item, parent, false)

        // return new item view holder instance where the root view
        // is the adapterLayout
        return ItemViewHolder(adapterLayout)
    }

    /**
     * Replace the contents of a view (invoked by layout manager)
     */
    //called by the layout manager in order to replace the contents
    // a list item view
    // ItemViewHolder previously created
    // Int represents current item position in the list
    // used to find the right Affirmation object from the dataset based
    // on position
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = dataset[position]
        // fill text if text view with correct string
        holder.textView.text = context.resources.getString(item.stringResourceId)
    }

    /**
     * Return the size of the dataset (invoked by layout manager)
     */
    // returns the size of the dataset
    override fun getItemCount(): Int {
        return dataset.size
    }

}