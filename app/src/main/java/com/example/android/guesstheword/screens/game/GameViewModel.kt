package com.example.android.guesstheword.screens.game

import android.os.CountDownTimer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android.guesstheword.databinding.GameFragmentBinding

class GameViewModel : ViewModel() {

    private val _word = MutableLiveData<String>()
    private val _score = MutableLiveData<Int>()
    private val _timerText = MutableLiveData<String>()
    private val _eventGameFinish = MutableLiveData<Boolean>()

    //external
    val score: LiveData<Int> get() = _score
    val word : LiveData<String> get() = _word
    val eventGameFinish : LiveData<Boolean> get() = _eventGameFinish
    val timerText : LiveData<String> get() = _timerText

    private lateinit var timer : CountDownTimer

    companion object {
        // These represent different important times
        // This is when the game is over
        const val DONE = 0L
        // This is the number of milliseconds in a second
        const val ONE_SECOND = 1000L
        // This is the total time of the game
        const val COUNTDOWN_TIME = 60000L
    }


    init {

        timer = object : CountDownTimer(COUNTDOWN_TIME, ONE_SECOND) {

            override fun onTick(millisUntilFinished: Long) {
                // TODO implement what should happen each tick of the timer
            }

            override fun onFinish() {
                // TODO implement what should happen when the timer finishes
            }
        }

        timer.start()
        resetList()
        nextWord()


    }

    private lateinit var wordList : MutableList<String>

    fun resetList() {
        wordList = mutableListOf(
                "queen",
                "hospital",
                "basketball",
                "cat",
                "change",
                "snail",
                "soup",
                "calendar",
                "sad",
                "desk",
                "guitar",
                "home",
                "railway",
                "zebra",
                "jelly",
                "car",
                "crow",
                "trade",
                "bag",
                "roll",
                "bubble"
        )
        wordList.shuffle()
    }

    fun nextWord() {
        //Select and remove a word from the list
        if (wordList.isEmpty()) {
            resetList()
        } else {
            _word.value = wordList.removeAt(0)
        }
    }

    fun onSkip() {
        _score.value = (score.value)?.minus(1)
        nextWord()
    }

    fun onCorrect() {
        _score.value = (score.value)?.plus(1)
        nextWord()
    }

    fun onGameFinishComplete(){
        _eventGameFinish.value = false
    }

    override fun onCleared() {
        super.onCleared()
        timer.cancel()
    }

}