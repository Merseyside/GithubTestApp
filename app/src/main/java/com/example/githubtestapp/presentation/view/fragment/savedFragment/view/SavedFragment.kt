package com.example.githubtestapp.presentation.view.fragment.savedFragment.view

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.example.githubtestapp.BR
import com.example.githubtestapp.R
import com.example.githubtestapp.data.entity.mapper.RepositoryDataMapper
import com.example.githubtestapp.databinding.FragmentSavedBinding
import com.example.githubtestapp.domain.RepositoryModel
import com.example.githubtestapp.presentation.base.BaseGithubFragment
import com.example.githubtestapp.presentation.di.components.DaggerSavedFragmentComponent
import com.example.githubtestapp.presentation.di.modules.SavedFragmentModule
import com.example.githubtestapp.presentation.view.fragment.savedFragment.model.SavedFragmentViewModel
import com.example.githubtestapp.presentation.view.fragment.searchFragment.adapter.RepoAdapter
import com.example.githubtestapp.utils.WrapContentLinearLayoutManager
import javax.inject.Inject

class SavedFragment : BaseGithubFragment<FragmentSavedBinding, SavedFragmentViewModel>() {

    companion object {
        fun newInstance() : SavedFragment {
            return SavedFragment()
        }
    }

    private lateinit var adapter : RepoAdapter

    private val repoObserver = Observer<PagedList<RepositoryModel>> {
        adapter.submitList(it)
    }

    override fun loadingObserver(isLoading: Boolean) {}

    override fun performInjection() {
        DaggerSavedFragmentComponent.builder()
            .appComponent(getAppComponent())
            .savedFragmentModule(getSavedFragmentModule())
            .build().inject(this)
    }

    private fun getSavedFragmentModule() : SavedFragmentModule {
        return SavedFragmentModule(this)

    }

    override fun setBindingVariable(): Int {
        return BR.viewModel
    }

    override fun setLayoutId(): Int {
        return R.layout.fragment_saved
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        doLayout()
        init()
    }

    private fun doLayout() {
        viewDataBinding.repoList.layoutManager = WrapContentLinearLayoutManager(
            getApplicationContext(),
            LinearLayout.VERTICAL,
            false
        )

        adapter = RepoAdapter()
        viewDataBinding.repoList.adapter = adapter
    }

    private fun init() {
        viewModel.loadRepositories()

        viewModel.propertyLiveData.observe(this, repoObserver)

    }
}