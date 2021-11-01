package com.app.viewmodelrepository.repository

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.app.viewmodelrepository.api.QuoteService
import com.app.viewmodelrepository.db.QuoteDao
import com.app.viewmodelrepository.model.QuoteList
import com.app.viewmodelrepository.model.Result
import com.app.viewmodelrepository.utils.Utility
import com.app.viewmodelrepository.viewModel.MainViewModel

class QuoteRepository(private val quoteService: QuoteService, private val context: Context,val quoteDao : QuoteDao) {

  private var mutableLiveData = MutableLiveData<List<Result>>()
  private val TAG: String = MainViewModel::class.java.simpleName

  suspend fun getQuotes(page: Int) {
    if (Utility.isInternetAvailable(context)) {
      val result = quoteService.getQuotes(page)
      if (result?.body() != null && result.isSuccessful) {
        mutableLiveData?.postValue(result.body()!!.results)
      }
    } else {
      mutableLiveData?.postValue(quoteDao.getResults())
      Log.i(TAG, "getQuotes: " + "internet connection is not available")
    }

  }

  fun getLiveDataQuote(): MutableLiveData<List<Result>> {
    return mutableLiveData
  }
}