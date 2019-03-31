package com.example.githubtestapp.presentation.view.fragment.detailFragment.view

import android.os.Bundle
import android.view.View
import androidx.databinding.ObservableField
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.githubtestapp.BR
import com.example.githubtestapp.R
import com.example.githubtestapp.presentation.base.BaseGithubFragment
import com.example.githubtestapp.presentation.di.modules.DetailFragmentModule
import com.example.githubtestapp.presentation.view.fragment.detailFragment.model.DetailFragmentViewModel
import com.example.githubtestapp.databinding.FragmentDetailBinding
import com.example.githubtestapp.presentation.di.components.DaggerDetailFragmentComponent
import com.example.githubtestapp.presentation.view.activity.mainActivity.model.SharedViewModel

class DetailFragment : BaseGithubFragment<FragmentDetailBinding, DetailFragmentViewModel>() {

    private lateinit var sharedViewModel: SharedViewModel

    companion object {

        fun newInstance() : DetailFragment {
            return DetailFragment()
        }
    }

    override fun loadingObserver(isLoading: Boolean) {}

    override fun performInjection() {
        DaggerDetailFragmentComponent.builder()
            .appComponent(getAppComponent())
            .detailFragmentModule(getDetailFragmentModule())
            .build().inject(this)
    }

    private fun getDetailFragmentModule() : DetailFragmentModule {
        return DetailFragmentModule(this)
    }

    override fun setBindingVariable(): Int {
        return BR.viewModel
    }

    override fun setLayoutId(): Int {
        return R.layout.fragment_detail
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sharedViewModel = ViewModelProviders.of(activity!!).get(SharedViewModel::class.java)
        sharedViewModel.getRepoContainerLiveData().observe(this, Observer {
            viewModel.loadDetails(it.fullName)
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (savedInstanceState == null) {
            init()
        }
    }

    private fun doLayout() {

    }

    private fun init() {
        viewModel.repoDetailsLiveData.observe(this, Observer {
            viewModel.showDetails(it)
        })
    }


}