package com.ska.navigationdemo


import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.ska.navigationdemo.databinding.FragmentEmailBinding

/**
 * A simple [Fragment] subclass.
 */
class EmailFragment : Fragment() {

    private lateinit var binding: FragmentEmailBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_email, container, false)
        binding.submitButton.setOnClickListener {
            if (!TextUtils.isEmpty((binding.emailEditText.text.toString()))) {
                val userName = arguments?.getString("user_name")
                val bundle = bundleOf("user_email" to binding.emailEditText.text.toString(),
                    "user_name" to userName)
                it.findNavController()
                    .navigate(R.id.action_emailFragment2_to_welcomeFragment, bundle)
            } else {
                Toast.makeText(activity, "Please insert your e-mail", Toast.LENGTH_LONG).show()
            }

        }
        return binding.root
    }
}
