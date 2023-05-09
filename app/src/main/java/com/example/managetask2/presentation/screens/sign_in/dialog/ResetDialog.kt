package com.example.managetask2.presentation.screens.sign_in.dialog

import androidx.fragment.app.Fragment
import com.example.managetask2.R
import com.example.managetask2.databinding.ResetPasswordDialogBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import java.util.zip.Inflater

fun Fragment.setupBottomSheetDialog(
    onSendClick: (String) -> Unit
){
    val dialog = BottomSheetDialog(requireContext(), R.style.DialogStyle)
    val view = ResetPasswordDialogBinding.inflate(layoutInflater)

    dialog.setContentView(view.root)
    dialog.behavior.state = BottomSheetBehavior.STATE_EXPANDED
    dialog.show()

    val etEmail = view.etResetEmail
    val cancelButton = view.btnCancel
    val sendButton = view.btnSend

    sendButton.setOnClickListener {
        val email = etEmail.text.toString().trim()
        onSendClick(email)
        dialog.dismiss()
    }

    cancelButton.setOnClickListener {
        dialog.dismiss()
    }

}