package com.example.bookxchange.presentation.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.app.bookxchange.R
import com.example.bookxchange.ui.theme.Purple1
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

@Composable
fun AuthScreen(navController: NavHostController) {
    var auth: FirebaseAuth = Firebase.auth
    var email: String by remember { mutableStateOf("") }
    var password: String by remember { mutableStateOf("") }
    var currentUser: FirebaseUser? by remember { mutableStateOf(null) }
    var error: String? by remember { mutableStateOf(null) }
    fun updateCurrentUser() {
        currentUser = Firebase.auth.currentUser
    }
    fun signUp() {
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
            updateCurrentUser()
            if (it.exception != null) {
                error = it.exception!!.localizedMessage
            }
        }
    }

    fun signIn() {
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
            if (it.exception != null) {
                error = it.exception!!.localizedMessage
            }
            updateCurrentUser()
        }
    }

    fun signOut() {
        auth.signOut()
        updateCurrentUser()
    }
    if (error != null) {
        AlertDialog(onDismissRequest = {error=null}, confirmButton = {
            Button(onClick = {error=null}) {
                Text(text = stringResource(id = R.string.ok))
            }
        }, text = { Text(text = error ?: "") })
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Purple1),
        horizontalAlignment = Alignment.CenterHorizontally,

        ) {
        if (currentUser == null) {
            Text(
                text = stringResource(id = R.string.welcome_account),
                style = MaterialTheme.typography.labelMedium
            )

            EmailField(
                modifier = Modifier.padding(vertical = 20.dp),
                value = email,
                onValueChange = {email=it}
            )
            PasswordField(
                value = password,
                onValueChange = {password=it}
            )
            Spacer(modifier = Modifier.height(30.dp))

            Button(onClick = {signIn()
                navController.navigate("Search")}) {
                Text(text = stringResource(id = R.string.sign_in))
            }
            Spacer(modifier = Modifier.height(20.dp))

            Button(onClick = {signUp()}) {
                Text(text = stringResource(id = R.string.sign_up))
            }
        } else {
            Text(text = stringResource(id = R.string.congratulation_for_sign_in))
            Spacer(modifier = Modifier.height(20.dp))
            Text(text = "${stringResource(id = R.string.your_email)}: ${currentUser!!.email!!}")
            Spacer(modifier = Modifier.height(20.dp))
            Button(onClick = {navController.navigate("Search")}) {
                Text(text = stringResource(id = R.string.start))
            }
        }
    }


}

@Composable
fun EmailField(modifier: Modifier = Modifier, value: String, onValueChange: (String) -> Unit) {
    TextField(
        modifier = modifier,
        value = value,
        onValueChange = onValueChange,
        label = { Text(text = stringResource(id = R.string.email)) },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
    )
}

@Composable
fun PasswordField(modifier: Modifier = Modifier, value: String, onValueChange: (String) -> Unit) {
    TextField(
        modifier = modifier,
        value = value,
        onValueChange = onValueChange,
        label = { Text(text = stringResource(id = R.string.password)) },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        visualTransformation = PasswordVisualTransformation()
    )
}