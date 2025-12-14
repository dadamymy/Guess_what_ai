package com.example.guess_what_ai

import android.app.AlertDialog
import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.example.guess_what_ai.databinding.DialogCustomTopicBinding

class CustomTopicDialog : DialogFragment(), View.OnClickListener {
    private var _binding : DialogCustomTopicBinding? = null
    private val binding get()  = _binding!!

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        _binding = DialogCustomTopicBinding.inflate(layoutInflater)
        val builder = AlertDialog.Builder(activity, R.style.TransparentDialog)
        builder.setCancelable(false)

        builder.setView(binding.root)
        initView()
        return builder.create()
    }
    fun initView(){
        binding.buttonSubmit.setOnClickListener(this)
        binding.buttonCancel.setOnClickListener(this)
    }


    override fun onClick(p0: View?) {
        when(p0?.id){
            binding.buttonSubmit.id->{
                if (binding.editTextTopic.text.isEmpty()) {
                    Toast.makeText(requireContext(), "你還沒輸入題目", Toast.LENGTH_SHORT).show()
                }else{
                    val intent = Intent(requireContext(), DrawActivity::class.java).apply {
                        putExtra("MODE", binding.editTextTopic.text.toString())
                    }
                    startActivity(intent)
                    requireActivity().finish()
                }
            }
            binding.buttonCancel.id->dismiss()
        }
    }
}