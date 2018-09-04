package com.example.alyss.palindrome.repositories

import android.content.Context
import com.example.alyss.palindrome.models.Palindrome
import io.realm.Realm
import io.realm.RealmList
import io.realm.RealmObject
import io.realm.Sort
import javax.inject.Inject
import javax.inject.Singleton

@Suppress("UNCHECKED_CAST")
@Singleton
class PalindromeRepository @Inject constructor(context: Context) {

    private var realm: Realm = Realm.getDefaultInstance()

    var palindromes: RealmList<Palindrome> = RealmList()

    init {
        this.openDB()
        val results = this.realm.where(Palindrome::class.java).findAll()
        palindromes.addAll(results.subList(0, results.size))
    }

    fun saveObj(obj: RealmObject) {
        this.openDB()

        realm.executeTransaction {
            it.copyToRealmOrUpdate(obj)
        }

       //realm.close()
    }

    fun findPalindrome(id: Int): Palindrome? {
        this.openDB()
        return this.realm.where(Palindrome::class.java).equalTo("id", id).findFirst()
    }

    private fun openDB(){
        if (realm.isClosed)
            realm = Realm.getDefaultInstance()
    }

    fun generateId(): Int {
        this.openDB()
        var id: Int? = this.realm.where(Palindrome::class.java).max("id")?.toInt()
        if (id != null)
            return id+1

        return 1
    }

    fun refreshList(){
        this.openDB()
        val results = this.realm.where(Palindrome::class.java).findAll()
        palindromes.addAll(results.subList(palindromes.size, results.size))
    }

}