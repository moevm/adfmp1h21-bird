package com.example.adfmp1h21_bird.note

class MyNote(
        var ID: Int,
        var name: String,
        var ImageURI: String,
        var geotag: String,
        var tags: String,
        var date: String,
        var comment: String){

    override fun toString(): String {
        return "\n{Id: $ID, name: \"$name\", ImageURI: \"$ImageURI\", geotag: \"$geotag\", tags: \"$tags\", date: \"$date\", comment: \"$comment\"}"
    }
}