package com.emreozel.cryptoapp.ui.home

import androidx.fragment.app.viewModels
import com.emreozel.cryptoapp.base.BaseFragment
import com.emreozel.cryptoapp.databinding.FragmentHomeBinding
import com.emreozel.cryptoapp.utils.Constans.API_KEY
import com.emreozel.cryptoapp.utils.Constans.LIMIT

import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>(
    FragmentHomeBinding::inflate
) {
    override val viewModel by viewModels<HomeViewModel>()

    override fun onCreateFinished() {
        viewModel.getData(API_KEY,LIMIT)
    }

    override fun initializeListeners() {

    }

    override fun observeEvents() {

    }

}