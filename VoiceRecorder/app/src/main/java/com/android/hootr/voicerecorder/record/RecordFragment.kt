package com.android.hootr.voicerecorder.record


import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.android.hootr.voicerecorder.MainActivity
import com.android.hootr.voicerecorder.R
import com.android.hootr.voicerecorder.database.RecordDatabase
import com.android.hootr.voicerecorder.database.RecordingDatabaseDao
import com.android.hootr.voicerecorder.databinding.FragmentRecordBinding
import kotlinx.android.synthetic.main.fragment_record.*
import java.io.File

/**
 * A simple [Fragment] subclass.
 */
class RecordFragment : Fragment() {

    private lateinit var viewModel: RecordViewModel
    private lateinit var mainActivity: MainActivity
    private var count: Int? = null
    private var database: RecordingDatabaseDao? = null
    private val MY_PERVISSIONS_RECORD_AUDIO = 123

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = DataBindingUtil.inflate<FragmentRecordBinding>(
            inflater,
            R.layout.fragment_record,
            container, false
        )

        database = context?.let {
            RecordDatabase.getInstance(it).recordDatabaseDao
        }
        database?.getCount()?.observe(viewLifecycleOwner, Observer { count = it })

        mainActivity = context as MainActivity

        viewModel = ViewModelProvider(this).get(RecordViewModel::class.java)

        binding.recordViewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        if (!mainActivity.isServiceRunning()) {
            viewModel.resetTime()
        } else {
            binding.playButton.setImageResource(R.drawable.ic_media_stop)
        }

        binding.playButton.setOnClickListener {
            if (ContextCompat.checkSelfPermission(
                    requireContext(),
                    Manifest.permission.RECORD_AUDIO
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                requestPermissions(
                    arrayOf(android.Manifest.permission.RECORD_AUDIO), MY_PERVISSIONS_RECORD_AUDIO
                )
            } else {
                if (mainActivity.isServiceRunning()) {
                    onRecord(false)
                    viewModel.stopTimer()
                } else {
                    onRecord(true)
                    viewModel.startTimer()
                }
            }
        }

        createChannel(
            getString(R.string.notification_channel_id),
            getString(R.string.notification_channel_name)
        )
        // Inflate the layout for this fragment
        return binding.root
    }

    fun onRecord(start: Boolean) {
        val intent = Intent(activity, RecordService::class.java)
        intent.putExtra("COUNT", count)

        if (start) {
            playButton.setImageResource(R.drawable.ic_media_stop)
            Toast.makeText(
                activity,
                getString(R.string.toast_recording_start), Toast.LENGTH_SHORT
            ).show()

            val folder = File(
                activity?.getExternalFilesDir(null)?.absolutePath.toString() + "/VoiceRecorder"
            )

            if (!folder.exists()) {
                folder.mkdir()
            }

            activity?.startService(intent)
            activity?.window?.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        } else {
            playButton.setImageResource(R.drawable.ic_mic_white_36dp)

            activity?.stopService(intent)
            activity?.window?.clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            MY_PERVISSIONS_RECORD_AUDIO -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    onRecord(true)
                    viewModel.startTimer()
                } else {
                    Toast.makeText(
                        activity,
                        getString(R.string.toast_recording_permissions), Toast.LENGTH_SHORT
                    ).show()
                }
                return
            }
        }
    }

    private fun createChannel(channelId: String, channelName: String) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationChannel = NotificationChannel(
                channelId,
                channelName,
                NotificationManager.IMPORTANCE_DEFAULT
            )
                .apply {
                    setShowBadge(false)
                    setSound(null, null)
                }
            val notificationManager = requireActivity().getSystemService(
                NotificationManager::class.java
            )
            notificationManager.createNotificationChannel(notificationChannel)
        }
    }

}
