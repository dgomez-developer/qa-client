package com.dgomez.developer.architecture.components.qa_client.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.dgomez.developer.architecture.components.qa_client.R

/**
 * @author Madrid Tech Lab on 2019-11-18.
 */
class OtherFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_create_question, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance() = OtherFragment()
    }

}