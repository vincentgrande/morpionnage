package fr.vincentgrande.morpionnage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    var tvInfos: TextView? = null
    var resetBtn: Button? = null
    var grid: Array<Array<Int>> = arrayOf(
        arrayOf(0, 0, 0),
        arrayOf(0, 0, 0),
        arrayOf(0, 0, 0)
    )
    val buttonsMap = mapOf("20" to "one", "21" to "two",  "22" to "three", "10" to "four", "11" to "five", "12" to "six", "00" to "seven", "01" to "eight",  "02" to "nine")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tvInfos = findViewById(R.id.tvInfos)
        resetBtn = findViewById(R.id.resetBtn)

        resetBtn?.setOnClickListener {
            tvInfos?.text = "Morpionnage"
            findViewById<Button>(R.id.one).text = "2,0"
            findViewById<Button>(R.id.one).textSize = 0f
            findViewById<Button>(R.id.one).isClickable = true

            findViewById<Button>(R.id.two).text = "2,1"
            findViewById<Button>(R.id.two).textSize = 0f
            findViewById<Button>(R.id.two).isClickable = true

            findViewById<Button>(R.id.three).text = "2,2"
            findViewById<Button>(R.id.three).textSize = 0f
            findViewById<Button>(R.id.three).isClickable = true

            findViewById<Button>(R.id.four).text = "1,0"
            findViewById<Button>(R.id.four).textSize = 0f
            findViewById<Button>(R.id.four).isClickable = true

            findViewById<Button>(R.id.five).text = "1,1"
            findViewById<Button>(R.id.five).textSize = 0f
            findViewById<Button>(R.id.five).isClickable = true

            findViewById<Button>(R.id.six).text = "1,2"
            findViewById<Button>(R.id.six).textSize = 0f
            findViewById<Button>(R.id.six).isClickable = true

            findViewById<Button>(R.id.seven).text = "0,0"
            findViewById<Button>(R.id.seven).textSize = 0f
            findViewById<Button>(R.id.seven).isClickable = true

            findViewById<Button>(R.id.eight).text = "0,1"
            findViewById<Button>(R.id.eight).textSize = 0f
            findViewById<Button>(R.id.eight).isClickable = true

            findViewById<Button>(R.id.nine).text = "0,2"
            findViewById<Button>(R.id.nine).textSize = 0f
            findViewById<Button>(R.id.nine).isClickable = true

            grid = arrayOf(
                arrayOf(0, 0, 0),
                arrayOf(0, 0, 0),
                arrayOf(0, 0, 0)
            )
        }
    }

    fun onClick(view: View){
        val btnClicked: Button = (view as Button)
        btnClicked.isClickable = false
        //tvInfos?.text = btnClicked.text
        game(btnClicked.text.toString())
        btnClicked.text = "X"
        btnClicked.textSize = 40f
    }

    fun game(clicked: String){
        var pos = clicked.split(",")
        grid[pos[0].toInt()][pos[1].toInt()] = 1
        var x = Random.nextInt(0, 3)
        var y = Random.nextInt(0, 3)
        while(grid[x][y] == 1){
            x = Random.nextInt(0, 3)
            y = Random.nextInt(0, 3)
        }
        grid[x][y] = -1
        var btnid = buttonsMap["$x$y"]
        var resID = getResources().getIdentifier(btnid, "id", getPackageName())
        findViewById<Button>(resID).text = "O"
        findViewById<Button>(resID).textSize = 40f
        findViewById<Button>(resID).isClickable = false
        winOrFail()
    }

    fun winOrFail(){
        if((grid[0][0] + grid[0][1] + grid[0][2]) == 3 || (grid[1][0] + grid[1][1] + grid[1][2]) == 3 || (grid[2][0] + grid[2][1] + grid[2][2]) == 3){
            tvInfos?.text = "Gagné"
        }else if((grid[0][0] + grid[1][0] + grid[2][0]) == 3 || (grid[0][1] + grid[1][1] + grid[2][1]) == 3 || (grid[0][2] + grid[1][2] + grid[2][2]) == 3 ){
            tvInfos?.text = "Gagné"
        }else if((grid[0][0] + grid[1][1] + grid[2][2]) == 3 || (grid[0][2] + grid[1][1] + grid[2][0]) == 3){
            tvInfos?.text = "Gagné"
        }
        if((grid[0][0] + grid[0][1] + grid[0][2]) == -3 || (grid[1][0] + grid[1][1] + grid[1][2]) == -3 || (grid[2][0] + grid[2][1] + grid[2][2]) == -3){
            tvInfos?.text = "Perdu"
        }else if((grid[0][0] + grid[1][0] + grid[2][0]) == -3 || (grid[0][1] + grid[1][1] + grid[2][1]) == -3 || (grid[0][2] + grid[1][2] + grid[2][2]) == -3 ){
            tvInfos?.text = "Perdu"
        }else if((grid[0][0] + grid[1][1] + grid[2][2]) == -3 || (grid[0][2] + grid[1][1] + grid[2][0]) == -3){
            tvInfos?.text = "Perdu"
        }
    }
}