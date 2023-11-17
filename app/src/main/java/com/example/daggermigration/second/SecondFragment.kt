package com.example.daggermigration.second

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.daggermigration.di.AuxModule
import com.example.daggermigration.R
import com.example.daggermigration.applicationGraph
import com.example.daggermigration.databinding.FragmentSecondBinding
import com.example.daggermigration.di.DaggerSecondComponent
import javax.inject.Inject

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null

    @Inject
    lateinit var viewModel: SecondViewModel

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private fun buildModule() = AuxModule(applicationGraph!!)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        DaggerSecondComponent.builder()
            .auxModule(buildModule())
            .build().inject(this)
        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonSecond.setOnClickListener {
            viewModel.log()
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
