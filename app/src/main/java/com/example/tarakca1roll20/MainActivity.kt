package com.example.tarakca1roll20

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tarakca1roll20.ui.theme.TarakCA1Roll20Theme

data class WeatherInfo(
    val day: String,
    val temperature: String,
    val condition: String,
    val humidity: String,
    val windSpeed: String
)

data class HourlyForecast(
    val time: String,
    val temp: String
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TarakCA1Roll20Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    WeatherScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun WeatherScreen(modifier: Modifier = Modifier) {
    val hourlyForecasts = listOf(
        HourlyForecast("Now", "25°"),
        HourlyForecast("1 PM", "26°"),
        HourlyForecast("2 PM", "27°"),
        HourlyForecast("3 PM", "28°"),
        HourlyForecast("4 PM", "27°"),
        HourlyForecast("5 PM", "26°"),
        HourlyForecast("6 PM", "24°"),
        HourlyForecast("7 PM", "22°")
    )

    val weeklyWeather = listOf(
        WeatherInfo("Monday", "25°C", "Sunny", "40%", "12 km/h"),
        WeatherInfo("Tuesday", "22°C", "Cloudy", "65%", "15 km/h"),
        WeatherInfo("Wednesday", "20°C", "Rainy", "85%", "20 km/h"),
        WeatherInfo("Thursday", "24°C", "Partly Cloudy", "50%", "10 km/h"),
        WeatherInfo("Friday", "26°C", "Sunny", "35%", "8 km/h"),
        WeatherInfo("Saturday", "28°C", "Hot", "30%", "5 km/h"),
        WeatherInfo("Sunday", "23°C", "Windy", "55%", "30 km/h"),
        WeatherInfo("Next Monday", "21°C", "Showers", "75%", "18 km/h"),
        WeatherInfo("Next Tuesday", "24°C", "Clear", "45%", "12 km/h")
    )

    Column(modifier = modifier.fillMaxSize()) {
        Text(
            text = "Weather Forecast",
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(16.dp)
        )

        Text(
            text = "Hourly Forecast",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
        )

        LazyRow(
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(hourlyForecasts) { hourly ->
                HourlyItem(hourly)
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "7-Day Forecast",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
        )

        LazyColumn(
            modifier = Modifier.weight(1f),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(weeklyWeather) { weather ->
                WeatherRow(weather)
            }
        }
    }
}

@Composable
fun HourlyItem(hourly: HourlyForecast) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.secondaryContainer
        ),
        modifier = Modifier.width(70.dp)
    ) {
        Column(
            modifier = Modifier.padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = hourly.time, style = MaterialTheme.typography.bodySmall)
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = hourly.temp,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun WeatherRow(weather: WeatherInfo) {
    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = weather.day,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = weather.condition,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
                Text(
                    text = weather.temperature,
                    style = MaterialTheme.typography.headlineSmall,
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Bold
                )
            }
            
            HorizontalDivider(
                modifier = Modifier.padding(vertical = 8.dp),
                thickness = 0.5.dp,
                color = MaterialTheme.colorScheme.outlineVariant
            )
            
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                WeatherDetail(label = "Humidity", value = weather.humidity)
                WeatherDetail(label = "Wind", value = weather.windSpeed)
            }
        }
    }
}

@Composable
fun WeatherDetail(label: String, value: String) {
    Column {
        Text(
            text = label,
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.secondary
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodySmall,
            fontWeight = FontWeight.Medium
        )
    }
}

@Preview(showBackground = true)
@Composable
fun WeatherPreview() {
    TarakCA1Roll20Theme {
        WeatherScreen()
    }
}
