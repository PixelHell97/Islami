package com.pixel.islami.ui.home.radio

import android.media.MediaPlayer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.pixel.islami.R
import com.pixel.islami.api.ApiManager
import com.pixel.islami.api.model.radioResponses.Radio
import com.pixel.islami.api.model.radioResponses.RadioResources
import com.pixel.islami.databinding.FragmentRadioBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RadioFragment : Fragment() {
    private lateinit var binding: FragmentRadioBinding
    private var mediaPlayer: MediaPlayer? = null
    private var currentPosition = 0
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentRadioBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getRadioSources()
    }

    private fun getRadioSources() {
        ApiManager
            .getServices()
            .getRadioSources()
            .enqueue(object : Callback<RadioResources> {
                override fun onResponse(
                    call: Call<RadioResources>,
                    response: Response<RadioResources>,
                ) {
                    if (response.isSuccessful) {
                        initRadios(response.body()?.radios)
                    } else {
                        showErrorMessage("Internet must be enabled")
                    }
                }

                override fun onFailure(call: Call<RadioResources>, t: Throwable) {
                    showErrorMessage(t.message)
                }
            })
    }

    private fun initRadios(radios: List<Radio?>?) {
        mediaPlayer = MediaPlayer()
        binding.btnPlay.setOnClickListener {
            if (mediaPlayer!!.isPlaying) {
                mediaPlayer?.reset()
                playRadio(radios!![currentPosition])
            } else {
                mediaPlayer?.pause()
                binding.btnPlay.setImageResource(R.drawable.ic_play)
            }
        }
        binding.btnNext.setOnClickListener {
            nextChanel(radios?.size)
            mediaPlayer?.reset()
            playRadio(radios!![currentPosition])
        }
        binding.btnPrevious.setOnClickListener {
            previousChannel(radios?.size)
            mediaPlayer?.reset()
            playRadio(radios!![currentPosition])
        }
    }

    private fun previousChannel(radiosSize: Int?) {
        currentPosition--
        if (currentPosition < 0) {
            currentPosition = radiosSize!! - 1
        }
    }

    private fun nextChanel(radiosSize: Int?) {
        currentPosition++
        if (currentPosition >= radiosSize!!) {
            currentPosition = 0
        }
    }

    private fun playRadio(radio: Radio?) {
        binding.textView.text = radio?.name
        mediaPlayer?.apply {
            setDataSource(radio?.url)
            prepare()
            start()
        }
        binding.btnPlay.setImageResource(R.drawable.ic_pause)
    }

    private fun showErrorMessage(message: String?) {
        Toast.makeText(
            requireContext(),
            message,
            Toast.LENGTH_LONG,
        )
            .show()
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer?.release()
        mediaPlayer = null
    }
}
