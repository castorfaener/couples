package com.emtecnics.pairs

import android.util.Log

class LetterProvider {
        val letterList = listOf<Letter>(
            Letter(R.drawable.a,1),
            Letter(R.drawable.e,2),
            Letter(R.drawable.i,3),
            Letter(R.drawable.o,4),
            Letter(R.drawable.u,5),
            Letter(R.drawable.a,1),
            Letter(R.drawable.e,2),
            Letter(R.drawable.i,3),
            Letter(R.drawable.o,4),
            Letter(R.drawable.u,5)

        )

    /**
     * @return A shuffled list of card pairs
     */
    public fun mixCards(): List<Letter> {
        var mixed = mutableListOf<Letter>()
        var mixedReturned = listOf<Letter>()

        for(i in 0..9){
            mixed.add(letterList[i])

        }


        mixedReturned = mixed.shuffled()

        for(index in mixedReturned.indices) {
            mixedReturned[index].uniqueId = index
        }

        Log.i("Letter", "$mixedReturned")

        return mixedReturned


    }










}

