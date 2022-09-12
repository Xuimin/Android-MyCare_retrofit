package com.example.tracingapp.ui.fragment

import android.os.Bundle
import android.text.Html
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.tracingapp.data.viewmodel.UserViewModel
import com.example.tracingapp.databinding.FragmentVaccinationBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class VaccinationFragment : Fragment() {
    private lateinit var binding: FragmentVaccinationBinding
    private val viewModel: UserViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        activity?.setTitle(Html.fromHtml("<big><big>Vaccination</big></big>"))

        binding = FragmentVaccinationBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bindView()
    }

    private fun bindView() {
        viewModel.getOwnDetails.observe(viewLifecycleOwner) { details ->
            binding.tvUserName.text = details.fullname
            binding.tvUserIc.text = details.ic
            binding.tvUserStatus.text = details.vaccine
        }
    }
}