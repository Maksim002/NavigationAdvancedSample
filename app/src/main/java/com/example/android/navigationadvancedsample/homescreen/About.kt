/*
 * Copyright 2019, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.navigationadvancedsample.homescreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.android.navigationadvancedsample.R
import java.util.HashMap

class About : Fragment() {
    private var viewModel = LoansViewModel()
    private var position = 0

    private var ex = false
    private lateinit var homeViewModel: HomeViewModel
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)
        return inflater.inflate(R.layout.fragment_about, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        iniRecyclerView(view)
    }

    init {
        val map = HashMap<String, String>()
        map.put("login", "143382")
        map.put("token", "d67d8ab4f4c10bf22aa353e27879133c")
        if (viewModel.listNewDta.value == null)
            viewModel.listNews(map)
    }

    private fun iniRecyclerView(view: View) {
        viewModel.listNewDta.observe(viewLifecycleOwner, Observer { result ->
            view.findViewById<TextView>(R.id.data_text).text = result.result[position].description
        })
    }
}


