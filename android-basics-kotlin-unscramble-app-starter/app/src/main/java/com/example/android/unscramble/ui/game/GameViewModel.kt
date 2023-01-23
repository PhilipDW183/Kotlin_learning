package com.example.android.unscramble.ui.game

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GameViewModel : ViewModel() {

    // move the data variables
    private val _score = MutableLiveData(0)
    val score: LiveData<Int>
        get() = _score

    private val _currentWordCount = MutableLiveData(0)
    val currentWordCount: LiveData<Int>
        get() = _currentWordCount

    private val _currentScrambledWord = MutableLiveData<String>()
    val currentScrambledWord: LiveData<String>
        get() = _currentScrambledWord

    // hold a list of words used in the game
    private var wordsList: MutableList<String> = mutableListOf()
    // hold the word the player is trying to unscramble
    private lateinit var currentWord: String


    init {
        Log.d("GameFragment", "GameViewModel created!")
        getNextWord()
    }

    override fun onCleared() {
        super.onCleared()
        Log.d("GameFragment", "GameViewModel destroyed!")
    }

    private fun getNextWord() {
        // get a random word from the allWordsList
        currentWord = allWordsList.random()
        // convert to an array of characters and assign it to a
        // new val
        val tempWord = currentWord.toCharArray()
        // shuffle the word
        tempWord.shuffle()


        // shuffle the word until different to the original
        while (String(tempWord).equals(currentWord, false)) {
            tempWord.shuffle()
        }
        // check if word has already been used
        if (wordsList.contains(currentWord)) {
            getNextWord()
        } else {
            _currentScrambledWord.value = String(tempWord)
            _currentWordCount.value = (_currentWordCount.value)?.inc()
            wordsList.add(currentWord)
        }
    }

    fun reinitializeData() {
        _score.value = 0
        _currentWordCount.value = 0
        wordsList.clear()
        getNextWord()
    }

    /*
    * Returns true if the current word count is less than MAX_NO_OF_WORDS.
    * Updates the next word.
    */
    fun nextWord(): Boolean {
        return if (_currentWordCount.value!! < MAX_NO_OF_WORDS) {
            getNextWord()
            true
        } else false
    }

    /*
    Helper function to increase score
     */
    private fun increaseScore() {
        _score.value = (_score.value)?.plus(SCORE_INCREASE)
    }

    /*
    Check if user is correct
     */
    fun isUserWordCorrect(playerWord: String): Boolean {
        if (playerWord.equals(currentWord, true)) {
            increaseScore()
            return true
        } else {
            return false
        }
    }


}