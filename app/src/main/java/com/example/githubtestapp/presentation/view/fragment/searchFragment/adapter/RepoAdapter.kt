package com.example.githubtestapp.presentation.view.fragment.searchFragment.adapter

import android.util.Log
import android.widget.AdapterView
import com.example.githubtestapp.BR
import com.example.githubtestapp.R
import com.example.githubtestapp.data.db.RepoEntity
import com.example.githubtestapp.domain.RepositoryModel
import com.upstream.basemvvmimpl.presentation.adapter.BaseAdapter
import com.upstream.basemvvmimpl.presentation.adapter.BasePagedAdapter
import com.upstream.basemvvmimpl.presentation.view.BaseViewHolder

class RepoAdapter : BasePagedAdapter<RepositoryModel>(RepositoryModel.diffCallback) {

    private var longClickListener: OnLongClickListener? = null

    interface OnLongClickListener {
        fun onLongClick(repository: RepositoryModel)
    }

    private val TAG = javaClass.simpleName

    override fun getBindingVariable(): Int {
        return BR.obj
    }

    override fun getLayoutIdForPosition(position: Int): Int {
        return R.layout.view_repo
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)

        holder.itemView.setOnClickListener {
            Log.d(TAG, "onClick")
            getOnItemClickListener()?.onItemClicked(getItem(position) as RepositoryModel)
        }

        holder.itemView.setOnLongClickListener {
            longClickListener?.onLongClick(getItem(position) as RepositoryModel)

            true
        }
    }

    fun setOnLongClickListener(longClickListener : OnLongClickListener) {
        this.longClickListener = longClickListener
    }

}