package com.velkonost.sample.myapplication.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<B : ViewBinding, V : BaseViewModel>(
    @LayoutRes val layout: Int
) : Fragment(layout) {

    abstract val bindingInflater: (LayoutInflater) -> B

    private var _binding: B? = null
    protected val binding: B get() = _binding as B

    lateinit var viewModel: V

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = bindingInflater.invoke(layoutInflater)
        return _binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setup(savedInstanceState)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    abstract fun setup(savedInstanceState: Bundle?)

}