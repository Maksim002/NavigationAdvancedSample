package com.example.android.navigationadvancedsample.homescreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kotlincashloan.service.model.Loans.GetNewsResultModel
import com.example.kotlincashloan.service.model.Loans.ListNewsModel
import com.example.kotlincashloan.service.model.Loans.ListNewsResultModel
import com.example.kotlinscreenscanner.service.model.CommonResponse
import com.timelysoft.tsjdomcom.service.NetworkRepository
import com.timelysoft.tsjdomcom.service.ResultStatus
import com.timelysoft.tsjdomcom.service.RetrofitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.HashMap

class LoansViewModel : ViewModel() {
    private val repository = NetworkRepository()

    val listNewDta = MutableLiveData<CommonResponse<ArrayList<ListNewsResultModel>>>()
    val error = MutableLiveData<String>()

    init {
        if (listNewDta.value == null)
            listNews()
    }

//        fun listNews(){
//            listNewDta.postValue(repository.listNews())
//        }

    fun listNews() {
        val map = HashMap<String, String>()
        map.put("login", "143382")
        map.put("token", "19ca14e7ea6328a42e0eb13d585e4c22")

        RetrofitService.apiService().listNews(map).enqueue(object : Callback<CommonResponse<ArrayList<ListNewsResultModel>>> {
            override fun onFailure(call: Call<CommonResponse<ArrayList<ListNewsResultModel>>>, t: Throwable) {
                error.postValue("Internet")
            }
            override fun onResponse(call: Call<CommonResponse<ArrayList<ListNewsResultModel>>>, response: Response<CommonResponse<ArrayList<ListNewsResultModel>>>) {
                if (response.isSuccessful) {
                    listNewDta.postValue(response.body())
                }
            }
        })
    }

    fun getNews(map: Map<String, String>): LiveData<ResultStatus<CommonResponse<GetNewsResultModel>>> {
        return repository.getNews(map)
    }
}