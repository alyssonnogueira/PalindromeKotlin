package com.example.alyss.palindrome.viewModels

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.content.Context
import com.example.alyss.palindrome.models.Palindrome
import com.example.alyss.palindrome.repositories.PalindromeRepository
import io.realm.RealmList
import javax.inject.Inject

class MainViewModel(context: Context) : ViewModel() {

    @Inject
    lateinit var palindromeRepository: PalindromeRepository

    var loading: MutableLiveData<Boolean> = MutableLiveData()

    var palindromes: RealmList<Palindrome>? = RealmList()

    init {
        loading.value = false
        palindromeRepository = PalindromeRepository(context)
        palindromes = palindromeRepository.palindromes
    }

    fun isPalindrome(word: String): Boolean {
        val invertWord = word.reversed()
        if (word.toLowerCase().equals(invertWord.toLowerCase())) {
            loading.postValue(true)
            var newWord = Palindrome()
            newWord.id = palindromeRepository.generateId()
            newWord.word = word
            palindromeRepository.saveObj(newWord)
            palindromeRepository.refreshList()
            loading.postValue(false)
            return true
        }

        return false
    }
}