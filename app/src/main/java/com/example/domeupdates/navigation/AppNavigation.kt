//package com.example.domeupdates.navigation
//
//import androidx.compose.runtime.Composable
//import androidx.lifecycle.viewmodel.compose.viewModel
//import androidx.navigation.NavType
//import androidx.navigation.compose.*
//import com.example.domeupdates.ui.screens.AddThreadScreen
//import com.example.domeupdates.ui.screens.ThreadDetailScreen
//import com.example.domeupdates.ui.screens.ThreadListScreen
//import com.example.domeupdates.viewmodel.ThreadViewModel
//
//sealed class Screen(val route: String) {
//    object List     : Screen("thread_list")
//    object Add      : Screen("add_thread")
//    object Detail   : Screen("thread_detail/{threadId}") {
//        fun createRoute(id: Long) = "thread_detail/$id"
//    }
//}
//
//@Composable
//fun AppNavHost() {
//    val navController = rememberNavController()
//    val vm: ThreadViewModel = viewModel()
//
//    NavHost(navController, startDestination = Screen.List.route) {
//
//        // 1. Thread list
//        composable(Screen.List.route) {
//            ThreadListScreen(
//                viewModel = vm,
//                onAdd        = { navController.navigate(Screen.Add.route) },
//                onItemClick = { threadId ->
//                    navController.navigate(Screen.Detail.createRoute(threadId))
//                }
//            )
//        }
//
//        // 2. Add thread
//        composable(Screen.Add.route) {
//            AddThreadScreen(
//                viewModel = vm,
//                onBack    = { navController.popBackStack() }
//            )
//        }
//
//        // 3. Thread detail (with viewCount increment)
//        composable(
//            route = Screen.Detail.route,
//            arguments = listOf(
//                navArgument("threadId") { type = NavType.LongType }
//            )
//        ) { backStack ->
//            val threadId = backStack.arguments!!.getLong("threadId")
//            ThreadDetailScreen(
//                threadId = threadId,
//                viewModel = vm,
//                onBack   = { navController.popBackStack() }
//            )
//        }
//    }
//}
