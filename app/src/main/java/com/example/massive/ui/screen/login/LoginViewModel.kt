import androidx.lifecycle.ViewModel
import com.example.massive.data.api.LoginResponse
import com.example.massive.data.storage.RetrofitInstance
import com.example.massive.data.storage.SharedPreferencesManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LoginViewModel : ViewModel() {
    suspend fun login(email: String, password: String, sharedPreferencesManager: SharedPreferencesManager): LoginResponse? {
        return withContext(Dispatchers.IO) {
            try {
                val response = RetrofitInstance.loginApi.login(email, password)
                response?.let {
                    sharedPreferencesManager.authToken = it.data.token
                }
                response
            } catch (e: Exception) {
                null
            }
        }
    }
}
