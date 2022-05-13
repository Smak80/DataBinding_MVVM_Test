package ru.smak.databinding_mvvm_test.ui

import android.os.Bundle
import android.util.SizeF
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import ru.smak.databinding_mvvm_test.R
import ru.smak.databinding_mvvm_test.databinding.FragmentPaintBinding

class PaintFragment : Fragment() {

    private var binding: FragmentPaintBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_paint, container, false)
        binding?.vm = ViewModelProvider(this)[PaintViewModel::class.java]
        binding?.painter?.addMeasureListener { w, h ->
            binding?.vm?.setRandomFigures(SizeF(w, h))
        }

        return binding?.root
    }

    companion object {
        @JvmStatic
        fun newInstance() = PaintFragment()
    }
}