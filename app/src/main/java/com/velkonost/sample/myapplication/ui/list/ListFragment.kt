package com.velkonost.sample.myapplication.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.velkonost.sample.myapplication.R
import com.velkonost.sample.myapplication.databinding.FragmentDishesListBinding
import com.velkonost.sample.myapplication.ui.base.BaseFragment
import com.velkonost.sample.myapplication.ui.detail.DetailFragment
import com.velkonost.sample.myapplication.ui.list.recycler.DishListAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListFragment :
    BaseFragment<FragmentDishesListBinding, ListViewModel>(R.layout.fragment_dishes_list) {

    override val bindingInflater: (LayoutInflater) -> FragmentDishesListBinding
        get() = FragmentDishesListBinding::inflate

    private val dishListAdapter: DishListAdapter by lazy {
        DishListAdapter(
            onCheckedChange = { index, selected ->
                val dish = dishListAdapter.currentList[index]
                with(viewModel) {
                    dish.updateState(selected)
                }
            },
            onItemClick = { itemId ->
                findNavController().navigate(
                    R.id.action_to_detail,
                    DetailFragment.getBundle(itemId)
                )
            }
        )
    }

    override fun setup(savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(this)[ListViewModel::class.java]

        setupRecyclerView()
        setupDeleteButton()

        viewModel.loadDishes()
    }

    private fun setupRecyclerView() {
        binding.recyclerView.apply {
            adapter = dishListAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }

        viewModel.dishes.observe(viewLifecycleOwner) { items ->
            dishListAdapter.submitList(items)
            binding.deleteButton.isEnabled = items.any { it.selected }
        }
    }

    private fun setupDeleteButton() {
        binding.deleteButton.setOnClickListener {
            viewModel.removeSelectedDishes()
        }
    }

}