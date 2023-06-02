package com.example.sevenminuteworkout

import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.speech.tts.TextToSpeech
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sevenminuteworkout.databinding.ActivityExerciseBinding

class ExerciseActivity : AppCompatActivity(), TextToSpeech.OnInitListener {

    private lateinit var binding: ActivityExerciseBinding

    private var restTimer: CountDownTimer? = null
    private var restProgress = 0
    private var restTimerDuration: Long = 1
    private var exerciseTimer: CountDownTimer? = null
    private var exerciseProgress = 0
    private var exerciseTimerDuration: Long = 1
    private var exerciseList: ArrayList<ExerciseModel>? = null
    private var currentExercisePos = -1

    private var tts: TextToSpeech? = null
    private var player: MediaPlayer? = null

    private var exerciseAdapter: ExerciseStatusAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExerciseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolBarExercise)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        exerciseList = Constants.defaultExerciseList()

        binding.toolBarExercise.setNavigationOnClickListener {
        }

        tts = TextToSpeech(this, this)
        setupRestView()
        setupExerciseStatusRecyclerView()
    }

    private fun setupExerciseStatusRecyclerView(){
        binding.rvExerciseStatus.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        exerciseAdapter = ExerciseStatusAdapter(exerciseList!!)
        binding.rvExerciseStatus?.adapter = exerciseAdapter
    }

    private fun setupRestView(){
        binding.flRestView.visibility = View.VISIBLE
        binding.textViewTitle.visibility = View.VISIBLE
        binding.tvExerciseName.visibility = View.INVISIBLE
        binding.exerciseImage.visibility = View.INVISIBLE
        binding.flExerciseView.visibility = View.INVISIBLE
        binding.tvUpcomingLabel.visibility = View.VISIBLE
        binding.tvUpcomingExercise.visibility = View.VISIBLE
        binding.tvUpcomingExercise.text = exerciseList!![currentExercisePos + 1].getName()

        try {
            val soundURI = Uri.parse(
                "android.resource://com.example.sevenminuteworkout/" + R.raw.press_start)
            player = MediaPlayer.create(applicationContext, soundURI)
            player?.isLooping = false
            player?.start()
        }catch (e: Exception){
            e.printStackTrace()
        }

        if(restTimer != null){
            restTimer?.cancel()
            restProgress = 0
        }
        setRestProgressBar()
    }

    private fun setupExerciseView(){
        binding.flRestView.visibility = View.INVISIBLE
        binding.textViewTitle.visibility = View.INVISIBLE
        binding.tvExerciseName.visibility = View.VISIBLE
        binding.exerciseImage.visibility = View.VISIBLE
        binding.flExerciseView.visibility = View.VISIBLE
        binding.tvUpcomingExercise.visibility = View.INVISIBLE
        binding.tvUpcomingLabel.visibility = View.INVISIBLE

        if(exerciseTimer != null){
            exerciseTimer?.cancel()
            exerciseProgress = 0
        }

        binding.exerciseImage.setImageResource(exerciseList!![currentExercisePos].getImage())
        binding.tvExerciseName.text = exerciseList!![currentExercisePos].getName()
        setExerciseProgressBar()
    }

    private fun setRestProgressBar(){
        binding.progressBar.progress = restProgress

        restTimer = object: CountDownTimer(restTimerDuration * 1000, 1000){
            override fun onTick(p0: Long) {
                restProgress++
                binding.progressBar.progress = 10 - restProgress
                binding.textViewTimer.text = (10 - restProgress).toString()
            }

            override fun onFinish() {
                currentExercisePos++
                exerciseList!![currentExercisePos].setIsSelected(true)
                exerciseAdapter!!.notifyDataSetChanged()
                setupExerciseView()
            }

        }.start()
    }

    private fun setExerciseProgressBar(){
        binding.exerciseProgressBar.progress = exerciseProgress
        exerciseTimer = object: CountDownTimer(exerciseTimerDuration*1000, 1000){

            override fun onTick(p0: Long) {
                exerciseProgress++
                binding.exerciseProgressBar.progress = 30 - exerciseProgress
                binding.tvExerciseTimer.text = (30 - exerciseProgress).toString()
            }

            override fun onFinish() {
                if(currentExercisePos < exerciseList!!.size -1){
                    exerciseList!![currentExercisePos].setIsSelected(false)
                    exerciseList!![currentExercisePos].setIsCompleted(true)
                    exerciseAdapter!!.notifyDataSetChanged()
                    setupRestView()
                }else {
                    finish()
                    val intent = Intent(this@ExerciseActivity, FinishActivity::class.java)
                    startActivity(intent)
                }
            }
        }.start()
    }


    override fun onDestroy() {
        super.onDestroy()

        if(restTimer != null){
            restTimer?.cancel()
            restProgress = 0
        }

        if(exerciseTimer != null){
            exerciseTimer?.cancel()
            exerciseProgress = 0
        }

        if (tts != null){
            tts!!.stop()
            tts!!.shutdown()
        }

        if(player != null){
            player!!.stop()
        }
    }

    override fun onInit(p0: Int) {

    }

    private fun speakOut(text:String){
        tts!!.speak(text, TextToSpeech.QUEUE_FLUSH,null, "")
    }

}