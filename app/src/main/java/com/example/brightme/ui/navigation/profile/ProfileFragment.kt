package com.example.brightme.ui.navigation.profile

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.brightme.databinding.FragmentProfileBinding
import com.example.brightme.ui.login.LoginActivity
import com.example.brightme.ui.main.MainActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private lateinit var auth: FirebaseAuth

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    companion object {
        private const val FRAGMENT_REQUEST_CODE = 123
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val notificationsViewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)

        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val tvName: TextView = binding.tvName
        val tvEmail : TextView = binding.tvEmail

        binding.btnSignOut.setOnClickListener {
            auth = Firebase.auth
            auth.signOut()

            val intent = Intent(activity, MainActivity::class.java)
            intent.putExtra("signOut", true)
            startActivity(intent)
        }

        notificationsViewModel.textName.observe(viewLifecycleOwner) {
            tvName.text = it
        }

        notificationsViewModel.textEmail.observe(viewLifecycleOwner) {
            tvEmail.text = it
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}