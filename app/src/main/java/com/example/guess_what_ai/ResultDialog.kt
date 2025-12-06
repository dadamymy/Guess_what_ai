package com.example.guess_what_ai

import android.app.AlertDialog
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.guess_what_ai.databinding.DialogResultBinding

class ResultDialog : DialogFragment(), View.OnClickListener {
    private var dialog: AlertDialog? = null
    private var _binding : DialogResultBinding? = null
    private val binding get() = _binding!!

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        _binding = DialogResultBinding.inflate(layoutInflater)
        val builder = AlertDialog.Builder(activity, R.style.TransparentDialog)
        builder.setCancelable(false)

        builder.setView(binding.root)
        dialog = builder.create()
        return builder.create()
    }


    fun initView(){
        TODO()
    }


    override fun onClick(p0: View?) {
        TODO("Not yet implemented")
    }
}