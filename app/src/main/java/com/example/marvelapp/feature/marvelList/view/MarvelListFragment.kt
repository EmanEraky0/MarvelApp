package com.example.marvelapp.feature.marvelList.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.marvelapp.R
import com.example.marvelapp.databinding.FragmentMarvelListBinding
import com.example.marvelapp.feature.marvelList.domain.models.Character
import com.example.marvelapp.feature.marvelList.viewModels.MarvelViewModel
import com.example.marvelapp.utils.UiResult
import com.example.marvelapp.utils.hideKeyboard
import org.koin.androidx.viewmodel.ext.android.viewModel


class MarvelListFragment : Fragment() {
    private val adapter = CharacterAdapter(arrayListOf())
    private var _binding: FragmentMarvelListBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MarvelViewModel by viewModel()
    private var charList: ArrayList<Character>? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMarvelListBinding.inflate(inflater, container, false)
        charList = arrayListOf()
        viewModel.fetchCharacters(null)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter.notifyItemChanged(0)
        binding.recycleMarvel.adapter = adapter

        binding.imgSearch.setOnClickListener {
            showSearch()
        }
        binding.txtCancel.setOnClickListener {
            hideSearch()
        }

        binding.edtSearch.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                this.hideKeyboard()
                charList = arrayListOf()
                viewModel.fetchCharacters(binding.edtSearch.text.toString())
                true
            } else {
                false
            }
        }
        observeChar()
        loadMore()
    }

    private fun showSearch() {
        binding.edtSearch.visibility = View.VISIBLE
        binding.txtCancel.visibility = View.VISIBLE
        binding.imgSearch.visibility = View.GONE
    }

    private fun hideSearch() {
        this.hideKeyboard()
        binding.edtSearch.setText("")
        binding.edtSearch.visibility = View.GONE
        binding.txtCancel.visibility = View.GONE
        binding.imgSearch.visibility = View.VISIBLE
        charList = arrayListOf()
        viewModel.reset()
        viewModel.fetchCharacters(null)
    }

    private fun loadMore() {
        binding.recycleMarvel.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            this.adapter = this@MarvelListFragment.adapter
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)
                    if (!recyclerView.canScrollVertically(1) && newState == RecyclerView.SCROLL_STATE_IDLE) {
                        if (binding.edtSearch.text.toString() != "")
                            viewModel.fetchCharacters(binding.edtSearch.text.toString())
                        else {
                            viewModel.fetchCharacters(null)
                        }
                    }
                }
            })
            this@MarvelListFragment.adapter.onItemClick = {
                val bundle =Bundle()
                bundle.putParcelable("character",it)
                findNavController().navigate(R.id.action_marvelListFragment_to_marvelDetailsFragment,bundle)
            }
        }
    }

    private fun observeChar() {
        viewModel.uiState.observe(viewLifecycleOwner) {
            when (it) {
                is UiResult.Loading -> {
                    binding.loading.visibility = View.VISIBLE
                }

                is UiResult.Success -> {
                    binding.loading.visibility = View.GONE
                    charList?.addAll(it.data?.results ?: arrayListOf())
                    adapter.updateAdapter(charList?: arrayListOf())
                }

                is UiResult.Error -> {
                    binding.loading.visibility = View.GONE
                }

                else -> {}
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewModel.clearUiState()
        viewModel.reset()
        charList = null
        _binding = null
    }
}