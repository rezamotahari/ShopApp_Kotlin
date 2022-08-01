package com.example.novinshop_project.feature.auth

object TokenContainer {
    var token :String? =null
    private set
    fun updateToken(token:String?)
    {
        this.token = token
    }

}