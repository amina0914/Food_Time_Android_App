package ca.dawson511.wheretoeat


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ca.dawson511.wheretoeat.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

      }
}