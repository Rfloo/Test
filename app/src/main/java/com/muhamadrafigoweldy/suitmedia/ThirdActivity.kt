package com.muhamadrafigoweldy.suitmedia;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.muhamadrafigoweldy.suitmedia.adapter.UserAdapter;
import com.muhamadrafigoweldy.suitmedia.data.ApiConfig;
import com.muhamadrafigoweldy.suitmedia.databinding.ActivityThirdBinding;
import com.muhamadrafigoweldy.suitmedia.repository.UserRepository;


class ThirdActivity : AppCompatActivity() {


    private lateinit var binding: ActivityThirdBinding
    private lateinit var userViewModel: UserViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityThirdBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val apiService = ApiConfig.getApiService()
        val userRepository = UserRepository(apiService)

        val adapter = UserAdapter { selectedUserName ->
            userViewModel.setSelectedUserName(selectedUserName)
            val intent = Intent(this, SecondActivity::class.java)
            intent.putExtra("selectedUserName", selectedUserName)
            startActivity(intent)
        }

        userViewModel = UserViewModel(userRepository)

        userViewModel.getUsers(1, 10)
        binding.usersView.layoutManager = LinearLayoutManager(this)
        binding.usersView.adapter = adapter
        userViewModel.userList.observe(this) { userList ->
            adapter.submitList(userList)
        }

        binding.btnBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }
}
