package com.example.githubtestapp.presentation.view.activity.authFragment


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.githubtestapp.R
import com.example.githubtestapp.presentation.view.activity.mainActivity.view.MainActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.SignInButton
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import kotlinx.android.synthetic.main.activity_auth.*

class AuthActivity : AppCompatActivity(), View.OnClickListener {

    private val TAG = "SignInActivity"
    private val RC_SIGN_IN = 9001

    private var mGoogleSignInClient: GoogleSignInClient? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        sign_in_button.setOnClickListener(this)
        sign_out_button.setOnClickListener(this)
        disconnect_button.setOnClickListener(this)

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso)

        sign_in_button.apply {
            setSize(SignInButton.SIZE_STANDARD)
            setColorScheme(SignInButton.COLOR_LIGHT)
        }
    }

    override fun onStart() {
        super.onStart()

        val account = GoogleSignIn.getLastSignedInAccount(this)
        updateUI(account)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN) {

            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleSignInResult(task)
        }
    }

    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account = completedTask.getResult(ApiException::class.java)

            updateUI(account)
        } catch (e: ApiException) {

            Log.w(TAG, "signInResult:failed code=" + e.statusCode)
            updateUI(null)
        }

    }

    private fun signIn() {
        val signInIntent = mGoogleSignInClient!!.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    private fun signOut() {
        mGoogleSignInClient!!.signOut()
            .addOnCompleteListener(this) {

                updateUI(null)
            }
    }

    private fun revokeAccess() {
        mGoogleSignInClient!!.revokeAccess()
            .addOnCompleteListener(this) {

                updateUI(null)
            }
    }

    private fun updateUI(account: GoogleSignInAccount?) {
        if (account != null) {
            navigateToMainActivity()

            status.text = getString(R.string.signed_in_fmt, account.displayName)

            sign_in_button.visibility = View.GONE
            sign_out_and_disconnect.visibility = View.VISIBLE
        } else {
            status.setText(R.string.signed_out)

            sign_in_button.visibility = View.VISIBLE
            sign_out_and_disconnect.visibility = View.GONE
        }
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.sign_in_button -> signIn()
            R.id.sign_out_button -> signOut()
            R.id.disconnect_button -> revokeAccess()
        }
    }

    private fun navigateToMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        startActivity(intent)
        finish()
    }

}