package com.rtncenteredtext

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class CenteredTextFragment: Fragment() {
    private lateinit var centeredText: CenteredText

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        super.onCreateView(inflater, container, savedInstanceState)
        centeredText = CenteredText(requireNotNull(context))
        return centeredText // this CustomView could be any view that you want to render
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // do any logic that should happen in an `onCreate` method, e.g:
//        cealScanQrView.setUpCamera(requireActivity())
    }




    override fun onDestroy() {
        super.onDestroy()
//        cealScanQrView.destroyCamera()
    }
}