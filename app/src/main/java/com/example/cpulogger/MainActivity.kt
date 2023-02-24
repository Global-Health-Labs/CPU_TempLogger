package com.example.cpulogger

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.*
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.TextView
import java.io.File
import java.io.FileWriter
import java.text.SimpleDateFormat
import java.util.*
import com.opencsv.CSVWriter
import java.io.IOException
import kotlin.concurrent.scheduleAtFixedRate

class MainActivity : AppCompatActivity() {

    private val handler = Handler(Looper.getMainLooper())
    private var timer = Timer()
    private var recordingInProgress = false
    private lateinit var fileName: String
    private lateinit var recordButton: Button
    private lateinit var thermalZoneFile: File
    private lateinit var temperatureTextView: TextView

    private val temperatureRunnable = object: Runnable{
        override fun run() {
            retrieveAndDisplayTemperature(fileName)
            handler.postDelayed(this, 1000)
//            Update display every 10seconds

            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        Create csv with start time of recording and with headers corresponding to temperature sensors
        val currentTime = getCurrentTime()
        fileName = "CPULoggerData_$currentTime.csv"
        val fileDir = getExternalFilesDir(null)
        val file = File(fileDir, fileName)

        val writer = CSVWriter(FileWriter(file))

//        TestGitUpdate
//        TestGitUpdate2

        val headers = arrayOf("Battery", "emmc_therm", "pa_therm0", "pm660_tz", "pm660l_tz", "msm_therm", "quiet_therm",
        "xo_therm", "tsens_tz_sensor0","tsens_tz_sensor1", "tsens_tz_sensor2", "tsens_tz_sensor3", "tsens_tz_sensor4", "tsens_tz_sensor5",
            "tsens_tz_sensor6", "tsens_tz_sensor7", "tsens_tz_sensor8", "tsens_tz_sensor9","tsens_tz_sensor10", "tsens_tz_sensor11", "tsens_tz_sensor12",
            "tsens_tz_sensor13", "GLM_soc", "LLM_cp1", "LLM_cp0")

        val headersList = headers.toList()

        writeCsvFile(headersList, this, fileName)

        recordButton = findViewById<Button>(R.id.recordButton)
        recordButton.text = "Start Recording"
        recordButton.backgroundTintList= ColorStateList.valueOf(Color.GREEN)

        recordButton.setOnClickListener{
            if (recordingInProgress){
                stopRecording()
            }else{
                startRecording()
            }
        }

        thermalZoneFile = File("sys/class/thermal/thermal_zone0/temp")
        temperatureTextView = findViewById(R.id.resultsTextView)

    }
    override fun onResume(){
        super.onResume()
        handler.postDelayed(temperatureRunnable,0)
    }

    override fun onPause(){
        super.onPause()
        handler.removeCallbacks(temperatureRunnable)
    }

    fun startRecording(){
        recordingInProgress =true
        recordButton.text = "Stop Recording"
        recordButton.backgroundTintList= ColorStateList.valueOf(Color.RED)

        val startNotice = arrayOf("Recording Start").toList()
        writeCsvFile(startNotice, this, fileName)

        val recordTask = timer.scheduleAtFixedRate(0,10000){
//            Save every 10 seconds
            retrieveAndSaveTemp(fileName)
        }
        //            writer.writeNext(startNotice)
        handler.postDelayed(temperatureRunnable,0)
    }

    fun stopRecording(){
        recordingInProgress = false
        recordButton.text = "Start Recording"

        timer.cancel()
        timer.purge()

        stopWritingToCSV(fileName)

        val stopNotice = arrayOf("Recording Stopped").toList()
        writeCsvFile(stopNotice, this, fileName)

        recordButton.backgroundTintList= ColorStateList.valueOf(Color.GREEN)

        timer=Timer()
    }



    private fun updateTemperatureDisplay(cpuTemps: FloatArray) {
        val resultTextView = findViewById<TextView>(R.id.resultsTextView)
        val resultTextView1 = findViewById<TextView>(R.id.result1TextView)
        val resultTextView2 = findViewById<TextView>(R.id.result2TextView)
        val resultTextView3 = findViewById<TextView>(R.id.result3TextView)
        val resultTextView4 = findViewById<TextView>(R.id.result4TextView)
        val resultTextView5 = findViewById<TextView>(R.id.result5TextView)
        val resultTextView6 = findViewById<TextView>(R.id.result6TextView)
        val resultTextView7 = findViewById<TextView>(R.id.result7TextView)
        val resultTextView8 = findViewById<TextView>(R.id.result8TextView)
        val resultTextView9 = findViewById<TextView>(R.id.result9TextView)
        val resultTextView10 = findViewById<TextView>(R.id.result10TextView)
        val resultTextView11 = findViewById<TextView>(R.id.result11TextView)
        val resultTextView12 = findViewById<TextView>(R.id.result12TextView)
        val resultTextView13 = findViewById<TextView>(R.id.result13TextView)
        val resultTextView14 = findViewById<TextView>(R.id.result14TextView)
        val resultTextView15 = findViewById<TextView>(R.id.result15TextView)
        val resultTextView16 = findViewById<TextView>(R.id.result16TextView)
        val resultTextView17 = findViewById<TextView>(R.id.result17TextView)
        val resultTextView18 = findViewById<TextView>(R.id.result18TextView)
        val resultTextView19 = findViewById<TextView>(R.id.result19TextView)
        val resultTextView20 = findViewById<TextView>(R.id.result20TextView)
        val resultTextView21 = findViewById<TextView>(R.id.result21TextView)
        val resultTextView25 = findViewById<TextView>(R.id.result25textView)
        val resultTextView26 = findViewById<TextView>(R.id.result26TextView)
        val resultTextView27 = findViewById<TextView>(R.id.result27TextView)

        resultTextView.text = cpuTemps[0].toDouble().toString()
        resultTextView1.text = cpuTemps[1].toDouble().toString()
        resultTextView2.text = cpuTemps[2].toDouble().toString()
        resultTextView3.text = cpuTemps[3].toDouble().toString()
        resultTextView4.text = cpuTemps[4].toDouble().toString()
        resultTextView5.text = cpuTemps[5].toDouble().toString()
        resultTextView6.text = cpuTemps[6].toDouble().toString()
        resultTextView7.text = cpuTemps[7].toDouble().toString()
        resultTextView8.text = cpuTemps[8].toDouble().toString()
        resultTextView9.text = cpuTemps[9].toDouble().toString()
        resultTextView10.text = cpuTemps[10].toDouble().toString()
        resultTextView11.text = cpuTemps[11].toDouble().toString()
        resultTextView12.text = cpuTemps[12].toDouble().toString()
        resultTextView13.text = cpuTemps[13].toDouble().toString()
        resultTextView14.text = cpuTemps[14].toDouble().toString()
        resultTextView15.text = cpuTemps[15].toDouble().toString()
        resultTextView16.text = cpuTemps[16].toDouble().toString()
        resultTextView17.text = cpuTemps[17].toDouble().toString()
        resultTextView18.text = cpuTemps[18].toDouble().toString()
        resultTextView19.text = cpuTemps[19].toDouble().toString()
        resultTextView20.text = cpuTemps[20].toDouble().toString()
        resultTextView21.text = cpuTemps[21].toDouble().toString()
        resultTextView25.text = cpuTemps[22].toDouble().toString()
        resultTextView26.text = cpuTemps[23].toDouble().toString()
        resultTextView27.text = cpuTemps[24].toDouble().toString()
    }

    private fun retrieveAndDisplayTemperature(filename: String){
        val result0 = getCpuTemperature("sys/class/thermal/thermal_zone0/temp")
        val result1 = getCpuTemperature("sys/class/thermal/thermal_zone1/temp")
        val result2 = getCpuTemperature("sys/class/thermal/thermal_zone2/temp")
        val result3 = getCpuTemperature("sys/class/thermal/thermal_zone3/temp")
        val result4 = getCpuTemperature("sys/class/thermal/thermal_zone4/temp")
        val result5 = getCpuTemperature("sys/class/thermal/thermal_zone5/temp")
        val result6 = getCpuTemperature("sys/class/thermal/thermal_zone6/temp")
        val result7 = getCpuTemperature("sys/class/thermal/thermal_zone7/temp")
        val result8 = getCpuTemperature("sys/class/thermal/thermal_zone8/temp")
        val result9 = getCpuTemperature("sys/class/thermal/thermal_zone9/temp")
        val result10 = getCpuTemperature("sys/class/thermal/thermal_zone10/temp")
        val result11 = getCpuTemperature("sys/class/thermal/thermal_zone11/temp")
        val result12 = getCpuTemperature("sys/class/thermal/thermal_zone12/temp")
        val result13 = getCpuTemperature("sys/class/thermal/thermal_zone13/temp")
        val result14 = getCpuTemperature("sys/class/thermal/thermal_zone14/temp")
        val result15 = getCpuTemperature("sys/class/thermal/thermal_zone15/temp")
        val result16 = getCpuTemperature("sys/class/thermal/thermal_zone16/temp")
        val result17 = getCpuTemperature("sys/class/thermal/thermal_zone17/temp")
        val result18 = getCpuTemperature("sys/class/thermal/thermal_zone18/temp")
        val result19 = getCpuTemperature("sys/class/thermal/thermal_zone19/temp")
        val result20 = getCpuTemperature("sys/class/thermal/thermal_zone20/temp")
        val result21 = getCpuTemperature("sys/class/thermal/thermal_zone21/temp")
        val result25 = getCpuTemperature("sys/class/thermal/thermal_zone25/temp")
        val result26 = getCpuTemperature("sys/class/thermal/thermal_zone26/temp")
        val result27 = getCpuTemperature("sys/class/thermal/thermal_zone27/temp")

        val tempArr = floatArrayOf(result0, result1, result2, result3, result4, result5, result6, result7, result8, result9, result10,
            result11, result12, result13, result14, result15, result16, result17, result18, result19, result20, result21, result25, result26,
            result27)

        //        Results 22-24 & 28,29 are NULL and cause crashes

        updateTemperatureDisplay(tempArr)

    }

    private fun retrieveAndSaveTemp(filename: String){
        val result0 = getCpuTemperature("sys/class/thermal/thermal_zone0/temp")
        val result1 = getCpuTemperature("sys/class/thermal/thermal_zone1/temp")
        val result2 = getCpuTemperature("sys/class/thermal/thermal_zone2/temp")
        val result3 = getCpuTemperature("sys/class/thermal/thermal_zone3/temp")
        val result4 = getCpuTemperature("sys/class/thermal/thermal_zone4/temp")
        val result5 = getCpuTemperature("sys/class/thermal/thermal_zone5/temp")
        val result6 = getCpuTemperature("sys/class/thermal/thermal_zone6/temp")
        val result7 = getCpuTemperature("sys/class/thermal/thermal_zone7/temp")
        val result8 = getCpuTemperature("sys/class/thermal/thermal_zone8/temp")
        val result9 = getCpuTemperature("sys/class/thermal/thermal_zone9/temp")
        val result10 = getCpuTemperature("sys/class/thermal/thermal_zone10/temp")
        val result11 = getCpuTemperature("sys/class/thermal/thermal_zone11/temp")
        val result12 = getCpuTemperature("sys/class/thermal/thermal_zone12/temp")
        val result13 = getCpuTemperature("sys/class/thermal/thermal_zone13/temp")
        val result14 = getCpuTemperature("sys/class/thermal/thermal_zone14/temp")
        val result15 = getCpuTemperature("sys/class/thermal/thermal_zone15/temp")
        val result16 = getCpuTemperature("sys/class/thermal/thermal_zone16/temp")
        val result17 = getCpuTemperature("sys/class/thermal/thermal_zone17/temp")
        val result18 = getCpuTemperature("sys/class/thermal/thermal_zone18/temp")
        val result19 = getCpuTemperature("sys/class/thermal/thermal_zone19/temp")
        val result20 = getCpuTemperature("sys/class/thermal/thermal_zone20/temp")
        val result21 = getCpuTemperature("sys/class/thermal/thermal_zone21/temp")
        val result25 = getCpuTemperature("sys/class/thermal/thermal_zone25/temp")
        val result26 = getCpuTemperature("sys/class/thermal/thermal_zone26/temp")
        val result27 = getCpuTemperature("sys/class/thermal/thermal_zone27/temp")

        val tempArr = floatArrayOf(result0, result1, result2, result3, result4, result5, result6, result7, result8, result9, result10,
            result11, result12, result13, result14, result15, result16, result17, result18, result19, result20, result21, result25, result26,
            result27)


        val tempString =tempArr.map {it.toString()}

        writeCsvFile(tempString, this, filename)

    }

}

fun getCpuTemperature(tempzoneLoc: String): Float {
    val tempFile = File(tempzoneLoc)
    if (tempFile.exists()) {
        val temp = tempFile.readText().trim().toFloat() / 1000
        return temp

        println("test successfultemp")
    }
    return -1069.0.toFloat()
    println("Could not recieve temp")
}


fun getCurrentTime(): String {
    val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
    return sdf.format(Date())
}
fun writeCsvFile(data: List<String>, context: Context, filename: String) {
    val fileDir = context.getExternalFilesDir(null)
    val file = File(fileDir, filename)
    val currentTime = getCurrentTime()

    val writer = CSVWriter(FileWriter(file, true))
    writer.writeNext(arrayOf(currentTime) + data.toTypedArray())
    writer.close()
}

fun stopWritingToCSV(filename: String){
    try {
        val writer = FileWriter(filename)
        writer.close()
    } catch (e: IOException){
        e.printStackTrace()
    }
}