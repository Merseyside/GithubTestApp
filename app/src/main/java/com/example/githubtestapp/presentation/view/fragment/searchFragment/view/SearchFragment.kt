package com.example.githubtestapp.presentation.view.fragment.searchFragment.view

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import androidx.lifecycle.*
import androidx.paging.PagedList
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.githubtestapp.BR
import com.example.githubtestapp.R
import com.example.githubtestapp.data.repository.datasource.State
import com.example.githubtestapp.databinding.FragmentSearchBinding
import com.example.githubtestapp.domain.RepositoryModel
import com.example.githubtestapp.presentation.base.BaseGithubFragment
import com.example.githubtestapp.presentation.di.components.DaggerSearchFragmentComponent
import com.example.githubtestapp.presentation.di.modules.SearchFragmentModule
import com.example.githubtestapp.presentation.view.activity.mainActivity.model.SharedViewModel
import com.example.githubtestapp.presentation.view.fragment.searchFragment.adapter.RepoAdapter
import com.example.githubtestapp.presentation.view.fragment.searchFragment.model.SearchFragmentViewModel
import com.example.githubtestapp.utils.WrapContentLinearLayoutManager
import com.upstream.basemvvmimpl.presentation.adapter.BaseAdapter



class SearchFragment : BaseGithubFragment<FragmentSearchBinding, SearchFragmentViewModel>(),
    SwipeRefreshLayout.OnRefreshListener, LifecycleObserver {

    private lateinit var sharedViewModel: SharedViewModel

    private val TAG = javaClass.simpleName

    private lateinit var adapter: RepoAdapter

    private val repoObserver = Observer<PagedList<RepositoryModel>> {

        Log.d(TAG, "onObserve size = ${it.size}")

        adapter.submitList(it)
        viewDataBinding.swipeContainer.isRefreshing = false
    }

    companion object {

        fun newInstance(): SearchFragment {
            return SearchFragment()
        }
    }

    override fun loadingObserver(isLoading: Boolean) {}

    override fun performInjection() {
        DaggerSearchFragmentComponent.builder()
            .appComponent(getAppComponent())
            .searchFragmentModule(getSearchFragmentModule())
            .build().inject(this)
    }

    private fun getSearchFragmentModule(): SearchFragmentModule {
        return SearchFragmentModule(this)
    }

    override fun setBindingVariable(): Int {
        return BR.viewModel
    }

    override fun setLayoutId(): Int {
        return R.layout.fragment_search
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sharedViewModel = ViewModelProviders.of(activity!!).get(SharedViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        doLayout()
        init()
    }

    private fun doLayout() {
        viewDataBinding.swipeContainer.setOnRefreshListener(this)

        viewDataBinding.repoList.layoutManager = WrapContentLinearLayoutManager(
            getApplicationContext(),
            LinearLayout.VERTICAL,
            false
        )

        adapter = RepoAdapter()
        adapter.addOnItemClickListener(object : BaseAdapter.AdapterClickListener {
            override fun onItemClicked(obj: Any) {

                if (obj is RepositoryModel) {
                    sharedViewModel.shareRepo(obj)
                }

                viewModel.navigateToDetailsScreen()
            }

        })

        adapter.setOnLongClickListener(object : RepoAdapter.OnLongClickListener {
            override fun onLongClick(repository: RepositoryModel) {

                viewModel.saveRepository(repository)
            }
        })

        viewDataBinding.repoList.adapter = adapter
    }

    private fun init() {
        viewModel.loadRepositories()

        viewModel.repoLiveData.observe(this, repoObserver)

        viewModel.getStateLiveData().observe(this, Observer<State> {
            when (it) {
                State.ERROR ->
                    showErrorMsg(it.getMessage(), "Retry") {
                        viewModel.refresh()
                    }

                else -> {
                }
            }
        })

    }

    override fun onRefresh() {
        adapter.submitList(null)

        viewModel.refresh()
    }

}