package com.example.terveyshelppi.components

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
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
import androidx.core.content.ContextCompat.startActivity
import androidx.navigation.NavController
import com.example.terveyshelppi.R
import com.example.terveyshelppi.libraryComponent.TextModifiedWithPaddingStart
import com.example.terveyshelppi.libraryComponent.TextModifiedWithString
import com.example.terveyshelppi.service.ResultViewModel
import com.example.terveyshelppi.service.StopWatch
import com.example.terveyshelppi.service.map.getAddress
import com.example.terveyshelppi.service.map.ShowPointsInMap
import com.example.terveyshelppi.service.roomDB.ExerciseData
import com.example.terveyshelppi.ui.theme.*
import org.osmdroid.util.GeoPoint
import java.util.*
import kotlin.math.round

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Exercise(navController: NavController, model: ResultViewModel) {
    val tag = "terveyshelppi"
    val context = LocalContext.current

    val stopWatch = remember { StopWatch() }

    // values from Android Map
    val distance: Double by model.distanceRecording.observeAsState(0.0)
    val speed: Double by model.speed.observeAsState(0.0)
    val firstAltitude: Double by model.firstAltitude.observeAsState(0.0)
    val secondAltitude: Double by model.secondAltitude.observeAsState(0.0)
    val long by model.long.observeAsState()
    val lat by model.lat.observeAsState()

    val heartRate: Int by model.mBPM.observeAsState(0)
    val listBPM by model.listBPM.observeAsState(mutableListOf(0))

    var showButton by remember { mutableStateOf(1) }

    var height by remember { mutableStateOf(0) }
    var weight by remember { mutableStateOf(0) }
    var calories by remember { mutableStateOf(0) }
    var elevation by remember { mutableStateOf(0.0) }
    var timeStart by remember { mutableStateOf("") }

    // get user data from Room
    val userData by model.getInfo().observeAsState()
    if (userData != null) {
        height = userData!!.height
        weight = userData!!.weight
    }

    // calculator elevation and calories
    elevation = round(secondAltitude - firstAltitude)
    calories = (distance / 0.75 * (0.57 * weight * 2.2) / (160934.4 / (height * 0.415))).toInt()

    Box(
        modifier = Modifier
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color.Black,
                        Color(0xFF321A54)
                    )
                )
            )
            .fillMaxSize()
    ) {
        TopAppBar(
            title = {
                Text(
                    stringResource(id = R.string.exercise),
                    color = Color.White,
                    fontFamily = regular
                )
            }, backgroundColor = Color.Black,
            navigationIcon = if (navController.previousBackStackEntry != null) {
                {
                    IconButton(onClick = {
                        navController.navigateUp()
                    }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.White
                        )
                    }
                }
            } else {
                null
            },
            actions = {
                Image(
                    painterResource(id = R.drawable.music),
                    "",
                    modifier = Modifier
                        .padding(end = 20.dp)
                        .size(20.dp)
                        .clickable {
                            // launch default music app
                            val intent = Intent("android.intent.action.MUSIC_PLAYER")
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                            startActivity(context, intent, Bundle())
                        }
                )
                Image(
                    painterResource(id = R.drawable.map),
                    "",
                    modifier = Modifier
                        .padding(end = 20.dp)
                        .size(20.dp)
                        .clickable {
                            // open google map application
                            val uri: String = java.lang.String.format(
                                Locale.ENGLISH,
                                "geo:%f,%f",
                                lat,
                                long
                            )
                            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(uri))
                            context.startActivity(intent)
                        }
                )
            }
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 40.dp, bottom = 20.dp, start = 20.dp, end = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            // show map in the top with current location using OSM map
            if (lat != 0.0 && long != 0.0)
                lat?.let { long?.let { it1 -> GeoPoint(it, it1) } }?.let {
                    long?.let { it1 -> getAddress(context = context, lat!!, it1) }?.let { it2 ->
                        ShowPointsInMap(
                            geoPoint = it,
                            address = it2
                        )
                    }
                }
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp),
                backgroundColor = card,
                elevation = 4.dp
            ) {
                // data for grid view
                val textArray = listOf(
                    Triple("Distance", round(distance), "m"),
                    Triple("Duration", stopWatch.formattedTime, ""),
                    Triple("Speed", round(speed), "km/h"),
                    Triple("Elevation", elevation, "m"),
                    Triple("Calories", calories, "Cal"),
                    Triple("Heart rate", heartRate, "BPM"),
                )
                Log.d(tag, "Exercise: speed is $speed and ${speed * 3.6}")

                LazyVerticalGrid(
                    cells = GridCells.Fixed(2)
                ) {
                    items(textArray) {
                        Card(
                            modifier = Modifier.padding(all = 5.dp),
                            backgroundColor = button
                        ) {
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Text(
                                    text = it.first,
                                    color = Color.White,
                                    fontSize = 16.sp,
                                    fontFamily = semibold,
                                    modifier = Modifier.padding(top = 15.dp)
                                )
                                Row(
                                    modifier = Modifier.padding(top = 10.dp, bottom = 15.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    TextModifiedWithString(string = it.second.toString(), size = 18)
                                    TextModifiedWithPaddingStart(string = it.third,
                                        size = 14,
                                        font = light)
                                }
                            }
                        }
                    }
                }
            }

            // check state to change buttons
            when (showButton) {
                1 -> {
                    // start button
                    Button(
                        onClick = {
                            stopWatch.start()
                            timeStart = Calendar.getInstance().time.toString()
                            model.recording.postValue(true)
                            showButton = 2
                        },
                        modifier = Modifier.padding(top = 10.dp),
                        colors = ButtonDefaults.buttonColors(backgroundColor = button2),
                    ) {
                        TextModifiedWithString(string = stringResource(id = R.string.start),
                            size = 18)
                    }
                }
                // pause button
                2 -> {
                    Button(
                        onClick = {
                            stopWatch.pause()
                            model.recording.postValue(false)
                            showButton = 3
                        },
                        modifier = Modifier.padding(top = 10.dp),
                        colors = ButtonDefaults.buttonColors(backgroundColor = button2),
                    ) {
                        TextModifiedWithString(string = stringResource(id = R.string.pause),
                            size = 18)
                    }
                }
                else -> {
                    // resume and finish buttons
                    Row(
                        modifier = Modifier.padding(top = 10.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Button(
                            onClick = {
                                stopWatch.start()
                                model.recording.postValue(true)
                                showButton = 2
                            },
                            colors = ButtonDefaults.buttonColors(backgroundColor = button2),
                        ) {
                            TextModifiedWithString(string = stringResource(id = R.string.resume),
                                size = 18)
                        }
                        Button(
                            onClick = {
                                val averageSpeed = distance / stopWatch.getTime()
                                val averageHeartRate = listBPM.filter { it != 0 }.average()

                                // insert exercise data to Room
                                val exerciseData = ExerciseData(
                                    0,
                                    timeStart,
                                    distance.toInt(),
                                    stopWatch.formattedTime,
                                    stopWatch.getTime(),
                                    if (!averageSpeed.isNaN()) averageSpeed else 0.0,
                                    calories,
                                    elevation.toInt(),
                                    if (!averageHeartRate.isNaN()) averageHeartRate else 0.0
                                )
                                model.insertExercise(exerciseData)

                                //reset data
                                model.recording.postValue(false)
                                model.locationState.postValue(true)
                                model.distanceRecording.postValue(0.0)
                                model.speed.postValue(0.0)
                                model.firstAltitude.postValue(0.0)
                                model.secondAltitude.postValue(0.0)

                                navController.navigate("exercise_result")
                            },
                            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF7ED67D)),
                            modifier = Modifier.padding(start = 20.dp),
                        ) {
                            TextModifiedWithString(string = stringResource(id = R.string.finish),
                                size = 18)
                        }
                    }
                }
            }
        }
    }
}

