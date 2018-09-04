package com.example.alyss.palindrome.views

import android.arch.lifecycle.Observer
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.View
import com.example.alyss.palindrome.R
import kotlinx.android.synthetic.main.activity_main.*
import com.example.alyss.palindrome.viewModels.MainViewModel

class MainActivity : AppCompatActivity() {

    val mainViewModel: MainViewModel = MainViewModel(this)
    private val spanCount: Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        palindromeRecyclerView.adapter = PalindromeAdapter(mainViewModel!!.palindromes!!, this, mainViewModel!!.palindromeRepository!!)

        val layoutManager = StaggeredGridLayoutManager(spanCount, StaggeredGridLayoutManager.VERTICAL)
        palindromeRecyclerView.layoutManager = layoutManager

        verifyButton.setOnClickListener {
            val word = wordEditText.text.toString()
            if (mainViewModel.isPalindrome(word)){
                wordTextView.text = "A Palavra $word é um Palindrome!"
            } else {
                wordTextView.text = "A Palavra $word não é um Palindrome!"
            }
        }
    }

    override fun onResume() {
        super.onResume()

        mainViewModel.loading.observe(this, Observer { it ->
            if (it != null) {
                if (mainViewModel.palindromes != null) {
                    palindromeRecyclerView.adapter.notifyItemInserted(mainViewModel.palindromes!!.size)
                }
            }
        })
    }

}
