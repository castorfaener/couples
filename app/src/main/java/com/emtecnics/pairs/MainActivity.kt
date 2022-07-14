package com.emtecnics.pairs

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import com.emtecnics.pairs.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var gameStarted: Boolean = false
    var score: Int = 0
    var maxScore: Int = 0
    var reversedCards = 0
    var firstCardReversed = false
    var firstCardId = -1
    var secondCardId = -1
    var firstCardUniqueId = -1
    var secondCardUniqueId = -1

    private val letterProvider = LetterProvider()

    private lateinit var mixedCards: List<Letter>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Hide the status bar.
        //window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        // Remember that you should never show the action bar if the
        // status bar is hidden, so hide that too if necessary.
        //actionBar?.hide()

        getSupportActionBar()?.hide()


        binding.tvScore.text = score.toString()

        binding.btnStart.setOnClickListener {
            if (!gameStarted) {
                binding.btnStart.text = getString(R.string.btnReset)
                gameStarted = true
                mixedCards = LetterProvider().mixCards()
                initRecyclerView()

            } else {
                mixedCards = LetterProvider().mixCards()
                for (i in mixedCards.indices) {
                    mixedCards[i].reversed = false
                }
                score = 0
                binding.tvScore.text = score.toString()
                initRecyclerView()
            }


        }


    }

    private fun initRecyclerView() {


        binding.rvCards.layoutManager = GridLayoutManager(this, 3)
        binding.rvCards.adapter = LetterAdapter(mixedCards) { onItemSelected(it) }
    }


    fun onItemSelected(card: Letter) {
        Log.i("itemSelected", "card ID =${card.id}, unique ID:${card.uniqueId}, reversed?:${card.reversed} RV0")


        //if is the first reversed card
        if(!firstCardReversed){
            firstCardReversed = true
            firstCardId = card.id
            firstCardUniqueId = card.uniqueId
            mixedCards[firstCardUniqueId].reversed = true
            initRecyclerView()
            Log.i("itemSelected", "card ID =${card.id}, unique ID:${card.uniqueId}, reversed?:${card.reversed} RV1")
        }else{
            //If is the second reversed card
            firstCardReversed = false
            secondCardId = card.id
            secondCardUniqueId = card.uniqueId
            mixedCards[secondCardUniqueId].reversed = true
            initRecyclerView()
            Log.i("itemSelected", "card ID =${card.id}, unique ID:${card.uniqueId}, reversed?:${card.reversed} RV2")

            if(firstCardId == secondCardId){
                score += 10
                binding.tvScore.text = score.toString()
                reversedCards += 2
                if(reversedCards == mixedCards.size){
                    reversedCards = 0
                    if(score > maxScore){
                        maxScore = score
                        binding.tvMaxScore.text = maxScore.toString()
                    }
                }

            }else{
                score -= 2
                binding.tvScore.text = score.toString()
                mixedCards[firstCardUniqueId].reversed = false
                mixedCards[secondCardUniqueId].reversed = false
                initRecyclerView()
                Log.i("itemSelected", "card ID =${card.id}, unique ID:${card.uniqueId}, reversed?:${card.reversed} RV3")
            }
        }







    }
}