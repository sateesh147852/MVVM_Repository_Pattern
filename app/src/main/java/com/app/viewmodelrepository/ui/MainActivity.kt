package com.app.viewmodelrepository.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.viewmodelrepository.adapter.QuotesAdapter
import com.app.viewmodelrepository.api.QuoteService
import com.app.viewmodelrepository.api.RetrofitHelper
import com.app.viewmodelrepository.databinding.ActivityMainBinding
import com.app.viewmodelrepository.db.QuoteDao
import com.app.viewmodelrepository.db.QuoteDataBase
import com.app.viewmodelrepository.repository.QuoteRepository
import com.app.viewmodelrepository.viewModel.MainViewModel
import com.app.viewmodelrepository.viewModel.ViewModelFactory
import kotlinx.coroutines.launch
import retrofit2.Retrofit

class MainActivity : AppCompatActivity() {

  private lateinit var retrofit: Retrofit
  private lateinit var adapter: QuotesAdapter
  private lateinit var binding: ActivityMainBinding
  private lateinit var viewModel: MainViewModel
  private lateinit var quoteRepository: QuoteRepository
  private lateinit var viewModelFactory: ViewModelFactory
  private lateinit var quoteDao: QuoteDao

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivityMainBinding.inflate(layoutInflater)
    setContentView(binding.root)

    initViewModel()
    setRecyclerViewData()
  }

  private fun setRecyclerViewData() {
    adapter = QuotesAdapter()
    binding.rvResults.layoutManager = LinearLayoutManager(this)
    binding.rvResults.adapter = adapter
  }

  private fun initViewModel() {
    quoteDao = QuoteDataBase.getInstance(this)!!.quoteDao()
    quoteRepository =
      QuoteRepository(RetrofitHelper.getInstance().create(QuoteService::class.java), this, quoteDao)
    viewModelFactory = ViewModelFactory(quoteRepository)
    viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)

    viewModel.getQuotes().observe(this, Observer {
      Toast.makeText(this, it.size.toString(), Toast.LENGTH_SHORT).show()
      lifecycleScope.launch {
        quoteDao.insertAll(it)
      }
      adapter.setData(it)
      adapter.notifyDataSetChanged()

    })

  }
}