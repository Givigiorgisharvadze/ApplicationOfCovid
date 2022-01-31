package com.example.testfinaluri

data class UserClickedModel(
    val name: String,
    val url: String,
    val year : String,
    val engine : String
) {
    constructor() : this(
        "",
        "",
        "",
        ""
    )
}
