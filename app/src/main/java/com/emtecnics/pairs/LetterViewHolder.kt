package com.emtecnics.pairs

import android.util.Log
import android.view.View

import androidx.recyclerview.widget.RecyclerView
import com.emtecnics.pairs.databinding.ItemLetterBinding

class LetterViewHolder(view: View): RecyclerView.ViewHolder(view) {

    val binding = ItemLetterBinding.bind(view)



    fun render(letterCard: Letter, onClickListener: (Letter) -> Unit){


        binding.ivLetter.setImageResource(letterCard.image)

        if (letterCard.reversed == false){
            binding.ivLetter.setImageResource(R.drawable.back)


        } else {

            /*
            binding.ivLetter.animate().apply {
                duration = 1000
                //rotationYBy(180f)
                rotationY(360f)
                translationXBy(10f)

            }.start()

             */

        }



        itemView.setOnClickListener{
            Log.i("item", "${layoutPosition}")
            onClickListener(letterCard)


        }
    }

}

