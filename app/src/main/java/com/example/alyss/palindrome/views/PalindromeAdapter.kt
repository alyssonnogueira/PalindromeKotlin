package com.example.alyss.palindrome.views

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.alyss.palindrome.R
import com.example.alyss.palindrome.models.Palindrome
import com.example.alyss.palindrome.repositories.PalindromeRepository
import io.realm.RealmList
import kotlinx.android.synthetic.main.card_view_palindrome.view.*

class PalindromeAdapter(val palindromeList: RealmList<Palindrome>, val context: Context, val palindromeRepository: PalindromeRepository) : RecyclerView.Adapter<PalindromeAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.card_view_palindrome, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = palindromeList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val palindrome = palindromeList[position]
        if (palindrome != null) {
            holder.let {
                it.bindView(palindrome, palindromeRepository)
            }
        }
    }

    class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {

        val word = itemView!!.palindromTextView!!

        fun bindView(palindrome: Palindrome, palindromeRepository: PalindromeRepository){
            word.text = palindrome.word
        }
    }
}