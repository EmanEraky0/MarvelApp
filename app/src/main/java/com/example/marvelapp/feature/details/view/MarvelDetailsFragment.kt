package com.example.marvelapp.feature.details.view

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.marvelapp.R
import com.example.marvelapp.databinding.FragmentMarvelDetailsBinding
import com.example.marvelapp.databinding.ItemRecycleBinding
import com.example.marvelapp.databinding.SliderDialogBinding
import com.example.marvelapp.feature.details.viewModels.DetailsViewModel
import com.example.marvelapp.feature.marvelList.domain.models.Character
import com.example.marvelapp.feature.marvelList.domain.models.Item
import com.example.marvelapp.utils.UiResult
import com.example.marvelapp.utils.showImgGlide
import org.koin.androidx.viewmodel.ext.android.viewModel


class MarvelDetailsFragment : Fragment() {
    private var _binding: FragmentMarvelDetailsBinding? = null
    private val binding get() = _binding!!
    private val viewModel: DetailsViewModel by viewModel()
    private var characterId = 0
    private var character =Character()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        character = arguments?.getParcelable("character")!!
//        characterId = arguments?.getInt("characterId") ?: 0
//        viewModel.fetchCharacterDetails(characterId)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMarvelDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.imgBack.setOnClickListener { findNavController().navigateUp() }
        setValueToView(character)
        observeUrlResourceDetails()
//        observeDetails()

    }

    private fun showSliderDialog(items: List<Item>, startPosition: Int) {
        val dialog = Dialog(requireContext(), R.style.FullScreenDialogTheme)
        val binding = SliderDialogBinding.inflate(LayoutInflater.from(context), null, false)

        dialog.setContentView(binding.root)

        val sliderAdapter = SliderAdapter(items)
        binding.sliderViewPager.adapter = sliderAdapter
        binding.sliderViewPager.setCurrentItem(startPosition, false)

        binding.imgCancel.setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()
    }

    private fun observeUrlResourceDetails() {
        viewModel.uiUrlState.observe(viewLifecycleOwner) { uiResult ->
            when (uiResult) {
                is UiResult.Success -> uiResult.data?.forEach { (title, items) ->
                    updateSectionUI(title, items)
                }
                else -> { /* Handle other cases if necessary */ }
            }
        }
    }

    private fun updateSectionUI(title: String, items: List<Item>) {
        val sectionBinding = when (title) {
            "Comics" -> binding.layComic
            "Series" -> binding.laySeries
            "Stories" -> binding.layStories
            "Events" -> binding.layEvent
            else -> return
        }

        if (items.isEmpty()) {
            sectionBinding.laySection.visibility = View.GONE
        } else {
            sectionBinding.loading.visibility = View.GONE
            sectionBinding.txtTitleRecycle.text = title
            sectionBinding.recycleSection.adapter = SectionAdapter(items) { items, pos ->
                showSliderDialog(items, pos)
            }
        }
    }


//    private fun observeDetails() {
//        viewModel.uiState.observe(viewLifecycleOwner) {
//            when (it) {
//                is UiResult.Loading -> {
//
//                }
//
//                is UiResult.Success -> {
//                    it.data?.results?.get(0)?.let { it1 -> setValueToView(it1) }
//                }
//
//                is UiResult.Error -> {
//                }
//
//                else -> {}
//            }
//        }
//
//    }

    private fun setValueToView(character: Character) {
        requireContext().showImgGlide(
            binding.imgMarvel,
            null,
            character.thumbnail?.path?:"",
            character.thumbnail?.extension?:""
        )
        binding.txtName.text = character.name

        if (character.description.isNotEmpty())
            binding.txtDesc.text = character.description
        else {
            binding.txtTitleDesc.visibility = View.GONE
            binding.txtDesc.visibility = View.GONE
        }

        if (character.comics?.items?.isNotEmpty() == true)
            viewModel.fetchSection("Comics", character.comics.items)
        if (character.series?.items?.isNotEmpty()== true)
            viewModel.fetchSection("Series", character.series.items)
        if (character.stories?.items?.isNotEmpty()== true)
            viewModel.fetchSection("Stories", character.stories.items)
        if (character.events?.items?.isNotEmpty()== true)
            viewModel.fetchSection("Events", character.events.items)

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}