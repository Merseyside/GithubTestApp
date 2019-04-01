package com.example.githubtestapp.presentation.view.activity.mainActivity.view

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProviders
import com.example.githubtestapp.BR
import com.example.githubtestapp.R
import com.example.githubtestapp.databinding.ActivityMainBinding
import com.example.githubtestapp.presentation.base.BaseGithubActivity
import com.example.githubtestapp.presentation.di.components.DaggerMainActivityComponent
import com.example.githubtestapp.presentation.di.modules.MainActivityModule
import com.example.githubtestapp.presentation.view.activity.authFragment.AuthActivity
import com.example.githubtestapp.presentation.view.activity.mainActivity.model.MainViewModel
import com.example.githubtestapp.presentation.view.activity.mainActivity.model.SharedViewModel
import com.google.android.material.snackbar.Snackbar
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import ru.terrakok.cicerone.commands.Command
import javax.inject.Inject

class MainActivity : BaseGithubActivity<ActivityMainBinding, MainViewModel>() {

    private lateinit var sharedViewModel : SharedViewModel

    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    private var navigator : Navigator = object : SupportAppNavigator(this, R.id.container) {

        override fun applyCommand(command: Command?) {
            super.applyCommand(command)
            supportFragmentManager.executePendingTransactions()
        }

        override fun setupFragmentTransaction(command: Command?,
                                              currentFragment: Fragment?,
                                              nextFragment: Fragment?,
                                              fragmentTransaction: FragmentTransaction?) {
            super.setupFragmentTransaction(command, currentFragment, nextFragment, fragmentTransaction)
            fragmentTransaction!!.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
        }
    }

    override fun loadingObserver(isLoading: Boolean) {}

    override fun performInjection() {
        DaggerMainActivityComponent.builder()
            .appComponent(getAppComponent())
            .mainActivityModule(getMainActivityModule())
            .build().inject(this)
    }

    private fun getMainActivityModule() : MainActivityModule {
        return MainActivityModule(this)
    }

    override fun setBindingVariable(): Int {
        return BR.viewModel
    }

    override fun setLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun onCreate(savedInstance: Bundle?) {
        super.onCreate(savedInstance)

        doLayout()

        if (savedInstance == null)
            init()
    }

    private fun doLayout() {
        viewDataBinding.fab.setOnClickListener {
            navigateToAuthActivity()
        }
    }

    private fun init() {
        sharedViewModel = ViewModelProviders.of(this).get(SharedViewModel::class.java)

        viewModel.navigateToMainFragment()
    }

    override fun onBackPressed() {
        viewModel.onBackPressed()
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        navigatorHolder.removeNavigator()
        super.onPause()
    }

    private fun navigateToAuthActivity() {
        val intent = Intent(this, AuthActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NO_HISTORY
        intent.putExtra(AuthActivity.SIGN_OUT_KEY, true)

        startActivity(intent)
        finish()
    }
}