package com.example.terveyshelppi.components

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.terveyshelppi.R
import com.example.terveyshelppi.libraryComponent.TextModifiedStringWithPadding
import com.example.terveyshelppi.libraryComponent.TextModifiedWithPadding
import com.example.terveyshelppi.service.ResultViewModel
import com.example.terveyshelppi.service.roomDB.UserData
import com.example.terveyshelppi.ui.theme.background
import com.example.terveyshelppi.ui.theme.main1
import com.example.terveyshelppi.ui.theme.main2
import java.util.*

@Composable
fun InfoLanding(navController: NavController, model: ResultViewModel) {
    val tag = "terveyshelppi"
    val mContext = LocalContext.current

    var name by remember { mutableStateOf("") }
    var weight by remember { mutableStateOf("") }
    var height by remember { mutableStateOf("") }
    var targetSteps by remember { mutableStateOf("") }
    var cal by remember { mutableStateOf("") }
    var hours by remember { mutableStateOf("") }

    // steps counter sensor data
    val totalStep by model.stepValue.observeAsState("")

    Box(
        modifier = Modifier
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color.Black,
                        background
                    )
                )
            )
            .fillMaxSize()
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            //basic info
            TextModifiedWithPadding(id = R.string.ready)
            TextField(
                value = name,
                onValueChange = { name = it },
                label = { Text(stringResource(id = R.string.name)) },
                modifier = Modifier
                    .padding(top = 20.dp, start = 30.dp, end = 30.dp)
                    .fillMaxWidth()
                    .border(
                        BorderStroke(
                            2.dp, brush = Brush.verticalGradient(
                                colors = listOf(
                                    main1, main2
                                )
                            )
                        ), shape = RoundedCornerShape(10)
                    )
                    .background(Color.White, shape = RoundedCornerShape(10))
            )
            TextField(
                value = weight,
                onValueChange = { weight = it },
                label = { Text(stringResource(id = R.string.weight)) },
                modifier = Modifier
                    .padding(top = 20.dp, start = 30.dp, end = 30.dp)
                    .fillMaxWidth()
                    .border(
                        BorderStroke(
                            2.dp, brush = Brush.verticalGradient(
                                colors = listOf(
                                    main1, main2
                                )
                            )
                        ), shape = RoundedCornerShape(10)
                    )
                    .background(Color.White, shape = RoundedCornerShape(10))
            )
            TextField(
                value = height,
                onValueChange = { height = it },
                label = { Text(stringResource(id = R.string.height)) },
                modifier = Modifier
                    .padding(top = 20.dp, start = 30.dp, end = 30.dp)
                    .fillMaxWidth()
                    .border(
                        BorderStroke(
                            2.dp, brush = Brush.verticalGradient(
                                colors = listOf(
                                    main1, main2
                                )
                            )
                        ), shape = RoundedCornerShape(10)
                    )
                    .background(Color.White, shape = RoundedCornerShape(10))
            )

            //set up goals
            TextModifiedWithPadding(id = R.string.goal)
            TextField(
                value = targetSteps,
                onValueChange = { targetSteps = it },
                label = { Text(stringResource(id = R.string.step)) },
                modifier = Modifier
                    .padding(top = 15.dp, start = 30.dp, end = 30.dp)
                    .fillMaxWidth()
                    .border(
                        BorderStroke(
                            2.dp, brush = Brush.verticalGradient(
                                colors = listOf(
                                    main1, main2
                                )
                            )
                        ), shape = RoundedCornerShape(10)
                    )
                    .background(Color.White, shape = RoundedCornerShape(10))
            )
            TextModifiedStringWithPadding(string = "recommended 6000 steps/day")
            TextField(
                value = cal,
                onValueChange = { cal = it },
                label = { Text(stringResource(id = R.string.cal)) },
                modifier = Modifier
                    .padding(top = 15.dp, start = 30.dp, end = 30.dp)
                    .fillMaxWidth()
                    .border(
                        BorderStroke(
                            2.dp, brush = Brush.verticalGradient(
                                colors = listOf(
                                    main1, main2
                                )
                            )
                        ), shape = RoundedCornerShape(10)
                    )
                    .background(Color.White, shape = RoundedCornerShape(10))
            )
            TextModifiedStringWithPadding(string = "recommended 1000 Cals/day")
            TextField(
                value = hours,
                onValueChange = { hours = it },
                label = { Text(stringResource(id = R.string.hour)) },
                modifier = Modifier
                    .padding(top = 15.dp, start = 30.dp, end = 30.dp)
                    .fillMaxWidth()
                    .border(
                        BorderStroke(
                            2.dp, brush = Brush.verticalGradient(
                                colors = listOf(
                                    main1, main2
                                )
                            )
                        ), shape = RoundedCornerShape(10)
                    )
                    .background(Color.White, shape = RoundedCornerShape(10))
            )
            TextModifiedStringWithPadding(string = "recommended at least 30 mins/day")
            // checking data input and navigate to next screen
            Image(
                painterResource(id = R.drawable.next),
                "",
                modifier = Modifier
                    .padding(top = 50.dp, end = 30.dp)
                    .size(50.dp)
                    .clickable(onClick = {
                        if (name == "") {
                            Toast
                                .makeText(mContext, "Please enter your name!", Toast.LENGTH_SHORT)
                                .show()
                        } else if (weight == "" || (weight.toIntOrNull() == null)) {
                            Toast
                                .makeText(
                                    mContext,
                                    "Please enter your weight in number!",
                                    Toast.LENGTH_SHORT
                                )
                                .show()
                        } else if (height == "" || (height.toIntOrNull() == null)) {
                            Toast
                                .makeText(
                                    mContext,
                                    "Please enter your height in number!",
                                    Toast.LENGTH_SHORT
                                )
                                .show()
                        } else {
                            val day = Calendar
                                .getInstance()
                                .get(Calendar.DAY_OF_YEAR)
                            val user = UserData(
                                name,
                                weight.toInt(),
                                height.toInt(),
                                // set goals to default value if no input
                                if (targetSteps == "") 6000 else targetSteps.toInt(),
                                if (cal == "") 1000 else cal.toInt(),
                                if (hours == "") 60 else hours.toInt(),
                                0,
                                0,
                                0,
                                if (totalStep == "") 0.0 else totalStep.toDouble(),
                                0,
                                "",
                                if (totalStep == "") 0.0 else totalStep.toDouble(),
                                day
                            )
                            Log.d(tag, "InfoLanding: user info $user")
                            model.insertUser(user)
                            navController.navigate("main") {
                                popUpTo("landingPage") {
                                    inclusive = true
                                }
                            }

                        }
                    })
                    .align(Alignment.End)
            )
        }
    }
}
