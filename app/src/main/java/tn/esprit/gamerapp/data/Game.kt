package tn.esprit.gamerapp.data

import androidx.compose.ui.graphics.painter.Painter
import tn.esprit.gamerapp.enums.Platform

data class Game(val title: String,val platform: Platform,val price: Double,val image: Painter)
