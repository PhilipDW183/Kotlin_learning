package com.example.affirmations

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.affirmations.adapter.ItemAdapter
import com.example.affirmations.data.Datasource

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // load in Affirmations
        val myDataset = Datasource().loadAffirmations()
        // find the recylcer view
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        // tell the recyler view to use the item adapter
        // which expectes the context (thi) and the dataset
        recyclerView.adapter = ItemAdapter(this, myDataset)
        // layout of the recyclerview is fixed in the activity laytou
        // can set teh setHasFixedSize parameter to true
        // this improves performance
        recyclerView.setHasFixedSize(true)

    }
}