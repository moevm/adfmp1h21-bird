package com.example.adfmp1h21_bird.note

class MyNote(
        val ID: Long,
        val name: String,
        var ImageURI: String,
        val geotag: String,
        val tags: String,
        val date: String,
        val comment: String){

    override fun toString(): String {
        return "{Id: $ID, name: \"$name\", ImageURI: \"$ImageURI\", geotag: \"$geotag\", tags: \"$tags\", date: \"$date\", comment: \"$comment\"}"
    }
}