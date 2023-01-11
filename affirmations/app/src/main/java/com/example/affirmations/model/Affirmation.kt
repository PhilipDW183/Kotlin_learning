package com.example.affirmations.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

// pass in the string resource ID when creating an affirmation
data class Affirmation(
    @StringRes val stringResourceId: Int,
    @DrawableRes val imageResourceId: Int
    )