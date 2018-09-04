package com.example.alyss.palindrome.models

import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass

@RealmClass
open class Palindrome : RealmObject() {
    @PrimaryKey
    var id: Int = 0
    var word: String? = null
}