package com.example.affirmations

import android.content.Context
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.affirmations.adapter.ItemAdapter
import com.example.affirmations.model.Affirmation
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.Mockito.mock

class AffirmationsAdapterTests {

    // create a mock instance of context becaus we run on aJVM
    // not an actual device
    private val context = mock(Context::class.java)

    @Test
    fun adapater_size() {
        val data = listOf(
            Affirmation(R.string.affirmation1, R.drawable.image1),
            Affirmation(R.string.affirmation2, R.drawable.image2)
        )
        val adapter = ItemAdapter(context, data)
        assertEquals("ItemAdapter is not the correct size", data.size, adapter.itemCount)
    }

}