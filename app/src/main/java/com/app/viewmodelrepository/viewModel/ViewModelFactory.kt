package com.app.viewmodelrepository.viewModel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.app.viewmodelrepository.repository.QuoteRepository

class ViewModelFactory(val quoteRepository: QuoteRepository) : ViewModelProvider.Factory {

  override fun <T : ViewModel?> create(modelClass: Class<T>): T {
    return MainViewModel(quoteRepository) as T
  }
}