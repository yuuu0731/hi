package com.example.autismsocialsimulator

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R

class MainActivity : AppCompatActivity() {

    private lateinit var scenarioTextView: TextView
    private lateinit var optionButton1: Button
    private lateinit var optionButton2: Button

    // 預設情境與回應
    private val scenarios = listOf(
        Scenario(
            description = "你看到一個新同學坐在教室角落，你想和他打招呼。",
            option1 = "微笑並說『嗨，我叫小明，可以一起坐嗎？』",
            option2 = "什麼都不說，直接坐下。",
            result1 = "新同學開心地點頭，你們成為了朋友！",
            result2 = "新同學感到困惑，場面有點尷尬。"
        ),
        Scenario(
            description = "老師在課堂上提問，你知道答案。",
            option1 = "舉手回答問題。",
            option2 = "保持沉默，避免引起注意。",
            result1 = "老師稱讚你的回答，你感到自信！",
            result2 = "你錯過了表現自己的機會。"
        )
    )

    private var currentScenarioIndex = 0

    data class Scenario(
        val description: String,
        val option1: String,
        val option2: String,
        val result1: String,
        val result2: String
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout)

        scenarioTextView = findViewById(R.id.scenarioTextView)
        optionButton1 = findViewById(R.id.optionButton1)
        optionButton2 = findViewById(R.id.optionButton2)

        loadScenario(currentScenarioIndex)

        optionButton1.setOnClickListener {
            showResult(scenarios[currentScenarioIndex].result1)
        }

        optionButton2.setOnClickListener {
            showResult(scenarios[currentScenarioIndex].result2)
        }
    }

    private fun loadScenario(index: Int) {
        val scenario = scenarios[index]
        scenarioTextView.text = scenario.description
        optionButton1.text = scenario.option1
        optionButton2.text = scenario.option2
    }

    private fun showResult(result: String) {
        AlertDialog.Builder(this)
            .setTitle("結果")
            .setMessage(result)
            .setPositiveButton("下一個情境") { _, _ ->
                currentScenarioIndex = (currentScenarioIndex + 1) % scenarios.size
                loadScenario(currentScenarioIndex)
            }
            .setCancelable(false)
            .show()
    }
}
