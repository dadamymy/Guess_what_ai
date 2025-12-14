package com.example.guess_what_ai

import android.app.AlertDialog
import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.DialogFragment
import com.example.guess_what_ai.databinding.DialogResultBinding

class ResultDialog : DialogFragment(), View.OnClickListener {
    private var _binding : DialogResultBinding? = null
    private val binding get() = _binding!!

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        _binding = DialogResultBinding.inflate(layoutInflater)
        val builder = AlertDialog.Builder(activity, R.style.TransparentDialog)
        builder.setCancelable(false)

        builder.setView(binding.root)
        initView()
        return builder.create()
    }


    fun initView(){
        binding.buttonRetry.setOnClickListener(this)
        val isCorrect = arguments?.getBoolean("IS_CORRECT") ?: false
        val topic = arguments?.getString("TOPIC") ?: ""
        val aiComment = arguments?.getString("AI_COMMENT") ?: ""
        Log.d("ResultDebug", aiComment)
        if (isCorrect){
            binding.textAIAns.text = "題目是$topic，AI答對了~"
        }else{
            binding.textAIAns.text = "猜錯了，AI覺得是$aiComment"
        }
    }


    override fun onClick(p0: View?) {
        when(p0?.id){
            binding.buttonRetry.id -> {
                val intent = Intent(requireContext(), SelectModeActivity::class.java)
                startActivity(intent)
                requireActivity().finish()
            }
        }
    }
}