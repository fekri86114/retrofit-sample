package info.fekri.retrofitsample.mainScreen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.squareup.picasso.Picasso
import info.fekri.retrofitsample.databinding.ActivityMainBinding
import info.fekri.retrofitsample.model.apiService.ApiManager
import info.fekri.retrofitsample.model.data.FoxData

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var apiManager: ApiManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        apiManager = ApiManager()
        getRandomFoxFromNet(binding)
    }

    override fun onResume() {
        super.onResume()
        binding.run {

            btnGetFox.setOnClickListener {
                getRandomFoxFromNet(binding)
            }

        }
    }

    private fun getRandomFoxFromNet(binding: ActivityMainBinding) {
        apiManager.getRandomFox(object : ApiManager.ApiCallback<FoxData>{
            override fun onSuccess(data: FoxData) {
                Picasso.get().load(data.image).into(binding.imgCoverFox)
            }

            override fun onFailure(msg: String) {
                Log.v("MainActivity", "Error ==> $msg")
                Toast.makeText(
                    this@MainActivity,
                    "Error occurred ==> $msg",
                    Toast.LENGTH_SHORT).show()
            }
        })
    }

}