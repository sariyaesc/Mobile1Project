package imc.viewmodels

import android.content.Context
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import com.example.mobile1project.R



class IMCViewModel:ViewModel() {
    var weight = MutableStateFlow("")
    var height = MutableStateFlow("")
    private val _imcResult = MutableStateFlow("")
    val imcResult = _imcResult.asStateFlow()
    private val _imcVal= MutableStateFlow<Float?>(null)
    val imcVal=_imcVal.asStateFlow()

    fun calculateIMC(context: Context) {  // Ahora recibe un Context
        val weightValue = weight.value.toFloatOrNull()
        val heightValue = height.value.toFloatOrNull()
        _imcResult.value = if (weightValue != null && heightValue != null && heightValue > 0) {
            val imc = weightValue / (heightValue * heightValue)
            _imcVal.value=imc


            when
            {
                imc in 19.0..24.9 -> context.getString(R.string.correct_weight)
                else -> context.getString(R.string.incorrect_weight)
            }
        } else {
            _imcVal.value = null
            context.getString(R.string.invalid_input)
        }
    }
}
