package com.example.assignment5

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import org.w3c.dom.Text


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val saveBtn = findViewById(R.id.save) as Button
        val displayBtn = findViewById(R.id.display) as Button
        val distInput = findViewById(R.id.enterDist) as EditText
        val distSwimInput = findViewById(R.id.enterDistSwim) as EditText
        val caloriesInput = findViewById(R.id.enterCalories) as EditText
        val distOutput = findViewById(R.id.Distance) as TextView
        val distSwimOutput = findViewById(R.id.Swim) as TextView
        val caloriesOutput = findViewById(R.id.Calories) as TextView
        val totalDistOutput = findViewById(R.id.TotalDist) as TextView

        saveBtn.setOnClickListener{

            val db = DBHelper(this, null)

            val dist = distInput.text.toString()
            val swim = distSwimInput.text.toString()
            val calories = caloriesInput.text.toString()

            db.save(dist.toFloat(), swim.toFloat(), calories.toInt())

            Toast.makeText(this, dist + " " + swim + " " + calories + " added to database", Toast.LENGTH_LONG).show()

            distInput.text.clear()
            distSwimInput.text.clear()
            caloriesInput.text.clear()
        }

        displayBtn.setOnClickListener{

            val db = DBHelper(this, null)

            val cursor = db.getName()

            cursor!!.moveToFirst()
            var totalDist = cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.DIST_COL)).toFloat()
            var totalDistSwam = cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.SWIM_COL)).toFloat()
            var totalCalories = cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.CALORIES_COL)).toInt()
            var i=1

            while(cursor.moveToNext()){
                totalDist += cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.DIST_COL)).toFloat()
                totalDistSwam += cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.SWIM_COL)).toFloat()
                totalCalories += cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.CALORIES_COL)).toInt()
                i += 1
            }

            distOutput.text = "Avg\nDist:\n" + (totalDist/i).toString()
            distSwimOutput.text =  "Avg\nSwim:\n" + (totalDistSwam/i).toString()
            caloriesOutput.text =  "Avg\nCals:\n" + (totalCalories/i).toString()
            totalDistOutput.text = "Total Distance:\n" + (totalDist).toString() + " m"


            cursor.close()
        }
    }
}
