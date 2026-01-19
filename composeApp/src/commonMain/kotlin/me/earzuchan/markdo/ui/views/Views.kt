package me.earzuchan.markdo.ui.views

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.extensions.compose.stack.Children
import lib.fetchmoodle.CourseModule
import me.earzuchan.markdo.duties.AllCoursesDuty
import me.earzuchan.markdo.duties.CourseDetailDuty
import me.earzuchan.markdo.duties.CourseDuty
import me.earzuchan.markdo.duties.GradesDuty
import me.earzuchan.markdo.duties.UserDuty
import me.earzuchan.markdo.resources.Res
import me.earzuchan.markdo.resources.ic_arrow_back_24px
import me.earzuchan.markdo.resources.ic_extension_24px
import me.earzuchan.markdo.resources.ic_forum_24px
import me.earzuchan.markdo.utils.ResUtils.vector

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GradesPage(duty: GradesDuty) = Scaffold(topBar = {
    TopAppBar({ Text("成绩") })
}) { padding ->
    val state by duty.state.collectAsState()

    Box(Modifier.fillMaxSize().padding(padding).consumeWindowInsets(WindowInsets.navigationBars.only(WindowInsetsSides.Top))) {
        when (val s = state) {
            is GradesDuty.UIState.Loading -> CircularProgressIndicator(Modifier.align(Alignment.Center))

            is GradesDuty.UIState.Error -> Column(Modifier.align(Alignment.Center), horizontalAlignment = Alignment.CenterHorizontally) {
                Text(s.msg, color = MaterialTheme.colorScheme.error)
                Button({ duty.loadGrades() }) { Text("重试") }
            }

            is GradesDuty.UIState.Success -> LazyColumn(Modifier.fillMaxSize()) {
                items(s.data) {
                    ListItem({ Text(it.name) }, trailingContent = { Text(it.grade, style = MaterialTheme.typography.labelLarge, color = MaterialTheme.colorScheme.primary) })
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CoursePage(duty: CourseDuty) = Children(duty.navStack, Modifier.fillMaxSize()) { created ->
    when (val ins = created.instance) {
        is AllCoursesDuty -> AllCoursesPage(ins)

        is CourseDetailDuty -> CourseDetailPage(ins)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AllCoursesPage(duty: AllCoursesDuty) = Scaffold(topBar = {
    TopAppBar({ Text("课程") })
}) { padding ->
    val state by duty.state.collectAsState()

    Box(Modifier.fillMaxSize().padding(padding).consumeWindowInsets(WindowInsets.navigationBars.only(WindowInsetsSides.Top))) {
        when (val s = state) {
            is AllCoursesDuty.UIState.Loading -> CircularProgressIndicator(Modifier.align(Alignment.Center))

            is AllCoursesDuty.UIState.Error -> Column(Modifier.align(Alignment.Center), horizontalAlignment = Alignment.CenterHorizontally) {
                Text(s.msg, color = MaterialTheme.colorScheme.error)
                Button({ duty.loadCourses() }) { Text("重试") }
            }

            is AllCoursesDuty.UIState.Success -> LazyColumn(Modifier.fillMaxSize()) {
                items(s.data) {
                    ListItem(
                        { Text(it.name) }, Modifier.clickable { duty.onClickCourse(it.id) },
                        trailingContent = { Text(it.category, style = MaterialTheme.typography.labelLarge, color = MaterialTheme.colorScheme.onSurfaceVariant) }
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserPage(duty: UserDuty) = Box(Modifier.fillMaxSize()) {
    Text("你好，${duty.userName}", Modifier.align(Alignment.Center))

    Button({ duty.logout() }, Modifier.align(Alignment.BottomCenter)) { Text("退出登录") }
}