package com.example.mini.view.common

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.currentComposer
import androidx.compose.runtime.currentCompositionLocalContext
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mini.data.HospitalMenu
import com.example.mini.navigation.NavRoute
import com.example.mini.navigation.RouteAction
import com.example.mini.view.HospitalBarIcon
import com.guru.fontawesomecomposelib.FaIcons
import com.skydoves.landscapist.glide.GlideImage

/**
 * 하단 자막 영역
 * @param tts
 */
@Composable
fun BottomTTSCaption(tts: String) {
    val scrollState = rememberScrollState()
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .padding(horizontal = 20.dp)
            .background(Color(0x27353838))
    ) {
        Text(
            text = tts,
            style = TextStyle(
                textAlign = TextAlign.Center,
                fontSize = 17.sp
            ),
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(scrollState)
        )
    }
}

/**
 * 직사각형 버튼 (Column (이미지, text))
 * @param menu
 * @param routeAction
 */
@Composable
fun ImgMenuBtn(menu: HospitalMenu, routeAction: RouteAction) {
    Card(
        modifier = Modifier
            .size(width = 150.dp, height = 200.dp)
            .padding(horizontal = 20.dp)
            .clickable {
                routeAction.navTo(menu.route)
            },
    ) {
        BoxWithConstraints(
            modifier = Modifier
                .background(Color.DarkGray)
                .padding(10.dp)//,
                .size(110.dp, 150.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Box(
                    modifier = Modifier
                        .background(Color.LightGray)
                        .fillMaxWidth()
                        .height(100.dp)
                ) {
//                    GlideImage(imageModel = )
                }
                Spacer(modifier = Modifier.size(10.dp))
                Text(
                    text = menu.menu,
                    style = TextStyle(
                        textAlign = TextAlign.Center,
                        fontSize = 10.sp
                    )
                )
            }
        }
    }
}

/**
 * Scaffold 내부에서 사용 가능한 TopAppBar
 * TODO : title - 날짜 - 시간 - 배터리 표시
 * @param routeAction
 * @param canNavigate
 */
@SuppressLint("InvalidColorHexValue") // 투명 색 - 임시
@Composable
fun HospitalTopBar(routeAction: RouteAction, canNavigate: Boolean = true) {
    TopAppBar(
        title = {
//                Text(text = "Logo")
        },
        backgroundColor = Color(0xff00000000),
        elevation = 0.dp,
        contentColor = Color.DarkGray,
        navigationIcon = {
            HospitalBarIcon(
                Modifier
                    .clip(CircleShape)
                    .background(Color.Yellow)
                    .clickable {
                        if (canNavigate)
                            routeAction.goMain()
                    },
                FaIcons.Home,
            )
        },
        actions = {
            HospitalBarIcon(
                Modifier
                    .clip(CircleShape)
                    .background(Color(0xFFC5E7E7))
                    .clickable {
                        routeAction.goBack()
                    }, FaIcons.ArrowLeft
            )
        }
    )
}

/**
 * TODO 분기 /
 *
 * @param
 */
@Composable
fun GlideImgView(
    routeAction: RouteAction,
    nextRoute: NavRoute,
    imgModel: String
) {
    GlideImage(
        imageModel = imgModel,
        modifier = Modifier
            .padding(start = 8.dp, end = 8.dp, top = 8.dp)
            .height(250.dp)
            .width(250.dp)
            .clip(CircleShape)
            .border(
                shape = CircleShape,
                border = BorderStroke(
                    width = 3.dp,
                    color = Color.LightGray
                )
            )
            .clickable {
                routeAction.navTo(nextRoute)
            },
    )
}


// tk 테스트 용
@Composable
fun BasicView(bodyView: Unit, routeAction: RouteAction) {
    Scaffold(
        topBar = { HospitalTopBar(routeAction) },
        bottomBar = {
            BottomTTSCaption("test")
        },
//        content = {
////            HomeContent(routeAction = routeAction)
//            bodyView
//        })
    ) {
        bodyView
    }
}