package com.app.viewmodelrepository.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.viewmodelrepository.model.Result
import com.app.viewmodelrepository.repository.QuoteRepository
import kotlinx.coroutines.launch

class MainViewModel(
  val quoteRepository: QuoteRepository
) : ViewModel() {

  init {
    viewModelScope.launch {
      quoteRepository.getQuotes(1)
    }
  }

  fun getQuotes(): MutableLiveData<List<Result>> {
    return quoteRepository.getLiveDataQuote()
  }

}