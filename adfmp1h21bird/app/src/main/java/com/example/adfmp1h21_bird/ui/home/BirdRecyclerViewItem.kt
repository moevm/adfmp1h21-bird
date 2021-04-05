package com.example.adfmp1h21_bird.ui.home

class BirdRecyclerViewItem(val name:String, var birdImageId:Int, val birdId:Int) {

    fun setBirdImage(_BirdImageId: Int){
        this.birdImageId = _BirdImageId
    }
}