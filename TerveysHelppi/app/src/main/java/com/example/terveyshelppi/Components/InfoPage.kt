package com.example.terveyshelppi.Components

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
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
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.terveyshelppi.R
import com.example.terveyshelppi.Service.RoomDB.UserData
import com.example.terveyshelppi.Service.ResultViewModel
import com.example.terveyshelppi.ui.theme.*
import java.util.*

@Composable
fun InfoLanding(navController: NavController, model: ResultViewModel) {
    val TAG = "terveyshelppi"
    val mContext = LocalContext.current

    var name by remember { mutableStateOf("") }
    var weight by remember { mutableStateOf("") }
    var height by remember { mutableStateOf("") }
    var targetSteps by remember { mutableStateOf("") }
    var cal by remember { mutableStateOf("") }
    var hours by remember { mutableStateOf("") }
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
            Text(
                stringResource(id = R.string.ready),
                color = Color.White,
                fontSize = 18.sp,
                fontFamily = semibold,
                modifier = Modifier.padding(top = 30.dp, start = 30.dp)
            )
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
            Text(
                stringResource(id = R.string.goal),
                color = Color.White,
                fontSize = 18.sp,
                fontFamily = semibold,
                modifier = Modifier.padding(top = 30.dp, start = 30.dp)
            )

            TextField(
                value = targetSteps.toString(),
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
            Text(
                "recommended 6,000 steps/day",
                color = smallText,
                fontSize = 14.sp,
                fontFamily = regular,
                modifier = Modifier.padding(top = 5.dp, start = 30.dp)
            )
            TextField(
                value = cal.toString(),
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
            Text(
                "recommended 1000 Cals/day",
                color = smallText,
                fontSize = 14.sp,
                fontFamily = regular,
                modifier = Modifier.padding(top = 5.dp, start = 30.dp)
            )
            TextField(
                value = hours.toString(),
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
            Text(
                "recommended at least 30 mins/day",
                color = smallText,
                fontSize = 14.sp,
                fontFamily = regular,
                modifier = Modifier.padding(top = 5.dp, start = 30.dp)
            )
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
                            val day = Calendar.getInstance().get(Calendar.DAY_OF_YEAR)
                            val user = UserData(
                                name,
                                weight.toInt(),
                                height.toInt(),
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
                            Log.d(TAG, "InfoLanding: user info $user")
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
