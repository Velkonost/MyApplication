package com.velkonost.sample.myapplication.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import com.velkonost.sample.myapplication.R
import com.velkonost.sample.myapplication.databinding.FragmentDishDetailBinding
import com.velkonost.sample.myapplication.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : BaseFragment<FragmentDishDetailBinding, DetailViewModel>(
    R.layout.fragment_dish_detail
) {

    companion object {
        private const val ITEM_ID = "item_id"

        fun getBundle(itemId: String): Bundle {
            return bundleOf(ITEM_ID to itemId)
        }
    }

    override val bindingInflater: (LayoutInflater) -> FragmentDishDetailBinding
        get() = FragmentDishDetailBinding::inflate

    private val itemId: String? by lazy { arguments?.getString(ITEM_ID) }

    override fun setup(savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(this)[DetailViewModel::class.java]

        itemId?.let { viewModel.load(it) }

        viewModel.dish.observe(viewLifecycleOwner) { item ->
            binding.dish = item
        }
    }
}