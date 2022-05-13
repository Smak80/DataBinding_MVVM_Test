package ru.smak.databinding_mvvm_test.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import ru.smak.databinding_mvvm_test.R
import ru.smak.databinding_mvvm_test.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    private var binding: FragmentMainBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false)
        binding?.vm = ViewModelProvider(this)[MainViewModel::class.java]
        return binding?.root
    }

    companion object {
        @JvmStatic
        fun newInstance() = MainFragment()
    }
}