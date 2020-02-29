package dev.synople.glassecho.phone.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import dev.synople.glassecho.phone.R
import dev.synople.glassecho.phone.services.GlassEchoNotificationListenerService
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) =
        inflater.inflate(R.layout.fragment_home, container, false)!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // TODO: Only show this if Glass isn't connected. If it is connected, show basic info about Glass.
        btnConnect.setOnClickListener {
            stopEchoService()
            startEchoService()
        }

        btnNotifications.setOnClickListener {
            this.findNavController()
                .navigate(HomeFragmentDirections.actionHomeFragmentToNotificationPickerFragment())
        }
    }

    private fun startEchoService() {
        val serviceIntent = Intent(context, GlassEchoNotificationListenerService::class.java)
        ContextCompat.startForegroundService(context!!, serviceIntent)
    }

    private fun stopEchoService() {
        val serviceIntent = Intent(context, GlassEchoNotificationListenerService::class.java)
        context!!.stopService(serviceIntent)
    }
}