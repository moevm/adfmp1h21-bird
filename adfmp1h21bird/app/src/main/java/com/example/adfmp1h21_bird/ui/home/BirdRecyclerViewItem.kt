package com.example.adfmp1h21_bird.ui.home

import android.view.View

class BirdRecyclerViewItem(val name:String,
                           var birdImageId:Int,
                           val birdId:Int,
                           val birdTag: String) {

    fun setBirdImage(_BirdImageId: Int){
        this.birdImageId = _BirdImageId
    }
}

interface OnBirdClickListener{
    fun onBirdClick(v: View?, bird: BirdRecyclerViewItem)
}