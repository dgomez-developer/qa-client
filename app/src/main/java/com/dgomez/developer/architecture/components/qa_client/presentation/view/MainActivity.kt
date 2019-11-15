package com.dgomez.developer.architecture.components.qa_client.presentation.view

import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dgomez.developer.architecture.components.qa_client.R

class MainActivity : AppCompatActivity(), CreateQuestionFragment.OnFragmentInteractionListener {

    override fun onFragmentInteraction(uri: Uri) {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

}
